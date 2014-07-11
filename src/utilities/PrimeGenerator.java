package utilities;

import java.util.ArrayList;
import java.util.List;

public class PrimeGenerator {

	public static final List<Long> getPrimesUnder(final int upperLimit ) {
		return getPrimes( 0, upperLimit );
	}

	public static final List<Long> getPrimesBetween(final int lowerLimit, final int upperLimit ) {
		return getPrimes( lowerLimit, upperLimit );
	}

	/** This prime generator first uses a Sieve of Eratosthenes to get all the primes numbers up to the upper limit
	 *  The ones added to the list are then limited by the lower limit. Of course, this could be made quicker by not
	 *  generating the ones we later discard in the first place, but for now it works OK.
	 */
	private static final List<Long> getPrimes(final int lowerLimit, final int upperLimit ) {

		final Boolean[] primes = new Boolean[upperLimit];
		primes[0] = Boolean.FALSE;
		primes[1] = Boolean.FALSE;

		for ( int i = 2; i < upperLimit; i++ ) {
			if( primes[i] == null ) {
				primes[i] = Boolean.TRUE;
			}

			for( int j = i+i; j < upperLimit; j+=i ) {
				primes[j] = Boolean.FALSE;
			}
		}

		final List<Long> primesList = new ArrayList<Long>();

		for (int i = 0; i<upperLimit; i++) {
			if( primes[i] && i >= lowerLimit ) {
				primesList.add( (long) i );
			}
		}

		return primesList;
	}
}