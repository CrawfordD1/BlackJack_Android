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

    public int Playerlogic(){
        if (player.getHandValue() == 21) {
            return 1;
        }
        while (player.getHandValue() < 21 && (inProgress)) {

            if (player.getHandValue() == 21) {
                System.out.println("Your total is: " + player.getHandValue());
                return 1;
            }
        }
        System.out.println("Your total is: " + player.getHandValue());
        if (player.getHandValue() > 21){
            return -1;
        }
        return 2;
    }

    public int DealerLogic(){
        if (dealer.getHandValue() == 21) {
            return -1;
        }
        while (dealer.getHandValue() <= 21) {
            if (dealer.getHandValue() < 17) {
                System.out.println("Dealer Hits!");
                dealer.addOnetoHand(deck);
                System.out.println(dealer.showOneCard());
                System.out.println("Dealer total is: " + dealer.getHandValue());
            }
            if (dealer.getHandValue() == 21) {
                System.out.println("Dealer total is: " + dealer.getHandValue());
                return -1;
            }
            if (dealer.getHandValue() > 21) {
                System.out.println("Dealer Bust!");
                return 1;
            }
            if(dealer.getHandValue() >=17 && dealer.getHandValue() <=21){
                if (dealer.getHandValue() >= player.getHandValue()){
                    return -1;
                }
                else
                    return 1;
            }
        }
        return -1;
    }

    public void getPlayerChoice(Choice playerChoice){
        switch (playerChoice){
            case HIT:
                player.addOnetoHand(deck);
                break;
            case STAND:
                inProgress = false;
                break;
        }
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
