package problem1;

import java.math.BigDecimal;

public class MultiplesOf3And5 {

    public static void main(final String... args) {
	BigDecimal total = BigDecimal.ZERO;

	for ( int i = 0; i < 1000; i++) {
	    if( i % 3 == 0 || i % 5 == 0 ) {
		total = total.add(BigDecimal.valueOf(i));
	    }
	}

	System.out.println(total);
    }
}