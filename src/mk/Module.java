package mk;

import mk.Goal.Location;

/**
 * A section within the sources.
 */
public final class Module extends Named {

	/**
	 * A virtual module with nothing in it by definition.
	 */
	public static final Module EMPTY = new Module(FileSelector.noFile);
	
	public static Module module(FileSelector files) {
		return new Module(files);
	}
	
	public final FileSelector files;

	private Module(FileSelector files) {
		super();
		this.files = files;
	}

	public Goal colocated() {
		return Goal.goal(this, Location.COLOCATED);
	}
	
	public Goal flattened() {
		
		return Goal.goal(this, Location.FLATTENED);
	}
	
	public Goal mirrored() {
		return Goal.goal(this, Location.MIRRORED);
	}
	
	public Module requires(Module... dependencies) {
		
		return this; // FIXME
	}

}
