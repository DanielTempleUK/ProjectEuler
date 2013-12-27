package problem17;

import java.util.HashMap;
import java.util.Map;

/**
 * If the numbers 1 to 5 are written out in words: one, two, three, four, five, then there are 3 + 3 + 5 + 4 + 4 = 19 letters used in total.
 * If all the numbers from 1 to 1000 (one thousand) inclusive were written out in words, how many letters would be used?
 *
 * NOTE: Do not count spaces or hyphens. For example, 342 (three hundred and forty-two) contains 23 letters and
 * 115 (one hundred and fifteen) contains 20 letters. The use of "and" when writing out numbers is in compliance with British usage
 */
public class NumberLetterCounts {

	@SuppressWarnings("serial")
	private static final Map<Integer, String> UNITS_LOOKUP = new HashMap<Integer, String>() {{
		put(0, ""); put(1, "ONE"); put(2, "TWO"); put(3, "THREE"); put(4, "FOUR");
		put(5, "FIVE"); put(6, "SIX"); put(7, "SEVEN"); put(8, "EIGHT"); put(9, "NINE");
	}};

	@SuppressWarnings("serial")
	private static final Map<Integer, String> TEENS_LOOKUP = new HashMap<Integer, String>() {{
		put(10, "TEN"); put(11, "ELEVEN"); put(12, "TWELVE"); put(13, "THIRTEEN"); put(14, "FOURTEEN");
		put(15, "FIFTEEN"); put(16, "SIXTEEN"); put(17, "SEVENTEEN"); put(18, "EIGHTEEN"); put(19, "NINETEEN");
	}};

	public static void main(final String[] args) {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	private static long calculateSolution() {

		final StringBuilder builder = new StringBuilder();

		for(int i = 1; i<1000; i++) {
			String string = "";
			String numberString = "" + i;

			if(numberString.length() == 3) {

				final Integer hundredDigit = Integer.valueOf("" + numberString.toCharArray()[0]);

				string = string + UNITS_LOOKUP.get(hundredDigit);

				if( i % 100 == 0 ) {
					string = string + "HUNDRED";
				}
				else {
					string = string + "HUNDREDAND";
				}

				numberString = numberString.substring(1, 3);
			}

			if (numberString.length() == 2) {
				final Integer number = Integer.valueOf(numberString);

				if( number < 10 ) {
					string = string + UNITS_LOOKUP.get(number);
				}
				else if( number < 20 ) {
					string = string + TEENS_LOOKUP.get(number);
				}
				else {
					if( number >= 90 ) {
						string = string +"NINETY";
					}
					else if( number >= 80 ) {
						string = string +"EIGHTY";
					}
					else if( number >= 70 ) {
						string = string +"SEVENTY";
					}
					else if( number >= 60 ) {
						string = string +"SIXTY";
					}
					else if( number >= 50 ) {
						string = string +"FIFTY";
					}
					else if( number >= 40 ) {
						string = string +"FORTY";
					}
					else if( number >= 30 ) {
						string = string +"THIRTY";
					}
					else if( number >= 20 ) {
						string = string +"TWENTY";
					}
					numberString = numberString.substring(1, 2);
				}
			}

			if (numberString.length() == 1) {
				final Integer units = Integer.valueOf("" + numberString.toCharArray()[0]);
				string = string + UNITS_LOOKUP.get(units);
			}

			builder.append(string);
		}

		builder.append("ONETHOUSAND");

		return builder.toString().length();
	}
}