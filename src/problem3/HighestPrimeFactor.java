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

	public static void main(final String[] args) throws IOException {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	private static long calculateSolution() throws IOException {
		final List<Long> primeFactors = findPrimeFactors(600851475143L);
		Collections.sort(primeFactors);
		return primeFactors.get(primeFactors.size()-1);
	}

	private static List<Long> findPrimeFactors(final long input) {
		final List<Long> primeFactors = new ArrayList<Long>();

		double d = input;
		long i = 1;
		while( d > 1) {
			if( ( (d/i) % 1 ) == 0 ) {
				if(PrimalityChecker.isPrime(i)) {
					primeFactors.add(i);
					d = d / i;
				}
			}
			i++;
		}

		return primeFactors;
	}
}