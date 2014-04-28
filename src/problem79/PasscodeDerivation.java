package problem79;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import utilities.ReadFile;

/**
 * Solution to Problem 79:
 * 
 * 
 * A common security method used for online banking is to ask the user for three random characters from a passcode.
 * For example, if the passcode was 531278, they may ask for the 2nd, 3rd, and 5th characters; the expected reply would be: 317.
 * 
 * The text file, keylog.txt, contains fifty successful login attempts.
 * 
 * Given that the three characters are always asked for in order, analyse the file
 * so as to determine the shortest possible secret passcode of unknown length.
 */
public class PasscodeDerivation {

	public static void main(final String[] args) throws IOException {
		final long startTime = System.currentTimeMillis();
		final String solution = calculateSolution();
		final long endTime = System.currentTimeMillis();

		System.out.println("The answer is: " + solution);
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	/**
	 * Solution runs in less than 10 millieconds, so I'm quite pleased.
	 * I just sort of hacked this one out until I hit the correct answer.
	 * I found it was straight forward to check that a solution had a given attempt in the correct order,
	 * so I wrote this check and used it quite a bit while hacking out the algorithm.
	 * It'd be interesting to see if, given another set of attempts this algorithm gets the right answer there too.
	 * 
	 * 
	 * The basic theory behind the solution is this:
	 *  1 Initially just concatenate any attempt that isn't current covered by the previously concatenated attempts.
	 *  2 Separate the remaining (filtered) attempts into 1st, 2nd & 3rd characters.
	 *  3 Combine the first characters in order based on whether they exist in the 2nd and 3rd sets.
	 *  4 Combine the second characters in order based on whether they exist in the 1st and 3rd sets.
	 *  5 Combine the third characters in order based on if they only exist in the 3rd set.
	 *  6 Check the solution for any out of order numbers and reorder as appropriate.
	 *  7 Ensure that final solution works for all 50 attempts in the list.
	 * 
	 * 
	 *  Just a note on what steps 3-5 work and why step 6 is required.
	 *  Steps 3-5 order the groups of characters. So the groups end up in order, but then each individual group could be out of order
	 *  Step 3 takes the first characters in order and ends up with the current solution being:
	 *  	<only in set 1><in set 1 & 2><in set 1 & 3>
	 *  Step 4 takes the second characters in order and ends up with the current solution being:
	 *  	<only in set 1><in set 1 & 2><in set 1 & 3><only in set 2><in set 2 & 3>
	 *  Step 5 takes the third characters in order and ends up with the current solution being:
	 *  	<only in set 1><in set 1 & 2><in set 1 & 3><only in set 2><in set 2 & 3><only in set 3>
	 * 
	 *  Step 6 then comes through and ensures that each of the groups is in the correct order.
	 */
	public static String calculateSolution() {

		final List<String> attempts = ReadFile.readMultipleSingleWordLines("src/problem79/keylog.txt");
		final List<String> filteredAttempts = new ArrayList<String>();

		//Initially set the solution to be the first attempt in the list
		String currentSolution = attempts.get(0);
		filteredAttempts.add( attempts.get(0) );
		attempts.remove(0);

		//Go through all of the attempts appending them onto the end if they don't work with the current solution
		for( final String attempt : attempts ) {
			if( containsAllCharactersInOrder(currentSolution, attempt) ) {
				continue;
			}

			currentSolution += attempt;
			filteredAttempts.add(attempt);
		}

		currentSolution = "";


		//Now we've filtered out the attempts to the important ones.
		//If the solution works for the remaining attempts, it will work for all 50.

		//Perform some analysis on the first characters and order them based on whether they
		//exist within the second or third characters lists.
		//This will only get the numbers roughly in the correct order.
		final List<String> firstCharacters = getCharacters( filteredAttempts, 0 );
		final List<String> secondCharacters = getCharacters( filteredAttempts, 1 );
		final List<String> thirdCharacters = getCharacters( filteredAttempts, 2 );

		String firstCharactersString = "";
		String secondCharactersString = "";
		String thirdCharactersString = "";

		for (final String firstCharacter : firstCharacters) {
			if( secondCharacters.contains( firstCharacter ) ) {
				secondCharactersString += firstCharacter;
			}
			else if( thirdCharacters.contains( firstCharacter ) ) {
				thirdCharactersString += firstCharacter;
			}
			else {
				firstCharactersString += firstCharacter;
			}
		}

		currentSolution += firstCharactersString;
		currentSolution += secondCharactersString;
		currentSolution += thirdCharactersString;


		//Do the same analysis for the second characters.
		secondCharactersString = "";
		thirdCharactersString = "";

		for (final String secondCharacter : secondCharacters) {
			if( firstCharacters.contains(secondCharacter) ) {
				continue;
			}
			if( thirdCharacters.contains( secondCharacter ) ) {
				thirdCharactersString += secondCharacter;
			}
			else {
				secondCharactersString += secondCharacter;
			}
		}

		currentSolution += secondCharactersString;
		currentSolution += thirdCharactersString;


		//Do the same analysis for the third characters.
		thirdCharactersString = "";

		for (final String thirdCharacter : thirdCharacters) {
			if( firstCharacters.contains(thirdCharacter) || secondCharacters.contains(thirdCharacter) ) {
				continue;
			}
			currentSolution += thirdCharacter;
		}


		//Now the numbers are in the rough ordering, we need to sort the order out completely.
		//This is achieved by swapping characters that make partial attempts succeed.
		for( final String attempt : attempts ) {
			if( containsAllCharactersInOrder(currentSolution, attempt) ) {
				continue;
			}

			if( containsAllCharactersInOrder( currentSolution, attempt.substring(0, 2) ) ) {
				final char char2 = attempt.charAt(1);
				final char char3 = attempt.charAt(2);

				final int indexOfChar2 = currentSolution.indexOf(char2);
				final int indexOfChar3 = currentSolution.indexOf(char3);

				final char[] charArray = currentSolution.toCharArray();
				charArray[indexOfChar3] = char2;
				charArray[indexOfChar2] = char3;

				currentSolution = new String(charArray);
			}
			else {
				final char char1 = attempt.charAt(0);
				final char char2 = attempt.charAt(1);

				final int indexOfChar1 = currentSolution.indexOf(char1);
				final int indexOfChar2 = currentSolution.indexOf(char2);

				final char[] charArray = currentSolution.toCharArray();
				charArray[indexOfChar2] = char1;
				charArray[indexOfChar1] = char2;

				currentSolution = new String(charArray);
			}
		}


		//One last cursory check now. This makes sure that the derived solution does work for all of the attempts.
		for( final String attempt : attempts ) {
			if( containsAllCharactersInOrder(currentSolution, attempt) ) {
				continue;
			}

			throw new IllegalStateException("\r\nThe solution has not been found!!!" +
					"\r\nThe solution: ["+currentSolution+"] does not contain the attempt: ["+attempt+"]");
		}

		return currentSolution;
	}

	private static List<String> getCharacters( final List<String> filteredAttempts, final int i ) {
		final List<String> ithCharacters = new ArrayList<String>();

		for (final String string : filteredAttempts) {
			final String ithCharacter = "" + string.charAt(i);
			if( ithCharacters.contains( ithCharacter ) ) {
				continue;
			}
			ithCharacters.add(ithCharacter);
		}
		return ithCharacters;
	}

	/**
	 * This method was originally written to check attempts of 3 characters in length.
	 * It was then adapted slightly to check the length of 2 and handle that case 2, makes the code a little messier, but it works.
	 * 
	 * TODO: Would be cleaner to run in a loop based on the length of the attempt parameter.
	 */
	private static boolean containsAllCharactersInOrder(final String currentSolution, final String attempt) {
		final char[] a = currentSolution.toCharArray();
		final char[] test = new char[a.length];

		for( int i = 0; i < a.length; i++ ) {
			test[i] = Character.toLowerCase( a[i] );
		}

		int i = 0;
		char temp = Character.toLowerCase( attempt.charAt(0) );
		while( i < test.length ) {
			if( temp == test[i] ) {
				i++;
				break;
			}
			i++;
		}

		temp = Character.toLowerCase( attempt.charAt(1) );
		while( i < test.length ) {
			if( temp == test[i] ) {
				i++;

				if( attempt.length() == 2) {
					return true;
				}
				break;
			}
			i++;
		}

		if( attempt.length() == 2) {
			return false;
		}

		temp= Character.toLowerCase( attempt.charAt(2) );
		while( i < test.length ) {
			if( temp == test[i] ) {
				i++;
				return true;
			}
			i++;
		}

		return false;
	}
}