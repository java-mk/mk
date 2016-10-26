package mk;

import static java.util.Arrays.copyOf;

/**
 * An "area" of source code. 
 */
public final class Unit {

	private final String[] segs;

	public static Unit unit(String...segs) {
		return new Unit(segs);
	}

	public Unit(String[] segs) {
		this.segs = segs;
	}
	
	public int level() {
		return segs.length;
	}

	public Unit part(String seg) {
		String[] part = copyOf(segs, segs.length+1);
		part[segs.length] = seg;
		return new Unit(part);
	}

	// use this as a more abstract concept of a package
	// because a unit does not refer to one exact package but everything with it including subpackages. 
	// also package is very java specific.
	
}
