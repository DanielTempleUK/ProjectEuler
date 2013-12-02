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

		for( long i = 0; i < 1000; i++ ) {
			for( long j = 0; j < 1000; j++ ) {
				for( long k = 0; k < 1000; k++ ) {

					if( i < j && j < k ) {

						final long total = i + j + k;

						if( total == 1000L ) {
							final long aSquaredPlusBSquared = (i*i) + (j*j);
							final long cSquared = (k*k);

							if( aSquaredPlusBSquared == cSquared ) {
								System.out.println("A is: " + (i));
								System.out.println("B is: " + (j));
								System.out.println("C is: " + (k));
								System.out.println("Product is: " + (i * j * k));
								break;
							}
						}
					}
				}
			}
		}
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