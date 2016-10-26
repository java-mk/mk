package mk;

public abstract class Named {

	private String name;
	
	public String name() {
		return name;
	}
	
	void name(String name) {
		this.name = name;
	}
}
