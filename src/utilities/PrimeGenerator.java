package utilities;

import java.util.ArrayList;
import java.util.List;

public class PrimeGenerator {

	public static final List<Long> getPrimesUnder(final int upperLimit ) {
		return getPrimes( 0, upperLimit);
	}

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
			if( primes[i] && i > lowerLimit ) {
				primesList.add( (long) i );
			}
		}

		return primesList;
	}
}