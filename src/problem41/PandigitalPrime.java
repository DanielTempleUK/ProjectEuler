package problem41;

import utilities.PandigitalChecker;
import utilities.PrimalityChecker;

/**
 * Solution to problem 41:
 * 
 * 
 * We shall say that an n-digit number is pandigital if it makes use of all the digits 1 to n exactly once.
 * For example, 2143 is a 4-digit pandigital and is also prime.
 * What is the largest n-digit pandigital prime that exists?
 * 
 * NOTE: Because the upper limit is so close to the actual answer, this runs quite quickly.
 */
public class PandigitalPrime {

	public static void main(final String[] args) {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	private static long calculateSolution() {
		//Found this upper limit by trial and error. However, it can be explained by the divisible by 3 rule.
		// 1+2+3+4+5+6+7+8+9=45, so all 9 digit pandigital numbers are divisible by 3. i.e. not prime
		// 1+2+3+4+5+6+7+8=36, so all 8 digit pandigital numbers are divisible by 3. i.e. not prime
		// 1+2+3+4+5+6+7=28, which isn't divisible by 3, so start from highest 7 digit pandigital number, 7654321
		for (long i = 7654321L; i > 0; i-- ) {
			if( PandigitalChecker.isPandigital(i) && PrimalityChecker.isPrime(i) ) {
				return i;
			}
		}

		return 0L;
	}
}