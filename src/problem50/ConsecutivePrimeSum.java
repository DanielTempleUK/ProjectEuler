package problem50;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import utilities.PrimalityChecker;

/**
 * Solution to problem 50:
 * 
 * 
 * The prime 41, can be written as the sum of six consecutive primes:
 *     41 = 2 + 3 + 5 + 7 + 11 + 13
 * This is the longest sum of consecutive primes that adds to a prime below one-hundred.
 * 
 * The longest sum of consecutive primes below one-thousand that adds to a prime, contains 21 terms, and is equal to 953.
 * Which prime, below one-million, can be written as the sum of the most consecutive primes?
 * 
 * NOTE: This could do with some optimisation. It takes around 40 seconds to run.
 */
public class ConsecutivePrimeSum {

	public static void main(final String[] args) throws IOException {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	private static long calculateSolution() {
		final List<Long> primes = getPrimes();
		System.out.println(primes.size());

		long longestSequence = 0L;
		long primeWithSequence = 0L;

		for (int i = 0; i < primes.size(); i++) {
			long total = 0L;
			long sequence = 0L;
			for(int j = 0; j <= i; j++) {
				total = total + primes.get(j);
				sequence++;

				if(total > 1000000){
					break;
				}

				if( PrimalityChecker.isPrime(total) && sequence > longestSequence) {
					longestSequence = sequence;
					primeWithSequence = total;
				}
			}

			total = 0L;
			sequence = 0L;
			for(int j = i; j < primes.size(); j++) {
				total = total + primes.get(j);
				sequence++;

				if(total > 1000000){
					break;
				}

				if( PrimalityChecker.isPrime(total) && sequence > longestSequence) {
					longestSequence = sequence;
					primeWithSequence = total;
				}
			}
		}

		System.out.println("Sequence is: " + longestSequence);
		return primeWithSequence;
	}

	private static List<Long> getPrimes() {
		final List<Long> primes = new ArrayList<Long>();
		for (long i = 1; i <= 1000000; i++) {
			if( PrimalityChecker.isPrime(i) ) {
				primes.add(i);
			}
		}
		return primes;
	}
}