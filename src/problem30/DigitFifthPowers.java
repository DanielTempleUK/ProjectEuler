package problem30;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Solution to problem 30:
 * 
 * Surprisingly there are only three numbers that can be written as the sum of fourth powers of their digits:
 * 
 * 1634 = 1^4 + 6^4 + 3^4 + 4^4
 * 8208 = 8^4 + 2^4 + 0^4 + 8^4
 * 9474 = 9^4 + 4^4 + 7^4 + 4^4
 * As 1 = 1^4 is not a sum it is not included.
 * 
 * The sum of these numbers is 1634 + 8208 + 9474 = 19316.
 * 
 * Find the sum of all the numbers that can be written as the sum of fifth powers of their digits.
 */
public class DigitFifthPowers {

	public static void main(final String[] args) throws IOException {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	private static long calculateSolution() {

		final Set<Integer> numbersThatAreTheSumOfTheirDigitsToTheFifthPower = new HashSet<Integer>();

		//This upper limit of a million was achieved through trial and error.
		for( int i = 2; i < 1000000; i++ ) {

			final String numberString = "" + i;
			final char[] numberDigits = numberString.toCharArray();

			long total = 0L;
			for( int a = 0; a < numberDigits.length; a++ ) {
				final Integer digit = Integer.valueOf("" + numberDigits[a]);
				total += (digit*digit*digit*digit*digit);
			}

			if( total == i ) {
				numbersThatAreTheSumOfTheirDigitsToTheFifthPower.add(i);
			}
		}

		long total = 0L;
		for (final Integer integer : numbersThatAreTheSumOfTheirDigitsToTheFifthPower) {
			total += integer;
		}

		return total;
	}
}