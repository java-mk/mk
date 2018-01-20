package mk;

/**
 * 
 * All {@link Named} classes are {@link #equals(Object)} by their {@link #name()} alone.
 */
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
	
	@Override
	public String toString() {
		return name;
	}
	
	@Override
	public final boolean equals(Object obj) {
		return obj != null && obj.getClass() == getClass() && name.equals(((Named)obj).name);
	}
	
	@Override
	public final int hashCode() {
		return name.hashCode();
	}
}
