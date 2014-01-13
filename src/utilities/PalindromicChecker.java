package utilities;

public class PalindromicChecker {

	public static boolean isPalindrome(final String string) {
		return string.equals(reverse(string));
	}

	public static boolean isPalindrome(final long number) {
		return String.valueOf(number).equals(reverse(String.valueOf(number)));
	}

	public static String reverse(final String string) {
		final char[] charArray = string.toCharArray();
		final char[] temp = new char[string.length()];

		for( int i = 0; i < string.length(); i++ ) {
			temp[temp.length - 1 - i] = charArray[i];
		}

		return new String(temp);
	}
}