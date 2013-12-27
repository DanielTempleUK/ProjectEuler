package problem9;

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

	public static void main(final String[] args) {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

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