package mk;

public final class Production extends Named {

	static Production procution(Filetype source, Filetype target) {
		return new Production(source, target, null);
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

	public Production by(Process process) {
		return new Production(source, target, process);
	}

	public Component at(String location) {
		return Component.component(this, location);
	}
}
