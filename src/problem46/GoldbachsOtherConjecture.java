package problem46;

import java.util.ArrayList;
import java.util.List;

import utilities.PrimalityChecker;

/**
 * Solution to problem 46
 * 
 *
 * It was proposed by Christian Goldbach that every odd composite number
 * can be written as the sum of a prime and twice a square.
 * 
 *     9  = 7  + 2×12
 *     15 = 7  + 2×22
 *     21 = 3  + 2×32
 *     25 = 7  + 2×32
 *     27 = 19 + 2×22
 *     33 = 31 + 2×12
 * 
 * It turns out that the conjecture was false.
 * What is the smallest odd composite that cannot be written as
 * the sum of a prime and twice a square?
 */
public class GoldbachsOtherConjecture {

	public static void main(final String[] args) {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	private static long calculateSolution() {
		final List<Long> primes = getPrimesUnder(1000000L);

		for ( long i = 33; i > 0; i += 2 ) {
			if( primes.contains(Long.valueOf(i)) ) {
				continue;
			}

			long lastPrime = 0L;
			for (final Long prime : primes) {
				if( prime > i ) {
					break;
				}

				final long difference = i - prime;
				if( (difference % 2) == 0 ) {
					final double root = Math.sqrt(difference / 2);
					if( root == (int) root ) {
						lastPrime = prime;
						break;
					}
				}
			}

			if( lastPrime == 0L ) {
				return i;
			}
		}

		return 0L;
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