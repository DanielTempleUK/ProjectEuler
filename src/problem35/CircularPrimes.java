package problem35;

import java.util.HashSet;
import java.util.Set;

import utilities.PrimalityChecker;

/**
 * Solution to Problem 35
 * 
 * 
 * The number, 197, is called a circular prime because all rotations of the digits: 197, 971, and 719, are themselves prime.
 * 
 * There are thirteen such primes below 100: 2, 3, 5, 7, 11, 13, 17, 31, 37, 71, 73, 79, and 97.
 * 
 * How many circular primes are there below one million?
 */
public class CircularPrimes {

	public static void main(final String[] args) {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	private static long calculateSolution() {

		final Set<Long> potentialCircularPrimes = new HashSet<Long>();
		for( long i = 101; i < 999999; i++ ) {
			if( PrimalityChecker.isPrime(i) ) {
				potentialCircularPrimes.add(i);
			}
		}

		final Set<Long> circularPrimes = new HashSet<Long>();

		for (final Long prime : potentialCircularPrimes) {
			boolean isCircularPrime = true;
			final String primeString = ""+prime;
			for( int i = 0; i < primeString.length(); i++ ) {
				final String end = primeString.substring(i);
				final String beginning = primeString.substring(0, i);
				final String cycledPrime = end + beginning;

				if( !PrimalityChecker.isPrime(Long.valueOf(cycledPrime)) ) {
					isCircularPrime = false;
					break;
				}
			}

			if( isCircularPrime ) {
				circularPrimes.add(prime);
			}
		}

		return circularPrimes.size() + 13;
	}
}