package mk;

import static mk.Module.module;
import static mk.Util.append;
import static mk.Util.concat;

/**
 * A virtual file-set.
 *  
 * @author jan
 */
public final class FileSelector {
	
	public static final class FilePattern {
		
		public Folder base;
		public String path;
		public Filetype type;
		public int depth;
		
		FilePattern(Folder base, String path, Filetype type, int depth) {
			super();
			this.base = base;
			this.path = path;
			this.type = type;
			this.depth = depth;
		}

		public boolean matches(File file) {
			if (!type.matches(file))
				return false;
			//TODO
			return true;
		}

		public FilePattern basedIn(Folder folder) {
			return new FilePattern(folder, path, type, depth);
		}
	}
	
	public static final FileSelector noFile = new FileSelector(new FilePattern[0]);

	public static FileSelector allOf(Filetype type) {
		return new FileSelector(new FilePattern(Folder.PROJECT_ROOT, "*", type, -1));
	}
	
	public static FileSelector file(String name, Filetype extension) {
		return new FileSelector(new FilePattern(Folder.PROJECT_ROOT, name, extension, 0));
	}
	
	public FilePattern[] patterns;
	
	private FileSelector(FilePattern... patterns) {
		super();
		this.patterns = patterns;
	}
	
	public FileSelector or(FileSelector other) {
		return new FileSelector(concat(patterns, other.patterns));
	}
	
	public FileSelector or(FilePattern pattern) {
		return new FileSelector(append(patterns, pattern));
	}

	public Module in(Folder folder) {
		return module(basedIn(folder));
	}

	private FileSelector basedIn(Folder folder) {
		FilePattern[] patterns = new FilePattern[this.patterns.length];
		for (int i = 0; i < patterns.length; i++) {
			patterns[i] = this.patterns[i].basedIn(folder);
		}
		return new FileSelector(patterns);
	}
	
}
