package mk;

import static mk.Util.append;

/**
 * Data description of a target state.  
 */
public final class Goal extends Named {

	private static final Goal[] EMPTY_SEQ = new Goal[0];

	public enum Type {
		SIMPLE, 
		SEQUENCE
	}
	
	public enum Location {
		COLOCATED,
		MIRRORED, 
		FLATTENED 
	}
	
	public enum Cleaning {
		INDIVIDUAL,
		BY_FILETYPE,
		BY_FOLDER
	}

	public static Goal goal(Module source, Location target) {
		return new Goal(Type.SIMPLE, source, Folder.PROJECT_ROOT, target, Filetype.VOID, Cleaning.BY_FILETYPE, EMPTY_SEQ);
	}
	
	// common
	public String name;
	public final Type type;

	// simple only
	public final Module source;
	public final Folder dest;
	public final Location target;
	public final Filetype output;
	public final Cleaning policy;
	
	// sequence only
	private final Goal[] sequence;

	private Goal(Goal...sequence) {
		this(Type.SEQUENCE, Module.EMPTY, Folder.PROJECT_ROOT, Location.COLOCATED, Filetype.VOID, Cleaning.BY_FILETYPE, sequence);
	}
	
	private Goal(Type type, Module source, Folder dest, Location target, Filetype output, Cleaning policy, Goal[] sequence) {
		super();
		this.type = type;
		this.dest = dest;
		this.source = source;
		this.target = target;
		this.output = output;
		this.policy = policy;
		this.sequence = sequence;
	}

	public Goal as(Filetype output) {
		return new Goal(type, source, dest, target, output, policy, sequence);
	}
	
	public Goal in(Folder dest) {
		return new Goal(type, source, dest, target, output, policy, sequence);
	}
	
	public Goal and(Goal then) {
		if (type == Type.SEQUENCE) {
			return new Goal(append(sequence, then));
		}
		return new Goal(this, then);
	}
	
	public Goal clean(Cleaning policy) {
		return new Goal(type, source, dest, target, output, policy, sequence);
	}

}
