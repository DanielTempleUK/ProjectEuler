package problem78;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Solution to problem 78:
 * 
 * 
 * Let p(n) represent the number of different ways in which n coins can be separated into piles.
 * For example, five coins can separated into piles in exactly seven different ways, so p(5)=7.
 * 
 *      OOOOO
 *      OOOO O
 *      OOO  OO
 *      OOO  O   O
 *      OO   OO  O
 *      OO   O   O   O
 *      O    O   O   O   O
 * 
 * Find the least value of n for which p(n) is divisible by one million.
 */
public class CoinPartitions {

	private static final Map<Long, Long> map = new HashMap<Long, Long>();

	public static void main(final String[] args) throws IOException {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	/**
	 * This solution uses the Pentagonal Number Theory to determine the next number of piles based on the
	 * recursive function that uses the previously calculated number of piles for the numbers (n - PentagonalNumber).
	 * 
	 * Possible improvements:
	 *  - I could store the pentagonal numbers up to some arbetrary limit, say 1000000, and use those rather than calculate them each time.
	 */
	public static long calculateSolution() {
		map.put(0L, 1L);
		map.put(1L, 1L);

		for( long i = 2; i > 0; i++ ) {
			final Long piles = calculateNumberOfPilesForCoins( i );

			if( piles.toString().endsWith("000000") ) {
				return i;
			}
		}
		return 0L;
	}

	private static Long calculateNumberOfPilesForCoins( final long n ) {
		long total = 0L;
		for( long k = 1; k <= n; k++ ) {
			final long a = (long)Math.pow(-1, k+1);

			final long negativePart = n - negativePentNum(k);
			final long positivePart = n - positivePentNum(k);

			if( negativePart < 0 && positivePart < 0 ) {
				break;
			}
			if( negativePart >= 0 ) {
				total = total + ( ( a * map.get(negativePart) ) % 1000000 );
			}
			if( positivePart >= 0 ) {
				total = total + ( ( a * map.get(positivePart) ) % 1000000 );
			}
		}

		map.put(n, total);

		return total;
	}

	private static long positivePentNum(final long k) {
		return ( k * ( (3*k) + 1 ) ) / 2;
	}

	private static long negativePentNum(final long k) {
		return ( k * ( (3*k) - 1 ) ) / 2;
	}
}