public enum Suit {
	DIAMONDS(0), CLUBS(1), HEARTS(2), SPADES(3);

	private int numSuit;

	Suit(int numSuit){
		this.numSuit = numSuit;
	}

	public int getNum(){
		return numSuit;
	}
	
}
