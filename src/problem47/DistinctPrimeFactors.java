package problem47;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import utilities.FactorUtils;

/**
 * Solution to problem 47:
 * 
 * 
 * The first two consecutive numbers to have two distinct prime factors are:
 * 
 *    14 = 2 × 7
 *    15 = 3 × 5
 * 
 * The first three consecutive numbers to have three distinct prime factors are:
 * 
 *    644 = 2² × 7 × 23
 *    645 = 3 × 5 × 43
 *    646 = 2 × 17 × 19.
 * 
 * Find the first four consecutive integers to have four distinct prime factors. What is the first of these numbers?
 */
public class DistinctPrimeFactors {

	public static void main(final String[] args) throws IOException {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	private static long calculateSolution() {
		Set<Long> primeFactors1 = findPrimeFactors(644);
		Set<Long> primeFactors2 = findPrimeFactors(645);
		Set<Long> primeFactors3 = findPrimeFactors(646);
		Set<Long> primeFactors4 = findPrimeFactors(647);

		for ( long i = 648; i > 0; i++ ) {
			primeFactors1 = primeFactors2;
			primeFactors2 = primeFactors3;
			primeFactors3 = primeFactors4;
			primeFactors4 = findPrimeFactors(i);

			if( primeFactors1.size() == primeFactors2.size() &&
					primeFactors2.size() == primeFactors3.size() &&
					primeFactors3.size() == primeFactors4.size() &&
					primeFactors4.size() == 4 ) {
				return (i - 3);
			}
		}

		return 0L;
	}

	private static Set<Long> findPrimeFactors(final long input) {
		final String[] primeFactors = FactorUtils.primeFactorise(input).split("X");
		final Set<Long> primeFactorsSet = new HashSet<Long>();

		for (final String primeFactor : primeFactors) {
			primeFactorsSet.add(Long.valueOf(primeFactor));
		}

		return primeFactorsSet;
	}
}