package problem26;

import java.util.HashSet;
import java.util.Set;

/**
 * Solution to problem 26:
 * 
 * A unit fraction contains 1 in the numerator. The decimal representation of the unit fractions with denominators 2 to 10 are given:
 * 
 * 1/2	= 	0.5
 * 1/3	= 	0.(3)
 * 1/4	= 	0.25
 * 1/5	= 	0.2
 * 1/6	= 	0.1(6)
 * 1/7	= 	0.(142857)
 * 1/8	= 	0.125
 * 1/9	= 	0.(1)
 * 1/10	= 	0.1
 * Where 0.1(6) means 0.166666..., and has a 1-digit recurring cycle. It can be seen that 1/7 has a 6-digit recurring cycle.
 * 
 * Find the value of d < 1000 for which 1/d contains the longest recurring cycle in its decimal fraction part.
 */
public class ReciprocalCycles {

	public static void main(final String[] args) {
		final long startTime = System.currentTimeMillis();

		System.out.println( "The answer is: " + calculateSolution() );

		final long endTime = System.currentTimeMillis();
		System.out.println("This solution took: " + (endTime - startTime) + " milliseconds");
	}

	private static int calculateSolution() {
		int longestSequence = 0;
		int numberWithLongestSequence = 0;

		for (int i = 11; i < 1001; i++) {
			final Set<Integer> remaindersFound = new HashSet<Integer>();
			int lastDecimal = 1;
			int sequenceLength = 0;

			while ( !remaindersFound.contains(lastDecimal) && lastDecimal != 0) {
				remaindersFound.add(lastDecimal);

				lastDecimal = (lastDecimal * 10) % i;
				sequenceLength++;
			}

			if ( sequenceLength > longestSequence) {
				numberWithLongestSequence = i;
				longestSequence = sequenceLength;
			}
		}

		return numberWithLongestSequence;
	}
}
