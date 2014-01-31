package problem60;

import java.util.ArrayList;
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
 * 
 * TODO: This solution takes around 15 seconds to execute, perhaps there is a better way....
 */
public class PrimePairSets {

	private static final boolean[][] hasProperty = new boolean[10000][10000];
	private static List<Long> allPrimes;
	private static int allPrimesSize;

	public static void main(final String[] args) {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	private static long calculateSolution() {
		//Guessed the primes in the set would be less that 10000.
		allPrimes = PrimeGenerator.getPrimesUnder(10000);
		//Storing this size avoids ahving to recalculate it as we use it for a loop limit quite a lot.
		allPrimesSize = allPrimes.size();

		List<List<Long>> primePairSets = computeCardinality2PrimePairSetsAndStorePrimalities();

		for( int i = 2; i < 5; i++ ) {
			primePairSets = computeNextCardinalPrimePairSets(primePairSets);
		}

		long lowestTotal = 0;
		for (final Long prime : primePairSets.get(0) ) {
			lowestTotal += prime;
		}

		return lowestTotal;
	}

	/**
	 * This method compares every prime in the allPrimes list with all the primes higher than it in the list.
	 * It checks if the pair of primes has the desired property and if they do:
	 *  1. Stores the pair as a list in the returned data structure
	 *  2. Stores the fact the pair has the desired property in the hasProperty array.
	 */
	private static List<List<Long>> computeCardinality2PrimePairSetsAndStorePrimalities() {
		final List<List<Long>> primePairSetsWith2Primes = new ArrayList<List<Long>>();

		for( int a = 0; a < allPrimesSize; a++ ) {
			for( int b = a+1; b < allPrimesSize; b++ ) {
				final long prime1 = allPrimes.get(a);
				final long prime2 = allPrimes.get(b);

				final Long primePlusPrime1 = Long.valueOf("" + prime1 + "" + prime2);
				final Long primePlusPrime2 = Long.valueOf("" + prime2 + "" + prime1);

				if( PrimalityChecker.isPrime(primePlusPrime1) && PrimalityChecker.isPrime(primePlusPrime2) ) {
					hasProperty[(int)prime1][(int)prime2] = true;
					hasProperty[(int)prime2][(int)prime1] = true;

					final List<Long> primePair = new ArrayList<Long>();
					primePair.add(prime1);
					primePair.add(prime2);
					primePairSetsWith2Primes.add(primePair);
				}
			}
		}
		return primePairSetsWith2Primes;
	}

	/**
	 * This assumes the hasProperty array has been populated.
	 * 
	 * Takes the primePairSets and checks each set with each prime and uses the hasProperty map
	 * to check if for each entry in the list combined with each prime, the pair has the desired
	 * property.
	 */
	private static List<List<Long>> computeNextCardinalPrimePairSets(final List<List<Long>> primePairSets) {
		final List<List<Long>> primePairSetsToReturn = new ArrayList<List<Long>>();

		for( int a = 0; a < primePairSets.size(); a++ ) {
			final List<Long> primePairSet = primePairSets.get(a);

			for( int b = 0; b < allPrimesSize; b++ ) {
				final long prime = allPrimes.get(b);

				if( primePairSet.contains(prime) ) {
					continue;
				}

				boolean hasPropertyWithAllPrimesInSet = true;
				for (final Long primeInSet : primePairSet) {
					if( !( hasProperty[primeInSet.intValue()][(int)prime] ) ) {
						hasPropertyWithAllPrimesInSet = false;
						break;
					}
				}
				if( hasPropertyWithAllPrimesInSet ) {
					final List<Long> primePair = new ArrayList<Long>(primePairSet);
					primePair.add(prime);
					primePairSetsToReturn.add(primePair);
				}
			}
		}

		return primePairSetsToReturn;
	}
}