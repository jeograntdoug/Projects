public class Card {
	private final Rank rank;
	private final Suit suit;

	public Card(Rank rank, Suit suit){
		this.rank = rank;
		this.suit = suit;
	}

	public Suit getSuit() {
		return suit;
	}
	public Rank getRank() {
		return rank;
	}
	public int getSuitNum() {
		return suit.getNum();
	}
	public int getRankNum() {
		return rank.getNum();
	}
}
