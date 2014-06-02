package problem28;

/**
 * Solution for Problem 28:
 * 
 * Starting with the number 1 and moving to the right in a clockwise direction a 5 by 5 spiral is formed as follows:
 * 
 *       21 22 23 24 25
 *       20  7  8  9 10
 *       19  6  1  2 11
 *       18  5  4  3 12
 *       17 16 15 14 13
 * 
 * It can be verified that the sum of the numbers on the diagonals is 101.
 * What is the sum of the numbers on the diagonals in a 1001 by 1001 spiral formed in the same way?
 */
public class NumberSpiralDiagonals {

	public static void main(final String[] args) {
		final long startTime = System.currentTimeMillis();

		calculateAndPrintSolution();

		final long endTime = System.currentTimeMillis();
		System.out.println("This solution took: " + (endTime - startTime) + " milliseconds");
	}

	/**
	 * In order to come up with an algorithm for this I first drew this:
	 * 
	 *            n =  0  1  2  3
	 *       43 44 45 46 47 48 49
	 *       42 21 22 23 24 25 26
	 *       41 20  7  8  9 10 27
	 *       40 19  6  1  2 11 28
	 *       39 18  5  4  3 12 29
	 *       38 17 16 15 14 13 30
	 *       37 36 35 34 33 32 31
	 * 
	 *  Where I've assigned values to n, it is possible to calculate the 4 corner values in the grid based on that n value.
	 *  Then we just need a limit on n, which will be half of the total width minus 1
	 */
	private static void calculateAndPrintSolution() {

		final int lengthOfSides = 1001;

		//Starting with a total of one means we can ignore the case where i=0;
		long total = 1;

		//We only need to evaluate for half of the grid width
		for( int n = 1; n <= ((lengthOfSides-1)/2); n++ ) {

			//From pattern analysis, we can find the square number in the top right hand corner of the grid as (2n + 1)^2
			final long squareNumberInTopRightCorner = ( (2*n) + 1 ) * ( (2*n) + 1 );

			//The numbers on the other corners are then just the top right square minus multiples of two of n.
			total += squareNumberInTopRightCorner;
			total += (squareNumberInTopRightCorner - (2*n) );
			total += (squareNumberInTopRightCorner - (4*n) );
			total += (squareNumberInTopRightCorner - (6*n) );
		}

		System.out.println(total);
	}
}