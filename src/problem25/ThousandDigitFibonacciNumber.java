package problem25;

import java.math.BigDecimal;

/**
 * The Fibonacci sequence is defined by the recurrence relation:
 * 
 * Fn = F(n-1) + F(n-2), where F(1) = 1 and F(2) = 1.
 * Hence the first 12 terms will be:
 * 
 * F(1) = 1
 * F(2) = 1
 * F(3) = 2
 * F(4) = 3
 * F(5) = 5
 * F(6) = 8
 * F(7) = 13
 * F(8) = 21
 * F(9) = 34
 * F(10) = 55
 * F(11) = 89
 * F(12) = 144
 * The 12th term, F12, is the first term to contain three digits.
 * 
 * What is the first term in the Fibonacci sequence to contain 1000 digits?
 */
public class ThousandDigitFibonacciNumber {

	public static void main(final String[] args) {
		final long startTime = System.currentTimeMillis();

		System.out.println( "The answer is: " + calculateSolution() );

		final long endTime = System.currentTimeMillis();
		System.out.println("This solution took: " + (endTime - startTime) + " milliseconds");
	}

	private static String calculateSolution() {
		BigDecimal i = BigDecimal.valueOf(1);
		BigDecimal j = BigDecimal.valueOf(1);
		BigDecimal k = BigDecimal.ZERO;

		BigDecimal term = BigDecimal.valueOf(2);

		while( true ) {
			term = term.add(BigDecimal.ONE);
			k = i.add(j);
			i = j;
			j = k;
			if( k.toString().length() == 1000) {
				return term.toString();
			}
		}
	}
}