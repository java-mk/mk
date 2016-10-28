package mk;

public abstract class BuildFailure extends RuntimeException {

	public BuildFailure(String message, Throwable cause) {
		super(message, cause);
	}

	public static final class NoSuchGoalException extends BuildFailure {

		public NoSuchGoalException(String name) {
			super("Nos such goal: "+name, null);
		}
		
	}
}
