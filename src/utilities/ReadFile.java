package utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ReadFile {

	/**
	 * Delimiter is a comma.
	 * The results are sorted alphabetically.
	 */
	public static List<String> readSplitAndSort(final String fileName) {
		return readSplitAndSort(fileName, ",", true);
	}

	/**
	 * Delimiter is a comma.
	 * Results are not sorted.
	 */
	public static List<String> readAndSplit(final String fileName) {
		return readSplitAndSort(fileName, ",", false);
	}

	/**
	 * Assumes the file is a single line.
	 */
	private static List<String> readSplitAndSort(final String fileName, final String delimiter, final boolean sort) {
		BufferedReader reader;
		try {
			reader = new BufferedReader( new FileReader( new File(fileName) ) );
			final String fileString = reader.readLine();
			reader.close();

			final String[] wordsArray = fileString.split(delimiter);
			final List<String> wordsList = Arrays.asList(wordsArray);

			for (String string : wordsList) {
				string = string.toUpperCase();
			}

			if( sort ) {
				Collections.sort(wordsList);
			}
			return wordsList;
		}
		catch (final FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		catch (final IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}