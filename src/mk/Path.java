package mk;

import static java.util.Objects.requireNonNull;

/**
 * A path is an abstract pattern of folder structures.
 * 
 * Star <code>*</code> is used as a wild-card, 
 * letters and digits are meant literally,
 * slash is just that and
 * all other characters are illegal.
 * 
 *  <pre>
 *  *
 *  * / *
 *  </pre>
 */
public final class Path implements Eq<Path> {

	public static final Path ANY = new Path("*");
	
	public final String pattern;

	public Path(String pattern) {
		super();
		requireNonNull(pattern);
		this.pattern = pattern;
	}
	
	@Override
	public String toString() {
		return pattern;
	}
	
	public boolean matches(Path path) {
		if (ANY.equalTo(path))
			return true;
		String[] sections = pattern.split("/");
		for (String sec : sections) {
			if (!path.pattern.matches("^"+(sec.replace("*", ".*"))))
				return false;
		}
		return true;
	}
	
	@Override
	public boolean equalTo(Path other) {
		return pattern.equals(other.pattern);
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Path && equalTo((Path) obj);
	}
	
	@Override
	public int hashCode() {
		return pattern.hashCode();
	}
}
