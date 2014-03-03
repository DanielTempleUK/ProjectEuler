package problem64;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import utilities.NumberChecker;

/**
 * Solution to problem 64:
 * 
 * Problem definition is quite long, so see this link: <a href="http://projecteuler.net/problem=64">Problem 64</a>
 */
public class OddPeriodSquareRoots {

	public static void main(final String[] args) {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	/**
	 * My repeated fractions look like this:
	 * 
	 * 		periodDigit + ( ( root(n) +- nonSurd ) / den )
	 * 
	 * I did some reading on how to calculate repeated fractions and then looked at the root(23) example.
	 * From that I was able to work out some number pattern formulas that would calculate the next periodDigit
	 * based upon the next denominator and the current nonSurd.
	 * 
	 * From the root(23) example, I was able to derive the following trace table:
	 * 
	 * 	iter	den		nonSurd		periodDigit
	 * 	 a1		 7		   3			1
	 * 	 a2		 2		   3			3
	 * 	 a3		 7		   4			1
	 * 	 a4		 1		   4			8
	 * 	 a5		 7		   3			1
	 * 
	 * The periodDigits could repeat, so I check existence of the "den,nonSurd" string.
	 */
	private static long calculateSolution() {
		int count = 4;

		for( int n = 14; n <= 10000; n++ ) {
			//We want to ignore square numbers
			if( NumberChecker.isSquareNumber(n) ) {
				continue;
			}

			//First find the nearest square number, this will give us a starting point for our fractions
			final int nearestSquare = findClosestSquare(n);

			//The first non surd in the numerator will be the period number
			int nonSurd = nearestSquare;

			//Set the first denominator to be 1
			int den = 1;

			//Initialise some collections to help the termination check and the period length check
			final List<Integer> periodDigits = new ArrayList<Integer>();
			final Set<String> combinationsSeen = new HashSet<String>();

			do {
				//The denominator is calculated from the conjugate cancelling the surd on the denominator
				den = (n - (nonSurd*nonSurd)) / den;

				//The period digit is then the number of denominators we can cancel from the integer part of the numerator (nearestSquare + nonSurd)
				final int periodDigit = (nearestSquare + nonSurd) / den;
				periodDigits.add(periodDigit);

				//The nonSurd is then the period digit cancelled from the fraction. Keep this absolute to make the maths a bit easier. :)
				nonSurd = Math.abs(nonSurd - (periodDigit * den));
			}
			while ( combinationsSeen.add(den + "," + nonSurd) );

			//Increase count if the period size is odd. Remove 1 from period size as extra period digit is added from next loop.
			if( (periodDigits.size()-1) % 2 == 1 ) {
				count++;
			}
		}

		return count;
	}

	private static int findClosestSquare(final int n) {
		int closestSquare = 0;

		for( int i = 0; true; i++ ) {
			if( (i*i) < n ) {
				closestSquare = i;
				continue;
			}
			break;
		}

		return closestSquare;
	}
}