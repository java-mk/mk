package mk;

import java.util.Objects;

public final class Filetype implements Eq<Filetype> {

	/**
	 * In case the type is not yet determined, unknown.
	 */
	public static final Filetype VOID = filetype("").external();

	static enum Origin { 
		/**
		 * A source file, like <code>.java</code>.
		 * Everything that is edited to produce final application code.
		 */
		SOURCE, 
		/**
		 * A file that is present as a result of a build goal of the described build, 
		 * like <code>.class</code> or a </code>.jar</code> file.
		 */
		BUILD,
		/**
		 * A file neither edited as a source not made present by a build goal 
		 * but that is simply expected to exist. 
		 * For example external dependences checked into source control.
		 */
		EXTERNAL
	} 
	
	public static Filetype filetype(String extension) {
		return new Filetype(extension, Origin.SOURCE);
	}
	
	public final String extension;
	public final Origin origin;
	
	private Filetype(String extension, Origin origin) {
		super();
		this.extension = extension;
		this.origin = origin;
	}

	public Filetype external() {
		return withOrigin(Origin.EXTERNAL);
	}
	
	public Filetype source() {
		return withOrigin(Origin.SOURCE);
	}
	
	public Filetype build() {
		return withOrigin(Origin.BUILD);
	}

	private Filetype withOrigin(Origin newOrigin) {
		return origin == newOrigin ? this : new Filetype(extension, newOrigin);
	}
	
	public boolean matches(File file) {
		return file.name.endsWith(extension);
	}

	@Override
	public String toString() {
		return "."+extension+"["+origin.name().charAt(0)+"]";
	}
	
	public Production to(Filetype target) {
		return Production.procution(this, target);
	}
	
	@Override
	public boolean equalTo(Filetype other) {
		return origin == other.origin && extension.equals(other.extension);
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Filetype && equalTo((Filetype) obj);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(origin, extension);
	}
	
}
