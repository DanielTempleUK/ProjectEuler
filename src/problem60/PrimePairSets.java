package problem60;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

	private static final long startTime = System.currentTimeMillis();

	public static void main(final String[] args) {
		System.out.println("The answer is: " + calculateSolution2());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	private static long calculateSolution() {
		final List<Long> allPrimes = PrimeGenerator.getPrimesUnder(10000);
		allPrimes.remove(2L); //Even numbers aren't prime.
		allPrimes.remove(5L); //Numbers ending with a 5 aren't prime.

		System.out.println("GENERATED PRIMES in: " + (System.currentTimeMillis() - startTime) + " milliseconds");

		final Map<Long, Set<Long>> map = new HashMap<Long, Set<Long>>();
		final int size = allPrimes.size() ;
		for(int a = 0; a < size; a++ ) {
			final long prime1 = allPrimes.get(a);
			final HashSet<Long> primePairs = new HashSet<Long>();

			for(int b = 0; b < size; b++ ) {
				if (a == b) {
					continue;
				}
				final long prime2 = allPrimes.get(b);

				final Long primePlusPrime1 = Long.valueOf("" + prime1 + "" + prime2);
				final Long primePlusPrime2 = Long.valueOf("" + prime2 + "" + prime1);

				if( PrimalityChecker.isPrime(primePlusPrime1) && PrimalityChecker.isPrime(primePlusPrime2) ) {
					primePairs.add(prime2);
				}
			}
			if( primePairs.size() > 4 ) {
				map.put(prime1, primePairs);
			}
		}

		System.out.println("PAIR SETS GENERATED in: " + (System.currentTimeMillis() - startTime) + " milliseconds");

		final Set<Set<Long>> primePairSets = new HashSet<Set<Long>>();
		for (final Long key : map.keySet()) {

			final Set<Long> set1 = new HashSet<Long>(map.get(key));
			set1.add(key);

			final Set<Long> intersection = new HashSet<Long>(set1);

			for (final Long prime : set1) {
				final Set<Long> set2 = new HashSet<Long>(map.get(prime));
				set2.add(prime);
				intersection.retainAll(set2);
			}

			if( intersection.size() == 5 ) {
				primePairSets.add(intersection);
			}

		}










		System.out.println("PAIR SETS WTIH 5 PRIMES FOUND in: " + (System.currentTimeMillis() - startTime) + " milliseconds");

		long lowestTotal = 1000000000L; //Arbitrarily Large

		for (final Set<Long> primes : primePairSets) {
			long currentTotal = 0L;
			for (final Long prime : primes) {
				currentTotal += prime;
			}

			if(currentTotal < lowestTotal) {
				System.out.println(primes.toString());
				lowestTotal = currentTotal;
			}
		}

		return lowestTotal;
	}

	private static long calculateSolution2() {
		final List<Long> allPrimes = PrimeGenerator.getPrimesUnder(10000);
		allPrimes.remove(2L); //Even numbers aren't prime.
		allPrimes.remove(5L); //Numbers ending with a 5 aren't prime.
		final int allPrimesSize = allPrimes.size();

		final boolean[][] hasProperty = new boolean[10000][10000];

		System.out.println("GENERATED PRIMES in: " + (System.currentTimeMillis() - startTime) + " milliseconds");

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

		System.out.println("GENERATED PRIME PAIR SETS WITH CARDINALITY 2 in: " + (System.currentTimeMillis() - startTime) + " milliseconds");

		final List<List<Long>> primePairSetsWith3Primes = new ArrayList<List<Long>>();

		for( int a = 0; a < primePairSetsWith2Primes.size(); a++ ) {
			final List<Long> primePairSet = primePairSetsWith2Primes.get(a);

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
					//					System.out.println(primePair.toString());
					primePairSetsWith3Primes.add(primePair);
				}
			}
		}

		System.out.println("GENERATED PRIME PAIR SETS WITH CARDINALITY 3 in: " + (System.currentTimeMillis() - startTime) + " milliseconds");

		final List<List<Long>> primePairSetsWith4Primes = new ArrayList<List<Long>>();

		for( int a = 0; a < primePairSetsWith3Primes.size(); a++ ) {
			final List<Long> primePairSet = primePairSetsWith3Primes.get(a);

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
					//					System.out.println(primePair.toString());
					primePairSetsWith4Primes.add(primePair);
				}
			}
		}

		System.out.println("GENERATED PRIME PAIR SETS WITH CARDINALITY 4 in: " + (System.currentTimeMillis() - startTime) + " milliseconds");

		final List<List<Long>> primePairSetsWith5Primes = new ArrayList<List<Long>>();

		for( int a = 0; a < primePairSetsWith4Primes.size(); a++ ) {
			final List<Long> primePairSet = primePairSetsWith4Primes.get(a);

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
					//					System.out.println(primePair.toString());
					primePairSetsWith5Primes.add(primePair);
				}
			}
		}

		System.out.println("GENERATED PRIME PAIR SETS WITH CARDINALITY 5 in: " + (System.currentTimeMillis() - startTime) + " milliseconds");

		long lowestTotal = 1000000000L; //Arbitrarily Large

		for (final List<Long> primes : primePairSetsWith5Primes) {
			long currentTotal = 0L;
			for (final Long prime : primes) {
				currentTotal += prime;
			}

			if(currentTotal < lowestTotal) {
				System.out.println(primes.toString());
				lowestTotal = currentTotal;
			}
		}

		return lowestTotal;
	}
}