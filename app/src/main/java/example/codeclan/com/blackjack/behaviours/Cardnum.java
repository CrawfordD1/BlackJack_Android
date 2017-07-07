package example.codeclan.com.blackjack.behaviours;

/**
 * Created by user on 06/07/2017.
 */

public enum Cardnum {
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(10),
    QUEEN(10),
    KING(10),
    ACE(11);


    private int cardNo;

    Cardnum (int cardNo){
        this.cardNo = cardNo;
    }

    public int getValue() {
        return this.cardNo;
    }
}
