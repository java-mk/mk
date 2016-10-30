package mk;

import static mk.Util.append;

/**
 * A conceptual/functional unit within a structure source.  
 */
public final class Component extends Named {

	public final Production process;
	public final String location;
	public final int level;
	public final Component[] sourceDependencies;
	public final FileSelector[] targetDependencies;
	
	static Component component(Production process, String location) {
		return new Component(process, location, new Component[0], new FileSelector[0]);
	}

	private Component(Production process, String location, Component[] sourceDependencies, FileSelector[] targetDependencies) {
		this.process = process;
		this.location = location;
		this.level = location.split("\\.").length;
		this.sourceDependencies = sourceDependencies;
		this.targetDependencies = targetDependencies;
	}
	
	public Component requires(Component dependency) {
		return new Component(process, location, append(sourceDependencies, dependency), targetDependencies);
	}

	public Component requires(FileSelector dependency) {
		return new Component(process, location, sourceDependencies, append(targetDependencies, dependency));
	}

}
