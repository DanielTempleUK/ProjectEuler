package problem34;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
		final Map<String, Long> factorials = getFactorials();

		final Set<Integer> digitFactorials = new HashSet<Integer>();

		//This upper limit is defined from the fact that no 8 digit number can be a factorion, so the longest number
		//that is a factorion is 7 digits long. Then calculating 7*(9!) = 2540160
		for(int i = 3; i < 2540160; i++ ) {
			final String[] digits = ("" + i).split("");

			long factorialSum = 0L;
			for (final String string : digits) {
				factorialSum += factorials.get(string);
			}

			if(factorialSum == i) {
				System.out.println("Sum of Factorial Digits: " + i);
				digitFactorials.add(i);
			}
		}

		long total = 0L;
		for (final Integer integer : digitFactorials) {
			total += integer;
		}
		return total;
	}

	private static Map<String, Long> getFactorials() {
		final Map<String, Long> factorialSums = new HashMap<String, Long>();
		for( int i = 0; i < 10; i++ ) {
			factorialSums.put(""+i, calculateFactorialFor(i));
		}
		factorialSums.put("", 0L);
		return factorialSums;
	}

	private static long calculateFactorialFor(final int number) {
		if(number == 0) {
			return 1L;
		}

		long factorial = 1L;
		for( int i = 1; i<=number; i++ ) {
			factorial *= i;
		}

		return factorial;
	}
}