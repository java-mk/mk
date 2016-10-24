package mk;

/**
 * A virtual file-set.
 *  
 * @author jan
 */
public final class FileSelector {

	/**
	 * E.g. used to delete all files in a folder.
	 */
	public static final FileSelector ALL = new FileSelector();
	
	public static final FileSelector NONE = new FileSelector();

	// can be
	// * all files
	// * no file
	// * a file name pattern (incl. path; think glob)
	// * a list patterns
	
	// depth?
	
	public static FileSelector file(String name) {
		return null;
	}
	
	public Module inSource(Folder folder) {
		// TODO Auto-generated method stub
		return null;
	}

	public Module inPackage(String string) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
