/* Blackjack.java
 *  Rule for blackjack
 */
//Import Scanner class
import java.util.Scanner;
import java.util.ArrayList;

public class Casino{
    public static void main(String[] args){
        /* Declaration Phase */
        int numPlayers;     // Number of players
        Scanner input = new Scanner(System.in);

        String[] name = {"Dealer","Katter","Saulo","Heok"};
        // players[0] is dealer
        ArrayList<Player> players = new ArrayList<>();


        /* Initialization Phase */
        // initialize class Player with name
        // players[0] is dealer
        for(int i=0; i<name.length ; i++){
            players.add(new Player(name[i]));
        }

        Rule Blackjack = new Rule(players,new Deck());
        /* Processing Phase */

        // Deal cards 2 times
        Blackjack.dealACard();
        Blackjack.dealACard();
		Blackjack.countPlayersCard();
        Blackjack.printCardCount();

        // If anyone has blackjack, game over
        for(Player player : players){
            if(player.isBlackjack()) {
                System.out.println("BLACKJACK!!");
                return;
            }
        }

        // Deal the cards
        while(!Blackjack.isGameover()){

            // Prompt to ask if players want to continue
            // player[0] is dealer(exclude)
            for(int i=1; i<players.size(); i++ ) {

                // Ask only player who currently plays
                if(players.get(i).getStatus() == Player.PLAYING ) {
                    System.out.print(players.get(i).getName() +" Wanna Stop?(yes/no) ");
                    String answer = input.nextLine();

                    if (answer.toLowerCase().equals("yes")) {
                        players.get(i).setStatus(Player.STOP);
                    }
                }
            }

			if( Blackjack.isGameover()){
			   	break;
			}	   
            Blackjack.dealACard();
			Blackjack.countPlayersCard();
            Blackjack.printCardCount();
        }
		System.out.println("GAME OVER");
    }
}
