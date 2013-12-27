package problem22;

import java.io.IOException;
import java.util.List;

import utilities.AlphaNumericValues;
import utilities.ReadFile;

/**
 * Solution to problem 22:
 * 
 * Using names.txt (right click and 'Save Link/Target As...'), a 46K text file containing over five-thousand first names,
 * begin by sorting it into alphabetical order. Then working out the alphabetical value for each name, multiply this value by
 * its alphabetical position in the list to obtain a name score.
 * 
 * For example, when the list is sorted into alphabetical order, COLIN,
 * which is worth 3 + 15 + 12 + 9 + 14 = 53, is the 938th name in the list.
 * So, COLIN would obtain a score of 938 × 53 = 49714.
 * 
 * What is the total of all the name scores in the file?
 */
public class NameScores {

	public static void main(final String[] args) throws IOException {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	private static long calculateSolution() throws IOException {
		final List<String> names = ReadFile.readAndSplit("src/problem22/names.txt");

		long total = 0;
		for (final String string : names) {
			total += ( AlphaNumericValues.alphaValueOf(string) * (names.indexOf(string) + 1) );
		}

		return total;
	}
}