package problem24;

/**
 * Solution to Problem 24:
 * 
 * 
 * A permutation is an ordered arrangement of objects. For example, 3124 is one possible permutation of the digits
 * 1, 2, 3 and 4. If all of the permutations are listed numerically or alphabetically, we call it lexicographic order.
 * The lexicographic permutations of 0, 1 and 2 are:
 * 			012   021   102   120   201   210
 * What is the millionth lexicographic permutation of the digits 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?
 */
public class LexicographicPermutations {

	public static void main(final String[] args) {
		final long startTime = System.currentTimeMillis();

		System.out.println( "The answer is: " + calculateSolution() );

		final long endTime = System.currentTimeMillis();
		System.out.println("This solution took: " + (endTime - startTime) + " milliseconds");
	}

	/**
	 * This solution finds the answer one digit at a time.
	 * 
	 * We're looking for the ordered permutations, so we can look at the number of permutations of 9 characters
	 * to find the first character. If we know 9! is 362880, then the first number isn't 0. It also isn't 1, since
	 * 362880 * 2  = 725760. Following this, the first number must be 2, since 362880 * 3 = 1088640 which is beyond
	 * our limit of the millionth permutation.
	 * Applying this logic to the 8! to find the second digit, and so on, we can find all of the digits, by limiting
	 * the factorial multiples to be less than the 1000000 limit we're trying to find.
	 */
	private static Long calculateSolution() {
		String digitsLeftToUse = "0123456789";
		String solution = "";
		long limit = 1000000;

		for( int i = 9; i > 0; i-- ) {

			final long iFactorial = factorial(i);

			long temp = iFactorial;
			int multipleWithinLimit = 1;
			int potentialMultiple = 2;
			while( temp < limit ) {
				temp = iFactorial * potentialMultiple;
				if( temp < limit ) {
					multipleWithinLimit = potentialMultiple;
					potentialMultiple++;
				}
			}

			//Now we have a multiple to use, we need to get the digit at that index from
			//the digits we have left. We also need to remove the digit from the ones left
			//update the limit that we're looking for.
			final String digitToUse = "" + digitsLeftToUse.toCharArray()[multipleWithinLimit];
			solution = solution + digitToUse;
			digitsLeftToUse = digitsLeftToUse.replace(digitToUse, "");
			limit -= (iFactorial * multipleWithinLimit);
		}

		return Long.valueOf(solution);
	}

	private static long factorial(final int number) {
		long factorial = 1L;
		for (int i = number; i > 0; i--) {
			factorial *= i;
		}
		return factorial;
	}

	/**
	 * This was my first solution using recursion to generate each of the permutations of 0123456789,
	 * then ordering them and returning the one at position 1000000
	 * 
	 		private static final List<String> permutations = new ArrayList<String>();

			private static String calculateSolution() {
				getPermutationsOf("", "0123456789");
				Collections.sort(permutations);
				return permutations.get(999999);
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
	 * 
	 */
}