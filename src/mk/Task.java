package mk;

public final class Task {

	/**
	 * The ROOT folder of the source to process.
	 */
	public Folder source;
	
	/**
	 * The ROOT folder of the destination for target files.
	 */
	public Folder dest;

	/**
	 * The set of files (within the {@link #source}) that should be processed. 
	 */
	public FileSelector task;
	
	/**
	 * The set of files (or implicitly folders) that are dependencies to the {@link #task}.
	 * For example library files like a jar or the packages within the source.
	 */
	public FileSelector dependencies;
	
}
