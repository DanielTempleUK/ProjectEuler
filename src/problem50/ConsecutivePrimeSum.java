package problem50;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import utilities.PrimalityChecker;

public class ConsecutivePrimeSum {

	public static void main(final String[] args) throws IOException {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	private static long calculateSolution() {
		final List<Long> primes = getPrimes();

		long longestSequence = 0L;
		long primeWithSequence = 0L;

		for(int i = 167; i<primes.size(); i++) {

			long total = 0L;
			long sequence = 0L;
			for(int j = i; j < primes.size(); j++) {
				total = total + primes.get(j);
				sequence++;

				if(total > 1000000){
					break;
				}

				if( primes.contains(total) && sequence > longestSequence) {
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
