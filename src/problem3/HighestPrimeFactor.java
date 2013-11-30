package problem3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class HighestPrimeFactor {

	private static final long INPUT = 600851475143L;

	private static final List<Long> primeFactors = new ArrayList<Long>();

	public static void main(final String... args) {
		findPrimeFactor(INPUT);

		Collections.sort(primeFactors);

		System.out.println(primeFactors.get(primeFactors.size()-1));
	}

	private static void findPrimeFactor(final long input) {
		final long factor = findFactorOf(input);
		if(!isPrime(factor)) {
			findPrimeFactor(input/factor);
			findPrimeFactor(factor);
		}
		else {
			primeFactors.add(factor);
		}

	}

	private static long findFactorOf(final double input) {
		long a = (long) Math.ceil(Math.sqrt(INPUT));
		long b = (a*a) - INPUT;

		while ( ((Math.sqrt(b)) % 1) > 0  ) {
			a++;
			b = (a*a) - INPUT;
		}

		return (long) (a + Math.sqrt(b));
	}

	private static boolean isPrime(final long input) {
		for ( int i = 2; i < Math.sqrt(input); i++ ) {
			if( ((input / i) % 1) > 0 ) {
				return false;
			}
		}

		return true;
	}
}