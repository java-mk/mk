package mk;

import static mk.Util.append;

/**
 * A conceptual/functional unit within a structure source.  
 */
public final class Component extends Named {

	public final Filetype source;
	public final String location;
	public final int level;
	public final Component[] sourceDependencies;
	public final FileSelector[] targetDependencies;
	
	public static Component at(Filetype source, String location) {
		return new Component(source, location, new Component[0], new FileSelector[0]);
	}

	private Component(Filetype source, String location, Component[] sourceDependencies, FileSelector[] targetDependencies) {
		this.source = source;
		this.location = location;
		this.level = location.split("\\.").length;
		this.sourceDependencies = sourceDependencies;
		this.targetDependencies = targetDependencies;
	}
	
	public Component includes(Component dependency) {
		return new Component(source, location, append(sourceDependencies, dependency), targetDependencies);
	}

	public Component includes(FileSelector dependency) {
		return new Component(source, location, sourceDependencies, append(targetDependencies, dependency));
	}

}
