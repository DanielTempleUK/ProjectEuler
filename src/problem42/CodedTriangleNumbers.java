package problem42;

import java.io.IOException;
import java.util.List;

import utilities.AlphaNumericValues;
import utilities.NumberChecker;
import utilities.ReadFile;

/**
 * Solution to problem 42
 * 
 * 
 * The nth term of the sequence of triangle numbers is given by, tn = ½n(n+1); so the first ten triangle numbers are:
 *      1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...
 * 
 * By converting each letter in a word to a number corresponding to its alphabetical position and adding
 * these values we form a word value. For example, the word value for SKY is 19 + 11 + 25 = 55 = t10.
 * If the word value is a triangle number then we shall call the word a triangle word.
 * 
 * Using words.txt (right click and 'Save Link/Target As...'), a 16K text file containing
 * nearly two-thousand common English words, how many are triangle words?
 */
public class CodedTriangleNumbers {

	public static void main(final String[] args)  throws IOException {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	private static long calculateSolution() throws IOException {
		final List<String> words = ReadFile.readAndSplit("src/problem42/words.txt");

		long count = 0L;

		for (final String word : words) {
			final Integer codedValue = AlphaNumericValues.alphaValueOf(word);
			if( NumberChecker.isTriangleNumber(codedValue) ) {
				count++;
			}
		}

		return count;
	}
}