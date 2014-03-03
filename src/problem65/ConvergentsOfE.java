package problem65;

import java.math.BigInteger;

/**
 * Solution to problem 65:
 * 
 * Problem definition is quite long, so see this link: <a href="http://projecteuler.net/problem=65">Problem 65</a>
 */
public class ConvergentsOfE {

	public static void main(final String[] args) {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	/**
	 * Given the period sequence, I did some pattern analysis on the numerators and denominators to
	 * find that the pattern is add the previous 2 together where the previous period is 1, or
	 * multiply previous 1 by the period and add other previous 1 (i-2) to that.
	 * 
	 * From here it was quite simple to get the answer by generating the subsequent numerators.
	 */
	private static long calculateSolution() {
		final long[] periodDigits = new long[100];
		int k = 1;
		for ( int i = 0; i < 100; i++ ) {
			if( ((i-1) % 3) == 0 ) {
				periodDigits[i] = k*2;
				k++;
			}
			else {
				periodDigits[i] = 1;
			}
		}

		final BigInteger[] numerators = new BigInteger[100];

		numerators[0] = BigInteger.valueOf(2L);
		numerators[1] = BigInteger.valueOf(3L);

		for (int i = 2; i < 100; i++) {
			final long period = periodDigits[i-1];
			if( period > 1 ) {
				numerators[i] = numerators[i-1].multiply(BigInteger.valueOf(period)).add(numerators[i-2]);
			}
			else {
				numerators[i] = numerators[i-1].add(numerators[i-2]);
			}
		}

		long sum = 0L;
		BigInteger numerator = numerators[99];
		while (numerator.compareTo(BigInteger.ZERO) > 0) {
			sum += Long.parseLong(numerator.mod(BigInteger.TEN).toString());
			numerator = numerator.divide(BigInteger.TEN);
		}
		return sum;
	}
}