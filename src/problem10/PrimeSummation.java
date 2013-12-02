package problem10;

import java.util.ArrayList;
import java.util.List;

/**
 * Solution to Problem 10:
 * 
 * The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
 * Find the sum of all the primes below two million.
 */
public class PrimeSummation {

	public static void main(final String[] args) {

		final List<Long> primes = new ArrayList<Long>();

		for(long i = 1; i<2000000; i++ ) {
			if(isPrime(i)) {
				primes.add(i);
			}
		}

		long total = 0;
		for (final Long prime : primes) {
			total = total + prime;
		}

		System.out.println(total);
	}

	private static boolean isPrime(final long input) {
		if( input == 2L || input == 3L || input == 5L || input == 7L ) {
			return true;
		}
		if( input < 11) {
			return false;
		}

		for ( int i = 2; i <= Math.sqrt(input); i++ ) {
			if( (input % i) == 0 ) {
				return false;
			}
		}

		return true;
	}
}