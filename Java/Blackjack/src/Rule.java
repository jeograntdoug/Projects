import java.util.ArrayList;

public class Rule {
    private ArrayList<Player> players;
	private ArrayList<Player> nonPlayers = new ArrayList<>();
    private Deck deck;

    public Rule(ArrayList<Player> players, Deck deck) {
        this.players = players;
		this.deck = deck;
    }

    public void dealACard(){
        // Share the cards

		for(Player player: players){

			Card card = deck.popCard();
			player.pushCard(card);

        }
    }

    // Check if game is over;
    public boolean isGameover(){
		
        // If there is no player, gameover;
		int countDeath = 0;
		int countPlaying = 0;
		for(int i=1; i < players.size(); i++){
			if(players.get(i).getStatus() == Player.DEATH){
				countDeath++;
			}else if(players.get(i).getStatus() == Player.PLAYING){
				countPlaying++;
			}
		}
        return (countDeath == players.size()-1 
			|| (countPlaying == 0 &&  players.get(0).getCardCount() >= 17)
			|| players.get(0).getStatus() == Player.DEATH) 
					? true : false; 

    }

    // Print Card count
    public void printCardCount(){
		ArrayList<Player> allPlayers = new ArrayList<>();
		allPlayers.addAll(players);
		allPlayers.addAll(nonPlayers);

        for(Player player : allPlayers){

            System.out.print("Player["+player.getName()+"] ");

            switch(player.getStatus())
            {
                case Player.DEATH :
                    System.out.print(" is Burst\n");
                    break;
                case Player.STOP :
                case Player.PLAYING :
                    System.out.println("has "+player.getCardCount());
            }
        }
    }

    // Count cards
    public void countPlayersCard(){
		for(Player player: players){
			if(player.getStatus() != Player.PLAYING){
				continue;
			}

			ArrayList<Card> unCheckedCardList = player.getUnCheckedCardList();

			for(Card card: unCheckedCardList){
				if(card.getRank().equals(Rank.ACE)){
					player.increaseOneCountAce();
				}
				player.addCardCount(card.getRank());

				// Blackjack
				if(player.getCardCount() == 21) {

					player.setStatus(Player.STOP);
					break;
				}

				if( (player.getName().equals("Dealer"))
						&& (player.getCardCount() >=17)
				 		&& (player.getCardCount() <21 )){

					player.setStatus(Player.STOP);
				}

				// player.cardCount is greater than 21, find Ace then convert 11 to 1
				while(player.getCardCount()>21) {

					// if there is ace in player's hand
					// recalculate Ace as 10 to 1
					if(player.getCountAce() > 0) {

						player.reduceCardCount(Rank.TEN);
						player.decreaseOneCountAce();

					} else {// there is no Ace& greater than 21, break;
						player.setStatus(Player.DEATH);
						break;
					}
				}
			}
			while(unCheckedCardList.size() != 0){
				unCheckedCardList.remove(0);
			}
		}
    }
}
