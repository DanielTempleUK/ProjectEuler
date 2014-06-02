package problem27;

import java.util.List;

import utilities.PrimalityChecker;
import utilities.PrimeGenerator;

/**
 * Solution to problem 27:
 * 
 * Euler discovered the remarkable quadratic formula:
 * 
 * 		n² + n + 41
 * 
 * It turns out that the formula will produce 40 primes for the consecutive values n = 0 to 39.
 * However, when n = 40, 40^2 + 40 + 41 = 40(40 + 1) + 41 is divisible by 41, and
 * certainly when n = 41, 41² + 41 + 41 is clearly divisible by 41.
 * 
 * The incredible formula  n² - 79n + 1601 was discovered, which produces 80 primes for
 * the consecutive values n = 0 to 79. The product of the coefficients, -79 and 1601, is -126479.
 * 
 * Considering quadratics of the form:
 * 
 * 		n² + an + b, where |a| < 1000 and |b| < 1000
 * 
 * 		where |n| is the modulus/absolute value of n
 * 		e.g. |11| = 11 and |-4| = 4
 * Find the product of the coefficients, a and b, for the quadratic expression that produces the
 * maximum number of primes for consecutive values of n, starting with n = 0.
 */
public class QuadraticPrimes {

	public static void main(final String[] args) {
		final long startTime = System.currentTimeMillis();

		System.out.println( "The answer is: " + calculateSolution() );

		final long endTime = System.currentTimeMillis();
		System.out.println("This solution took: " + (endTime - startTime) + " milliseconds");
	}

	private static Long calculateSolution() {
		Integer mostPrimes = 0;
		Long product = 0L;

		//Since the equation needs to produce a prime number with n=0, then b needs to be prime.
		//So get the prime numbers below 1000 to start with.
		final List<Long> primes = PrimeGenerator.getPrimesUnder(1000);

		//Since we're looking for the product (which is a commutative operation) we can use negative numbers
		//for a and positive ones for b and we will still cover the entire range of possible products.
		for( int a = -999; a < 1; a++ ) {
			for( final Long prime : primes ) {

				Integer numberOfPrimes = 0;
				for( int n = 0; n > -1; n++ ) {
					final Long potentialPrime = (long) ((n*n) + (a*n) + prime);

					if( PrimalityChecker.isPrime(potentialPrime) ) {
						numberOfPrimes++;
					}
					else {
						//The first result from the formula that isn't a prime number, we can simply break
						//out of this prime finding loop as the primes need to be for consecutive values of n
						break;
					}
				}

				if( numberOfPrimes > mostPrimes ) {
					mostPrimes = numberOfPrimes;
					product = a * prime;
				}
			}
		}

		return product;
	}
}