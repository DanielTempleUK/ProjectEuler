package problem7;

import java.util.ArrayList;
import java.util.List;

/**
 * Solution to problem 7:
 * 
 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.
 * What is the 10 001st prime number?
 */
public class TenThousandAndFirstPrime {

	public static void main(final String[] args) {

		final List<Long> primeNumbers = new ArrayList<Long>();

		for( long i = 2; i > 0; i++ ) {
			if( TenThousandAndFirstPrime.isPrime(i) ) {
				primeNumbers.add(i);

				if( primeNumbers.size() == 10001 ) {
					System.out.println(i);
					break;
				}
				continue;
			}
		}
	}

	private static boolean isPrime(final long input) {
		if( input == 2L || input == 3L || input == 5L || input == 7L ) {
			return true;
		}
		if( input < 11) {
			return false;
		}

		for ( int i = 2; i <= Math.sqrt(input); i++ ) {
			if( input % i == 0 ) {
				return false;
			}
		}

		return true;
	}
}