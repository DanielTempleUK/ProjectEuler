package problem38;

import java.util.HashSet;
import java.util.Set;

import utilities.PandigitalChecker;

/**
 * Solution to problem 38
 * 
 * 
 * Take the number 192 and multiply it by each of 1, 2, and 3:
 * 
 * 192 × 1 = 192
 * 192 × 2 = 384
 * 192 × 3 = 576
 * By concatenating each product we get the 1 to 9 pandigital, 192384576.
 * We will call 192384576 the concatenated product of 192 and (1,2,3)
 * 
 * The same can be achieved by starting with 9 and multiplying by 1, 2, 3, 4, and 5, giving the pandigital, 918273645,
 *  which is the concatenated product of 9 and (1,2,3,4,5).
 * 
 * What is the largest 1 to 9 pandigital 9-digit number that can be formed as the concatenated
 * product of an integer with (1,2, ... , n) where n > 1?
 */
public class PandigitalMultiples {

	public static void main(final String[] args) {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	private static long calculateSolution() {
		final Set<Integer> pandigitalMultiples = new HashSet<Integer>();

		for( int a = 1; a <= 9876; a++ ) {

			String potentialPandigitalNumber = "";

			for( int n = 1; n <= 5; n++ ) {
				potentialPandigitalNumber = potentialPandigitalNumber + "" + (a*n);

				if( potentialPandigitalNumber.length() < 9 ) {
					continue;
				}
				else if( potentialPandigitalNumber.length() > 9 ) {
					break;
				}
				else if( PandigitalChecker.isPandigital( Long.valueOf( potentialPandigitalNumber ) ) ) {
					pandigitalMultiples.add(Integer.valueOf(potentialPandigitalNumber));
				}
			}
		}

		long largest = 0L;
		for (final Integer product : pandigitalMultiples) {
			if( product > largest ) {
				largest = product;
			}
		}

		return largest;
	}
}