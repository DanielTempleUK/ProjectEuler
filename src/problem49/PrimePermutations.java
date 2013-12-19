package problem49;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import utilities.PrimalityChecker;

/**
 * Solution to problem 49:
 * 
 * 
 * The arithmetic sequence, 1487, 4817, 8147, in which each of the terms increases by 3330, is unusual in two ways:
 * 		(i) each of the three terms are prime, and,
 * 		(ii) each of the 4-digit numbers are permutations of one another.
 * There are no arithmetic sequences made up of three 1-, 2-, or 3-digit primes, exhibiting this property,
 * but there is one other 4-digit increasing sequence.
 * What 12-digit number do you form by concatenating the three terms in this sequence?
 */
public class PrimePermutations {

	public static void main(final String[] args) throws IOException {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	private static long calculateSolution() {

		//Get the primes between 1001 and 9997 since we know the numbers are 4 digits
		final List<Long> primes = getPrimes();

		for (int i = 0; i < primes.size(); i++) {
			for (int j = i+1; j < primes.size(); j++) {

				final Long prime1 = primes.get(i);
				final Long prime2 = primes.get(j);

				//Ignore the case w eknow about
				if(1487 == prime1) {
					continue;
				}

				//Make sure the primes have the sequence property
				final long prime3 = prime2 + (prime2 - prime1);
				if( primes.contains( prime3 ) ) {

					final char[] prime1Chars = String.valueOf(prime1).toCharArray();
					final char[] prime2Chars = String.valueOf(prime2).toCharArray();
					final char[] prime3Chars = String.valueOf(prime3).toCharArray();
					Arrays.sort(prime1Chars);
					Arrays.sort(prime2Chars);
					Arrays.sort(prime3Chars);

					//Make sure they are all permutations of each other. (Check they order to the same thing)
					if(Arrays.equals(prime1Chars, prime2Chars) && Arrays.equals(prime1Chars, prime3Chars)) {
						return Long.valueOf("" + prime1 + "" + prime2 + "" + prime3);
					}
				}
			}
		}

		return 0L;
	}

	private static List<Long> getPrimes() {
		final List<Long> primes = new ArrayList<Long>();
		for (long i = 1001; i <= 9997; i++) {
			if( PrimalityChecker.isPrime(i) ) {
				primes.add(i);
			}
		}
		return primes;
	}
}