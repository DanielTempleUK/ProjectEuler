package problem3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This appears to not be my completed class.
 * Working on these prblems on more than one machine before I got github set up for them
 * problem wasn't the best plan....
 * 
 * @author Dan
 */
public class HighestPrimeFactor {

    private static final long INPUT = 600851475143L;

    private static final List<Long> primeFactors = new ArrayList<Long>();

    public static void main(final String... args) {
	HighestPrimeFactor.findPrimeFactor(HighestPrimeFactor.INPUT);

	Collections.sort(HighestPrimeFactor.primeFactors);

	System.out.println(HighestPrimeFactor.primeFactors.get(HighestPrimeFactor.primeFactors.size()-1));
    }

    private static void findPrimeFactor(final long input) {
	final long factor = HighestPrimeFactor.findFactorOf(input);
	if(!HighestPrimeFactor.isPrime(factor)) {
	    HighestPrimeFactor.findPrimeFactor(input/factor);
	    HighestPrimeFactor.findPrimeFactor(factor);
	}
	else {
	    HighestPrimeFactor.primeFactors.add(factor);
	}

    }

    private static long findFactorOf(final double input) {
	long a = (long) Math.ceil(Math.sqrt(HighestPrimeFactor.INPUT));
	long b = a*a - HighestPrimeFactor.INPUT;

	while ( Math.sqrt(b) % 1 > 0  ) {
	    a++;
	    b = a*a - HighestPrimeFactor.INPUT;
	}

	return (long) (a + Math.sqrt(b));
    }

    private static boolean isPrime(final long input) {
	for ( int i = 2; i < Math.sqrt(input); i++ ) {
	    if( input / i % 1 > 0 ) {
		return false;
	    }
	}

	return true;
    }

    /**
     *     private static boolean isPrime(final long input) {
	if( input == 2L || input == 3L || input == 5L || input == 7L ) {
	    return true;
	}
	if( input < 11) {
	    return false;
	}

	for ( int i = 2; i <= Math.sqrt(input); i++ ) {
	    if( (input % i) == 0 ) {
		return false;
	    }
	}

	return true;
    }
     */
}