package problem70;

import java.io.IOException;
import java.util.Set;

import utilities.FactorUtils;
import utilities.Permutations;

/**
 * Solution to problem 70:
 * 
 * 
 * Euler's Totient function, phi(n), is used to determine the number of
 * positive numbers less than or equal to n which are relatively prime to n.
 * For example, as 1, 2, 4, 5, 7, and 8, are all less than nine and relatively prime to nine, phi(9)=6.
 * 
 * The number 1 is considered to be relatively prime to every positive number, so phi(1)=1.
 * 
 * Interestingly, phi(87109)=79180, and it can be seen that 87109 is a permutation of 79180.
 * 
 * Find the value of n, 1 < n < 10000000, for which phi(n) is a permutation of n and the ratio n/phi(n) produces a minimum.
 */
public class TotientPermutation {

	public static void main(final String[] args) throws IOException {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	/**
	 * Super dooper brute force.
	 * Takes ~20 seconds though.
	 */
	private static long calculateSolution() {
		long currentSolution = 0;
		double currentRatio = 10000000;

		/* We want to minimise the number over the totient, so we want the bggest totient possible.
		 * Therefore we consider odd numbers only. Odd numbers have more chance of having more co primes
		 * as more of the even numbers are included due to the lack of 2 as a common factor.
		 */
		for( long i = 9999999; i > 0; i=i-2 ) {

			final Set<Long> primeFactorise = FactorUtils.getUniqePrimeFactorsOf(i);
			double totient = i;

			for (final Long factor : primeFactorise) {
				final double factorMinus1 = factor-1;
				totient *= factorMinus1;
				totient /= factor;
			}

			final double ratio = i/totient;
			if( ratio < currentRatio ) {
				//Perform the permutation check after the ratio check as it is a longer check to perform
				if( Permutations.permutationCheck(i, (long)totient) ) {
					//					System.out.println("i = " + i);
					//					System.out.println("totient = " + totient);
					//					System.out.println("ratio = " + ratio);
					currentSolution = i;
					currentRatio = ratio;
				}
			}
		}

		return currentSolution;
	}
}