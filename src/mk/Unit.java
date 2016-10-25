package mk;

/**
 * An "area" of source code. 
 */
public final class Unit {

	public final String canonicalName;

	public static Unit unit(String canonicalName) {
		return new Unit(canonicalName);
	}

	public Unit(String canonicalName) {
		this.canonicalName = canonicalName;
	}

	// use this as a more abstract concept of a package
	// because a unit does not refer to one exact package but everything with it including subpackages. 
	// also package is very java specific.
	
}
