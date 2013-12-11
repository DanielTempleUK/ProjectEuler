package problem1;

/**
 * Solution to problem 1:
 * 
 * If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.
 * Find the sum of all the multiples of 3 or 5 below 1000.
 */
public class MultiplesOf3And5 {

	public static void main(final String[] args) {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	private static long calculateSolution() {
		long total = 0L;

		for ( int i = 0; i < 1000; i++) {
			if( i % 3 == 0 || i % 5 == 0 ) {
				total += i;
			}
		}

		return total;
	}
}