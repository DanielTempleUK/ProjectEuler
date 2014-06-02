package problem75;

import java.util.Set;

import utilities.PythagoreanTriple;
import utilities.PythagoreanTriplesGenerator;

/**
 * Solution to problem 75:
 * 
 * 
 * It turns out that 12 cm is the smallest length of wire that can be bent to form an integer sided right angle
 * triangle in exactly one way, but there are many more examples.
 * 
 *      12 cm: (3,4,5)
 *      24 cm: (6,8,10)
 *      30 cm: (5,12,13)
 *      36 cm: (9,12,15)
 *      40 cm: (8,15,17)
 *      48 cm: (12,16,20)
 * 
 * In contrast, some lengths of wire, like 20 cm, cannot be bent to form an integer sided right angle triangle,
 * and other lengths allow more than one solution to be found; for example, using 120 cm it is possible to form
 * exactly three different integer sided right angle triangles.
 * 
 *       120 cm: (30,40,50), (20,48,52), (24,45,51)
 * 
 * Given that L is the length of the wire, for how many values of L <= 1,500,000 can exactly one
 * integer sided right angle triangle be formed?
 */
public class SingleIntegerRightTriangles {

	public static void main(final String[] args) {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	private static final int TOTAL_LENGTH_LIMIT = 1500000;

	/**
	 * Brute force, but flipped on it's head a little.
	 * 
	 * If we can generate all of the primitive pythagorean triples whose length is less than 1500000,
	 * then we can simply get the total length of the triple and increment a counter for that length.
	 * 
	 * We can also then multiply the length by and incrementing counter so we can count the lengths
	 * of non-primitive triples too.
	 * 
	 * This is exactly what this solution does, and quite quickly.
	 */
	private static long calculateSolution() {
		final Set<PythagoreanTriple> triples =
				PythagoreanTriplesGenerator.generatePrimitivePythagoreanTriplesWithLengthLessThan( TOTAL_LENGTH_LIMIT );

		//Use this array to keep counts of the lengths that can be a pythagorean triple.
		final int[] lengths = new int[TOTAL_LENGTH_LIMIT];

		//For each triple, increment the counter in the lengths array, where the triple length and any multiples
		//of the triple length falls within the overall length limit
		for (final PythagoreanTriple triple : triples) {
			final long length = triple.getTotalLength();

			long tempLength = length;
			int i = 2;
			while( tempLength < TOTAL_LENGTH_LIMIT ) {
				lengths[ (int)tempLength ] = lengths[ (int)tempLength ] + 1;
				tempLength = length*i;
				i++;
			}
		}

		//Return a count of all the lengths with a triple count of 1.
		long count = 0L;
		for (int i = 0; i < lengths.length; i++) {
			if( lengths[i] == 1 ) {
				count++;
			}
		}

		return count;
	}
}