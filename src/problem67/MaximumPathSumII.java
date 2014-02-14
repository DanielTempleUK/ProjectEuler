package problem67;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Solution to Problem 67
 * 
 * By starting at the top of the triangle below and moving to adjacent numbers on the row below,
 * the maximum total from top to bottom is 23.
 * 
 *    3
 *   7 4
 *  2 4 6
 * 8 5 9 3
 * 
 * That is, 3 + 7 + 4 + 9 = 23.
 * 
 * Find the maximum total from top to bottom in triangle.txt (right click and 'Save Link/Target As...'),
 * a 15K text file containing a triangle with one-hundred rows.
 * 
 * NOTE: This is a much more difficult version of Problem 18. It is not possible to try every route to
 * solve this problem, as there are 299 altogether! If you could check one trillion (1012) routes every
 * second it would take over twenty billion years to check them all.
 * There is an efficient algorithm to solve it. ;o)
 */
public class MaximumPathSumII {

	private static final int[][] input = new int[100][100];

	public static void main(final String[] args) {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	private static long calculateSolution() {

		readFile();
		final int[] results = input[99];

		for (int i = input.length-2; i >= 0; i-- ) {
			for (int j = 0; j < input[input.length-1].length-1; j++ ) {
				final int result1 = getInput(i, j) + results[j];
				final int result2 = getInput(i, j) + results[j+1];

				results[j] = result1 > result2 ? result1 : result2;
			}
		}

		return results[0];
	}

	private static int getInput(final int i, final int j) {
		if( i < 0 || j < 0) {
			return 0;
		}
		return input[i][j];
	}

	private static void readFile() {

		BufferedReader reader;
		try {
			reader = new BufferedReader( new FileReader( new File("src/problem67/triangle.txt") ) );

			int i = 0;
			String lineString = reader.readLine();
			while( lineString != null ) {

				final String[] wordsArray = lineString.split(" ");
				for (int j = 0; j < 100; j++) {
					int number = 0;
					if( j < wordsArray.length ) {
						number = Integer.valueOf(wordsArray[j]).intValue();
					}
					input[i][j] = number;
				}

				i++;
				lineString = reader.readLine();
			}

			reader.close();
		}
		catch (final FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (final IOException e) {
			e.printStackTrace();
		}
	}
}