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
		//First derive the list of D values that are not square
		final List<Integer> dValues = new ArrayList<Integer>();

		for (int i = 1; i <= 1000; i++) {
			if( NumberChecker.isSquareNumber(i) ) {
				continue;
			}
			dValues.add(i);
		}

		//Define some values to hold the current best known solution
		BigDecimal highestX = BigDecimal.ZERO;
		long solution = 0L;

		/*
		 * Algorithm is:
		 * 		Find the period digits
		 * 		Use the convergents of root(D) to find the smallest x that solves Pell's Equation
		 * 		Check the x value against the current best answer.
		 */
		for (final Integer d : dValues) {
			final List<Integer> periodDigits = getPeriodDigits(d);

			final BigDecimal bN = useConvergentsOfRootDToFindMinimumX(d, periodDigits);
			if( bN.compareTo(highestX) > 0 ) {
				highestX = bN;
				solution = d;
			}
		}

		return solution;
	}

	/**
	 * I spent ages looking at the convergent series of continued fractions.
	 * I knew from problem 64 and problem 65 that I could generate convergents given the period digits.
	 * Some reading told me the numerator and denominator of the convergents would be solutions to Pell's
	 * equations.
	 * But this website gave me the initial values to use:
	 * 		http://modular.math.washington.edu/edu/2007/spring/ent/ent-html/node60.html
	 * 
	 * Had to use BigDecimals as the x and y values quickly become greater than the maximum long value.
	 */
	private static BigDecimal useConvergentsOfRootDToFindMinimumX(final Integer d, final List<Integer> periodDigits) {
		final int nearestSquare = findClosestSquare(d);

		BigDecimal numerator1 = BigDecimal.ZERO;
		BigDecimal numerator2 = BigDecimal.ONE;
		BigDecimal numerator3 = BigDecimal.valueOf(nearestSquare);

		BigDecimal denominator1 = BigDecimal.ONE;
		BigDecimal denominator2 = BigDecimal.ZERO;
		BigDecimal denominator3 = BigDecimal.ONE;

		BigDecimal subtract = numerator3.pow(2).subtract( denominator3.pow(2).multiply(BigDecimal.valueOf(d)) );
		int index = 0;

		while( ( subtract.longValue() != 1L ) ) {
			numerator1 = new BigDecimal(numerator2.toString());
			numerator2 = new BigDecimal(numerator3.toString());
			numerator3 = BigDecimal.valueOf(periodDigits.get(index)).multiply(numerator2).add( numerator1 );

			denominator1 = new BigDecimal(denominator2.toString());
			denominator2 = new BigDecimal(denominator3.toString());
			denominator3 = BigDecimal.valueOf(periodDigits.get(index)).multiply(denominator2).add( denominator1 );

			subtract = numerator3.pow(2).subtract( denominator3.pow(2).multiply(BigDecimal.valueOf(d)) );
			index = (index + 1) % periodDigits.size(); //Just make sure to mod the index, so we avoid out of bounds errors
		}

		return numerator3;
	}

	/**
	 * Reused this function from Problem 64's solution to acquire the set of period digits
	 */
	private static List<Integer> getPeriodDigits(final int n) {
		final int nearestSquare = findClosestSquare(n);
		int nonSurd = nearestSquare;
		int den = 1;

		//Initialise some collections to help the termination check and the period length check
		final List<Integer> periodDigits = new ArrayList<Integer>();
		final Set<String> combinationsSeen = new HashSet<String>();

		do {
			den = (n - (nonSurd*nonSurd)) / den;

			final int periodDigit = (nearestSquare + nonSurd) / den;
			periodDigits.add(periodDigit);

			nonSurd = Math.abs(nonSurd - (periodDigit * den));
		}
		while ( combinationsSeen.add(den + "," + nonSurd) );

		//I should really rework the loop conditions so I don't need to remove the final period digit.
		periodDigits.remove(periodDigits.size()-1);
		return periodDigits;
	}

	/**
	 * Reused this function from Problem 64's solution to find the nearest square number to a given number.
	 */
	private static int findClosestSquare(final int n) {
		for( int i = 0; true; i++ ) {
			if( (i*i) > n ) {
				return i-1;
			}
		}
	}
}