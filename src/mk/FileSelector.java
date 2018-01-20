package mk;

import static mk.Module.module;
import static mk.Util.append;
import static mk.Util.concat;
import static mk.Util.distinct;

import java.util.Arrays;
import java.util.Objects;

/**
 * A virtual file-set.
 */
public final class FileSelector implements Eq<FileSelector> {
	
	private static final int UNLIMITED_DEPTH = -1;

	public static final class FilePattern implements Eq<FilePattern> {
		
		public Folder base;
		public Path path;
		public Filetype type;
		public int depth;
		
		FilePattern(Folder base, Path path, Filetype type, int depth) {
			super();
			this.base = base;
			this.path = path;
			this.type = type;
			this.depth = depth;
		}

		public boolean matches(File file) {
			if (!type.matches(file) || !base.contains(file.parent))
				return false;
			Path path = file.pathFrom(base);
			return this.path.matches(path) && (depth < 0 || path.pattern.split("/").length <= depth);
		}
		
		public FilePattern withBase(Folder folder) {
			return new FilePattern(folder, path, type, depth);
		}

		@Override
		public boolean equalTo(FilePattern other) {
			return base.equalTo(other.base) && type.equalTo(other.type) 
					 && depth == other.depth && path.equalTo(path);
		}
		
		@Override
		public boolean equals(Object obj) {
			return obj instanceof FilePattern && equalTo((FilePattern) obj);
		}
		
		@Override
		public int hashCode() {
			return Objects.hash(base, path, type, depth);
		}
	}
	
	public static final FileSelector noFile = new FileSelector(new FilePattern[0]);

	public static FileSelector allOf(Filetype type) {
		return new FileSelector(new FilePattern(Folder.PROJECT_ROOT, Path.ANY, type, UNLIMITED_DEPTH));
	}
	
	public static FileSelector file(String name, Filetype extension) {
		return new FileSelector(new FilePattern(Folder.PROJECT_ROOT, new Path(name), extension, 0));
	}
	
	public FilePattern[] patterns;
	
	private FileSelector(FilePattern... patterns) {
		super();
		this.patterns = patterns;
	}
	
	public Module in(Folder folder) {
		return module(withBase(folder));
	}
	
	public FileSelector or(FileSelector other) {
		return new FileSelector(distinct(concat(patterns, other.patterns)));
	}
	
	public FileSelector or(FilePattern pattern) {
		return new FileSelector(distinct(append(patterns, pattern)));
	}

	public FileSelector withBase(Folder folder) {
		FilePattern[] patterns = new FilePattern[this.patterns.length];
		for (int i = 0; i < patterns.length; i++) {
			patterns[i] = this.patterns[i].withBase(folder);
		}
		return new FileSelector(distinct(patterns));
	}
	
	@Override
	public boolean equalTo(FileSelector other) {
		return Arrays.equals(patterns, other.patterns);
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof FileSelector && equalTo((FileSelector) obj);
	}
	
	@Override
	public int hashCode() {
		return Arrays.hashCode(patterns);
	}
	
}
