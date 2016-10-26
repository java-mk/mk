package mk;

/**
 * A file relative to project home. 
 */
public final class File {

	public final String name;

	public File(String name) {
		super();
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
