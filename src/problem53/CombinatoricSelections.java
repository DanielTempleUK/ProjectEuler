package problem53;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * Solution to Problem 53:
 * 
 * 
 * There are exactly ten ways of selecting three from five, 12345:
 *    123, 124, 125, 134, 135, 145, 234, 235, 245, and 345
 * In combinatorics, we use the notation, 5C3 = 10.
 * 
 * In general,
 * nCr = n! / (r! * (n-r)!) , where r <= n, n! = nx(n-1)x...x3x2x1, and 0! = 1.
 * 
 * It is not until n = 23, that a value exceeds one-million: 23C10 = 1144066.
 * How many, not necessarily distinct, values of  nCr, for 1 <= n <= 100, are greater than one-million?
 */
public class CombinatoricSelections {

	private static final BigDecimal MILLION = BigDecimal.valueOf(1000000);

	public static void main(final String[] args) throws IOException {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	private static long calculateSolution() {

		long count = 0;
		for (int n = 1; n <= 100; n++) {
			for (int r = 1; r < n; r++) {
				if( choose(n, r).compareTo(MILLION) >= 0 ) {
					count++;
				}
			}
		}

		return count;
	}

	private static BigDecimal choose(final int n, final int r) {
		return factorial(n).divide(    factorial(r).multiply( factorial(n-r) )    );
	}

	private static BigDecimal factorial(final int x) {
		BigDecimal factorial = BigDecimal.ONE;

		for( int i = 1; i <= x; i++ ) {
			factorial = factorial.multiply(BigDecimal.valueOf(i));
		}

		return factorial;
	}
}