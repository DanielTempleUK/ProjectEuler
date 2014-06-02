package problem77;

import java.io.IOException;
import java.util.List;

import utilities.PrimeGenerator;

/**
 * Solution to problem 77:
 * 
 * 
 * It is possible to write ten as the sum of primes in exactly five different ways:
 * 
 * 7 + 3
 * 5 + 5
 * 5 + 3 + 2
 * 3 + 3 + 2 + 2
 * 2 + 2 + 2 + 2 + 2
 * 
 * What is the first value which can be written as the sum of primes in over five thousand different ways?
 */
public class PrimeSummations {

	public static void main(final String[] args) throws IOException {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	/**
	 * Took a very similar approach to solutions I've seen for problem 31 here.
	 * Instead of coin sizes, I produced a list of primes.
	 * 
	 * I then set an arbitrary limit to calculate the number of ways to make that number.
	 * I then returned the first number where the ways was over 5000.
	 */
	public static long calculateSolution() {
		final int limit = 10000;
		final List<Long> primes = PrimeGenerator.getPrimesUnder(limit);
		final int primesSize = primes.size();

		final long[] ways = new long[limit + 1];
		ways[0] = 1;

		for(int i = 0; i < primesSize; i++) {
			for(long j = primes.get(i); j <= limit; j++) {
				ways[(int)j] += ways[(int)j - (int)(long)primes.get(i)];
			}
		}

		for( int i = 0; i < ways.length; i++ ) {
			if( ways[i] > 5000 ) {
				return i;
			}
		}
		return 0L;
	}
}