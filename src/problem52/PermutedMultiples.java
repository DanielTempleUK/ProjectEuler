package problem52;

import java.io.IOException;
import java.util.Arrays;

/**
 * Solution to problem 52:
 * 
 * 
 * It can be seen that the number, 125874, and its double, 251748, contain exactly the same digits, but in a different order.
 * Find the smallest positive integer, x, such that 2x, 3x, 4x, 5x, and 6x, contain the same digits.
 */
public class PermutedMultiples {

	public static void main(final String[] args) throws IOException {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	private static long calculateSolution() {
		for (long i = 1; i > 0; i++) {
			final char[] goalArray = (""+i).toCharArray();
			Arrays.sort(goalArray);

			boolean all6ArePermutationsOfI = true;

			for (int j = 6; j > 1; j--) {

				//Sort the multiplied number and compare it to the goal sorting. Only passing if all 6 multiples are matches.
				final String multplied = "" + (j*i);
				final char[] tempArray = multplied.toCharArray();
				Arrays.sort(tempArray);

				if( !Arrays.equals(tempArray, goalArray) ) {
					all6ArePermutationsOfI = false;
					break;
				}
			}

			if(all6ArePermutationsOfI) {
				return i;
			}
		}

		return 0L;
	}
}