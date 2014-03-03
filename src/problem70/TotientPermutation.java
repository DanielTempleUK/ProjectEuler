package problem70;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Solution to problem 70:
 * 
 * Problem definition is quite long, so see this link: <a href="http://projecteuler.net/problem=70">Problem 70</a>
 */
public class TotientPermutation {

	public static void main(final String[] args) throws IOException {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	private static long calculateSolution() {
		final List<Long> numbers = new ArrayList<Long>();
		for (long i = 9999999; i > 0; i-=2) {
			numbers.add(i);
		}

		for( final Long number : numbers ) {
			final Long numberOfRelativePrimes = number-1;

			final char[] charArray = number.toString().toCharArray();
			Arrays.sort(charArray);
			final String numberOrdered = new String(charArray);

			final char[] charArray2 = numberOfRelativePrimes.toString().toCharArray();
			Arrays.sort(charArray2);
			final String numberOfRelativePrimesOrdered = new String(charArray2);

			if( numberOrdered.equals(numberOfRelativePrimesOrdered) ) {
				return number;
			}
		}

		return 0L;
	}
}