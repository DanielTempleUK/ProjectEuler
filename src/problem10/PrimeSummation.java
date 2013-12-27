package problem10;

import java.util.List;

import utilities.PrimeGenerator;

/**
 * Solution to Problem 10:
 * 
 * The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
 * Find the sum of all the primes below two million.
 */
public class PrimeSummation {

	public static void main(final String[] args) {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	private static long calculateSolution() {
		final List<Long> primes = PrimeGenerator.getPrimesUnder(2000000);

		long total = 0;
		for (final Long prime : primes) {
			total += prime;
		}

		return total;
	}
}