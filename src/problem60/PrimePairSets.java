package problem60;

import java.util.Arrays;
import java.util.List;

import utilities.PrimalityChecker;
import utilities.PrimeGenerator;

/**
 * Solution to Problem 60:
 * 
 * 
 * The primes 3, 7, 109, and 673, are quite remarkable.
 * By taking any two primes and concatenating them in any order the result will always be prime.
 * For example, taking 7 and 109, both 7109 and 1097 are prime. The sum of these four primes, 792,
 * represents the lowest sum for a set of four primes with this property.
 * 
 * Find the lowest sum for a set of five primes for which any two primes concatenate to produce another prime.
 */
public class PrimePairSets {

	public static void main(final String[] args) {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	private static long calculateSolution() {
		final List<Long> allPrimes = PrimeGenerator.getPrimesUnder(1000);
		allPrimes.remove(2L);
		allPrimes.remove(5L);

		final int size = allPrimes.size() - 4;
		for(int a = 0; a < size; a++ ) {
			for(int b = a+1; b < size; b++ ) {
				for(int c = b+1; c < size; c++ ) {
					for(int d = c+1; d < size; d++ ) {
						for(int e = d+1; e < size; e++ ) {

							final long prime1 = allPrimes.get(a);
							final long prime2 = allPrimes.get(b);
							final long prime3 = allPrimes.get(c);
							final long prime4 = allPrimes.get(d);
							final long prime5 = allPrimes.get(e);

							final long[] primes = new long[]{prime1, prime2, prime3, prime4, prime5};
							System.out.println(Arrays.toString(primes));

							boolean allCombinationsArePrime = true;
							for (int i = 0; i < primes.length; i++) {
								for (int j = 0; j < primes.length; j++) {
									if(i==j) {
										continue;
									}
									final long prime = Long.valueOf(""+primes[i]+""+primes[j]);
									System.out.println(prime);
									if( !PrimalityChecker.isPrime(prime) ) {
										allCombinationsArePrime = false;
										break;
									}
								}
							}

							if( allCombinationsArePrime ) {
								System.out.println("The values are: " + prime1 + " & " + prime2 + " & " + prime3 + " & " + prime4);// + " & " + prime5);
								return prime1 + prime2 + prime3 + prime4 + prime5;
							}
						}
					}
				}
			}
		}
		return 0L;
	}

}