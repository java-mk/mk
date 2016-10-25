package mk;

public interface Process {

	/**
	 * 
	 * @param task
	 * @return a {@link FileSelector} that matches ALL files created.
	 */
	FileSelector run(Task task);
	
	// How are errors comunicated?
	// How can the process know that a more conceptual selector will not match other files not made by the process?
}
