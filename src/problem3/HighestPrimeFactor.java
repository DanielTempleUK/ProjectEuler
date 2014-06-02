package problem3;

import java.io.IOException;

import utilities.FactorUtils;

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
		final String[] primeFactors = FactorUtils.primeFactorise(600851475143L).split("X");
		return Long.valueOf( primeFactors[primeFactors.length-1] );
	}
}