package mk;

public final class Production {

	public static Production is(Filetype source) {
		return new Production(source, Filetype.VOID, null);
	}
	
	public final Filetype source;
	public final Filetype target;
	public final Process process;

	private Production(Filetype source, Filetype target, Process process) {
		super();
		this.source = source;
		this.target = target;
		this.process = process;
	}

	public Production to(Filetype target) {
		return new Production(source, target, process);
	}
	
	public Production by(Process process) {
		return new Production(source, target, process);
	}
	
}
