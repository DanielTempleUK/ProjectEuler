package problem54;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Hand {
	private static final char[][] STRAIGHTS = {
		{'2', '3', '4', '5', '6'},
		{'3', '4', '4', '6', '7'},
		{'4', '5', '5', '7', '8'},
		{'5', '6', '6', '8', '9'},
		{'6', '7', '7', '9', 'T'},
		{'7', '8', '8', 'J', 'T'},
		{'8', '9', 'J', 'Q', 'T'},
		{'9', 'J', 'K', 'Q', 'T'},
		{'A', 'J', 'K', 'Q', 'T'}
	};


	final List<String> cards = new ArrayList<String>();
	int highCard = 0;

	public void addCard(final String card) {
		this.cards.add(card);
		Collections.sort(this.cards);
	}

	public boolean beats(final Hand player2Hand) {
		final HandType player1HandType = convertHand();
		final HandType player2HandType = player2Hand.convertHand();

		if( player1HandType.beats().contains(player2HandType) ) {
			return true;
		}

		if( player2HandType.beats().contains(player1HandType) ) {
			return false;
		}

		if( getHighCardValue() > player2Hand.getHighCardValue() ) {
			return true;
		}

		return false;
	}

	public HandType convertHand() {
		if( isRoyalFlush() ) {
			return HandType.ROYAL_FLUSH;
		}
		if( isStraightFlush() ) {
			this.highCard = getHighCardValueFrom(getSortedCardValues());
			return HandType.STRAIGHT_FLUSH;
		}
		if( isFourOfAKind() ) {
			final char fourOfAKindValue = getFourOfAKindValue();
			this.highCard = new String(getSortedCardValues()).replace(""+fourOfAKindValue, "").toCharArray()[0];
			return HandType.FOUR_OF_A_KIND;
		}
		if( isFullHouse() ) {
			this.highCard = getHighCardValueFrom(getSortedCardValues());
			return HandType.FULL_HOUSE;
		}
		if( isFlush() ) {
			this.highCard = getHighCardValueFrom(getSortedCardValues());
			return HandType.FLUSH;
		}
		if( isStraight() ) {
			this.highCard = getHighCardValueFrom(getSortedCardValues());
			return HandType.STRAIGHT;
		}
		if( isThreeOfAKind() ) {
			final char threeOfAKindValue = getThreeOfAKindValue();
			this.highCard = new String(getSortedCardValues()).replace(""+threeOfAKindValue, "").toCharArray()[0];
			return HandType.THREE_OF_A_KIND;
		}
		if( isTwoPair() ) {
			final char pairValue = getPairValue();
			final char[] onePairMissing = new String(getSortedCardValues()).replace(""+pairValue, "").toCharArray();
			final char secondPairValue = getPairValueFrom(onePairMissing);
			this.highCard = new String(onePairMissing).replace(""+secondPairValue, "").toCharArray()[0];
			return HandType.TWO_PAIR;
		}
		if( isOnePair() ) {
			final char pairValue = getPairValue();
			this.highCard = new String(getSortedCardValues()).replace(""+pairValue, "").toCharArray()[0];
			return HandType.ONE_PAIR;
		}
		this.highCard = getHighCardValueFrom(getSortedCardValues());
		return HandType.HIGH_CARD;
	}

	/* HAND LOGIC */

	public boolean isTwoPair() {
		final char[] cardValues = getSortedCardValues();

		int pairs = 0;
		for (int i = 0; i < cardValues.length; i++) {
			for (int j = i+1; j < cardValues.length; j++) {
				if( isPair(new char[]{cardValues[i], cardValues[j]} ) ) {
					pairs++;
				}
			}
		}

		return pairs == 2;
	}

	public boolean isOnePair() {
		final char[] cardValues = getSortedCardValues();

		int pairs = 0;
		for (int i = 0; i < cardValues.length; i++) {
			for (int j = i+1; j < cardValues.length; j++) {
				if(isPair(new char[]{cardValues[i], cardValues[j]} )) {
					pairs++;
				}
			}
		}

		return pairs == 1;
	}

	public boolean isFullHouse() {
		if ( isThreeOfAKind() ) {
			final char threeOfAKindValue = getThreeOfAKindValue();
			final String valuesString = new String(getSortedCardValues());
			final char[] charArray = valuesString.replace(""+threeOfAKindValue, "").toCharArray();
			return isPair(charArray);
		}
		return false;
	}

	public int getHighCardValueFrom(final char[] sortedCardValues) {
		final String orderValues = "23456789TJQKA";
		int highestValue = orderValues.indexOf(sortedCardValues[0]);

		for (int i = 1; i < sortedCardValues.length; i++) {
			if( orderValues.indexOf(sortedCardValues[i]) > orderValues.indexOf(highestValue) ) {
				highestValue = orderValues.indexOf(sortedCardValues[i]);
			}
		}
		return highestValue;
	}

	public boolean isThreeOfAKind() {
		final char[] sortedCardValues = getSortedCardValues();

		final String valuesString = new String(sortedCardValues);

		for (int i = 0; i < sortedCardValues.length; i++) {
			if(valuesString.replace(""+sortedCardValues[i], "").length() == 2 ) {
				return true;
			}
		}
		return false;
	}

	public char getPairValue() {
		final char[] sortedCardValues = getSortedCardValues();

		return getPairValueFrom(sortedCardValues);
	}

	public char getPairValueFrom(final char[] input) {
		final String valuesString = new String(input);

		for (int i = 0; i < input.length; i++) {
			if(valuesString.replace(""+input[i], "").length() == (input.length - 2) ) {
				return input[i];
			}
		}
		return 0;
	}

	public char getThreeOfAKindValue() {
		final char[] sortedCardValues = getSortedCardValues();

		final String valuesString = new String(sortedCardValues);

		for (int i = 0; i < sortedCardValues.length; i++) {
			if(valuesString.replace(""+sortedCardValues[i], "").length() == 2 ) {
				return sortedCardValues[i];
			}
		}
		return 0;
	}

	public char getFourOfAKindValue() {
		final char[] sortedCardValues = getSortedCardValues();

		final String valuesString = new String(sortedCardValues);

		for (int i = 0; i < sortedCardValues.length; i++) {
			if(valuesString.replace(""+sortedCardValues[i], "").length() == 1 ) {
				return sortedCardValues[i];
			}
		}
		return 0;
	}

	public boolean isFourOfAKind() {
		final char[] sortedCardValues = getSortedCardValues();

		final String valuesString = new String(sortedCardValues);

		for (int i = 0; i < sortedCardValues.length; i++) {
			if(valuesString.replace(""+sortedCardValues[i], "").length() == 1 ) {
				return true;
			}
		}
		return false;
	}

	public boolean isFlush() {
		boolean isFlush = true;
		final char firstSuit = this.cards.get(0).toCharArray()[1];

		for (final String card : this.cards) {
			if( firstSuit != card.toCharArray()[1] ) {
				isFlush = false;
				break;
			}
		}

		return isFlush;
	}

	public boolean isRoyalFlush() {
		if( isFlush() ) {
			final char[] cardValues = getSortedCardValues();
			return Arrays.equals(cardValues, STRAIGHTS[8]);
		}
		return false;
	}

	public boolean isStraight() {
		if( isFlush() ) {
			final char[] cardValues = getSortedCardValues();

			for(int i = 0; i < STRAIGHTS.length; i++) {
				if( Arrays.equals(cardValues, STRAIGHTS[i]) ) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean isStraightFlush() {
		return isStraight() && isFlush();
	}

	/* HELPER METHODS */

	private char[] getSortedCardValues() {
		final char[] cardValues = new char[5];

		for (final String card : this.cards) {
			cardValues[this.cards.indexOf(card)] = card.toCharArray()[0];
		}

		Arrays.sort(cardValues);
		return cardValues;
	}

	private boolean isPair(final char[] charArray) {
		return charArray[0] == charArray[1];
	}

	public int getHighCardValue() {
		return this.highCard;
	}
}