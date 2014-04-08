package problem73;

import utilities.FactorUtils;

/**
 * Solution to Problem 73:
 * 
 * 
 * Consider the fraction, n/d, where n and d are positive integers.
 * If n<d and HCF(n,d)=1, it is called a reduced proper fraction.
 * If we list the set of reduced proper fractions for d <= 8 in ascending order of size, we get:
 * 
 * 		1/8, 1/7, 1/6, 1/5, 1/4, 2/7, 1/3, 3/8, 2/5, 3/7, 1/2, 4/7, 3/5, 5/8, 2/3, 5/7, 3/4, 4/5, 5/6, 6/7, 7/8
 * 
 * It can be seen that there are 3 fractions between 1/3 and 1/2.
 * How many fractions lie between 1/3 and 1/2 in the sorted set of reduced proper fractions for d <= 12,000?
 */
public class CountingFractionsInARange {

	public static void main(final String[] args) {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	/**
	 * Classic Brute Force.
	 * 
	 * Basically, we take a denominator, consider a numerator, n, in the range: (d/3)+1 <= n < (d/2)+1
	 * And then check that n and d are relatively prime (HCF == 1)
	 * 
	 * Takes a while though ~13 seconds
	 */
	private static long calculateSolution() {
		long count = 0L;

		//Start at 5, since 5 is the first denominator to have a fraction between 1/3 and 1/2
		for (int i = 5; i < 12001; i++) {
			final int numLower = (i / 3) + 1;
			final int numHigher = (i / 2) + 1;

			for (int j = numLower; j < numHigher; j++) {
				if( FactorUtils.getHighestCommonFactor(i, j) == 1 ) {
					count++;
				}
			}
		}

		return count;
	}
}