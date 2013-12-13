package problem32;

import java.util.HashSet;
import java.util.Set;

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

	private static long calculateSolution() {
		final Set<Integer> pandigitalProducts = new HashSet<Integer>();

		//There can only be 9 digits in the resulting equation, so the multiplicand and the multiplier must be less than 5 characters each.
		//Then the digits can appear only once, so the highest multiplier or multiplicand we could use would be 9876
		for( int a = 1; a <= 9876; a++ ) {
			//Commutativity of multiplication means we can start at a and cover all the options (4 x 1738 = 1738 x 4)
			for( int b = a; b <= 9876; b++ ) {
				final int product = a*b;
				final String digits = "" + a + "" + b + "" + product;

				if( not9CharactersInLength(digits) || containsAnyZeros(digits) || containsAnyDuplicateDigits(digits) ) {
					continue;
				}
				System.out.println("" + a + " x " + b + " = " + product);
				pandigitalProducts.add(product);
			}
		}

		long total = 0L;
		for (final Integer product : pandigitalProducts) {
			total += product;
		}

		return total;
	}

	private static boolean containsAnyDuplicateDigits(final String digits) {
		final String[] digitsArray = digits.split("");
		final Set<String> digitsSet = new HashSet<String>();

		for (final String digit : digitsArray) {
			if( digitsSet.add(digit) == false ) {
				return true;
			}
		}

		return false;
	}

	private static boolean containsAnyZeros(final String digits) {
		return digits.contains("0");
	}

	private static boolean not9CharactersInLength(final String digits) {
		return digits.length() != 9;
	}
}