package utilities;

import java.util.HashSet;
import java.util.Set;


public class FactorUtils {

	private static final String FACTOR_DELIMITER = "X";

	/**
	 * Returns the smallest prime factor of the provided value
	 */
	public static long smallestPrimeFactor( final long n ) {
		if (n==0 || n==1) return n;
		if (n%2==0) return 2;
		if (n%3==0) return 3;
		if (n%5==0) return 5;

		final double m = Math.sqrt(n);

		/* This loop is incredible.
		 * It generates all the primes ever, with some extra numbers thrown in.
		 * I'm going to work out why this works!
		 */
		for (int i=7;i<=m;i+=30) {

			if ( n % i == 0 ) {
				return i;
			}
			if ( n % ( i+4 ) == 0) {
				return i+4;
			}
			if ( n % ( i+6 ) == 0) {
				return i+6;
			}
			if ( n % ( i+10 ) == 0) {
				return i+10;
			}
			if ( n % ( i+12 ) == 0) {
				return i+12;
			}
			if ( n % ( i+16 ) == 0) {
				return i+16;
			}
			if ( n % ( i+22 ) == 0) {
				return i+22;
			}
			if ( n % ( i+24 ) == 0) {
				return i+24;
			}
		}
		return n;
	}

	/**
	 * Returns a string containing the prime factors of the provided long.
	 * The string is built using each factor followed by an 'X'.
	 * 
	 * The factors will also be in order.
	 */
	public static String primeFactorise(final long n){
		if (n == 1 || n == 0) {
			return "" + n;
		}

		final long minFactor = smallestPrimeFactor(n);

		if (n==minFactor) {
			return "" + n;
		}
		return minFactor + FACTOR_DELIMITER + primeFactorise(n/minFactor);
	}

	/**
	 * Returns a set of the prime factors of the provided value.
	 * 
	 * Importantly, is the number is 2*2*2*2*2*5*5*5*5*5*5,
	 * then the set will contain 2 and 5
	 */
	public static Set<Long> getUniqePrimeFactorsOf(final long n){
		final String string = primeFactorise(n);
		final String[] split = string.split(FACTOR_DELIMITER);
		final Set<Long> factors = new HashSet<Long>();
		for (final String factor : split) {
			factors.add(Long.valueOf(factor));
		}
		return factors;
	}
}