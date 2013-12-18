package problem44;

import java.util.ArrayList;
import java.util.List;

/**
 * Solution to problem 44:
 * 
 * 
 * Pentagonal numbers are generated by the formula, Pn=n(3n-1)/2.
 * The first ten pentagonal numbers are:
 * 
 *     1, 5, 12, 22, 35, 51, 70, 92, 117, 145,...
 * 
 * It can be seen that P4 + P7 = 22 + 70 = 92 = P8.
 * However, their difference, 70 - 22 = 48, is not pentagonal.
 * 
 * Find the pair of pentagonal numbers, Pj and Pk, for which their
 * sum and difference are pentagonal and D = |Pk - Pj| is minimised; what is the value of D?
 */
public class PentagonNumbers {

	public static void main(final String[] args) {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	private static long calculateSolution() {

		//Reached this upper limit by trial and error with my solution
		final List<Long> numbers = generatePentagonalNumbersUnder(10000000L);
		System.out.println(numbers);

		Integer smallestDifference = 999999999; //arbitrarily large

		for (final Long long1 : numbers) {
			for (final Long long2 : numbers) {
				final long sum = long1 + long2;
				final long difference = Math.abs(long1 - long2);
				if( isPentagonal(sum) && isPentagonal(difference) && difference < smallestDifference) {
					smallestDifference = Integer.valueOf((int) difference);
				}
			}
		}

		return smallestDifference;
	}

	private static boolean isPentagonal(final long x) {
		final double n = ((Math.sqrt((24 * x) + 1)) + 1) / 6;
		return n % 1 == 0;
	}

	private static List<Long> generatePentagonalNumbersUnder(final long l) {
		final List<Long> numbers = new ArrayList<Long>();

		long pentagonalNumber = 1L;
		int counter = 1;
		while( pentagonalNumber < l ) {
			numbers.add(pentagonalNumber);
			counter++;
			pentagonalNumber = (long) (counter * ( (3 * counter) - 1 ) * 0.5);
		}
		return numbers;
	}
}