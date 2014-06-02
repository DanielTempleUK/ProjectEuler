package problem51;

import java.io.IOException;
import java.util.List;

import utilities.PrimeGenerator;

/**
 * Solution to Problem 51:
 * 
 * 
 * By replacing the 1st digit of the 2-digit number *3, it turns out that six of the nine possible values:
 * 13, 23, 43, 53, 73, and 83, are all prime.
 * 
 * By replacing the 3rd and 4th digits of 56**3 with the same digit, this 5-digit number is the first example having seven primes
 * among the ten generated numbers, yielding the family: 56003, 56113, 56333, 56443, 56663, 56773, and 56993. Consequently
 * 56003, being the first member of this family, is the smallest prime with this property.
 * 
 * Find the smallest prime which, by replacing part of the number (not necessarily adjacent digits)
 * with the same digit, is part of an eight prime value family.
 */
public class PrimeDigitReplacements {

	public static void main(final String[] args) throws IOException {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	private static long calculateSolution()  {
		//I've taken a guess that the number we need will be 5 or 6 digits.
		final List<Long> primes = PrimeGenerator.getPrimesBetween(10000, 999999);

		//The lowest number in the family must contain 0, 1 or 2 as the repeated digit since there needs to be 8 of them.
		for (final Long prime : primes) {

			int count = 0;
			final String primeString = ""+prime;
			if( repeatedCharacterIs0( primeString ) ) {
				count++;
				for (int i = 1; i < 10; i++) {
					if(primes.contains(Long.valueOf(primeString.replace("0", ""+i)))) {
						count++;
					}
				}
			}

			else if( repeatedCharacterIs1( primeString ) ) {
				count++;
				for (int i = 2; i < 10; i++) {
					if(primes.contains(Long.valueOf(primeString.replace("1", ""+i)))) {
						count++;
					}
				}
			}

			else if( repeatedCharacterIs2( primeString ) ) {
				count++;
				for (int i = 3; i < 10; i++) {
					if(primes.contains(Long.valueOf(primeString.replace("2", ""+i)))) {
						count++;
					}
				}
			}

			if( count == 8 ) {
				return prime;
			}
		}

		return 0L;
	}

	/*
	 * These repeated character checkers simply remove the repeated character and then check the difference in length.
	 * We know the difference needs to be 3 because there needs to be 3 repeated characters to produce a family of 8 members.
	 */
	private static boolean repeatedCharacterIs0(final String primeString) {
		return primeString.length() - primeString.replace("0", "").length() == 3;
	}
	private static boolean repeatedCharacterIs1(final String primeString) {
		return primeString.length() - primeString.replace("1", "").length() == 3;
	}
	private static boolean repeatedCharacterIs2(final String primeString) {
		return primeString.length() - primeString.replace("2", "").length() == 3;
	}
}