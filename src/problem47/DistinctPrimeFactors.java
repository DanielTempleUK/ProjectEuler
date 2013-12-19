package problem47;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import utilities.PrimalityChecker;

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

	private static final List<Long> primes = getPrimesUnder(1000000L);

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
		final Set<Long> primeFactors = new HashSet<Long>();

		double d = input;
		long i = 0;

		while( d > 1) {
			final Long prime = primes.get((int)i);
			if( (  ( (d/prime) % 1 ) == 0  ) ) {
				primeFactors.add(i);
				d = d / prime;
				i = 0;
				continue;
			}
			i++;
		}

		return primeFactors;
	}

	private static List<Long> getPrimesUnder(final long limit) {
		final List<Long> primes = new ArrayList<Long>();
		for (long i = 0; i <= limit; i++) {
			if( PrimalityChecker.isPrime(i) ) {
				primes.add(i);
			}
		}
		return primes;
	}
}