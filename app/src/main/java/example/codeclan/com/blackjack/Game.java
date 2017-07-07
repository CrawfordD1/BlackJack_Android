package example.codeclan.com.blackjack;

import example.codeclan.com.blackjack.behaviours.Choice;

/**
 * Created by user on 06/07/2017.
 */

public class Game {

    private Player player;
    private Dealer dealer;
    private Deck deck;
    private boolean inProgress;


    public Game(Dealer dealer, Player player){
        this.player = player;
        this.dealer = dealer;
        deck = new Deck();
        this.inProgress =  true;

    }

    public int playerWon(){
        if (player.getHandValue() == 21) {
            return 1;
        }
        if (player.getHandValue() < 21){
            return 0;
        }
        if (player.getHandValue() > 21){
            return -1;
        }
        return 0;
    }

    public int dealerWon(){
        if (player.getHandValue() == 21) {
            return -1;
        }
        if (player.getHandValue() < 21){
            return 0;
        }
        if (player.getHandValue() > 21){
            return 1;
        }
        return 0;
    }

    public String displayWinner(int result){
        switch(result){
            case 1 :
                return "Player Wins!";
            case -1 :
                return "Dealer Wins!";
            default:
                return null;
        }

    }
}
