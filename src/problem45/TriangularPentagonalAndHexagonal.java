package problem45;

import utilities.NumberChecker;

/**
 *	Solution to problem 45:
 * 
 * 
 * Triangle, pentagonal, and hexagonal numbers are generated by the following formulae:
 *   Triangle	 	Tn=n(n+1)/2	 	1, 3, 6,  10, 15, ...
 *   Pentagonal	 	Pn=n(3n-1)/2	1, 5, 12, 22, 35, ...
 *   Hexagonal	 	Hn=n(2n-1)	 	1, 6, 15, 28, 45, ...
 * It can be verified that T285 = P165 = H143 = 40755.
 * Find the next triangle number that is also pentagonal and hexagonal.
 */
public class TriangularPentagonalAndHexagonal {

	public static void main(final String[] args) {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	private static long calculateSolution() {
		long triangularNumber = 40755;
		for ( long i = 286; i > 0; i++ ) {
			triangularNumber += i;
			if( NumberChecker.isPentagonal(triangularNumber) && NumberChecker.isHexagonal(triangularNumber) ) {
				return triangularNumber;
			}
		}

		return 0L;
	}
}