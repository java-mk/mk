package mk;

public final class Filetype {

	/**
	 * In case the type is not yet determined, unknown.
	 */
	public static final Filetype $ = filetype("", Origin.EXTERNAL);

	static enum Origin { 
		/**
		 * A source file, like <code>.java</code>
		 */
		SOURCE, 
		/**
		 * A generated file, like <code>.class</code> or a build
		 * </code>.jar</code> file.
		 */
		MAKE,
		/**
		 * A file neither edited as a source not made by a build like external
		 * </code>.jar</code> dependencies.
		 */
		EXTERNAL
	} 
	
	public static Filetype filetype(String extension, Origin origin) {
		return new Filetype(extension, origin);
	}
	
	public final String extension;
	public final Origin origin;
	
	private Filetype(String extension, Origin origin) {
		super();
		this.extension = extension;
		this.origin = origin;
	}
	
}
