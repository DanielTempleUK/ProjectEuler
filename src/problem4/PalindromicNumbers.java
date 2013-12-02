package problem4;

public class PalindromicNumbers {

	public static void main(final String... args) {

		long biggestPalindrome = 0;

		for (int i = 999; i > 99; i-- ) {
			for( int j = 999; j > 99; j-- ) {
				final long product = i * j;
				if ( PalindromicNumbers.isPalindrome(product) && product > biggestPalindrome ) {
					biggestPalindrome = product;
				}
			}
		}

		System.out.println(biggestPalindrome);
	}

	private static boolean isPalindrome(final long number) {
		final String numberString = String.valueOf(number);
		String firstHalf;
		String secondHalf;

		if( numberString.length() % 2 == 0 ) {
			firstHalf = numberString.substring(0, numberString.length() / 2 );
			secondHalf = numberString.substring( numberString.length() / 2, numberString.length() );
		}
		else {
			firstHalf = numberString.substring(0, (int)Math.ceil(numberString.length() / 2) );
			secondHalf = numberString.substring( (int)( Math.ceil(numberString.length() / 2) + 1 ), numberString.length() );
		}

		final String firstHalfReversed = PalindromicNumbers.reverse(firstHalf);
		return secondHalf.equals(firstHalfReversed);
	}

	private static String reverse(final String string) {
		final char[] charArray = string.toCharArray();
		final char[] temp = new char[string.length()];

		for( int i = 0; i < string.length(); i++ ) {
			temp[temp.length - 1 - i] = charArray[i];
		}

		return new String(temp);
	}
}