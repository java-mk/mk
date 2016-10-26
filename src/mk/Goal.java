package mk;

import static java.util.Arrays.copyOf;

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

	public static Goal is(Module source, Location target) {
		return new Goal(Type.SIMPLE, Folder.PROJECT_ROOT, source, target, Filetype.VOID, EMPTY_SEQ);
	}
	
	// common
	public String name;
	public final Type type;
	public final Folder dest;

	// creation only
	public final Module source;
	public final Location target;
	public final Filetype output;
	
	// sequence only
	private final Goal[] sequence;

	private Goal(Goal...sequence) {
		this(Type.SEQUENCE, Folder.PROJECT_ROOT, Module.EMPTY, Location.COLOCATED, Filetype.VOID, sequence);
	}
	
	private Goal(Type type, Folder dest, Module source, Location target, Filetype output, Goal[] sequence) {
		super();
		this.name = "<unnamed>";
		this.type = type;
		this.dest = dest;
		this.source = source;
		this.target = target;
		this.output = output;
		this.sequence = sequence;
	}

	public Goal as(Filetype output) {
		return new Goal(type, dest, source, target, output, sequence);
	}
	
	public Goal in(Folder dest) {
		return new Goal(type, dest, source, target, output, sequence);
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
