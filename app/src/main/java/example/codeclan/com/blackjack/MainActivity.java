package example.codeclan.com.blackjack;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnClickListener{

    private Deck deck;
    private Player player;
    private Dealer dealer;
    private HandAdaptor playerHandAdaptor;
    private HandAdaptor dealerHandAdaptor;
    private ListView playerListView;
    private ListView dealerListView;
    private TextView handValue;
    private TextView dealerValue;
    private Game game;
    private ImageView cardback;
    private Button hitButton;
    private Button standButton;
    private CountDownTimer dealerTimer;
    private WalletTracker walletTracker;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerListView = (ListView) findViewById(R.id.player_Cards);
        dealerListView = (ListView) findViewById(R.id.dealer_Cards);
        handValue = (TextView) findViewById(R.id.hand_value_text);
        dealerValue = (TextView) findViewById(R.id.dealer_value_text);
        cardback = (ImageView) findViewById(R.id.card_back);
        standButton = (Button) findViewById(R.id.stand_button);
        hitButton = (Button) findViewById(R.id.hit_button);
        TextView betView = (TextView) findViewById(R.id.betView);

        deck = new Deck();
        player = new Player();
        dealer = new Dealer();
        game = new Game(dealer, player);
        walletTracker = new WalletTracker(this);

        betView.setText("Bet: " + walletTracker.getCurrentBet());


        player.startHand(deck);
        dealer.startHand(deck);

        ArrayList<Card> playerList = player.getCardList();
        ArrayList<Card> dealerList = dealer.getCardList();

        playerHandAdaptor = new HandAdaptor(this, playerList);
        dealerHandAdaptor = new HandAdaptor(this, dealerList);

        playerListView.setAdapter(playerHandAdaptor);
        dealerListView.setAdapter(dealerHandAdaptor);
        handValue.setText("Current Hand Value: " + player.getHandValue());
        dealerValue.setText("Dealer Hand Value: " + (dealer.getHandValue() - dealer.getLastCard().getCardno()));

        playerTurn();

    }

    @Override
    public void onClick(View v) {
    }

    public void onHitClick(View button){
        // Player presses Hit button
        player.addOnetoHand(deck);
        playerHandAdaptor.notifyDataSetChanged(); //updates the listview after adding card
        playerListView.setSelection(playerHandAdaptor.getPosition(player.getLastCard())); // moves list view to select latest added card.
        handValue.setText("Current Hand Value: " + player.getHandValue());
        //playerTurn performs checks to see if they have won
        playerTurn();
    }

    public void onStandClick(View button){
        //after stand sets all buttons to be inactive.
        //removes card back covering the dealers second card
        cardback.setVisibility(View.INVISIBLE);
        hitButton.setEnabled(false);
        standButton.setEnabled(false);
        //loops dealer turn while less than 17, checks winner when above 17.
        while(dealer.getHandValue() < 17) {
            dealerTurn();
        }if(dealer.getHandValue() >=17 && dealer.getHandValue() < 21){
            compareValues();
        }if(dealer.getHandValue() == 21){
            gameWon(game.displayWinner(-1));
        }if(dealer.getHandValue() > 21){
            gameWon(game.displayWinner(1));
        }
    }

    public void compareValues(){
        // compares hand values at the end of game to find definite winner, forces dealer win on draw.
        if(player.getHandValue() > dealer.getHandValue()){
            gameWon(game.displayWinner(1));
        }else{
            gameWon(game.displayWinner(-1));
        }
    }

    private void dealerTurn() {
                if (game.dealerWon() == 1) {
                    gameWon(game.displayWinner(1));
                }
                if (game.dealerWon() == -1) {
                    gameWon(game.displayWinner(-1));
                }
                if (game.dealerWon() == 0) {

                    dealer.addOnetoHand(deck);
                    //timer to reduce speed of game, allow the user to see whats happening.
                    CountDownTimer gameTimer = new CountDownTimer(1000, 1000) {

                        @Override
                        public void onTick(long millisUntilFinished) {
                            // do something after 1s
                        }

                        @Override
                        public void onFinish() {
                            //do something when timer finished.
                            dealerValue.setText("Dealer Hand Value: " + dealer.getHandValue());
                            dealerHandAdaptor.notifyDataSetChanged();
                        }

                    }.start();
                    //delays everything for a second.
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
    }




    private void gameWon(final String winner) {
        hitButton.setEnabled(false);
        standButton.setEnabled(false);
        final Intent intent = new Intent(getBaseContext(), WinnerActivity.class);
        intent.putExtra("winner", winner);
        dealerValue.setText("Dealer Hand Value: " + dealer.getHandValue());
        dealerListView.setSelection(dealerHandAdaptor.getPosition(dealer.getLastCard()));


         CountDownTimer gameTimer = new CountDownTimer(3000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                // do something after 1s
            }

            @Override
            public void onFinish() {
                startActivity(intent);
            }

        }.start();

    }

    public void playerTurn(){
            if (game.playerWon() == 1) {
                gameWon(game.displayWinner(1));
            }if(game.playerWon() == -1){
                gameWon(game.displayWinner(-1));
            }
    }

    public void onBackPressed(){
        walletTracker.undoBet();
        Intent intent = new Intent(this, LaunchActivity.class);
        startActivity(intent);
    }
}

