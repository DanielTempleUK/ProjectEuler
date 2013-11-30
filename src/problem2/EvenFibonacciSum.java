package problem2;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class EvenFibonacciSum {

    public static void main(final String... args) {

	final List<BigDecimal> fibonacci = EvenFibonacciSum.getFibonacciSequence();

	BigDecimal total = BigDecimal.ZERO;

	for (final BigDecimal bigDecimal : fibonacci) {
	    if( bigDecimal.intValue() % 2 == 0 ) {
		total = total.add(bigDecimal);
	    }
	}

	System.out.println(total);
    }

    private static List<BigDecimal> getFibonacciSequence() {
	final List<BigDecimal> fibonacci = new ArrayList<BigDecimal>();

	BigDecimal i = BigDecimal.valueOf(1);
	BigDecimal j = BigDecimal.valueOf(2);
	BigDecimal k = BigDecimal.ZERO;

	fibonacci.add(i);
	fibonacci.add(j);

	do {
	    k = i.add(j);
	    fibonacci.add(k);

	    i = j;
	    j = k;
	}
	while( k.compareTo(BigDecimal.valueOf(4000000)) == -1 );

	System.out.println(fibonacci);

	return fibonacci;
    }
}