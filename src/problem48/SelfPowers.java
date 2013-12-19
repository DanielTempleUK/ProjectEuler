package problem48;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * Solution to problem 48:
 * 
 * 
 * The series, 1^1 + 2^2 + 3^3 + ... + 10^10 = 10405071317.
 * Find the last ten digits of the series, 1^1 + 2^2 + 3^3 + ... + 1000^1000.
 */
public class SelfPowers {

	public static void main(final String[] args) throws IOException {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	private static long calculateSolution() {
		BigDecimal num = BigDecimal.ZERO;

		for(int i = 1; i<=1000; i++) {
			num = num.add( BigDecimal.valueOf(i).pow(i) );
		}

		final String string = num.toString();
		return Long.valueOf(string.substring(string.length()-10, string.length()));
	}
}