package problem66;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utilities.NumberChecker;

/**
 * Solution to problem 66:
 * 
 * Consider quadratic Diophantine equations of the form:
 * 
 * x^2 – D*(y^2) = 1
 * 
 * For example, when D=13, the minimal solution in x is 6492 – 13×1802 = 1.
 * 
 * It can be assumed that there are no solutions in positive integers when D is square.
 * 
 * By finding minimal solutions in x for D = {2, 3, 5, 6, 7}, we obtain the following:
 * 
 * 3^2 - 2x2^2 = 1
 * 2^2 - 3x1^2 = 1
 * 9^2 - 5x4^2 = 1
 * 5^2 - 6x2^2 = 1
 * 8^2 - 7x3^2 = 1
 * 
 * Hence, by considering minimal solutions in x for D <= 7, the largest x is obtained when D=5.
 * 
 * Find the value of D <= 1000 in minimal solutions of x for which the largest value of x is obtained.
 */
public class DiophantineEquation {

	public static void main(final String[] args) {
		final long startTime = System.currentTimeMillis();

		System.out.println("The answer is: " + calculateSolution());

		final long endTime = System.currentTimeMillis();
		System.out.println("The solution took: " + (endTime - startTime) + " milliseconds");
	}

	private static long calculateSolution() {

		final List<Integer> dValues = new ArrayList<Integer>();

		for (int i = 1; i <= 1000; i++) {
			if( NumberChecker.isSquareNumber(i) ) {
				continue;
			}
			dValues.add(i);
		}

		final Map<Long, Integer> map = new HashMap<Long, Integer>();

		for (final Integer d : dValues) {
			long xSquared = -1;
			long y = 0;
			while ( !NumberChecker.isSquareNumber(xSquared) ) {
				y++;
				final long ySquared = y * y;
				final long ySquaredTimesD = ySquared * d;
				xSquared = ySquaredTimesD + 1;
			}

			final double x = Math.sqrt(xSquared);
			System.out.println("D=" + d + ", x=" + (long)x + ", y=" + y);
			map.put((long)x, d);
		}

		final List<Long> xValues = new ArrayList<Long>(map.keySet());
		Collections.sort(xValues);
		Collections.reverse(xValues);
		return map.get(xValues.get(0));
	}
}