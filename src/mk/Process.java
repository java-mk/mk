package mk;

@FunctionalInterface
public interface Process {

	/**
	 * 
	 * @param task
	 * @return a {@link FileSelector} that matches ALL files created.
	 */
	FileSelector run(Task task) throws BuildFailure;
	
}
