package mk;

public abstract class BuildFailure extends RuntimeException {

	public BuildFailure(String message, Throwable cause) {
		super(message, cause);
	}

	public static final class NoSuchGoalException extends BuildFailure {

		public NoSuchGoalException(String name) {
			super("No such goal: "+name, null);
		}

		public NoSuchGoalException(Module source, Filetype output) {
			super("", null);
		}
		
	}
	
	public static final class UnknownProduction extends BuildFailure {

		public UnknownProduction(Filetype source, Filetype target) {
			super("Unknown production from "+source+" to "+target, null);
		}
		
	}
}
