package problem39;

/**
 * Solution to problem 39:
 * 
 * 
 * If p is the perimeter of a right angle triangle with integral length
 * sides, {a,b,c}, there are exactly three solutions for p = 120.
 * 
 * 			{20,48,52}, {24,45,51}, {30,40,50}
 * 
 * For which value of p <= 1000, is the number of solutions maximised?
 */
public class IntegerRightTriangles {

	public static void main(final String[] args) {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	private static long calculateSolution() {

		final int maxP = 1000;
		int mostSolutions = 0;
		int pWithMostSolutions = 0;

		for(int p = 1; p <= maxP; p++ ) {
			int solutions = 0;

			for( int a = 1; a <= p/2; a++ ) {
				for( int b = a; b <= p/2; b++ ) {
					final int c = p - b - a;
					if( (a*a) + (b*b) == (c*c) ) {
						solutions++;
					}
				}
			}

			if( solutions > mostSolutions ) {
				mostSolutions = solutions;
				pWithMostSolutions = p;
			}
		}

		return pWithMostSolutions;
	}
}