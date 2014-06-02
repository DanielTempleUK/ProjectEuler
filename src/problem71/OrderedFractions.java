package problem71;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Solution to problem 71:
 * 
 * 
 * Consider the fraction, n/d, where n and d are positive integers.
 * If n<d and HCF(n,d)=1, it is called a reduced proper fraction.
 * 
 * If we list the set of reduced proper fractions for d <= 8 in ascending order of size, we get:
 * 
 * 1/8, 1/7, 1/6, 1/5, 1/4, 2/7, 1/3, 3/8, 2/5, 3/7, 1/2, 4/7, 3/5, 5/8, 2/3, 5/7, 3/4, 4/5, 5/6, 6/7, 7/8
 * 
 * It can be seen that 2/5 is the fraction immediately to the left of 3/7.
 * 
 * By listing the set of reduced proper fractions for d <= 1,000,000 in ascending order of size,
 * find the numerator of the fraction immediately to the left of 3/7.
 */
public class OrderedFractions {

	public static void main(final String[] args) throws IOException {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	/**
	 * Using the inequality rule:
	 * 		a/b < p/q  <-->  aq < bp
	 * 
	 * We know that we need to be less than 3/7 to be to the left of it when we're ordered, so make p=3 and q=7
	 * So if we have a denominator, we can calculate the numerator as such:
	 * 		((3*b) - 1) / 7
	 * 
	 * The "-1" is thrown in because we are dealing with integers, so we know that the minimum difference is 1.
	 * We also know that we want an integer out as the numerator, so we can simply throw a floor function in (implicit cast to long).
	 * 
	 * Then we sort the fractions using the same inequality rules and then take the second to last one, the last one will be 3/7
	 */
	private static long calculateSolution() {
		final List<Fraction> fractions = new ArrayList<Fraction>();

		for( long denominator = 3; denominator <= 1000000; denominator++ ) {
			final long numerator = ((3*denominator) - 1)/7;
			fractions.add(new Fraction(numerator, denominator));
		}

		Collections.sort(fractions);

		return fractions.get(fractions.size()-1).numerator;
	}

	private static class Fraction implements Comparable<Fraction>{
		private final long numerator;
		private final long denominator;

		public Fraction(final Long numerator, final long denominator) {
			this.numerator = numerator;
			this.denominator = denominator;
		}

		/**
		 * This compare method is based on the following:
		 * 		a/b < p/q  <-->  aq < bp
		 */
		@Override
		public int compareTo(final Fraction o) {
			final long approx = this.numerator * o.denominator;
			final long oApprox = o.numerator * this.denominator;

			if( approx > oApprox ) {
				return 1;
			}
			else if( approx < oApprox ) {
				return -1;
			}
			return 0;
		}
	}
}