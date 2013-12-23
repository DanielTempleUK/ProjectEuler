package problem5;

import java.io.IOException;

/**
 * Solution to problem 5:
 * 
 * 2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
 * What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
 */
public class SmallestMultiple {

	public static void main(final String[] args) throws IOException {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	private static long calculateSolution() {
		//Can start from this minimum since the number needs to divide by all of these primes which have no smaller factors.
		final long minimum = 2*3*5*7*11*13*17*19;

		//Can increment by this minimum value too, to skip the most number of irrelevant numbers in the middle.
		for( long i = minimum; i >= 0; i+=minimum ) {
			boolean dividesByAll1To20 = true;

			//Only need to check 11-20 since all the smaller numbers are factors of these bigger ones.
			for( int j = 20; j > 10; j-- ) {
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