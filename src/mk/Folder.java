package mk;

/**
 * All folders are relative to the build root directory. This is the directory
 * where the build file <code>mk.java</code> is located and that should be the
 * root directory of the project.
 * 
 * @see File
 */
public final class Folder implements Eq<Folder> {

	/**
	 * relative root of a build
	 */
	public static final Folder PROJECT_ROOT = new Folder("");
	
	public static Folder folder(String relativePath) {
		relativePath = relativePath.replace('\\', '/');
		return new Folder(relativePath.endsWith("/") ? relativePath : relativePath+"/");
	}
	
	private final String relativePath;
	
	private Folder(String relativePath) {
		super();
		this.relativePath = relativePath;
	}
	
	public boolean isRoot() {
		return relativePath.isEmpty();
	}
	
	public Folder parent() {
		return isRoot() ? this : new Folder(relativePath.substring(0, relativePath.lastIndexOf('/')));
	}
	
	@Override
	public String toString() {
		return relativePath;
	}

	public Folder X(String subfolder) {
		return new Folder(relativePath+"/"+subfolder);
	}
	
	@Override
	public boolean equalTo(Folder other) {
		return relativePath.equals(other.relativePath);
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Folder && equalTo((Folder) obj);
	}
	
	@Override
	public int hashCode() {
		return relativePath.hashCode();
	}

	public boolean contains(Folder f) {
		return f.relativePath.startsWith(relativePath);
	}
	
}
