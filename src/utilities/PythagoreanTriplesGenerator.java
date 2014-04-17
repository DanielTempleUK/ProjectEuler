package utilities;

import java.util.HashSet;
import java.util.Set;


public class PythagoreanTriplesGenerator {


	/**
	 * This method generates all of the primitive Pythagorean triples that have total length less than the
	 * provided limit. The fact each one is primitive is quite important as it means we don't need to worry
	 * about multiple triples, like (6,8,10)
	 * 
	 * This uses the matrices and linear transformations method to generate all of the triples given the initial
	 * triple of: 3,4,5
	 * 
	 * Taken from Wikipedia, given a triple, you can generate more triples using the following matrices:
	 * 
	 * 		|-1 2 2| |a|   |a1|      |1 2 2| |a|   |a2|     |1 -2 2| |a|   |a3|
	 * 		|-2 1 2| |b| = |b1|  ,   |2 1 2| |b| = |b2|  ,  |2 -1 2| |b| = |b3|
	 * 		|-2 2 3| |c|   |c1|      |2 2 3| |c|   |c2|     |2 -2 3| |c|   |c3|
	 */
	public static Set<PythagoreanTriple> generatePrimitivePythagoreanTriplesWithLengthLessThan(final long lengthLimit) {
		final Set<PythagoreanTriple> triples = new HashSet<PythagoreanTriple>();
		triples.add(new PythagoreanTriple(3, 4, 5));

		Set<PythagoreanTriple> tempTriples = new HashSet<PythagoreanTriple>(triples);

		boolean loopCondition = true;
		while( loopCondition ) {
			boolean tempLoopCondition = false;
			final Set<PythagoreanTriple> newTriples = new HashSet<PythagoreanTriple>();

			for (final PythagoreanTriple pythagoreanTriple : tempTriples) {
				final long a = pythagoreanTriple.getA();
				final long b = pythagoreanTriple.getB();
				final long c = pythagoreanTriple.getC();

				final PythagoreanTriple triple1 =
						new PythagoreanTriple(
								( (-1*a) + (2*b) + (2*c) ) ,
								( (-2*a) + (1*b) + (2*c) ) ,
								( (-2*a) + (2*b) + (3*c) ) );
				if( triple1.totalLengthLessThanOrEqualTo(lengthLimit) ) {
					newTriples.add( triple1 );
					tempLoopCondition = true;
				}

				final PythagoreanTriple triple2 =
						new PythagoreanTriple(
								( (1*a) + (2*b) + (2*c) ) ,
								( (2*a) + (1*b) + (2*c) ) ,
								( (2*a) + (2*b) + (3*c) ) );
				if( triple2.totalLengthLessThanOrEqualTo(lengthLimit) ) {
					newTriples.add( triple2 );
					tempLoopCondition = true;
				}

				final PythagoreanTriple triple3 =
						new PythagoreanTriple(
								( (1*a) + (-2*b) + (2*c) ) ,
								( (2*a) + (-1*b) + (2*c) ) ,
								( (2*a) + (-2*b) + (3*c) ) );
				if( triple3.totalLengthLessThanOrEqualTo(lengthLimit) ) {
					newTriples.add( triple3 );
					tempLoopCondition = true;
				}

				loopCondition = tempLoopCondition;
			}

			triples.addAll(newTriples);
			tempTriples = new HashSet<PythagoreanTriple>(newTriples);
		}
		return triples;
	}


}