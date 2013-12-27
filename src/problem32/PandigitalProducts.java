package problem32;

import java.util.HashSet;
import java.util.Set;

import utilities.PandigitalChecker;

/**
 * Solution to Problem 32:
 * 
 * 
 * We shall say that an n-digit number is pandigital if it makes use of all the digits 1 to n exactly once;
 * 		for example, the 5-digit number, 15234, is 1 through 5 pandigital.
 * 
 * The product 7254 is unusual, as the identity, 39 × 186 = 7254,
 * containing multiplicand, multiplier, and product is 1 through 9 pandigital.
 * 
 * Find the sum of all products whose multiplicand/multiplier/product identity can be written as a 1 through 9 pandigital.
 * HINT: Some products can be obtained in more than one way so be sure to only include it once in your sum.
 */
public class PandigitalProducts {

	public static void main(final String[] args) {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	/**
	 * The only multiplier and multiplicands that result in a 9 digit number (when combined with
	 * their product) must be 1 digit and 4 digits, or 2 digits and 3 digits) So we have two sets
	 * of loops. One for each of those options. This limits the search parameter greatly.
	 * 
	 * NOTE: I initially had one set of loops that looked at the range 1 to 9876.
	 */
	private static long calculateSolution() {

		final Set<Integer> pandigitalProducts = new HashSet<Integer>();

		for( int a = 1; a <= 9; a++ ) {
			for( int b = 1234; b <= 9876; b++ ) {
				final int product = a * b;
				final String digits = "" + a + "" + b + "" + product;

				if( nineCharactersInLength(digits) && PandigitalChecker.isPandigital(Long.valueOf(digits)) ) {
					pandigitalProducts.add(product);
				}
			}
		}

		for( int a = 11; a <= 99; a++ ) {
			for( int b = 123; b <= 987; b++ ) {
				final int product = a * b;
				final String digits = "" + a + "" + b + "" + product;

				if( nineCharactersInLength(digits) && PandigitalChecker.isPandigital(Long.valueOf(digits)) ) {
					pandigitalProducts.add(product);
				}
			}
		}

		long total = 0L;
		for (final Integer product : pandigitalProducts) {
			total += product;
		}

		return total;
	}

	private static boolean nineCharactersInLength(final String digits) {
		return digits.length() == 9;
	}
}