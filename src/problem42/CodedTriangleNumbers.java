package problem42;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

	@SuppressWarnings("serial")
	private static final List<String> letters = new ArrayList<String>(){{
		add("A");add("B");add("C");add("D");add("E");add("F");add("G");add("H");add("I");add("J");add("K");add("L");add("M");
		add("N");add("O");add("P");add("Q");add("R");add("S");add("T");add("U");add("V");add("W");add("X");add("Y");add("Z");
	}};

	public static void main(final String[] args)  throws IOException {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	private static long calculateSolution() throws IOException {
		final BufferedReader reader = new BufferedReader(new FileReader(new File("src/problem42/words.txt")));
		final String wordString = reader.readLine();
		reader.close();


		final String[] namesArray = wordString.split(",");
		final List<String> words = Arrays.asList(namesArray);


		final List<String> codedTriangleNumberWords = new ArrayList<String>();

		for (final String word : words) {
			final Integer codedValue = code(word);
			if( isTriangleNumber(codedValue) ) {
				codedTriangleNumberWords.add(word);
			}
		}

		return codedTriangleNumberWords.size();
	}

	private static boolean isTriangleNumber(final Integer codedValue) {
		final Integer times2 = codedValue * 2;
		final int rootFloor = (int) Math.sqrt(times2);

		return (rootFloor * (rootFloor + 1) * 0.5) == codedValue;
	}

	private static Integer code(final String word) {
		Integer code = 0;

		final char[] characters = word.toCharArray();

		for( int i = 0; i<characters.length; i++ ) {
			code += alphaValueOf("" + characters[i]);
		}

		return code;
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