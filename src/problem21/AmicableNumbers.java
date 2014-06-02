package problem21;

import java.util.HashSet;
import java.util.Set;

/**
 * Solution to Problem 21:
 *
 * Let d(n) be defined as the sum of proper divisors of n (numbers less than n which divide evenly into n).
 * If d(a) = b and d(b) = a, where a != b, then a and b are an amicable pair and each of a and b are called amicable numbers.
 * 
 * For example, the proper divisors of 220 are 1, 2, 4, 5, 10, 11, 20, 22, 44, 55 and 110; therefore d(220) = 284.
 * The proper divisors of 284 are 1, 2, 4, 71 and 142; so d(284) = 220.
 * 
 * Evaluate the sum of all the amicable numbers under 10000.
 */
public class AmicableNumbers {

	public static void main(final String[] args) {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	private static long calculateSolution() {

		final Set<Integer> amicableNumbers = new HashSet<Integer>();

		for( int i = 0; i < 10001; i++ ) {

			final Set<Integer> factorsForI = getFactorsFor(i);
			int factorsForITotal = 0;
			for (final Integer factor: factorsForI) {
				factorsForITotal = factorsForITotal + factor;
			}

			final Set<Integer> factorsForTotal = getFactorsFor(factorsForITotal);
			int factorsForTotalTotal = 0;
			for (final Integer factor: factorsForTotal) {
				factorsForTotalTotal = factorsForTotalTotal + factor;
			}

			if ( factorsForTotalTotal == i && factorsForTotalTotal != factorsForITotal ) {
				amicableNumbers.add(i);
				amicableNumbers.add(factorsForTotalTotal);
			}
		}

		Long total = 0L;
		for (final Integer integer : amicableNumbers) {
			total += integer;
		}

		return total;
	}

	private static Set<Integer> getFactorsFor(final int number) {
		final Set<Integer> factors = new HashSet<Integer>();
		if( number == 0 || number == 1 ) {
			return factors;
		}
		factors.add(1);

		final double numberRoot = Math.sqrt(number);
		if(numberRoot % 1 == 0) {
			factors.add( (int) numberRoot );
		}

		for( int i = 2; i < numberRoot; i++ ) {
			if(number % i == 0) {
				factors.add(i);
				factors.add(number / i);
			}
		}

		return factors;
	}
}