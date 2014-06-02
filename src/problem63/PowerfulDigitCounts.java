package problem63;

import java.math.BigDecimal;

/**
 * Solution to problem 63:
 * 
 * The 5-digit number, 16807=7^5, is also a fifth power.
 * Similarly, the 9-digit number, 134217728=8^9, is a ninth power.
 * How many n-digit positive integers exist which are also an nth power?
 */
public class PowerfulDigitCounts {

	public static void main(final String[] args) {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	/**
	 * First thing to note is that no number over 10 to any power will have the same number of digits as the power.
	 * Second thing to note is that for numbers under 10, the power will increase more than the number of digits will.
	 * 
	 * So, we only need to check bases 1-9 and some number of powers, then just check the length of the resulting number.
	 */
	private static long calculateSolution() {

		long count = 0L;

		for (int base = 1; base < 10; base++) {
			//Chose 50 as 10 wasn't enough. It transpires 21 would be enough too. :)
			for (int power = 1; power < 50; power++) {
				final BigDecimal result = BigDecimal.valueOf(base).pow(power);
				if( result.toString().length() == power ) {
					count++;
				}
			}
		}

		return count;
	}
}