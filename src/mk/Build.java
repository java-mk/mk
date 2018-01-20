package mk;

import static java.lang.reflect.Modifier.isStatic;
import static java.util.Arrays.asList;
import static mk.Util.filter;
import static mk.Util.firstOrThrow;
import static mk.Util.map;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import mk.BuildFailure.NoSuchGoalException;
import mk.BuildFailure.UnknownProduction;
import mk.Plan.Step;

/**
 * A {@link Build} describes the facts of a build that can be used to yield a
 * {@link Plan} that can be executed.
 */
public final class Build {

	public final Goal[] goals;
	public final Module[] modules;
	public final Production[] productions;
	public final Component[] components;

	public Build(Goal[] goals, Module[] modules, Production[] productions, Component[] components) {
		super();
		this.goals = goals;
		this.modules = modules;
		this.productions = productions;
		this.components = components;
	}
	
	public Goal goalFor(String name) throws NoSuchGoalException {
		return firstOrThrow(goals, (Goal goal) -> { 
			return goal.name().equals(name); }, 
			new NoSuchGoalException(name));
	}
	
	public Goal[] goalsFor(Module source) {
		return filter(goals,(Goal g) -> { return g.source == source; });
	}
	
	public Goal goalFor(Module source, Filetype output) {
		return firstOrThrow(goals, (Goal g) -> { 
			return g.source == source && g.output.equals(output); }, 
			new NoSuchGoalException(source, output));
	}
	
	public Component[] componentsFor(Production process) {
		return filter(components, (Component c) -> { return c.process == process; });
	}
	
	public Production production(Filetype from, Filetype to) throws UnknownProduction {
		return firstOrThrow(productions, (Production p) -> 
			{ return p.source.equals(from) && p.target.equals(to); }, 
			new UnknownProduction(from, to));
	}
	
	// even already planed goals must become be added as dependencies to those that are dependent on them

	public Plan plan(String...goals) {
		return plan(map(goals, (String name) -> { return goalFor(name); }));
	}
	
	public Plan plan(Goal...goals) {
		LinkedHashMap<String, FileSelector> moduleDependencies = new LinkedHashMap<>();
		for (Goal goal : goals) {
			moduleDependenciesFor(goal, moduleDependencies);
		}
		for (Entry<String, FileSelector> e : moduleDependencies.entrySet()) { // order is now the order needed to run the goals
			
		}
		return null;
	}
	
	private FileSelector moduleDependenciesFor(Goal goal, LinkedHashMap<String, FileSelector> knownModuleDependencies) {
		FileSelector dependencies = knownModuleDependencies.get(goal.name);
		if (dependencies == null) {
			
			knownModuleDependencies.put(goal.name, dependencies);
		}
		return dependencies;
	}
	
	static Build from(Class<?> build) throws Exception {
		final List<Goal> goals = new ArrayList<>();
		final List<Module> modules = new ArrayList<>();
		final List<Component> components = new ArrayList<>();
		final List<Production> productions = new ArrayList<>();
		for (Field field : build.getDeclaredFields()) {
			if (isStatic(field.getModifiers())) {
				final Class<?> type = field.getType();
				final Object value = field.get(null);
				if (value instanceof Named) {
					((Named) value).name(field.getName().replace('_', '-'));
				}
				if (type == Goal.class) {
					goals.add((Goal) value);
				} else if (type == Module.class) {
					modules.add((Module) value);
				} else if (type == Production.class) {
					productions.add((Production) value);
				} else if (type == Component.class) {
					components.add((Component) value);
				}
			}
		}
		return new Build(
				goals.toArray(new Goal[0]), 
				modules.toArray(new Module[0]), 
				productions.toArray(new Production[0]), 
				components.toArray(new Component[0]));
	}
}
