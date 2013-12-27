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
	 * Assumes the file is a single line and the separator is a comma.
	 */
	public static List<String> readAndSplit(final String fileName) {
		BufferedReader reader;
		try {
			reader = new BufferedReader( new FileReader( new File(fileName) ) );
			final String fileString = reader.readLine();
			reader.close();

			final String[] wordsArray = fileString.split(",");
			final List<String> wordsList = Arrays.asList(wordsArray);

			for (String string : wordsList) {
				string = string.toUpperCase();
			}

			Collections.sort(wordsList);
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