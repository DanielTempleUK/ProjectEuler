package utilities;

import java.util.ArrayList;
import java.util.List;

public class AlphaNumericValues {

	@SuppressWarnings("serial")
	public static final List<String> letters = new ArrayList<String>(){{
		add("A");add("B");add("C");add("D");add("E");add("F");add("G");add("H");add("I");add("J");add("K");add("L");add("M");
		add("N");add("O");add("P");add("Q");add("R");add("S");add("T");add("U");add("V");add("W");add("X");add("Y");add("Z");
	}};

	public static int alphaValueOf(final String string) {
		final char[] characters = string.toCharArray();

		int total = 0;
		for( int i = 0; i < characters.length; i++ ) {
			total += ( letters.indexOf(""+characters[i]) + 1 );
		}

		return total;
	}
}