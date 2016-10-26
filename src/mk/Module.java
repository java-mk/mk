package mk;

import mk.Goal.Location;

/**
 * A section within the sources.
 *  
 * @author jan
 */
public final class Module {

	/**
	 * A virtual module with nothing in it by definition.
	 */
	public static final Module EMPTY = new Module();
	
	public enum Type {
		/**
		 * A "top level" module has a root folder and might contain submodules. 
		 */
		SOURCE_FOLDER, 
		
		/**
		 * A submodule is used to describe structures within the source that can be found in multiple top level modules.
		 * For example the submodules within main or core reoccur in test.
		 */
		UNIT
	}
	
	private Folder base;
	private Type type;

	
	public Goal colocated() {
		return Goal.is(this, Location.COLOCATED);
	}
	
	public Goal flattened() {
		
		return Goal.is(this, Location.FLATTENED);
	}
	
	public Goal mirrored() {
		return Goal.is(this, Location.MIRRORED);
	}
	
	public Module uses(Module... dependencies) {
		
		return this; // FIXME
	}
	
	public Module uses(FileSelector dependencies) {
		
		return this; // FIXME
	}
}
