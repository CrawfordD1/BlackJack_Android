package example.codeclan.com.blackjack;

import example.codeclan.com.blackjack.behaviours.Cardnum;
import example.codeclan.com.blackjack.behaviours.Suit;

/**
 * Created by user on 06/07/2017.
 */

public class Card {

    private Suit suit;
    private Cardnum cardnum;

    public Card(Cardnum cardnum, Suit suit){
        this.cardnum = cardnum;
        this.suit = suit;
    }

    public Suit getSuit() {
        return this.suit;
    }

    public int getCardno() {
        return this.cardnum.getValue();
    }

    public String getCardnoDisplay(){

        if(this.cardnum == Cardnum.KING){
            return "K";
        }else if(this.cardnum == Cardnum.QUEEN){
            return "Q";
        }else if(this.cardnum == Cardnum.JACK) {
            return "J";
        }else if(this.cardnum == Cardnum.ACE) {
            return "A";
        }
        return Integer.toString(this.cardnum.getValue());
    }

    public String fullCard(){
        return ("- " + this.cardnum + " of " + this.suit);
    }

    public int getImage(){
        Suit suit = this.suit;
        switch (suit){
            case CLUBS:
                return R.drawable.clubs;
            case SPADES:
                return R.drawable.spades;
            case HEARTS:
                return R.drawable.hearts;
            case DIAMONDS:
                return R.drawable.diamonds;
            default:
                return 0;
        }
    }


}













