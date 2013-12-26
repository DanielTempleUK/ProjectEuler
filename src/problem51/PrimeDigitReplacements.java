package problem51;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import utilities.PrimalityChecker;

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
	final List<Long> primes = getPrimes();

	for(final Long prime : primes) {
	    String primeString = ""+prime;
	    primeString = "0" + primeString.substring(1);
	    for (int j = 0; j < 6; j++) {
		int numberOfPrimes = 0;
		int numberOfNonPrimes = 0;

		for (int i = 1; i < 10; i++) {
		    if( numberOfNonPrimes > 2) {
			break;
		    }
		    final char[] charArray = primeString.toCharArray();
		    charArray[j] = (""+i).toCharArray()[0];
		    final String replace = new String(charArray);
		    if( primes.contains(Long.valueOf(replace)) ) {
			numberOfPrimes++;
		    }
		    else {
			numberOfNonPrimes++;
		    }
		}
		if(numberOfPrimes == 8){
		    return prime;
		}
	    }
	}
	return 0L;
    }

    private static List<Long> getPrimes() {
	final List<Long> primes = new ArrayList<Long>();
	for (long i = 99999; i <= 1000000; i++) {
	    if( PrimalityChecker.isPrime(i) ) {
		primes.add(i);
	    }
	}
	return primes;
    }
}