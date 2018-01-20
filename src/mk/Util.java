package mk;

import static java.lang.System.arraycopy;
import static java.util.Arrays.copyOf;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Predicate;

import mk.FileSelector.FilePattern;

/**
 * All the cross-functional static helper methods go here. 
 */
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

	public static <T> T[] filter(T[] arr, Predicate<T> func) {
		if (arr.length == 0)
			return arr;
		@SuppressWarnings("unchecked")
		T[] res = (T[]) Array.newInstance(arr.getClass().getComponentType(), arr.length);
		int i = 0;
		for (T e : arr) {
			if (func.test(e)) {
				res[i++] = e;
			}
		}
		return copyOf(res, i);
	}
	
	public static <T> T firstOrThrow(T[] arr, Predicate<T> func, RuntimeException e) {
		for (T el : arr) {
			if (func.test(el))
				return el;
		}
		throw e;
	}
	
	public static <A,B> B[] map(A[] arr, Function<A, B> f) {
		if (arr.length == 0)
			return null;
		B b0 = f.apply(arr[0]);
		@SuppressWarnings("unchecked")
		B[] res = (B[]) Array.newInstance(b0.getClass(), arr.length);
		res[0] = b0;
		for (int i = 1; i < arr.length; i++) {
			res[i] = f.apply(arr[i]);
		}
		return res;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T[] empty(T[] arr) {
		return (T[]) Array.newInstance(arr.getClass().getComponentType(), arr.length);
	}

	@SuppressWarnings("unchecked")
	public static <T extends Eq<T>> T[] distinct(T[] arr) {
		return Arrays.stream(arr).distinct().toArray((int len) -> { return (T[]) Array.newInstance(arr.getClass().getComponentType(), len); });
	}
}
