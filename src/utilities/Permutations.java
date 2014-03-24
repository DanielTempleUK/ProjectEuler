package utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutations {

	private static final List<String> permutations = new ArrayList<String>();

	public static List<String> getPermutationsOf(final String input) {
		permutations.clear();
		getPermutationsOf("", input);
		return permutations;
	}

	private static void getPermutationsOf(final String prefix, final String str) {
		final int n = str.length();

		if (n == 1) {
			permutations.add(prefix + str);
		}
		else {
			for (int i = 0; i < n; i++) {
				getPermutationsOf(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1));
			}
		}
	}

	public static final int[][] generatePermutationsAsIntArray( final String input ) {
		final List<String> permutationsOfInput = getPermutationsOf(input);

		final int[][] returnArray = new int[permutationsOfInput.size()][input.length()];

		int j = 0;
		for (final String string : permutationsOfInput) {
			final int[] array = new int[input.length()];
			int i = 0;
			for ( final Character character : string.toCharArray() ) {
				array[i] = Integer.parseInt("" + character);
				i++;
			}
			returnArray[j] = array;
			j++;
		}

		return returnArray;
	}

	/**
	 * Checks that two provided numbers are permutations of one another.
	 */
	public static final boolean permutationCheck(final long number1, final long number2) {
		final char[] charArray1 = (""+number1).toCharArray();
		Arrays.sort(charArray1);
		final char[] charArray2 = (""+number2).toCharArray();
		Arrays.sort(charArray2);
		return Arrays.equals(charArray1, charArray2);
	}
}