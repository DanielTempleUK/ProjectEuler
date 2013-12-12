package problem31;

import java.io.IOException;

/**
 * Solution to Problem 31:
 * 
 * In England the currency is made up of pound, £, and pence, p, and there are eight coins in general circulation:
 * 
 * 1p, 2p, 5p, 10p, 20p, 50p, £1 (100p) and £2 (200p).
 * It is possible to make £2 in the following way:
 * 
 * 1×£1 + 1×50p + 2×20p + 1×5p + 1×2p + 3×1p
 * How many different ways can £2 be made using any number of coins?
 */
public class CoinSums {

	private static final int[] coinValues = new int[]{200, 100, 50, 20, 10, 5, 2};

	public static void main(final String[] args) throws IOException {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	/**
	 * Initially did this, but then changed to recursive solution:
	 * 
	 *      long numberOfWaysToMake2Pounds = 0L;
	 * 
	 *      for( int a = 200; a >= 0; a-=200 ) {
	 *      	for( int b = a; b >= 0; b-=100 ) {
	 *      		for( int c = b; c >= 0; c-=50 ) {
	 *      			for( int d = c; d >= 0; d-=20 ) {
	 *      				for( int e = d; e >= 0; e-=10 ) {
	 *      					for( int f = e; f >= 0; f-=5 ) {
	 *      						for( int g = f; g >= 0; g-=2 ) {
	 *      							numberOfWaysToMake2Pounds++;
	 *      						}
	 *      					}
	 *      				}
	 *      			}
	 *      		}
	 *      	}
	 *      }
	 * 
	 *      return numberOfWaysToMake2Pounds;
	 */
	private static long calculateSolution() {
		return findNumberOfWaysToMakeLimit(200, 0);
	}

	private static final long findNumberOfWaysToMakeLimit(final int limit, final int position) {
		long numberOfWaysToMakeLimit = 0L;

		if(position == coinValues.length) {
			return 1L;
		}

		for( int a = limit; a >= 0; a-=coinValues[position] ) {
			numberOfWaysToMakeLimit += findNumberOfWaysToMakeLimit(a, position+1);
		}

		return numberOfWaysToMakeLimit;
	}
}