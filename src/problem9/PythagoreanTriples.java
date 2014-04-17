package problem9;

import java.util.Set;

import utilities.PythagoreanTriple;
import utilities.PythagoreanTriplesGenerator;

/**
 * Solution to Problem 9:
 * 
 * A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,
 * 		a^2 + b^2 = c^2
 * For example, 32 + 42 = 9 + 16 = 25 = 52.
 * 
 * There exists exactly one Pythagorean triplet for which a + b + c = 1000.
 * Find the product abc.
 */
public class PythagoreanTriples {

	/**
	 * This is a rare problem where I've produced extra solutions given the work I did on later problems.
	 */
	public static void main(final String[] args) {
		solution1();
		solution2();
	}

	private static void solution1() {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	private static void solution2() {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution2());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	/**
	 * This solution uses the a<b<c inequality to choose ranges for each of the values.
	 * A time saving trick is that c can be generated once a and b are known.
	 */
	private static long calculateSolution()  {
		for( int a = 1; a < 333; a++ ) {
			for( int b = a+1; b < 500; b++ ) {

				final int c = 1000 - b - a;
				if( (a*a) + (b*b) == (c*c) ) {
					return (a * b * c);
				}
			}
		}

		return 0L;
	}

	/**
	 * This solution relies on generating all of the primitive triples below total length of 1000.
	 * Then we simply loop through them and find the one where the total length is 1000.
	 * 
	 * As it happens, there is no primitive triple with a total length of 1000, so I had to be a little clever
	 * and check if the 1000 limit divided evenly by the actual total length.
	 * 
	 * Even with having that extra check, this is the quicker of the two solutions.
	 */
	private static long calculateSolution2()  {

		final Set<PythagoreanTriple> generatePrimitivePythagoreanTriplesWithLengthLessThan =
				PythagoreanTriplesGenerator.generatePrimitivePythagoreanTriplesWithLengthLessThan(1000L);

		for (final PythagoreanTriple triple : generatePrimitivePythagoreanTriplesWithLengthLessThan) {
			if( triple.getTotalLength() == 1000 ) {
				return triple.getA() * triple.getB() * triple.getC();
			}

			final double ratio = (double)1000 / (double)triple.getTotalLength();
			if( ratio == (long)ratio ) {
				return triple.getA() * triple.getB() * triple.getC() * (long)ratio * (long)ratio * (long)ratio;
			}
		}

		return 0L;
	}

	/**
	 * This was my first attempt
	 * This method finds pythagorean triples, but sadly, not the one we're looking for.
	 * 
	private static void findTriplesButNotTheOneWeNeed() {
		for( int i = 1; i < 1000; i++ ) {

			final Integer b = (2 * i) + 1;
			final Integer a = (b + 1) * i;
			final Integer c = (int) Math.sqrt((a*a) + (b*b));

			final int sumTotal = a + b + c;

			System.out.println("Total is: " + sumTotal);

			if( sumTotal <= 1000) {
				System.out.println("Product is: " + a*b*c);
			}
			else {
				break;
			}
		}
	}

	 */
}