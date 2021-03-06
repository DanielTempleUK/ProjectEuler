package problem6;

import java.io.IOException;

/**
 * Solution to Problem 6:
 * 
 * 
 * The sum of the squares of the first ten natural numbers is,
 * 		1^2 + 2^2 + ... + 10^2 = 385
 * 
 * The square of the sum of the first ten natural numbers is,
 * 		(1 + 2 + ... + 10)^2 = 55^2 = 3025
 * 
 * Hence the difference between the sum of the squares of the first ten natural numbers and the square of the sum is 3025 - 385 = 2640.
 * Find the difference between the sum of the squares of the first one hundred natural numbers and the square of the sum.
 */
public class SumSquareDifferences {

	public static void main(final String[] args) throws IOException {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	private static long calculateSolution() {

		long sum = 0;
		long squareSum = 0;

		for( int i = 1; i < 101; i++ ) {
			sum = sum + i;
			squareSum = squareSum + i * i;
		}

		final long difference = sum * sum - squareSum;

		return difference;
	}
}