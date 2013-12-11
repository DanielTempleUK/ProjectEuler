package problem5;

import java.io.IOException;

/**
 * Solution to problem 5:
 * 
 * 2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
 * What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
 * 
 * 
 * Note: This one does take a little while to run, although it is fully within the 60 second time limit, probably around 10 seconds.
 */
public class SmallestMultiple {

	public static void main(final String[] args) throws IOException {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	private static long calculateSolution() {
		for( int i = 20; i >= 0; i++ ) {
			boolean dividesByAll1To20 = true;
			for( int j = 1; j < 21; j++ ) {
				if( i%j != 0 ) {
					dividesByAll1To20 = false;
					break;
				}
			}
			if (dividesByAll1To20) {
				return i;
			}
		}
		return 0L;
	}
}