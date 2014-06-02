package problem7;

import java.io.IOException;

import utilities.PrimalityChecker;

/**
 * Solution to problem 7:
 * 
 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.
 * What is the 10 001st prime number?
 */
public class TenThousandAndFirstPrime {

	public static void main(final String[] args) throws IOException {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	private static long calculateSolution() {
		int primes = 168;

		for( long i = 1001; i > 0; i+=2 ) {
			if(PrimalityChecker.isPrime(i) ) {
				primes++;
				if( primes > 10000 ) {
					return i;
				}
			}
		}

		return 0L;
	}
}