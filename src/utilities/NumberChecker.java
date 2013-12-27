package utilities;

public class NumberChecker {

	public static boolean isTriangleNumber(final long x) {
		final double n = ( Math.sqrt( (8 * x) + 1 ) - 1) / 2;
		return n % 1 == 0;
	}

	public static boolean isPentagonal(final long x) {
		final double n = ((Math.sqrt((24 * x) + 1)) + 1) / 6;
		return n % 1 == 0;
	}

	public static boolean isHexagonal(final long x) {
		final double n = ((Math.sqrt((8 * x) + 1)) + 1) / 4;
		return n % 1 == 0;
	}
}