package problem34;


/**
 * Solution to Problem 34:
 * 
 * 
 * 145 is a curious number, as 1! + 4! + 5! = 1 + 24 + 120 = 145.
 * 
 * Find the sum of all numbers which are equal to the sum of the factorial of their digits.
 * 
 * Note: as 1! = 1 and 2! = 2 are not sums they are not included.
 */
public class DigitFactorials {

	public static void main(final String[] args) {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	private static long calculateSolution() {
		final int[] factorials = getFactorials();
		long total = 0L;

		//This upper limit is defined from the fact that no 8 digit number can be a factorion, so the longest number
		//that is a factorion is 7 digits long. Then calculating 7*(9!) = 2540160
		for(int i = 3; i <= 2540160; i++ ) {
			int number = i;
			int factorialSum = 0;
			while( number > 0 ) {
				final int d = number % 10;
				number /= 10;
				factorialSum += factorials[d];
			}

			if(factorialSum == i) {
				total += i;
			}
		}

		return total;
	}

	private static int[] getFactorials() {
		final int[] factorials = new int[10];

		factorials[0] = 1;
		for( int i = 1; i < 10; i++ ) {
			factorials[i] = i * factorials[i-1];
		}
		return factorials;
	}
}