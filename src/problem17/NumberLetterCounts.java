package problem17;

/**
 * If the numbers 1 to 5 are written out in words: one, two, three, four, five, then there are 3 + 3 + 5 + 4 + 4 = 19 letters used in total.
 * If all the numbers from 1 to 1000 (one thousand) inclusive were written out in words, how many letters would be used?
 *
 * NOTE: Do not count spaces or hyphens. For example, 342 (three hundred and forty-two) contains 23 letters and
 * 115 (one hundred and fifteen) contains 20 letters. The use of "and" when writing out numbers is in compliance with British usage
 */
public class NumberLetterCounts {

    public static void main (final String[] args) throws InterruptedException {

	final StringBuilder builder = new StringBuilder();

	for(int i = 1; i<1000; i++) {
	    String string = "";
	    String numberString = "" + i;

	    if(numberString.length() == 3) {

		final Integer hundredDigit = Integer.valueOf("" + numberString.toCharArray()[0]);

		string = string + singleUnitString(hundredDigit);

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
		    string = string + singleUnitString(number);
		}
		else if( number < 20 ) {
		    if( number == 10 ) {
			string = string +"TEN";
		    }
		    if( number == 11 ) {
			string = string +"ELEVEN";
		    }
		    if( number == 12 ) {
			string = string +"TWELVE";
		    }
		    if( number == 13 ) {
			string = string +"THIRTEEN";
		    }
		    if( number == 14 ) {
			string = string +"FOURTEEN";
		    }
		    if( number == 15 ) {
			string = string +"FIFTEEN";
		    }
		    if( number == 16 ) {
			string = string +"SIXTEEN";
		    }
		    if( number == 17 ) {
			string = string +"SEVENTEEN";
		    }
		    if( number == 18 ) {
			string = string +"EIGHTEEN";
		    }
		    if( number == 19 ) {
			string = string +"NINETEEN";
		    }
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
		string = string + singleUnitString(units);
	    }

	    builder.append(string);
	}

	builder.append("ONETHOUSAND");

	System.out.println(builder.toString().length());
    }

    private static String singleUnitString(final Integer unit) {
	if( unit == 1 ) {
	    return "ONE";
	}
	if( unit == 2 ) {
	    return "TWO";
	}
	if( unit == 3 ) {
	    return "THREE";
	}
	if( unit == 4 ) {
	    return "FOUR";
	}
	if( unit == 5 ) {
	    return "FIVE";
	}
	if( unit == 6 ) {
	    return "SIX";
	}
	if( unit == 7 ) {
	    return "SEVEN";
	}
	if( unit == 8 ) {
	    return "EIGHT";
	}
	if( unit == 9 ) {
	    return "NINE";
	}
	return ""; //0
    }
}