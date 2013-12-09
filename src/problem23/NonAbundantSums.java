package problem23;

import java.util.HashSet;
import java.util.Set;

/**
 * Solution to Problem 23:
 * 
 * A perfect number is a number for which the sum of its proper divisors is exactly equal to the number.
 * For example, the sum of the proper divisors of 28 would be 1 + 2 + 4 + 7 + 14 = 28, which means that 28 is a perfect number.
 * 
 * A number n is called deficient if the sum of its proper divisors is less than n and it is called abundant if this sum exceeds n.
 * 
 * As 12 is the smallest abundant number, 1 + 2 + 3 + 4 + 6 = 16, the smallest number that can be written as the sum of two abundant
 * numbers is 24. By mathematical analysis, it can be shown that all integers greater than 28123 can be written as the sum of two
 * abundant numbers. However, this upper limit cannot be reduced any further by analysis even though it is known that the greatest
 * number that cannot be expressed as the sum of two abundant numbers is less than this limit.
 * 
 * Find the sum of all the positive integers which cannot be written as the sum of two abundant numbers.
 */
public class NonAbundantSums {

	private static final int LIMIT = 28123;

	public static void main(final String[] args) {

		final long startTime = System.currentTimeMillis();


		final Set<Integer> abundantNumbers = findAbundantNumbersLessThan(LIMIT);
		System.out.println(abundantNumbers.size() + " Abundant Numbers");


		final Set<Integer> allNumbers = getAllNaturalNumbersLessThan(LIMIT);


		//Remove all the natural number that are the sum of 2 abundant numbers
		for (final Integer integer1 : abundantNumbers) {
			for (final Integer integer2 : abundantNumbers) {
				allNumbers.remove(integer1 + integer2);
			}
		}

		System.out.println(allNumbers.size() + " number that are NOT sums of 2 abundant numbers");


		//Total the remaining numbers
		long total = 0L;
		for (final Integer integer : allNumbers) {
			total += integer;
		}

		System.out.println(total + " is the sum of all numbers that are not the sum of two abundant numbers.");


		final long endTime = System.currentTimeMillis();
		System.out.println("This took: " + (endTime - startTime) + " milliseconds");
	}


	/* HELPER METHODS */

	private static Set<Integer> getAllNaturalNumbersLessThan(final int limit) {
		final Set<Integer> allNumbers = new HashSet<Integer>();
		for( int i = 0; i < limit; i++ ) {
			allNumbers.add(i);
		}
		return allNumbers;
	}

	private static Set<Integer> findAbundantNumbersLessThan(final int limit) {
		final Set<Integer> abundantNumbers = new HashSet<Integer>();

		for( int i = 0; i < limit; i++ ) {

			if (sumOfFactorsOf(i) > i ) {
				abundantNumbers.add(i);
			}
		}
		return abundantNumbers;
	}

	private static long sumOfFactorsOf(final int number) {
		final Set<Integer> factors = getFactorsFor(number);

		int total = 0;
		for (final Integer factor: factors) {
			total = total + factor;
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