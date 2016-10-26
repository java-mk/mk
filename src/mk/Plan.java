package mk;

public final class Plan {

	public static final class Step {
		
		/**
		 * These may be run in parallel as they are independent from each other.
		 */
		public final Task[] tasks;

		public Step(Task[] tasks) {
			super();
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

}
