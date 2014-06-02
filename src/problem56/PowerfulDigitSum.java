package problem56;

import java.math.BigDecimal;

/**
 * Solution to Problem 56:
 * 
 * 
 * A googol (10100) is a massive number: one followed by one-hundred zeros; 100^100 is almost unimaginably large:
 * one followed by two-hundred zeros. Despite their size, the sum of the digits in each number is only 1.
 * 
 * Considering natural numbers of the form, ab, where a, b < 100, what is the maximum digital sum?
 */
public class PowerfulDigitSum {

	public static void main(final String[] args) {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	private static long calculateSolution() {
		long highestTotal = 0L;

		for( int a = 1; a < 100; a++ ) {
			for( int b = 1; b < 100; b++ ) {

				final BigDecimal power = BigDecimal.valueOf(a).pow(b);
				final String[] split = power.toString().split("");

				long total = 0L;
				for (final String string : split) {
					if( string.length() > 0 ) {
						total += Long.valueOf(string);
					}
				}

				if( total> highestTotal ) {
					highestTotal = total;
				}
			}
		}

		return highestTotal;
	}
}