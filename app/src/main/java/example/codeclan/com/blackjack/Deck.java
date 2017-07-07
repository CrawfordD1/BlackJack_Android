package example.codeclan.com.blackjack;

import java.util.ArrayList;
import java.util.Random;

import example.codeclan.com.blackjack.behaviours.Cardnum;
import example.codeclan.com.blackjack.behaviours.Suit;

/**
 * Created by user on 06/07/2017.
 */

public class Deck {

    private ArrayList<Card> deck;

    public Deck(){
        this.deck = new ArrayList<Card>();
        populateDeck();
    }

    public int deckSize(){
        return this.deck.size();
    }

    public void addToDeck(Card card){
        this.deck.add(card);
    }

    public void populateDeck() {
        for (Cardnum cardnum : Cardnum.values()) {
            for (Suit suit: Suit.values()) {
                Card card = new Card(cardnum, suit);
                addToDeck(card);
            }
        }
    }

    public Card getCard(){
        Random random = new Random();
        int index = random.nextInt(this.deck.size());
        return this.deck.remove(index);
    }
}
