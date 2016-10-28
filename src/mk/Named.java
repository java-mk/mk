package mk;

public abstract class Named {

	private String name;
	
	public Named() {
		this.name="<unnamed>";
	}
	
	public String name() {
		return name;
	}
	
	void name(String name) {
		this.name = name;
	}
}
