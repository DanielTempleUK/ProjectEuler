package problem54;

import java.util.HashSet;
import java.util.Set;

import utilities.CollectionUtils;

public enum HandType {
	ROYAL_FLUSH {
		@Override
		public Set<HandType> beats() {
			return CollectionUtils.setOf(HandType.STRAIGHT_FLUSH, HandType.FOUR_OF_A_KIND, HandType.FULL_HOUSE, HandType.FLUSH,
					HandType.STRAIGHT, HandType.THREE_OF_A_KIND, HandType.TWO_PAIR, HandType.ONE_PAIR, HandType.HIGH_CARD);
		}
	},
	STRAIGHT_FLUSH {
		@Override
		public Set<HandType> beats() {
			return CollectionUtils.setOf(HandType.FOUR_OF_A_KIND, HandType.FULL_HOUSE, HandType.FLUSH,
					HandType.STRAIGHT, HandType.THREE_OF_A_KIND, HandType.TWO_PAIR, HandType.ONE_PAIR, HandType.HIGH_CARD);
		}
	},
	FOUR_OF_A_KIND {
		@Override
		public Set<HandType> beats() {
			return CollectionUtils.setOf(HandType.FULL_HOUSE, HandType.FLUSH, HandType.STRAIGHT,
					HandType.THREE_OF_A_KIND, HandType.TWO_PAIR, HandType.ONE_PAIR, HandType.HIGH_CARD);
		}
	},
	FULL_HOUSE {
		@Override
		public Set<HandType> beats() {
			return CollectionUtils.setOf(HandType.FLUSH, HandType.STRAIGHT,
					HandType.THREE_OF_A_KIND, HandType.TWO_PAIR, HandType.ONE_PAIR, HandType.HIGH_CARD);
		}
	},
	FLUSH {
		@Override
		public Set<HandType> beats() {
			return CollectionUtils.setOf(HandType.STRAIGHT, HandType.THREE_OF_A_KIND, HandType.TWO_PAIR, HandType.ONE_PAIR, HandType.HIGH_CARD);
		}
	},
	STRAIGHT {
		@Override
		public Set<HandType> beats() {
			return CollectionUtils.setOf(HandType.THREE_OF_A_KIND, HandType.TWO_PAIR, HandType.ONE_PAIR, HandType.HIGH_CARD);
		}
	},
	THREE_OF_A_KIND {
		@Override
		public Set<HandType> beats() {
			return CollectionUtils.setOf(HandType.TWO_PAIR, HandType.ONE_PAIR, HandType.HIGH_CARD);
		}
	},
	TWO_PAIR {
		@Override
		public Set<HandType> beats() {
			return CollectionUtils.setOf(HandType.ONE_PAIR, HandType.HIGH_CARD);
		}
	},
	ONE_PAIR {
		@Override
		public Set<HandType> beats() {
			return CollectionUtils.setOf(HandType.HIGH_CARD);
		}
	},
	HIGH_CARD {
		@Override
		public Set<HandType> beats() {
			return new HashSet<HandType>();
		}
	};

	public abstract Set<HandType> beats();
}