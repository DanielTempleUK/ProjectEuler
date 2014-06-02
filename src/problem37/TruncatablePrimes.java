package problem37;

import java.io.IOException;

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

		long total = 0L;
		int count = 0;
		for( long i = 11; i > 10; i++ ) {
			if( i < 100 || !containsAnEvenNumberOrAFive(i) ) {
				if( PrimalityChecker.isPrime(i) && isTruncatablePrime(i) ) {
					total += i;
					count++;;
					if(count == 11) {
						break;
					}
				}
			}
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

	private static boolean containsAnEvenNumberOrAFive(final long i) {
		long number = i;
		while( number > 0 ) {
			final long d = number % 10;
			number /= 10;
			if( d % 2 == 0 || d == 5 ) {
				return true;
			}
		}
		return false;
	}
}