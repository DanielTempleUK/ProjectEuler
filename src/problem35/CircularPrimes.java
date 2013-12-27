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

		final Set<Integer> potentialCircularPrimes = new HashSet<Integer>();
		for( int i = 101; i < 999999; i++ ) {
			if( !containsAnEvenNumberOrAFive(i) && PrimalityChecker.isPrime(i) ) {
				potentialCircularPrimes.add(i);
			}
		}

		final Set<Integer> circularPrimes = new HashSet<Integer>(potentialCircularPrimes);

		for (final Integer prime : potentialCircularPrimes) {
			final String primeString = ""+prime;
			for( int i = 0; i < primeString.length(); i++ ) {
				final String end = primeString.substring(i);
				final String beginning = primeString.substring(0, i);
				final String cycledPrime = end + beginning;

				if( !PrimalityChecker.isPrime(Long.valueOf(cycledPrime)) ) {
					circularPrimes.remove(prime);
					break;
				}
			}
		}

		return circularPrimes.size() + 13;
	}

	private static boolean containsAnEvenNumberOrAFive(final int i) {
		int number = i;
		while( number > 0 ) {
			final int d = number % 10;
			number /= 10;
			if( d % 2 == 0 || d == 5 ) {
				return true;
			}
		}
		return false;
	}
}