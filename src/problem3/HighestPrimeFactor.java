package problem3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import utilities.PrimalityChecker;

/**
 * Solution to problem 3:
 * 
 * The prime factors of 13195 are 5, 7, 13 and 29.
 * What is the largest prime factor of the number 600851475143 ?
 */
public class HighestPrimeFactor {

	private static final double INPUT = 600851475143d;

	private static final List<Long> primeFactors = new ArrayList<Long>();

	public static void main(final String[] args) throws IOException {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	private static long calculateSolution() throws IOException {
		findPrimeFactors(INPUT);

		Collections.sort(primeFactors);
		return primeFactors.get(primeFactors.size()-1);
	}

	private static void findPrimeFactors(final double input) {
		for ( long i = 2; i < Math.sqrt(input); i++ ) {

			final double d = input / i;

			if( ( d % 1 ) == 0 ) {
				if(PrimalityChecker.isPrime(i)) {
					primeFactors.add(i);
				}
			}
		}
	}
}