package problem36;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Solution to Problem 36:
 * 
 * 
 * The decimal number, 585 = 10010010012 (binary), is palindromic in both bases.
 * 
 * Find the sum of all numbers, less than one million, which are palindromic in base 10 and base 2.
 * 
 * (Please note that the palindromic number, in either base, may not include leading zeros.)
 */
public class DoubleBasePalindromes {

	public static void main(final String[] args) throws IOException {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	private static long calculateSolution() throws IOException {

		final Set<Integer> palindromesBelowAMillion = new HashSet<Integer>();
		for( int i = 0; i < 1000000; i++ ) {
			if( isPalindrome(i) ) {
				palindromesBelowAMillion.add(i);
			}
		}

		final Set<Integer> palindromesInBase2Too = new HashSet<Integer>();
		for( final Integer palindrome : palindromesBelowAMillion ) {
			if( isPalindromeInBinary(palindrome) ) {
				palindromesInBase2Too.add(palindrome);
			}
		}

		long total = 0L;
		for (final Integer palindrome : palindromesInBase2Too) {
			total += palindrome;
		}

		return total;
	}

	private static boolean isPalindromeInBinary(final long number) {
		final String numberString = Long.toBinaryString(number);
		return isPalindromeString(numberString);
	}

	private static boolean isPalindrome(final long number) {
		final String numberString = String.valueOf(number);
		return isPalindromeString(numberString);
	}

	private static boolean isPalindromeString(final String numberString) {
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

		final String firstHalfReversed = reverse(firstHalf);
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