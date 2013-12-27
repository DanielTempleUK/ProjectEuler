package problem20;

import java.math.BigDecimal;

/**
 * Solution to Problem 20:
 * 
 * n! means n x (n - 1) x ... x 3 x 2 x 1
 * 
 * For example, 10! = 10 x 9 x ... x 3 x 2 x 1 = 3628800,
 * and the sum of the digits in the number 10! is 3 + 6 + 2 + 8 + 8 + 0 + 0 = 27.
 * 
 * Find the sum of the digits in the number 100!
 */
public class FactorialDigitSum {

	public static void main(final String[] args) {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	private static long calculateSolution() {
		long digitsSum = 0L;
		BigDecimal factorial = BigDecimal.ONE;

		for( int i = 2; i < 101; i++ ) {
			factorial = factorial.multiply( BigDecimal.valueOf(i) );
		}

		final char[] digits = factorial.toString().toCharArray();

		for(int i = 0; i < digits.length; i++ ) {
			digitsSum = digitsSum + Long.valueOf("" + digits[i]);
		}

		return digitsSum;
	}
}