package problem68;

import utilities.Permutations;

/**
 * Solution to problem 68:
 * 
 * Problem definition is quite long, so see this link: <a href="http://projecteuler.net/problem=68">Problem 68</a>
 */
public class Magic5GonRing {

	public static void main(final String[] args) {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	/**
	 * Use the number 0-9 rather than 1-10 since then they're all single integers (and can be array indexes)
	 * 
	 * Using all the permutations, we can arrange the indexes in this order:
	 * 	012,324,546,768,981
	 * 
	 * Then using that arrangement, we can get the resulting numbers from all the permutations of 0-9 (increment them by 1)
	 * And ensure they conform to the desired result constraints.
	 */
	private static String calculateSolution() {
		String currentHighest = "0";

		//Generate all the permutations of the indexes 0-9
		final int[][] permutations = Permutations.generatePermutationsAsIntArray("0123456789");

		for( int i = 0; i < permutations.length; i++ ) {
			final int[] perm = permutations[i];

			//The solution needs to start from the lowest number on the outer ring.
			if( perm[0] > perm[3] ||
					perm[0] > perm[5] ||
					perm[0] > perm[7] ||
					perm[0] > perm[9] ) {
				continue;
			}

			//Build the sums of 3 numbers to check the property where they all match.
			final int line1 = perm[0] + 1 + perm[1] + 1 + perm[2] + 1;
			final int line2 = perm[3] + 1 + perm[2] + 1 + perm[4] + 1;
			final int line3 = perm[5] + 1 + perm[4] + 1 + perm[6] + 1;
			final int line4 = perm[7] + 1 + perm[6] + 1 + perm[8] + 1;
			final int line5 = perm[9] + 1 + perm[8] + 1 + perm[1] + 1;

			if( line1 == line2 &&
					line2 == line3 &&
					line3 == line4 &&
					line4 == line5) {

				//Build the potential solution string
				final String woop = "" + (perm[0] + 1) + "" + (perm[1] + 1) + "" + (perm[2] + 1) +
						"" + (perm[3] + 1) + "" + (perm[2] + 1) + "" + (perm[4] + 1) +
						"" + (perm[5] + 1) + "" + (perm[4] + 1) + "" + (perm[6] + 1) +
						"" + (perm[7] + 1) + "" + (perm[6] + 1) + "" + (perm[8] + 1) +
						"" + (perm[9] + 1) + "" + (perm[8] + 1) + "" + (perm[1] + 1);

				//Ensure we only return the highest one
				if( woop.length() == 16 && (woop.compareTo(currentHighest) > 0) ) {
					currentHighest = woop;
				}
			}
		}

		return currentHighest;
	}
}