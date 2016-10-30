package mk;

import static java.lang.reflect.Modifier.isStatic;
import static java.util.Arrays.asList;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mk.BuildFailure.NoSuchGoalException;
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
	
	public Goal goal(String name) throws NoSuchGoalException {
		for (Goal goal : goals)
			if (goal.name().equals(name))
				return goal;
		throw new NoSuchGoalException(name);
	}

	public Plan plan(String...goals) {
		Set<String> planedGoals = new HashSet<>();
		List<Step> steps = new ArrayList<>();
		for (String goal : goals) {
			if (!planedGoals.contains(goal)) {
				Step[] goalSteps = plan(goal(goal));
				steps.addAll(asList(goalSteps));
				for (Step s : goalSteps) {
					planedGoals.add(s.goal.name);
				}
			}
		}
		return new Plan(steps.toArray(new Step[0]));
	}
	
	public Plan.Step[] plan(Goal goal) {
		return plan(goal.source, new HashSet<>());
	}
	
	public Plan.Step[] plan(Module module, Set<String> planedModules) {
		List<Step> steps = new ArrayList<>();
		for (Module dependency : module.dependencies) {
			steps.addAll(asList(plan(dependency, planedModules)));
		}
		if (!planedModules.contains(module.name())) {
			planedModules.add(module.name());
			//TODO actually plan the module
		}
		return steps.toArray(new Step[0]);
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
