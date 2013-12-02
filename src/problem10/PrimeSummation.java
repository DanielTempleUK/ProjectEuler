package problem10;

import java.util.ArrayList;
import java.util.List;

import utilities.PrimalityChecker;

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
			if(PrimalityChecker.isPrime(i)) {
				primes.add(i);
			}
		}

		long total = 0;
		for (final Long prime : primes) {
			total = total + prime;
		}

		System.out.println(total);
	}
}