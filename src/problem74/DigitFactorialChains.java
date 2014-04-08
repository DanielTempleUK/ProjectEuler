package problem74;

import java.util.HashSet;
import java.util.Set;


/**
 * Solution to problem 74:
 * 
 * The number 145 is well known for the property that the sum of the factorial of its digits is equal to 145:
 * 
 * 		1! + 4! + 5! = 1 + 24 + 120 = 145
 * 
 * Perhaps less well known is 169, in that it produces the longest chain of numbers that link back to 169;
 * it turns out that there are only three such loops that exist:
 * 
 * 		169 -> 363601 -> 1454 -> 169
 * 		871 -> 45361  -> 871
 * 		872 -> 45362  -> 872
 * 
 * It is not difficult to prove that EVERY starting number will eventually get stuck in a loop. For example,
 * 
 * 		69  -> 363600 -> 1454 -> 169    -> 363601 (-> 1454)
 * 		78  -> 45360  -> 871  -> 45361 (-> 871)
 * 		540 -> 145   (-> 145)
 * 
 * Starting with 69 produces a chain of five non-repeating terms,
 * but the longest non-repeating chain with a starting number below one million is sixty terms.
 * 
 * How many chains, with a starting number below one million, contain exactly sixty non-repeating terms?
 */
public class DigitFactorialChains {

	public static void main(final String[] args) {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	private static long calculateSolution() {

		// Calculate the factorials and store them, no need to continually calculate them
		final long[] factorials = new long[10];
		factorials[0] = 1;

		for( int i = 1; i < 10; i++ ) {
			factorials[i] = factorials[i-1] * i;
		}

		long solutionCount = 0L;

		//We know that 1 & 2 have chains of 1. So don't bother with those.
		for (long i = 3; i < 1000000; i++) {

			//Keep track of the numbers we've seen in the chain.
			final Set<Long> seenNumbers = new HashSet<Long>();
			long currentDigitFactorialTotal = i;

			//If we fail to add the current number in the chain, we know we've got a loop, so can stop calculating.
			while( seenNumbers.add(currentDigitFactorialTotal) ) {
				long number = currentDigitFactorialTotal;
				currentDigitFactorialTotal = 0L;

				//For each digit in the number, add the factorial to the current total.
				while( number > 0 ) {
					final long digit = number%10;
					number /= 10;
					currentDigitFactorialTotal += factorials[(int)digit];
				}
			}

			//Increment the count of desired numbers if the chains size is 60.
			if( seenNumbers.size() == 60 ) {
				solutionCount++;
			}
		}

		return solutionCount;
	}
}