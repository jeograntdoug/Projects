// For random int(nextInt(int))
import java.security.SecureRandom;//FIXME to Math.random()
import java.util.ArrayList;

public class Deck {

	// To Track cards left in the deck
	private ArrayList<Card> cardsInDeck = new ArrayList<>();

	// Random number // Math.random() is enough
	private SecureRandom random = new SecureRandom();

	public Deck() {

		for(Suit suit : Suit.values()){
			for(Rank rank : Rank.values()){
				cardsInDeck.add(new Card(rank,suit));
			}
		}
	}

	public int getNumCards(){
		return cardsInDeck.size();
	}

	public Card popCard() {

		// There is no card in the deck
		if(cardsInDeck.size()== 0){
			System.out.println("The deck is empty");
			return null;
		}

		// choose random card
		int randomInt = random.nextInt(cardsInDeck.size());

		// Remove the card from the deck
		return cardsInDeck.remove(randomInt);
	}

	// Player gives a card back to deck
	public void pushCard(Card card) {

		// The deck is full
		if(cardsInDeck.size()== 52){
			System.out.println("Deck is Full");
			return;
		}

		cardsInDeck.add(card);
	}
}
