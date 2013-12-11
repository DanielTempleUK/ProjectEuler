package problem27;

import utilities.PrimalityChecker;


/**
 * Solution to problem 27:
 * 
 * Euler discovered the remarkable quadratic formula:
 * 
 * 		n² + n + 41
 * 
 * It turns out that the formula will produce 40 primes for the consecutive values n = 0 to 39.
 * However, when n = 40, 40^2 + 40 + 41 = 40(40 + 1) + 41 is divisible by 41, and certainly when n = 41, 41² + 41 + 41 is clearly divisible by 41.
 * 
 * The incredible formula  n² - 79n + 1601 was discovered, which produces 80 primes for the consecutive values n = 0 to 79.
 * The product of the coefficients, -79 and 1601, is -126479.
 * 
 * Considering quadratics of the form:
 * 
 * 		n² + an + b, where |a| < 1000 and |b| < 1000
 * 
 * 		where |n| is the modulus/absolute value of n
 * 		e.g. |11| = 11 and |-4| = 4
 * Find the product of the coefficients, a and b, for the quadratic expression that produces the maximum number of primes for consecutive values of n, starting with n = 0.
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

		for( int a = -1000; a < 1000; a++ ) {
			for( int b = -1000; b < 1000; b++ ) {

				Integer numberOfPrimes = 0;
				for( int n = 0; n > -1; n++ ) {
					final Long potentialPrime = (long) ((n*n) + (a*n) + b);

					if( PrimalityChecker.isPrime(potentialPrime) ) {
						numberOfPrimes++;
					}
					else {
						break;
					}
				}

				if( numberOfPrimes > mostPrimes ) {
					mostPrimes = numberOfPrimes;
					bestA = a;
					bestB = b;
					product = a * b;
				}
			}
		}

		System.out.println("a = " + bestA);
		System.out.println("b = " + bestB);
		System.out.println("n^2 + an + b  give " + mostPrimes + " prime numbers");
		System.out.println("a * b = " + product);
	}
}