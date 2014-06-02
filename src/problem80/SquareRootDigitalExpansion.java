package problem80;

import java.io.IOException;
import java.math.BigInteger;

import utilities.NumberChecker;

/**
 * Solution to Problem 80:
 * 
 * 
 * It is well known that if the square root of a natural number is not an integer, then it is irrational.
 * The decimal expansion of such square roots is infinite without any repeating pattern at all.
 * 
 * The square root of two is 1.41421356237309504880..., and the digital sum of the first one hundred decimal digits is 475.
 * 
 * For the first one hundred natural numbers, find the total of the digital sums of
 * the first one hundred decimal digits for all the irrational square roots.
 */
public class SquareRootDigitalExpansion {

	public static void main(final String[] args) throws IOException {
		final long startTime = System.currentTimeMillis();
		final long solution = calculateSolution();
		final long endTime = System.currentTimeMillis();

		System.out.println("The answer is: " + solution);
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	/**
	 * Check out: http://www.afjarvis.staff.shef.ac.uk/maths/jarvisspec02.pdf
	 * 		This subtraction method is awesome!!! I can't believe it works, like, at all.
	 * 
	 * This solution is an implementation of that method. The nice thing is that just using subtraction and digit replacement,
	 * you avoid having to deal with decimal precision. All the values remain in integers.
	 */
	public static long calculateSolution() {
		long solution = 0L;

		for( int i = 2; i < 101; i++ ) {

			//Ignore any perfect squares.
			if( NumberChecker.isSquareNumber( i ) ) {
				continue;
			}

			//Follow the rules set out in the subtraction method until the last 05 is after the required accuracy
			BigInteger a = new BigInteger( "" + (5*i) );
			BigInteger b = new BigInteger( "5" );

			while( b.toString().length() < 102 ) {

				if( a.compareTo(b) >= 0 ) {
					a = a.subtract(b);
					b = b.add(BigInteger.TEN);
				}
				else {
					a = a.multiply(new BigInteger("100"));
					b = new BigInteger( b.toString().substring(0, b.toString().length()-1 ) + "05" );
				}

			}

			//Add on the digit values of the first 100 digits in the root
			final String digits = b.toString();

			for( int j = 0; j <= 99; j++ ) {
				solution += Long.valueOf( "" + digits.charAt(j) );
			}
		}

		return solution;
	}
}