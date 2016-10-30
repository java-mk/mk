package mk;

import static java.lang.System.arraycopy;
import static java.util.Arrays.copyOf;

public final class Util {

	private Util() {
		throw new UnsupportedOperationException("util class");
	}
	
	public static <T> T[] append(T[] arr, T e) {
		T[] res = copyOf(arr, arr.length+1);
		res[arr.length] = e;
		return res;
	}
	
	public static <T> T[] concat(T[] arr1, T[] arr2) {
		T[] res = copyOf(arr1, arr1.length+arr2.length);
		arraycopy(arr2, 0, res, arr1.length, arr2.length);
		return res;
	}
}
