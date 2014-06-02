package problem72;

import java.io.IOException;
import java.util.Set;

import utilities.FactorUtils;

/**
 * Solution to problem 71:
 * 
 * 
 * Consider the fraction, n/d, where n and d are positive integers.
 * If n<d and HCF(n,d)=1, it is called a reduced proper fraction.
 * 
 * If we list the set of reduced proper fractions for d <= 8 in ascending order of size, we get:
 * 
 * 1/8, 1/7, 1/6, 1/5, 1/4, 2/7, 1/3, 3/8, 2/5, 3/7, 1/2, 4/7, 3/5, 5/8, 2/3, 5/7, 3/4, 4/5, 5/6, 6/7, 7/8
 * 
 * It can be seen that there are 21 elements in this set.
 * 
 * How many elements would be contained in the set of reduced proper fractions for d <= 1,000,000?
 */
public class CountingFractions {

	public static void main(final String[] args) throws IOException {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	/**
	 * For a fraction to be reduced & proper, the HCF between n and d needs to be 1.
	 * So for each denominator, we need to know how many numerators are relatively prime it.
	 * This is the totient function that I have calculated previously.
	 * 
	 * So, I just add up the totient value for each number from 1 to a million.
	 */
	private static long calculateSolution() {
		long solution = 0L;
		for (long d = 1; d <= 1000000; d++) {
			solution += calculateTotient(d);
		}
		return solution;
	}

	private static long calculateTotient(final long n) {
		final Set<Long> primeFactorise = FactorUtils.getUniqePrimeFactorsOf(n);
		double totient = n;

		for (final Long factor : primeFactorise) {
			final double factorMinus1 = factor-1;
			totient *= factorMinus1;
			totient /= factor;
		}

		return (long) totient;
	}
}
