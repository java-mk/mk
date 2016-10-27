package mk;

import static java.lang.reflect.Modifier.isStatic;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public final class Build {

	public final Goal[] goals;
	public final Module[] modules;
	public final Production[] productions;

	public Build(Goal[] goals, Module[] modules, Production[] productions) {
		super();
		this.goals = goals;
		this.modules = modules;
		this.productions = productions;
	}

	public Plan plan(String...goals) {
		// plan 1. goal -> [steps] + {goal names}
		// skip all goals already in {goal names}, simple add further steps
		// create plan with all steps
		return null;
	}

	static Build from(Class<?> build) throws Exception {
		List<Goal> goals = new ArrayList<>();
		List<Module> modules = new ArrayList<>();
		List<Production> productions = new ArrayList<>();
		for (Field field : build.getDeclaredFields()) {
			if (isStatic(field.getModifiers())) {
				Class<?> type = field.getType();
				Object value = field.get(null);
				if (value instanceof Named) {
					((Named) value).name(field.getName().replace('_', '-'));
				}
				if (type == Goal.class) {
					goals.add((Goal) value);
				} else if (type == Module.class) {
					modules.add((Module) value);
				} else if (type == Production.class) {
					productions.add((Production) value);
				}
			}
		}
		return new Build(goals.toArray(new Goal[0]), modules.toArray(new Module[0]), productions.toArray(new Production[0]));
	}
}
