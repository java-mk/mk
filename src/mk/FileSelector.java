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
	public static final FileSelector ANY = new FileSelector();
	
	public static final FileSelector NONE = new FileSelector();
	
	public Goal in(Folder dest) {
		return Goal.unmake(this, dest);
	}
}
