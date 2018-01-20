package mk;

import java.util.Objects;

/**
 * A file relative to project home. 
 */
public final class File implements Eq<File> {

	public final Folder parent;
	public final String name;

	public File(Folder parent, String name) {
		super();
		this.parent = parent;
		this.name = name;
	}

	@Override
	public String toString() {
		return parent.toString() + name;
	}
	
	@Override
	public boolean equalTo(File other) {
		return parent.equalTo(other.parent) && name.equals(other.name);
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof File && equalTo((File) obj);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(parent, name);
	}

	public Path pathFrom(Folder base) {
		return new Path(parent.toString().substring(base.toString().length())+"/"+name);
	}
}
