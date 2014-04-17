package problem78;

import java.io.IOException;
import java.math.BigDecimal;
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

	private static final Map<Long, BigDecimal> map = new HashMap<Long, BigDecimal>();

	public static void main(final String[] args) throws IOException {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	/**
	 * This calculates the correct solution, but in around 10 seconds, so it's slow....
	 * 
	 * It uses the Pentagonal Number Theory to determine the next number of ways based on the
	 * recursive function from the previously calculated number of ways for the numbers less than n.
	 * 
	 * Possible imporvements:
	 *  - I have seen solutions that only store the last 6 digits by mod-ing by 1000000 before storing the numbers.
	 *  - I could also store the pentagonal numbers up to some arbetrary limit, say 1000000, and use those rather than calculate them each time.
	 */
	public static long calculateSolution() {
		map.put(0L, BigDecimal.ONE);
		map.put(1L, BigDecimal.ONE);

		for( long i = 2; i > 0; i++ ) {
			final BigDecimal piles = sum( i );

			if( piles.toString().endsWith("000000") ) {
				return i;
			}
		}
		return 0L;
	}

	private static BigDecimal sum( final long n ) {
		BigDecimal total = BigDecimal.ZERO;
		for( long k = 1; k <= n; k++ ) {
			final long a = (long)Math.pow(-1, k+1);
			final BigDecimal b = new BigDecimal("" + a);

			final long negativePart = n - negativePentNum(k);
			final long positivePart = n - positivePentNum(k);

			if( negativePart >= 0 ) {
				total = total.add( b.multiply( map.get(negativePart) ) );
			}
			if( positivePart >= 0 ) {
				total = total.add( b.multiply( map.get(positivePart) ) );
			}
			if( negativePart < 0 && positivePart < 0 ) {
				break;
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