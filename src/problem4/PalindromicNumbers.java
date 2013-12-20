package problem4;

import java.io.IOException;

/**
 * Solution to problem 4:
 * 
 * A palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.
 * Find the largest palindrome made from the product of two 3-digit numbers.
 */
public class PalindromicNumbers {

	public static void main(final String[] args) throws IOException {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	private static long calculateSolution() throws IOException {

		long biggestPalindrome = 9009;

		for (int i = 999; i > 99; i-- ) {
			for( int j = 999; j > 99; j-- ) {
				final long product = i * j;
				if ( product > biggestPalindrome && isPalindrome(product) ) {
					biggestPalindrome = product;
				}
			}
		}

		return biggestPalindrome;
	}

	private static boolean isPalindrome(final long number) {
		return String.valueOf(number).equals(reverse(String.valueOf(number)));
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