package problem62;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Solution to problem 62:
 * 
 * The cube, 41063625 (345^3), can be permuted to produce two other cubes: 56623104 (384^3) and 66430125 (405^3).
 * In fact, 41063625 is the smallest cube which has exactly three permutations of its digits which are also cube.
 * 
 * Find the smallest cube for which exactly five permutations of its digits are cube.
 */
public class CubicPermutations {

	public static void main(final String[] args) {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	/**
	 * Keep track of the number of times we see the same highest permutation.
	 * This avoids having to check all of the permutations and means we can just check as we generate.
	 * 
	 * The extra object is to maintain the lowest cube that gives us the highest permutation with 5 occurrences.
	 */
	private static long calculateSolution() {
		final Map<String, LowestPermAndOccurrences> map = new HashMap<String, LowestPermAndOccurrences>();

		for (long i = 1; true; i++) {
			final BigDecimal iCubed = BigDecimal.valueOf(i).pow(3);

			final String highestPermutation = getHighestPermutationOf(iCubed);

			if( map.get(highestPermutation) == null ) {
				map.put(highestPermutation, new LowestPermAndOccurrences(iCubed, 1) );
				continue;
			}

			final int numberOfCubes = map.get(highestPermutation).occurrences + 1;
			if( numberOfCubes == 5 ) {
				return map.get(highestPermutation).lowestPerm.longValue();
			}

			map.put( highestPermutation, new LowestPermAndOccurrences(map.get(highestPermutation).lowestPerm, numberOfCubes) );
		}
	}

	/**
	 * Highest permutation is reverse ordered digits
	 */
	private static String getHighestPermutationOf(final BigDecimal iCubed) {
		final char[] charArray = iCubed.toString().toCharArray();
		Arrays.sort(charArray);

		String highestPerm = "";
		for (int i = charArray.length-1; i > -1; i--) {
			highestPerm = highestPerm + charArray[i];
		}

		return highestPerm;
	}

	private static class LowestPermAndOccurrences {
		private BigDecimal lowestPerm;
		private int occurrences;
		public LowestPermAndOccurrences( final BigDecimal lowestPerm, final int occurrences) {
			this.lowestPerm = lowestPerm;
			this.occurrences = occurrences;
		}
	}
}