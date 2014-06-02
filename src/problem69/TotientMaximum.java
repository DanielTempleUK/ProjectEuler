package problem69;

import java.util.List;

import utilities.PrimeGenerator;

/**
 * Solution to problem 69:
 * 
 * Problem definition is quite long, so see this link: <a href="http://projecteuler.net/problem=69">Problem 69</a>
 */
public class TotientMaximum {

	private static List<Long> primesInRange;

	public static void main(final String[] args) {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution2());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	/**
	 * This solution relies on the knowledge that the ratio is maximised when the unique prime factors are minimised.
	 * So simply multiply the smallest primes together until the largest value less than a million is achieved.
	 * 
	 * This feels like more of a reverse engineered solution as I don't quite understand the minimization and maximization logic.
	 */
	private static long calculateSolution2() {
		primesInRange = PrimeGenerator.getPrimesBetween(2, 200);

		long solution = 1L;
		int i = 0;
		while( solution*primesInRange.get(i) < 1000000 ) {
			solution *= primesInRange.get(i);
			i++;
		}

		return solution;
	}




	/**
	 * My initial solution:
	 * 
	 * NOTE 1: The PDF provided has explanations of how to find the number of relative primes for a given number.
	 * NOTE 2: This takes ~58 seconds, only just within the time limit, looks like we can do this one quicker.
	 * 

	private static long calculateSolution() {
		final List<Long> numbers = new ArrayList<Long>();

		/* Ignore the solutions we're told about in the question (n<=10)
	 * 
	 * Then only consider even numbers.
	 * We're looking for numbers with the fewest number of relative primes. Odd numbers are on average going to have more
	 * relative primes because they can have relative primes with a factor of 2. Even Numbers Cannot have relative primes
	 * with a factor of 2, so there are likely to be fewer relative primes for even numbers.

		for (long i = 12; i <= 1000000; i+=2) {
			numbers.add(i);
		}

		primesInRange = PrimeGenerator.getPrimesUnder(1000000);

		//Best known solution from question is 6 with a ratio of 3.
		long solution = 6L;
		double currentHighestRatio = 3.0d;

		for( final Long number : numbers ) {

			final Set<Long> primeFactors = new HashSet<Long>( findPrimeFactors(number) );
			long numberOfRelativePrimes = number;

			for (final Long factor : primeFactors) {
				numberOfRelativePrimes = (numberOfRelativePrimes / factor) * (factor - 1);
			}

			final double ratio = (double)number / (double)numberOfRelativePrimes;
			if( ratio > currentHighestRatio ) {
				System.out.print("Processing: " + number);
				System.out.println(" has " + (numberOfRelativePrimes) + " relative primes.");
				System.out.println("NEW RATIO!!! " + ratio);
				solution = number;
				currentHighestRatio = ratio;
			}
		}

		return solution;
	}

	private static Set<Long> findPrimeFactors(final long input) {
		final Set<Long> primeFactors = new HashSet<Long>();

		double d = input;
		for( final Long prime : primesInRange ) {
			if( d == 1.0 ) {
				break;
			}

			while( ( (d/prime) % 1 ) == 0 ) {
				primeFactors.add(prime);
				d /= prime;
			}
		}

		return primeFactors;
	}

	 */
}