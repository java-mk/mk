package mk;

import static mk.Goal.goal;
import static mk.Util.concat;
import mk.Goal.Location;

/**
 * A section within the sources.
 */
public final class Module extends Named {

	/**
	 * A virtual module with nothing in it by definition.
	 */
	public static final Module EMPTY = new Module(FileSelector.noFile, new Module[0]);
	
	static Module module(FileSelector sources) {
		return new Module(sources, new Module[0]);
	}
	
	public final FileSelector sources;
	public final Module[] dependencies;

	private Module(FileSelector sources, Module[] dependencies) {
		super();
		this.sources = sources;
		this.dependencies = dependencies;
	}

	public Goal colocated() {
		return goal(this, Location.COLOCATED);
	}
	
	public Goal flattened() {
		
		return goal(this, Location.FLATTENED);
	}
	
	public Goal mirrored() {
		return goal(this, Location.MIRRORED);
	}
	
	public Module requires(Module... dependencies) {
		return new Module(sources, concat(this.dependencies, dependencies));
	}

}
