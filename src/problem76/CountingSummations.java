package problem76;

import java.io.IOException;

/**
 * Solution to problem 76:
 * 
 * 
 * It is possible to write five as a sum in exactly six different ways:
 * 
 * 4 + 1
 * 3 + 2
 * 3 + 1 + 1
 * 2 + 2 + 1
 * 2 + 1 + 1 + 1
 * 1 + 1 + 1 + 1 + 1
 * 
 * How many different ways can one hundred be written as a sum of at least two positive integers?
 */
public class CountingSummations {

	public static void main(final String[] args) throws IOException {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	/**
	 * Used the same theory behind quick solutions for problem 31.
	 * Adding up the number of ways to make the possible parts below the current number gives
	 * the number of ways to make the current number.
	 * 
	 * The -1 at the end removes the fact that this algorithm calculates the number of ways to make the
	 * number, including the number itself. Our restriction is to remove the number the itself.
	 */
	public static long calculateSolution() {
		final int limit = 100;

		final int[] ways = new int[limit + 1];
		ways[0] = 1;

		for(int i = 1; i <= limit; i++) {
			for(int j = i; j <= limit; j++) {
				ways[j] += ways[j - i];
			}
		}

		return ways[limit] - 1;
	}
}
