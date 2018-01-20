package mk;

public final class Plan {

	public static final class Step {
		
		/**
		 * These may be run in parallel as they are independent from each other.
		 */
		public final Task[] tasks;
		/**
		 * The {@link Goal} the task {@link Step} and {@link Task}s have be created from/for.
		 */
		public final Goal goal;

		public Step(Goal goal, Task[] tasks) {
			super();
			this.goal = goal;
			this.tasks = tasks;
		}
		
	}
	
	/**
	 * These need to run sequential as they depend on each other.
	 */
	public final Step[] steps;

	public Plan(Step[] steps) {
		super();
		this.steps = steps;
	}

	public Plan continuesWith(Plan other) {
		//TODO concat...
		return this;
	}
}
