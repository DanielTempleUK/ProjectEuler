package utilities;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
public class PrimalityChecker {

	private static final Integer KNOWN_PRIMES_LIMIT = 20;
	private static final Set<Long> KNOWN_PRIMES_BELOW_LIMIT = new HashSet<Long>(){
		{ add(2L);add(3L);add(5L);add(7L);add(11L);add(13L);add(17L);add(19L); }
	};

	public static boolean isPrime(final long input) {
		if ( isAKnownPrime(input) ) {
			return true;
		}
		if( input < KNOWN_PRIMES_LIMIT ) {
			return false;
		}
		if( input % 2 == 0 ) {
			return false;
		}

		for ( int i = 3; i <= Math.sqrt(input); i++ ) {
			if( (input % i) == 0 ) {
				return false;
			}
		}

		return true;
	}

	private static boolean isAKnownPrime(final long input) {
		if( KNOWN_PRIMES_BELOW_LIMIT.contains(input)) {
			return true;
		}
		return false;
	}
}