package problem54;

import java.util.HashSet;
import java.util.Set;

public enum HandType {
	ROYAL_FLUSH {
		@Override
		public Set<HandType> beats() {
			return new HashSet<HandType>(){{
				add(HandType.STRAIGHT_FLUSH);
				add(HandType.FOUR_OF_A_KIND);
				add(HandType.FULL_HOUSE);
				add(HandType.FLUSH);
				add(HandType.STRAIGHT);
				add(HandType.THREE_OF_A_KIND);
				add(HandType.TWO_PAIR);
				add(HandType.ONE_PAIR);
				add(HandType.HIGH_CARD);
			}};
		}
	},
	STRAIGHT_FLUSH {
		@Override
		public Set<HandType> beats() {
			return new HashSet<HandType>(){{
				add(HandType.FOUR_OF_A_KIND);
				add(HandType.FULL_HOUSE);
				add(HandType.FLUSH);
				add(HandType.STRAIGHT);
				add(HandType.THREE_OF_A_KIND);
				add(HandType.TWO_PAIR);
				add(HandType.ONE_PAIR);
				add(HandType.HIGH_CARD);
			}};
		}
	},
	FOUR_OF_A_KIND {
		@Override
		public Set<HandType> beats() {
			return new HashSet<HandType>(){{
				add(HandType.FULL_HOUSE);
				add(HandType.FLUSH);
				add(HandType.STRAIGHT);
				add(HandType.THREE_OF_A_KIND);
				add(HandType.TWO_PAIR);
				add(HandType.ONE_PAIR);
				add(HandType.HIGH_CARD);
			}};
		}
	},
	FULL_HOUSE {
		@Override
		public Set<HandType> beats() {
			return new HashSet<HandType>(){{
				add(HandType.FLUSH);
				add(HandType.STRAIGHT);
				add(HandType.THREE_OF_A_KIND);
				add(HandType.TWO_PAIR);
				add(HandType.ONE_PAIR);
				add(HandType.HIGH_CARD);
			}};
		}
	},
	FLUSH {
		@Override
		public Set<HandType> beats() {
			return new HashSet<HandType>(){{
				add(HandType.STRAIGHT);
				add(HandType.THREE_OF_A_KIND);
				add(HandType.TWO_PAIR);
				add(HandType.ONE_PAIR);
				add(HandType.HIGH_CARD);
			}};
		}
	},
	STRAIGHT {
		@Override
		public Set<HandType> beats() {
			return new HashSet<HandType>(){{
				add(HandType.THREE_OF_A_KIND);
				add(HandType.TWO_PAIR);
				add(HandType.ONE_PAIR);
				add(HandType.HIGH_CARD);
			}};
		}
	},
	THREE_OF_A_KIND {
		@Override
		public Set<HandType> beats() {
			return new HashSet<HandType>(){{
				add(HandType.TWO_PAIR);
				add(HandType.ONE_PAIR);
				add(HandType.HIGH_CARD);
			}};
		}
	},
	TWO_PAIR {
		@Override
		public Set<HandType> beats() {
			return new HashSet<HandType>(){{
				add(HandType.ONE_PAIR);
				add(HandType.HIGH_CARD);
			}};
		}
	},
	ONE_PAIR {
		@Override
		public Set<HandType> beats() {
			return new HashSet<HandType>(){{
				add(HandType.HIGH_CARD);
			}};
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
