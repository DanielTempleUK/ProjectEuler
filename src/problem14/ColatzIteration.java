package problem14;

/**
 * Solution to problem 14
 * 
 * 
 * The following iterative sequence is defined for the set of positive integers:
 * 
 * n = n/2 (n is even)
 * n = 3n + 1 (n is odd)
 * 
 * Using the rule above and starting with 13, we generate the following sequence:
 * 
 * 13, 40, 20, 10, 5, 16, 8, 4, 2, 1
 * It can be seen that this sequence (starting at 13 and finishing at 1) contains 10 terms.
 * Although it has not been proved yet (Collatz Problem), it is thought that all starting numbers finish at 1.
 * 
 * Which starting number, under one million, produces the longest chain?
 * 
 * Note: Once the chain starts the terms are allowed to go above one million.
 */
public class ColatzIteration {

	public static void main(final String[] args) {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	private static long calculateSolution() {
		long numberWithLongetSequence = 0;
		long currentlongestSequence = 0;

		for(long i = 1000000; i > 1; i-- ) {

			long sequenceLength = 0L;
			long temp = i;

			while( temp > 1 ) {
				sequenceLength++;
				if(temp%2==0) {
					temp = temp/2;
				}
				else {
					temp = 3*temp + 1;
				}
			}

			if(sequenceLength > currentlongestSequence) {
				currentlongestSequence = sequenceLength;
				numberWithLongetSequence = i;
			}
		}

		return numberWithLongetSequence;
	}
}