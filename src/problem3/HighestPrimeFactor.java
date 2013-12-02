package problem3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import utilities.PrimalityChecker;

/**
 * Solution to problem 3:
 * 
 * The prime factors of 13195 are 5, 7, 13 and 29.
 * What is the largest prime factor of the number 600851475143 ?
 */
public class HighestPrimeFactor {

	private static final double INPUT = 600851475143d;

	private static final List<Double> primeFactors = new ArrayList<Double>();

	public static void main(final String... args) {
		findPrimeFactors(INPUT);

		Collections.sort(primeFactors);
		System.out.println(primeFactors.get(primeFactors.size()-1));
	}

	private static void findPrimeFactors(final double input) {
		for ( double i = 2; i < Math.sqrt(input); i++ ) {

			final double d = input / i;

			if( ( d % 1 ) == 0 ) {
				if(PrimalityChecker.isPrime((long) i)) {
					primeFactors.add(i);
				}
			}
		}
	}
}