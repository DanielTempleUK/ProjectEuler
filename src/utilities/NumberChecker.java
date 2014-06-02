package utilities;

public class NumberChecker {

	public static boolean isTriangleNumber(final long x) {
		return isNumberSGonal(3, x);
	}

	public static boolean isSquareNumber(final long x) {
		return isNumberSGonal(4, x);
	}

	public static boolean isPentagonalNumber(final long x) {
		return isNumberSGonal(5, x);
	}

	public static boolean isHexagonalNumber(final long x) {
		return isNumberSGonal(6, x);
	}

	public static boolean isHeptagonalNumber(final long x) {
		return isNumberSGonal(7, x);
	}

	public static boolean isOctagonalNumber(final long x) {
		return isNumberSGonal(8, x);
	}

	private static boolean isNumberSGonal(final int s, final long x) {
		final int a = (8*s)-16;
		final int b = s-4;
		final int c = (2*s)-4;
		final int bSquared = b*b;
		final long aX = a * x;

		final double n = ( Math.sqrt(aX + bSquared) + b ) / c;
		return n % 1 == 0;
	}
}