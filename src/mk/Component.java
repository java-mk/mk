package mk;

/**
 * A conceptual/functional unit within a structure source.  
 */
public final class Component extends Named {

	public static final Component VOID = new Component("");

	public final String location;
	public final int level;
	
	public static Component at(String location) {
		return at(location);
	}

	private Component(String location) {
		this.location = location;
		this.level = location.split("\\.").length;
	}
	
	public Component includes(Component dependency) {
		// TODO Auto-generated method stub
		return null;
	}

	public Component includes(FileSelector dependency) {
		// TODO Auto-generated method stub
		return null;
	}

	// use this as a more abstract concept of a package
	// because a unit does not refer to one exact package but everything with it including subpackages. 
	// also package is very java specific.
	
}
