package mk;

public interface Generator {

	enum ResultCode { ERROR, SUCCESS }
	
	ResultCode generate(Folder source, Folder target, FileSelector dependencies);
}
