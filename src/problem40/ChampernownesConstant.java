package problem40;

/**
 * Solution to problem 40:
 * 
 * 
 * An irrational decimal fraction is created by concatenating the positive integers:
 * 
 * 			0.123456789101112131415161718192021...
 * 
 * It can be seen that the 12th digit of the fractional part is 1.
 * If dn represents the nth digit of the fractional part, find the value of the following expression.
 * 
 * 		d1 × d10 × d100 × d1000 × d10000 × d100000 × d1000000
 */
public class ChampernownesConstant {

	public static void main(final String[] args) {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	private static long calculateSolution() {

		final Integer d1 =       1;
		final Integer d10 =      1;
		final Integer d100 =     getNumberAt(100L);
		final Integer d1000 =    getNumberAt(1000L);
		final Integer d10000 =   getNumberAt(10000L);
		final Integer d100000 =  getNumberAt(100000L);
		final Integer d1000000 = getNumberAt(1000000L);

		return d1 * d10 * d100 * d1000 * d10000 * d100000 * d1000000;
	}

	private static final Integer getNumberAt(final Long indexDesired) {
		int length = 0;
		int finalI = 0;
		for( int i = 1; length < indexDesired; i++ ) {
			length = length + ("" + i).length();
			finalI = i;
		}

		final String finalIString = "" + finalI;
		final int indexInFinalI = (int) (indexDesired - (length - finalIString.length()));
		return Integer.valueOf( "" + finalIString.charAt(indexInFinalI - 1));
	}
}