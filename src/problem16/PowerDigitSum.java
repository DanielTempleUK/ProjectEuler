package problem16;

import java.math.BigDecimal;

/**
 * Solution to problem 16:
 * 
 * 2^15 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.
 * What is the sum of the digits of the number 2^1000?
 */
public class PowerDigitSum {

	public static void main(final String[] args) {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	private static long calculateSolution() {
		final BigDecimal two = BigDecimal.valueOf(2);
		final BigDecimal power = two.pow(1000);

		final char[] digits = power.toString().toCharArray();
		long total = 0;
		for(int i=0; i<digits.length; i++) {
			total += Long.valueOf(""+digits[i]);
		}
		return total;
	}
}