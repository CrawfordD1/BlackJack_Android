package example.codeclan.com.blackjack;

import android.content.Intent;
import android.os.CountDownTimer;

import java.util.ArrayList;

/**
 * Created by user on 06/07/2017.
 */

public class Hand {

    ArrayList<Card> hand;


    public Hand(){
        this.hand = new ArrayList<Card>();
    }



    public void startHand(Deck deck){
        this.hand.add(deck.getCard());
        this.hand.add(deck.getCard());
    }


    public void addOnetoHand(Deck deck){
        this.hand.add(deck.getCard());
    }

    public void showHand(){
        for (Card card: this.hand) {
            System.out.println(card.fullCard());
        }
    }

    public String showOneCard(){
        int index = getHandCardTotal() - 1;
        Card card = this.hand.get(index);
        return card.fullCard();
    }

    public int getHandCardTotal(){
        return this.hand.size();
    }

    public int getHandValue(){
        int checkAce = 0;
        int totalValue = 0;
        for (Card card: this.hand) {
            totalValue += card.getCardno();
            if(card.getCardno() == 11) {
                checkAce += 1;
            }
        }
        if(totalValue >21){
            totalValue -= (10 * checkAce);
            if(totalValue < 12){
                totalValue += 10;
            }
        }
        return totalValue;
    }

    public Card getLastCard(){
        int index = getHandCardTotal() - 1;
        return this.hand.get(index);
    }

    public ArrayList<Card> getCardList() {
        return this.hand;
    }
}
