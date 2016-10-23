package mk;

import static java.util.Arrays.copyOf;

/**
 * Data description of a target state.  
 */
public final class Goal {

	private static final Goal[] EMPTY_SEQ = new Goal[0];

	public enum Type {
		MAKE, 
		UNMAKE, 
		SEQUENCE
	}
	
	public enum Location {
		ASIDE,
		MIRRORED, 
		FLATTENED 
	}

	public static Goal make(Module source, Location target) {
		return new Goal(Type.MAKE, Folder.SELF, source, target, Filetype.$, FileSelector.NONE, EMPTY_SEQ);
	}
	
	public static Goal unmake(FileSelector deleted, Folder dest) {
		return new Goal(Type.UNMAKE, dest, Module.EMPTY, Location.ASIDE, Filetype.$, deleted, EMPTY_SEQ);
	}
	
	// common
	public final Type type;
	public final Folder dest;

	// creation only
	public final Module source;
	public final Location target;
	public final Filetype output;
	
	// deletion only
	public final FileSelector deleted;
	
	// sequence only
	private final Goal[] sequence;

	private Goal(Goal...sequence) {
		this(Type.SEQUENCE, Folder.SELF, Module.EMPTY, Location.ASIDE, Filetype.$, FileSelector.NONE, sequence);
	}
	
	private Goal(Type type, Folder dest, Module source, Location target,
			Filetype output, FileSelector deleted, Goal[] sequence) {
		super();
		this.type = type;
		this.dest = dest;
		this.source = source;
		this.target = target;
		this.output = output;
		this.deleted = deleted;
		this.sequence = sequence;
	}

	public Goal as(Filetype output) {
		return new Goal(type, dest, source, target, output, deleted, sequence);
	}
	
	public Goal in(Folder dest) {
		return new Goal(type, dest, source, target, output, deleted, sequence);
	}
	
	public Goal and(Goal then) {
		if (type == Type.SEQUENCE) {
			Goal[] seq = copyOf(sequence, sequence.length+1);
			seq[sequence.length] = then;
			return new Goal(seq);
		}
		return new Goal(this, then);
	}

}
