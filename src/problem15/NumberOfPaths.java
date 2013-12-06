package problem15;

/**
 * Solution to problem 15:
 * 
 * Starting in the top left corner of a 2×2 grid, and only being able to move to the right and down,
 * there are exactly 6 routes to the bottom right corner.
 * How many such routes are there through a 20×20 grid?
 */
public class NumberOfPaths {

    public static void main (final String[] args) {
	final int gridSize = 20;

	long numberOfPaths = 1;

	for ( int i = 1; i <= gridSize; i++ ) {
	    numberOfPaths *= 2*gridSize-i+1;
	    numberOfPaths /= i;
	}

	System.out.println(numberOfPaths);
    }
}