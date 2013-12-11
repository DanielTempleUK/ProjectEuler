package problem19;

/**
 * Solution to Problem 19:
 * 
 * You are given the following information, but you may prefer to do some research for yourself.
 * 
 *  - 1 Jan 1900 was a Monday.
 *  - Thirty days has September, April, June and November.
 *    All the rest have thirty-one, Saving February alone,
 *    Which has twenty-eight, rain or shine. And on leap years, twenty-nine.
 *  - A leap year occurs on any year evenly divisible by 4, but not on a century unless it is divisible by 400.
 * 
 * How many Sundays fell on the first of the month during the twentieth century (1 Jan 1901 to 31 Dec 2000)?
 * 
 * NOTE: I'm not sure the logic here is actually correct, but the answer it produces is...
 */
public class CountingSundays {

	private static final int LEAP_FEBRUARY = 29;
	private static int[] monthLengths = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

	public static void main(final String[] args) {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	private static long calculateSolution() {

		//Start at 2 so that when we do our check of "numberOfDays MOD 7" we get the Sundays. 0 = Sunday, 1 = Monday, 2 = Tuesday
		//01/01/1901 fell on a Tuesday. FACT
		long numberOfDays = 2L;
		long numberOfSundaysOnFirstOfTheMonth = 0L;

		for( long year = 1901; year < 2001; year++ ) {

			for( int month = 0; month < 12; month++ ) {

				if( isLeap(year) && month == 1 ) {
					numberOfDays = numberOfDays + LEAP_FEBRUARY;
				}
				else {
					numberOfDays = numberOfDays + monthLengths[month];
				}

				if(numberOfDays % 7 == 0) {
					numberOfSundaysOnFirstOfTheMonth++;
				}
			}
		}

		return numberOfSundaysOnFirstOfTheMonth;
	}

	private static boolean isLeap(final long year) {
		if( year % 100 == 0 ) {
			return year % 400 == 0;
		}
		return year % 4 == 0;
	}
}