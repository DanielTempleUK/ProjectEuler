package problem22;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

	@SuppressWarnings("serial")
	private static final List<String> letters = new ArrayList<String>(){{
		add("A");add("B");add("C");add("D");add("E");add("F");add("G");add("H");add("I");add("J");add("K");add("L");add("M");
		add("N");add("O");add("P");add("Q");add("R");add("S");add("T");add("U");add("V");add("W");add("X");add("Y");add("Z");
	}};

	public static void main(final String[] args) throws IOException {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	private static long calculateSolution() throws IOException {
		final BufferedReader reader = new BufferedReader(new FileReader(new File("src/problem22/names.txt")));
		final String nameString = reader.readLine();
		reader.close();

		final String[] namesArray = nameString.split(",");
		final List<String> names = Arrays.asList(namesArray);

		Collections.sort(names);
		for (String string : names) {
			string = string.toUpperCase();
		}

		long total = 0;
		for (final String string : names) {
			total += ( alphaValueOf(string) * (names.indexOf(string) + 1) );
		}

		return total;
	}

	private static int alphaValueOf(final String string) {
		final char[] characters = string.toCharArray();

		int total = 0;
		for( int i = 0; i < characters.length; i++ ) {
			total += ( letters.indexOf(""+characters[i]) + 1 );
		}

		return total;
	}
}