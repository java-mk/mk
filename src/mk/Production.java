package mk;

public final class Production {

	public static Production makes(Filetype target) {
		return new Production(Filetype.$, target, null);
	}
	
	public final Filetype source;
	public final Filetype target;
	public final Generator process;

	private Production(Filetype source, Filetype target, Generator process) {
		super();
		this.source = source;
		this.target = target;
		this.process = process;
	}

	public Production from(Filetype source) {
		return new Production(source, target, process);
	}
	
	public Production using(Generator process) {
		return new Production(source, target, process);
	}
	
}
