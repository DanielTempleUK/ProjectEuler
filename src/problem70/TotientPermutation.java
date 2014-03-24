package problem70;

import java.io.IOException;

/**
 * Solution to problem 70:
 * 
 * 
 * Euler's Totient function, phi(n), is used to determine the number of
 * positive numbers less than or equal to n which are relatively prime to n.
 * For example, as 1, 2, 4, 5, 7, and 8, are all less than nine and relatively prime to nine, phi(9)=6.
 * 
 * The number 1 is considered to be relatively prime to every positive number, so phi(1)=1.
 * 
 * Interestingly, phi(87109)=79180, and it can be seen that 87109 is a permutation of 79180.
 * 
 * Find the value of n, 1 < n < 10000000, for which phi(n) is a permutation of n and the ratio n/phi(n) produces a minimum.
 */
public class TotientPermutation {

	public static void main(final String[] args) throws IOException {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	private static long calculateSolution() {








		return 0L;
	}
}