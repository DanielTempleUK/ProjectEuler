package problem33;

import java.util.HashSet;
import java.util.Set;

/**
 * Solution to Problem 32:
 * 
 * 
 * The fraction 49/98 is a curious fraction, as an inexperienced mathematician in attempting to simplify it may incorrectly
 * believe that 49/98 = 4/8, which is correct, is obtained by cancelling the 9s.
 * 
 * We shall consider fractions like, 30/50 = 3/5, to be trivial examples.
 * 
 * There are exactly four non-trivial examples of this type of fraction, less than one in value,
 * and containing two digits in the numerator and denominator.
 * 
 * If the product of these four fractions is given in its lowest common terms, find the value of the denominator.
 */
public class DigitCancellingFunctions {

	public static void main(final String[] args) {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	private static long calculateSolution() {
		final Set<Integer> numerators = new HashSet<Integer>();
		final Set<Integer> denominators = new HashSet<Integer>();


		for( int numerator = 11; numerator <= 99; numerator++ ) {
			for( int denominator = numerator+1; denominator <= 99; denominator++ ) {

				final int sharedDigit = getSharedDigit(numerator, denominator);
				if( sharedDigit > 0 ) {

					final Set<Integer> sharedFactors = findSharedFactorsOf(numerator, denominator);

					for (final Integer sharedFactor : sharedFactors) {
						final int dividedA = numerator / sharedFactor;
						final int dividedB = denominator / sharedFactor;

						final String requiredNumerator = (""+numerator).replace(""+sharedDigit, "");
						final String requiredDenominator = (""+denominator).replace(""+sharedDigit, "");

						for( int i = 1; i < 10; i++ ) {

							if( requiredNumerator.equals(""+(dividedA*i)) && requiredDenominator.equals(""+(dividedB*i)) ) {
								System.out.println("" + numerator + " / " + denominator +" = " + dividedA*i + " / " + dividedB*i);
								numerators.add(numerator);
								denominators.add(denominator);
							}

						}
					}
				}
			}
		}

		long numerator = 1L;
		long denominator = 1L;

		for (final Integer integer : numerators) {
			numerator *= integer;
		}

		for (final Integer integer : denominators) {
			denominator *= integer;
		}


		Set<Integer> sharedFactors = findSharedFactorsOf(numerator, denominator);
		while( !sharedFactors.isEmpty() ) {
			final int factor = sharedFactors.toArray(new Integer[]{})[0];
			numerator /= factor;
			denominator /= factor;

			sharedFactors = findSharedFactorsOf(numerator, denominator);
		}

		return denominator;
	}

	private static Set<Integer> findSharedFactorsOf(final long a, final long b) {
		final Set<Integer> factorsForA = getFactorsFor(a);
		final Set<Integer> factorsForB = getFactorsFor(b);
		final Set<Integer> sharedFactors = new HashSet<Integer>();

		for (final Integer integer : factorsForA) {
			if( factorsForB.contains(integer) ) {
				sharedFactors.add(integer);
			}
		}
		return sharedFactors;
	}

	private static int getSharedDigit(final int a, final int b) {
		final String[] digitsInA = ("" + a).split("");
		final String[] digitsInB = ("" + b).split("");

		for( int i = 0; i < digitsInA.length; i++ ) {
			for( int j = 0; j < digitsInB.length; j++ ) {
				if( !digitsInA[i].equals("") && digitsInA[i].equals(digitsInB[j]) ) {
					return Integer.valueOf(digitsInA[i]);
				}
			}
		}

		return 0;
	}

	private static Set<Integer> getFactorsFor(final long number) {
		final Set<Integer> factors = new HashSet<Integer>();
		if( number == 0 || number == 1 ) {
			return factors;
		}
		factors.add(Integer.valueOf("" + number));

		final double numberRoot = Math.sqrt(number);
		if(numberRoot % 1 == 0) {
			factors.add( (int) numberRoot );
		}

		for( int i = 2; i < numberRoot; i++ ) {
			if(number % i == 0) {
				factors.add(i);
				factors.add( Integer.valueOf("" + (number / i) ) );
			}
		}

		return factors;
	}
}