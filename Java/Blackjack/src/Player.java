/* Player.java
*  Class for player and dealer
*/
import java.util.ArrayList;

public class Player {
    private String name;
    private int status = PLAYING;
    private int numCards = 0;
    private int countAce = 0;
    private int cardCount = 0;

    // Cards in the player's hand
    private ArrayList<Card> checkedCardList= new ArrayList<>();
	private ArrayList<Card> unCheckedCardList = new ArrayList<>();

    static final int PLAYING = 0;
    static final int DEATH = 1;        // Burst
    static final int STOP = 2;

    // Initialize Player class
    // Player doesn't have cards at the beginning
    public Player(String name) {
        this.name = name;
    }

    // Getters
    public String getName() { return name; }
    public int getStatus(){ return status; }
    public int getCardCount() {return cardCount;}
    public int getCountAce() {return countAce;}
	public ArrayList<Card> getUnCheckedCardList(){ return unCheckedCardList; }
    // Setters
    public void setStatus(int status){ this.status = status;}

    public void increaseOneCountAce() { countAce++; }
    public void decreaseOneCountAce() { countAce--;}

	// All Rank number is -1 of original value for array convenience
	// count + 1
    public void addCardCount(Rank rank){ 
		if(rank.equals(Rank.ACE)){
			this.cardCount += 11;
		}else if(rank.equals(Rank.JACK) || rank.equals(Rank.QUEEN) || rank.equals(Rank.KING)){
			this.cardCount += 10;
		} else {
			this.cardCount += rank.getNum()+1; 
		}
	}
    public void reduceCardCount(Rank rank) { this.cardCount -= rank.getNum()+1; }

    // Give a card away
    public Card popCard(Card card) {

        // Card Validation
        if(!isPlaying()) {
            return null;
        }

        // Remove the card from player's hand
		return checkedCardList.remove(checkedCardList.indexOf(card));
    }

    // Take a card
    public void pushCard(Card card) {
        // Card Validation for pushCard
        if(!isPlaying()) {
            return;
        }

		unCheckedCardList.add(card);
    }

    public boolean isBlackjack(){
        return cardCount == 21 ? true: false;
    }

    // Validators
    private boolean isPlaying(){
        return status == PLAYING ? true : false;
    }

}
