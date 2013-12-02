package problem1;

import java.math.BigDecimal;

/**
 * Solution to problem 1:
 * 
 * 			If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.
 * 			Find the sum of all the multiples of 3 or 5 below 1000.
 */
public class MultiplesOf3And5 {

	public static void main(final String... args) {
		BigDecimal total = BigDecimal.ZERO;

		for ( int i = 0; i < 1000; i++) {
			if( i % 3 == 0 || i % 5 == 0 ) {
				total = total.add(BigDecimal.valueOf(i));
			}
		}

		System.out.println(total);
	}
}