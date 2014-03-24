package problem54;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class HandTest {

	@Test
	public void testRoyalFlush() {
		final Hand hand = new Hand();
		hand.addCard("TS");
		hand.addCard("JS");
		hand.addCard("QS");
		hand.addCard("KS");
		hand.addCard("AS");

		assertTrue(hand.isFlush());
		assertTrue(hand.isStraight());
		assertTrue(hand.isRoyalFlush());
	}

	@Test
	public void testStraight23456() {
		final Hand hand = new Hand();
		hand.addCard("2H");
		hand.addCard("3H");
		hand.addCard("4H");
		hand.addCard("5C");
		hand.addCard("6H");

		assertTrue(hand.isStraight());
	}

	@Test
	public void testFlush() {
		final Hand hand = new Hand();
		hand.addCard("2S");
		hand.addCard("JS");
		hand.addCard("7S");
		hand.addCard("KS");
		hand.addCard("8S");

		assertTrue(hand.isFlush());
	}

	@Test
	public void testFourOfAKind() {
		final Hand hand = new Hand();
		hand.addCard("2S");
		hand.addCard("2C");
		hand.addCard("2H");
		hand.addCard("2D");
		hand.addCard("8S");

		assertTrue(hand.isFourOfAKind());
	}

	@Test
	public void testThreeOfAKind() {
		final Hand hand = new Hand();
		hand.addCard("2S");
		hand.addCard("2C");
		hand.addCard("2H");
		hand.addCard("4D");
		hand.addCard("8S");

		assertTrue(hand.isThreeOfAKind());
		assertEquals('2', hand.getThreeOfAKindValue());
	}

	@Test
	public void testTwoPair() {
		final Hand hand = new Hand();
		hand.addCard("5C");
		hand.addCard("5D");
		hand.addCard("9C");
		hand.addCard("AC");
		hand.addCard("AD");

		assertTrue(hand.isTwoPair());
	}

	@Test
	public void testOnePair() {
		final Hand hand = new Hand();
		hand.addCard("5C");
		hand.addCard("5D");
		hand.addCard("9C");
		hand.addCard("AC");
		hand.addCard("KD");

		assertTrue(hand.isOnePair());
	}
}
