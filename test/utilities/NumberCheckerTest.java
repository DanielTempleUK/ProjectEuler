package utilities;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

public class NumberCheckerTest {

	@Test
	public void thatNumbersAreTriangluar() {
		final long[] numbers = new long[]{1,3,6,10,15,21,28,36};

		for (final long l : numbers) {
			Assert.assertThat( NumberChecker.isTriangleNumber(l), CoreMatchers.is(true) );
		}
	}

	@Test
	public void thatNumbersAreSquare() {
		final long[] numbers = new long[]{1,4,9,16,25,36,49,64,81,100};

		for (final long l : numbers) {
			Assert.assertThat( NumberChecker.isSquareNumber(l), CoreMatchers.is(true) );
		}
	}

	@Test
	public void thatNumbersArePentagonal() {
		final long[] numbers = new long[]{1,5,12,22,35,51,70,92};

		for (final long l : numbers) {
			Assert.assertThat( NumberChecker.isPentagonalNumber(l), CoreMatchers.is(true) );
		}
	}

	@Test
	public void thatNumbersAreHexagonal() {
		final long[] numbers = new long[]{1,6,15,28,45,66,91,120};

		for (final long l : numbers) {
			Assert.assertThat( NumberChecker.isHexagonalNumber(l), CoreMatchers.is(true) );
		}
	}

	@Test
	public void thatNumbersAreHeptagonal() {
		final long[] numbers = new long[]{1,7,18,34,55,81,112,148};

		for (final long l : numbers) {
			Assert.assertThat( NumberChecker.isHeptagonalNumber(l), CoreMatchers.is(true) );
		}
	}

	@Test
	public void thatNumbersAreOctagonal() {
		final long[] numbers = new long[]{1,8,21,40,65,96,133,176};

		for (final long l : numbers) {
			Assert.assertThat( NumberChecker.isOctagonalNumber(l), CoreMatchers.is(true) );
		}
	}

	@Test
	public void thatNumbersAreNotTriangluar() {
		final long[] numbers = new long[]{2,4,7,11,16,22,29,37};

		for (final long l : numbers) {
			Assert.assertThat( NumberChecker.isTriangleNumber(l), CoreMatchers.is(false) );
		}
	}

	@Test
	public void thatNumbersAreNotSquare() {
		final long[] numbers = new long[]{2,5,10,17,26,37,50,65,82,101};

		for (final long l : numbers) {
			Assert.assertThat( NumberChecker.isSquareNumber(l), CoreMatchers.is(false) );
		}
	}

	@Test
	public void thatNumbersAreNotPentagonal() {
		final long[] numbers = new long[]{2,6,13,23,36,52,71,93};

		for (final long l : numbers) {
			Assert.assertThat( NumberChecker.isPentagonalNumber(l), CoreMatchers.is(false) );
		}
	}

	@Test
	public void thatNumbersAreNotHexagonal() {
		final long[] numbers = new long[]{2,7,16,29,46,67,92,121};

		for (final long l : numbers) {
			Assert.assertThat( NumberChecker.isHexagonalNumber(l), CoreMatchers.is(false) );
		}
	}

	@Test
	public void thatNumbersAreNotHeptagonal() {
		final long[] numbers = new long[]{2,8,19,35,56,82,113,149};

		for (final long l : numbers) {
			Assert.assertThat( NumberChecker.isHeptagonalNumber(l), CoreMatchers.is(false) );
		}
	}

	@Test
	public void thatNumbersAreNotOctagonal() {
		final long[] numbers = new long[]{2,9,22,41,66,97,134,177};

		for (final long l : numbers) {
			Assert.assertThat( NumberChecker.isOctagonalNumber(l), CoreMatchers.is(false) );
		}
	}
}