package example.codeclan.com.blackjack.behaviours;

/**
 * Created by user on 06/07/2017.
 */

public enum Suit {

    SPADES("Spades"),
    DIAMONDS("Diamonds"),
    CLUBS("Clubs"),
    HEARTS("Hearts");

    private String cardsuit;

    Suit(String cardsuit){
        this.cardsuit = cardsuit;
    }

    public String getCardsuit() {
        return this.cardsuit;
    }
}
