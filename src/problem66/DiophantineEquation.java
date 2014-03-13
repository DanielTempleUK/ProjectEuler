package problem66;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import utilities.NumberChecker;

/**
 * Solution to problem 66:
 * 
 * Consider quadratic Diophantine equations of the form:
 * 
 * x^2 – D*(y^2) = 1
 * 
 * For example, when D=13, the minimal solution in x is 6492 – 13×1802 = 1.
 * 
 * It can be assumed that there are no solutions in positive integers when D is square.
 * 
 * By finding minimal solutions in x for D = {2, 3, 5, 6, 7}, we obtain the following:
 * 
 * 3^2 - 2x2^2 = 1
 * 2^2 - 3x1^2 = 1
 * 9^2 - 5x4^2 = 1
 * 5^2 - 6x2^2 = 1
 * 8^2 - 7x3^2 = 1
 * 
 * Hence, by considering minimal solutions in x for D <= 7, the largest x is obtained when D=5.
 * 
 * Find the value of D <= 1000 in minimal solutions of x for which the largest value of x is obtained.
 */
public class DiophantineEquation {

	public static void main(final String[] args) {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	private static Long calculateSolution() {

		final List<Integer> dValues = new ArrayList<Integer>();

		//TODO: Should be 1000
		for (int i = 1; i <= 7; i++) {
			if( NumberChecker.isSquareNumber(i) ) {
				continue;
			}
			dValues.add(i);
		}

		System.out.println("Got the D values");

		BigDecimal highestX = BigDecimal.ZERO;
		long solution = 0L;

		for (final Integer d : dValues) {

			final int nearestSquare = findClosestSquare(d);
			final List<Integer> periodDigits = getPeriodDigits(d);

			if( periodDigits.size() <= 2 ) {
				long xSquared = -1;
				long y = 0;
				while ( !NumberChecker.isSquareNumber(xSquared) ) {
					y++;
					final long ySquared = y * y;
					final long ySquaredTimesD = ySquared * d;
					xSquared = ySquaredTimesD + 1;
				}

				final double x = Math.sqrt(xSquared);
				System.out.println((int)x + "^2 - " + d + "*(" + y + "^2) = 1");
				if( x > highestX.intValue() ) {
					highestX = BigDecimal.valueOf(x);
					solution = d;
				}
			}
			else {

				int index=2;

				BigDecimal aMinus2 = BigDecimal.ZERO;
				BigDecimal aMinus1 = BigDecimal.valueOf(nearestSquare);
				BigDecimal aN = BigDecimal.valueOf(periodDigits.get(index)).multiply(aMinus1).add(aMinus2);

				BigDecimal bMinus2 = BigDecimal.ONE;
				BigDecimal bMinus1 = BigDecimal.valueOf(nearestSquare);
				BigDecimal bN = BigDecimal.valueOf(periodDigits.get(index)).multiply(bMinus1).add(bMinus2);

				BigDecimal subtract = aN.pow(2).subtract( bN.pow(2).multiply(BigDecimal.valueOf(d)) );
				index++;

				while( ( subtract.longValue() != 1L ) && ( index < periodDigits.size() ) ) {

					aMinus2 = aMinus1;
					aMinus1 = aN;
					aN = BigDecimal.valueOf(periodDigits.get(index)).multiply(aMinus1).add(aMinus2);

					bMinus2 = bMinus1;
					bMinus1 = bN;
					bN = BigDecimal.valueOf(periodDigits.get(index)).multiply(bMinus1).add(bMinus2);

					subtract = aN.pow(2).subtract( bN.pow(2).multiply(BigDecimal.valueOf(d)) );
					index++;
				}
				System.out.println(bN + "^2 - " + d + "*(" + aN + "^2) = 1");
				if( bN.compareTo(highestX) > 0 ) {
					highestX = bN;
					solution = d;
				}
			}


		}

		return solution;

		//		final Map<Long, Integer> map = new HashMap<Long, Integer>();
		//
		//		for (final Integer d : dValues) {
		//			long xSquared = -1;
		//			long y = 0;
		//			while ( !NumberChecker.isSquareNumber(xSquared) ) {
		//				y++;
		//				final long ySquared = y * y;
		//				final long ySquaredTimesD = ySquared * d;
		//				xSquared = ySquaredTimesD + 1;
		//			}
		//
		//			final double x = Math.sqrt(xSquared);
		//			System.out.println("D=" + d + ", x=" + (long)x + ", y=" + y);
		//			map.put((long)x, d);
		//		}
		//
		//		final List<Long> xValues = new ArrayList<Long>(map.keySet());
		//		Collections.sort(xValues);
		//		Collections.reverse(xValues);
		//		return map.get(xValues.get(0));
	}


	private static List<Integer> getPeriodDigits(final int n) {
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

		return periodDigits;

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