package mk;

import java.nio.file.Path;

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
		TOP, // ROOT 
		
		/**
		 * A submodule is used to describe structures within the source that can be found in multiple top level modules.
		 * For example the submodules within main or core reoccur in test.
		 */
		SUB // PKG, PACKAGE
	}
	
	private Path root;
	private Type type;

	
	public Goal aside() {
		return Goal.make(this, Location.ASIDE);
	}
	
	public Goal flattened() {
		
		return Goal.make(this, Location.FLATTENED);
	}
	
	public Goal mirrored() {
		return Goal.make(this, Location.MIRRORED);
	}
	
	public Module dependsOn(Module dependency) {
		
		return this; // FIXME
	}
	
	public Module dependsOn(FileSelector dependencies) {
		
		return this; // FIXME
	}
}
