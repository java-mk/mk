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

	public Module within(Folder folder) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
