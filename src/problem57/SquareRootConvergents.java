package problem57;

import java.math.BigDecimal;

/**
 * Solution to Problem 57:
 * 
 * 
 * It is possible to show that the square root of two can be expressed as an infinite continued fraction.
 * 
 *     2^(1/2) = 1 + 1/(2 + 1/(2 + 1/(2 + ... ))) = 1.414213...
 * 
 * By expanding this for the first four iterations, we get:
 * 
 *     1 + 1/2 = 3/2 = 1.5
 *     1 + 1/(2 + 1/2) = 7/5 = 1.4
 *     1 + 1/(2 + 1/(2 + 1/2)) = 17/12 = 1.41666...
 *     1 + 1/(2 + 1/(2 + 1/(2 + 1/2))) = 41/29 = 1.41379...
 * 
 * The next three expansions are 99/70, 239/169, and 577/408, but the eighth expansion, 1393/985,
 * is the first example where the number of digits in the numerator exceeds the number of digits in the denominator.
 * 
 * In the first one-thousand expansions, how many fractions contain a numerator with more digits than denominator?
 */
public class SquareRootConvergents {

	public static void main(final String[] args) {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	/**
	 * To calculate the next denominator, you add the numerator to the denominator.
	 * Then to calculate the next numerator, you add the next denominator to the current denominator.
	 * 
	 *     Den(n+1) = Den(n) + num(n);
	 *     Num(n+1) = Den(n+1) + Den(n);
	 * 
	 * I worked those two things out by looking at the number sequences formed by the numerators, the
	 * denominators and the differences of the two.
	 */
	private static long calculateSolution() {
		long desiredFractions = 0;

		BigDecimal numerator = BigDecimal.valueOf(3);
		BigDecimal denominator = BigDecimal.valueOf(2);

		for( int i = 1; i < 1000; i++ ) {
			if( (""+numerator).length() > (""+denominator).length() ) {
				desiredFractions++;
			}

			final BigDecimal oldDenominator = denominator;
			denominator = denominator.add(numerator);
			numerator = denominator.add(oldDenominator);
		}

		return desiredFractions;
	}
}