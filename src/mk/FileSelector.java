package mk;

import java.util.Arrays;

/**
 * A virtual file-set.
 *  
 * @author jan
 */
public final class FileSelector {

	// can be
	// * all files (of a type)
	// * no file
	// * a file name pattern (incl. path; of a type)
	// * a list patterns
	
	// depth?
	// base folder (all other filders are relative!)
	// file type are always expressed using Filetype - not by using patterns! this is required so that planing can infer what inputs might be generated first.
	
	public static final class FilePattern {
		
		public Folder base;
		public String path;
		public Filetype type;
		public int depth;
		
		public boolean matches(File file) {
			if (!type.matches(file))
				return false;
			
			return true;
		}
	}
	
	public static final FileSelector noFile = new FileSelector(new FilePattern[0]);

	public static FileSelector allOf(Filetype type) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static FileSelector allIn(Folder base) {
		
		return null;
	}
	
	public static FileSelector file(String name, Filetype extension) {
		return null;
	}
	
	public FilePattern[] patterns;
	
	private FileSelector(FilePattern[] patterns) {
		super();
		this.patterns = patterns;
	}
	
	public FileSelector or(FilePattern pattern) {
		FilePattern[] all = Arrays.copyOf(patterns, patterns.length+1);
		all[patterns.length] = pattern;
		return new FileSelector(all);
	}

	public Module in(Folder folder) {
		// TODO Auto-generated method stub
		return null;
	}

	public Module in(Unit unit) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
