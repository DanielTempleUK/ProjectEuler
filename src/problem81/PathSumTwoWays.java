package problem81;

import java.util.List;

import utilities.ReadFile;

/**
 * Solution to problem 81:
 * 
 * In the 5 by 5 matrix below, the minimal path sum from the top left to the bottom right,
 * by only moving to the right and down, is indicated in bold red and is equal to 2427.
 * 
 *      131	673	234	103	18
 *      201	96	342	965	150
 *      630	803	746	422	111
 *      537	699	497	121	956
 *      805	732	524	37	331
 * 
 *      131 + 201 + 96 + 342 + 746 + 422 + 121 + 37 + 331 = 2427
 * 
 * Find the minimal path sum, in matrix.txt (right click and 'Save Link/Target As...'),
 * a 31K text file containing a 80 by 80 matrix, from the top left to the bottom right by only moving right and down.
 */
public class PathSumTwoWays {

	public static void main(final String[] args) {
		final long startTime = System.currentTimeMillis();
		final long solution = calculateSolution();
		final long endTime = System.currentTimeMillis();

		System.out.println("The answer is: " + solution);
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	/**
	 * My theory here was that this problem could be represented in a similar way to problem 18.
	 * 
	 * If all we can do is move down or right, that is the same as being able to move down-left and down-right.
	 * So it must be possible to represent our square of numbers as a triangle and then run the same algorithm
	 * as we did for problem 18.
	 * 
	 * The way we change the square into a triangle is to consider diagonals that run from bottom left to top right.
	 * Then we use those diagonals as the rows for our triangle. (Basically, we rotate it 45 degrees to the right)
	 * 
	 * Using the 5x5 square from the question as an example
	 * 		131	673	234	103	18
	 *      201	96	342	965	150
	 *      630	803	746	422	111
	 *      537	699	497	121	956
	 *      805	732	524	37	331
	 * 
	 * Becomes:
	 * 
	 *             131
	 *           201 673
	 *         630  96 234
	 *       537 803 342 103
	 *     805 699 746 965  18
	 *       732 497 422 150
	 *         524 121 111
	 *            37 956
	 *             331
	 * 
	 * Obviously, this is not a triangle, so we need to add some triangles at the bottom left and bottom right to make it one.
	 * In order to find the smallest route through this diamond, the triangles we need to add need to have numbers that are higher
	 * than all numbers in the diamond. For this example, 1000 will work nicely. So we get:
	 * 
	 *                      131
	 *                    201 673
	 *                  630  96 234
	 *                537 803 342 103
	 *              805 699 746 965  18
	 *           1000 732 497 422 150 1000
	 *        1000 1000 524 121 111 1000 1000
	 *     1000 1000 1000  37 956 1000 1000 1000
	 *  1000 1000 1000 1000 331 1000 1000 1000 1000
	 * 
	 *  So we now have our triangle to work with.
	 * 
	 *  Now we just apply the logic from the solution to problem 18.
	 *  We need to turn it back into a square such that the route wont use the
	 *  top right hand corner of the square. Again, we add a triangle of 1000 to give us this:
	 * 
	 *      131 1000 1000 1000 1000 1000 1000 1000 1000
	 *      201  673 1000 1000 1000 1000 1000 1000 1000
	 *      630   96  234 1000 1000 1000 1000 1000 1000
	 *      537  803  342  103 1000 1000 1000 1000 1000
	 *      805  699  746  965   18 1000 1000 1000 1000
	 *     1000  732  497  422  150 1000 1000 1000 1000
	 *     1000 1000  524  121  111 1000 1000 1000 1000
	 *     1000 1000 1000   37  956 1000 1000 1000 1000
	 *     1000 1000 1000 1000  331 1000 1000 1000 1000
	 * 
	 *  So from this, we can apply the algorithm from problem 18 and get our answer of 2427 in <1 millisecond.
	 *  The algorithm does need one tweak, which is to make it check for < rather than > as problem 18 was looking for the maximum path
	 *  through the triangle, but we want the minimum
	 * 
	 *  The algorithm calculates the quickest path from the top left corner of the square down to any item in the bottom row.
	 *  Obviously the quickest path is not going to use any of the 1000 entries, so we always end up at the 331 in the bottom row.
	 * 
	 *  Once I had figured this theory out and tried it on the smaller square, I just wrote some code to do the data
	 *  representation changes for me and then ran from there.
	 */
	private static long calculateSolution() {
		final long[][] readData = new long[80][80];
		final long highestDataValue = readDataAndFindHighestValue(readData);

		final long highValue = calculatePowerOfTenAbove(highestDataValue);
		final long[][] actualData = reRepresentDataForAlgorithm(readData, highValue);

		final long[] results = actualData[actualData.length-1];

		for (int i = actualData.length-2; i >= 0; i-- ) {
			for (int j = 0; j < actualData[actualData.length-1].length-1; j++ ) {
				final long result1 = actualData[i][j] + results[j];
				final long result2 = actualData[i][j] + results[j+1];

				results[j] = result1 < result2 ? result1 : result2;
			}
		}

		return results[0];
	}

	/**
	 * The data grid is assumed to be square.
	 * 
	 * This represents a grid of data as another grid of data following the process of:
	 *  1. Move each subsequent column down by an incrementing counter.
	 *  2. Ensure any gaps created by the moves are filled with the highValue
	 *  3. Add in grid highValue elements to ensure running into middle element of bottom row.
	 * 
	 * As an example, it turns this:
	 * 
	 *		131	673	234	103	 18
	 *      201	 96	342	965	150
	 *      630	803	746	422	111
	 *      537	699	497	121	956
	 *      805	732	524	 37	331
	 * 
	 * Into this (assuming a high value of 1000)
	 * 
	 *      131 1000 1000 1000 1000 1000 1000 1000 1000
	 *      201  673 1000 1000 1000 1000 1000 1000 1000
	 *      630   96  234 1000 1000 1000 1000 1000 1000
	 *      537  803  342  103 1000 1000 1000 1000 1000
	 *      805  699  746  965   18 1000 1000 1000 1000
	 *     1000  732  497  422  150 1000 1000 1000 1000
	 *     1000 1000  524  121  111 1000 1000 1000 1000
	 *     1000 1000 1000   37  956 1000 1000 1000 1000
	 *     1000 1000 1000 1000  331 1000 1000 1000 1000
	 */
	private static long[][] reRepresentDataForAlgorithm( final long[][] readData, final long highValue ) {
		final int length = readData.length;
		final int newLength = (2*length)-1;

		final long[][] actualData = new long[newLength][newLength];

		//Performs step 1
		for( int i = 0; i < length; i++ ) {
			for( int j = 0; j < length; j++ ) {
				actualData[j+i][i] = readData[j][i];
			}
		}

		//Performs step 2 & 3
		for( int i = 0; i < newLength; i++ ) {
			for( int j = 0; j < newLength; j++ ) {
				if( actualData[i][j] == 0 ) {
					actualData[i][j] = highValue;
				}
			}
		}
		return actualData;
	}

	/**
	 * Returns the power of ten above the provided number.
	 * 
	 * For example:
	 * 		input: 5			output: 10
	 * 		input: 9,876 		output: 10,000
	 * 		input: 1,234,567	output: 10,000,000
	 */
	private static long calculatePowerOfTenAbove( final long baseValue ) {
		long highValue = 1;

		for( int i = 0; i < (""+baseValue).length(); i++ ) {
			highValue *= 10;
		}
		return highValue;
	}

	/**
	 * This method reads the data from the file and returns it in a 2D array.
	 * 
	 * The data file is 80 lines of numbers, 80 numbers to a line separated by commas.
	 */
	private static long readDataAndFindHighestValue( final long[][] data ) {
		long currentHighest = 0L;

		final List<String> readLines = ReadFile.readMultipleSingleWordLines("src/problem81/matrix.txt");

		for (int j = 0; j < readLines.size(); j++) {

			final String[] split = readLines.get(j).split(",");
			final long[] array = new long[80];
			for( int i = 0; i < array.length; i++ ) {
				final Long valueAsLong = Long.valueOf(split[i]);
				array[i] = valueAsLong;

				if( valueAsLong > currentHighest ) {
					currentHighest = valueAsLong;
				}
			}

			data[j] = array;
		}
		return currentHighest;
	}
}