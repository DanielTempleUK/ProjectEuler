package problem24;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Solution to Problem 24:
 * 
 * A permutation is an ordered arrangement of objects. For example, 3124 is one possible permutation of the digits
 * 1, 2, 3 and 4. If all of the permutations are listed numerically or alphabetically, we call it lexicographic order.
 * The lexicographic permutations of 0, 1 and 2 are:
 * 			012   021   102   120   201   210
 * What is the millionth lexicographic permutation of the digits 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?
 */
public class LexicographicPermutations {

	private static final List<String> permutations = new ArrayList<String>();

	public static void main(final String[] args) {
		final long startTime = System.currentTimeMillis();

		System.out.println( "The answer is: " + calculateSolution() );

		final long endTime = System.currentTimeMillis();
		System.out.println("This solution took: " + (endTime - startTime));
	}

	private static String calculateSolution() {
		getPermutationsOf("", "0123456789");

		Collections.sort(permutations);

		return permutations.get(999999);
	}

	private static void getPermutationsOf(final String prefix, final String str) {
		final int n = str.length();

		if (n == 0) {
			permutations.add(prefix);
		}
		else {
			for (int i = 0; i < n; i++) {
				getPermutationsOf(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n));
			}
		}
	}
}