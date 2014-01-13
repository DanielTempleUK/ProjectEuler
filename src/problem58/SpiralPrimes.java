package problem58;

import utilities.PrimalityChecker;

/**
 * Solution to Problem 58:
 * 
 * 
 * Starting with 1 and spiralling anticlockwise in the following way, a square spiral with side length 7 is formed.
 * 
 *          37 36 35 34 33 32 31
 *          38 17 16 15 14 13 30
 *          39 18  5  4  3 12 29
 *          40 19  6  1  2 11 28
 *          41 20  7  8  9 10 27
 *          42 21 22 23 24 25 26
 *          43 44 45 46 47 48 49
 * 
 * It is interesting to note that the odd squares lie along the bottom right diagonal,
 * but what is more interesting is that 8 out of the 13 numbers lying along both
 * diagonals are prime; that is, a ratio of 8/13 ~ 62%.
 * 
 * If one complete new layer is wrapped around the spiral above, a square spiral with
 * side length 9 will be formed. If this process is continued, what is the side length
 * of the square spiral for which the ratio of primes along both diagonals first falls below 10%?
 */
public class SpiralPrimes {

	public static void main(final String[] args) {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	private static long calculateSolution() {
		double totalNumberOfPrimes = 0;
		double totalNumberOfNumbersOnDiagonals = 1;

		for( int n = 1; n > 0; n++ ) {
			final long squareNumberInBottomRightCorner = ( (2*n) + 1 ) * ( (2*n) + 1 );

			if( PrimalityChecker.isPrime( (squareNumberInBottomRightCorner - (2*n) ) ) ) {
				totalNumberOfPrimes++;
			}

			if( PrimalityChecker.isPrime( (squareNumberInBottomRightCorner - (4*n) ) ) ) {
				totalNumberOfPrimes++;
			}

			if( PrimalityChecker.isPrime( (squareNumberInBottomRightCorner - (6*n) ) ) ) {
				totalNumberOfPrimes++;
			}

			totalNumberOfNumbersOnDiagonals += 4;

			final double ratio = totalNumberOfPrimes / totalNumberOfNumbersOnDiagonals;
			if( ( ratio * 100) < 10 ) {
				return (n*2) + 1;
			}
		}
		return 0L;
	}
}