package problem27;

import java.util.ArrayList;
import java.util.List;

import utilities.PrimalityChecker;

/**
 * Solution to problem 27:
 * 
 * Euler discovered the remarkable quadratic formula:
 * 
 * 		n� + n + 41
 * 
 * It turns out that the formula will produce 40 primes for the consecutive values n = 0 to 39.
 * However, when n = 40, 40^2 + 40 + 41 = 40(40 + 1) + 41 is divisible by 41, and
 * certainly when n = 41, 41� + 41 + 41 is clearly divisible by 41.
 * 
 * The incredible formula  n� - 79n + 1601 was discovered, which produces 80 primes for
 * the consecutive values n = 0 to 79. The product of the coefficients, -79 and 1601, is -126479.
 * 
 * Considering quadratics of the form:
 * 
 * 		n� + an + b, where |a| < 1000 and |b| < 1000
 * 
 * 		where |n| is the modulus/absolute value of n
 * 		e.g. |11| = 11 and |-4| = 4
 * Find the product of the coefficients, a and b, for the quadratic expression that produces the
 * maximum number of primes for consecutive values of n, starting with n = 0.
 */
public class QuadraticPrimes {

	public static void main(final String[] args) {
		final long startTime = System.currentTimeMillis();

		calculateAndPrintSolution();

		final long endTime = System.currentTimeMillis();
		System.out.println("This solution took: " + (endTime - startTime) + " milliseconds");
	}

	private static void calculateAndPrintSolution() {
		Integer mostPrimes = 0;
		Integer bestA = 0;
		Integer bestB = 0;
		Integer product = 0;

		//Since the equation needs to produce a prime number with n=0, then b needs to be prime.
		//So get the prime numbers below 1000 to start with.
		final List<Integer> primes = new ArrayList<Integer>();
		for( int i = 1; i < 1000; i++ ) {
			if( PrimalityChecker.isPrime(i) ) {
				primes.add(i);
			}
		}

		//Since we're looking for the product (which is a commutative operation) we can use negative numbers
		//for a and positive ones for b and we will still cover the entire range of possible products.
		for( int a = -999; a < 1; a++ ) {
			for( final Integer prime : primes ) {

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
					bestA = a;
					bestB = prime;
					product = a * prime;
				}
			}
		}

		System.out.println("a = " + bestA);
		System.out.println("b = " + bestB);
		System.out.println("n^2 + an + b  give " + mostPrimes + " prime numbers");
		System.out.println("a * b = " + product);
	}
}