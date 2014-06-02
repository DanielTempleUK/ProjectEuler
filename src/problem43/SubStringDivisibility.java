package problem43;

import java.util.ArrayList;
import java.util.List;

/**
 * Solution to problem 43:
 * 
 * 
 * The number, 1406357289, is a 0 to 9 pandigital number because it is made up of each of the digits 0 to 9 in some order,
 * but it also has a rather interesting sub-string divisibility property.
 * Let d1 be the 1st digit, d2 be the 2nd digit, and so on. In this way, we note the following:
 * 
 *     d2d3d4=406 is divisible by 2
 *     d3d4d5=063 is divisible by 3
 *     d4d5d6=635 is divisible by 5
 *     d5d6d7=357 is divisible by 7
 *     d6d7d8=572 is divisible by 11
 *     d7d8d9=728 is divisible by 13
 *     d8d9d10=289 is divisible by 17
 * 
 * Find the sum of all 0 to 9 pandigital numbers with this property.
 */
public class SubStringDivisibility {

	private static final List<String> permutations = new ArrayList<String>();

	public static void main(final String[] args) {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	private static long calculateSolution() {
		getPermutationsOf("", "0123456789");

		System.out.println("Permutations generated");

		long total = 0L;

		for (final String permutation : permutations) {
			if( (Integer.valueOf(permutation.substring(1, 4)) % 2) == 0 &&
					(Integer.valueOf(permutation.substring(2, 5)) % 3) == 0 &&
					(Integer.valueOf(permutation.substring(3, 6)) % 5) == 0 &&
					(Integer.valueOf(permutation.substring(4, 7)) % 7) == 0 &&
					(Integer.valueOf(permutation.substring(5, 8)) % 11) == 0 &&
					(Integer.valueOf(permutation.substring(6, 9)) % 13) == 0 &&
					(Integer.valueOf(permutation.substring(7, 10)) % 17) == 0 ) {
				total += Long.valueOf(permutation);
			}
		}

		return total;
	}

	private static void getPermutationsOf(final String prefix, final String str) {
		final int n = str.length();

		if (n == 1) {
			permutations.add(prefix + str);
		}
		else {
			for (int i = 0; i < n; i++) {
				getPermutationsOf(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1));
			}
		}
	}
}