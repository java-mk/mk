package mk;

public interface Generator {

	enum ResultCode { ERROR, SUCCESS }
	
	// TODO should also return a file selector that will match all files created?
	ResultCode generate(Task task);
}
