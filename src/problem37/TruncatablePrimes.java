package problem37;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import utilities.PrimalityChecker;

/**
 * Solution to Problem 37:
 * 
 * 
 * The number 3797 has an interesting property. Being prime itself, it is possible to continuously remove digits from left to right,
 * and remain prime at each stage: 3797, 797, 97, and 7. Similarly we can work from right to left: 3797, 379, 37, and 3.
 * 
 * Find the sum of the only eleven primes that are both truncatable from left to right and right to left.
 * 
 * NOTE: 2, 3, 5, and 7 are not considered to be truncatable primes.
 */
public class TruncatablePrimes {

	public static void main(final String[] args) throws IOException {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	private static long calculateSolution() throws IOException {

		final Set<Long> truncatablePrimes = new HashSet<Long>();

		for( long i = 11; i > 10; i++ ) {
			if( PrimalityChecker.isPrime(i) && isTruncatablePrime(i) ) {
				truncatablePrimes.add(i);
			}
			if(truncatablePrimes.size() == 11) {
				break;
			}
		}

		long total = 0L;
		for (final Long prime : truncatablePrimes) {
			total += prime;
		}

		return total;
	}

	private static boolean isTruncatablePrime(final long i) {
		final String primeString = ""+i;

		for( int j = 0; j < primeString.length(); j++ ) {
			final String truncateFromLeft = primeString.substring(j);
			final String truncateFromRight = primeString.substring(0, j+1);

			if( !PrimalityChecker.isPrime(Long.valueOf(truncateFromLeft))
					|| !PrimalityChecker.isPrime(Long.valueOf(truncateFromRight)) ) {
				return false;
			}
		}
		return true;
	}
}