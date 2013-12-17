package utilities;

import java.util.Arrays;

public class PandigitalChecker {

	/**
	 * Stole this pandigital checker from the interwebs.
	 * I really like the idea of sorting the characters and comparing the arrays.
	 */
	private static final char[][] orderedPandigitals = new char[][]{
		"1".toCharArray(),
		"12".toCharArray(),
		"123".toCharArray(),
		"1234".toCharArray(),
		"12345".toCharArray(),
		"123456".toCharArray(),
		"1234567".toCharArray(),
		"12345678".toCharArray(),
		"123456789".toCharArray(),
	};

	public static boolean isPandigital(final long i) {
		final char[] c = String.valueOf(i).toCharArray();
		Arrays.sort(c);
		return Arrays.equals(c, orderedPandigitals[c.length-1]);
	}
}