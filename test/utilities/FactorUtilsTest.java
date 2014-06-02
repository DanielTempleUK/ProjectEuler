package utilities;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class FactorUtilsTest {

	@Test
	public void thatFactorisingWorks() {
		final long n = 600851475143L;
		final String factor = FactorUtils.primeFactorise(n);
		assertThat(factor, is("71X839X1471X6857"));
	}

	@Test
	public void thatLeastFactorWorks() {
		long n = 23*13*7;
		long leastFactor = FactorUtils.smallestPrimeFactor(n);
		assertThat(leastFactor, is(7L));

		n = 2*7*53;
		leastFactor = FactorUtils.smallestPrimeFactor(n);
		assertThat(leastFactor, is(2L));

		n = 3*7*53;
		leastFactor = FactorUtils.smallestPrimeFactor(n);
		assertThat(leastFactor, is(3L));

		n = 5*7*53;
		leastFactor = FactorUtils.smallestPrimeFactor(n);
		assertThat(leastFactor, is(5L));

		n = 1;
		leastFactor = FactorUtils.smallestPrimeFactor(n);
		assertThat(leastFactor, is(1L));

		n = 13*19*53;
		leastFactor = FactorUtils.smallestPrimeFactor(n);
		assertThat(leastFactor, is(13L));

		n = 600851475143L;
		leastFactor = FactorUtils.smallestPrimeFactor(n);
		assertThat(leastFactor, is(71L));
	}

	@Test
	public void thatHighestCommonFactorWorks() {
		long n = 23*13*7;
		long m = 23*13*9;
		long highestCommonFactor = FactorUtils.getHighestCommonFactor(n, m);
		assertThat(highestCommonFactor, is(23L));

		n = 2*7*53;
		m = 23*13*9;
		highestCommonFactor = FactorUtils.getHighestCommonFactor(n, m);
		assertThat(highestCommonFactor, is(1L));
	}
}