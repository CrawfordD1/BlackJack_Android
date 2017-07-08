package example.codeclan.com.blackjack;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity implements View.OnClickListener{

    private Deck deck;
    private Player player;
    private Dealer dealer;
    private ArrayList<Card> playerList;
    private ArrayList<Card> dealerList;
    private HandAdaptor playerHandAdaptor;
    private HandAdaptor dealerHandAdaptor;
    private ListView playerListView;
    private ListView dealerListView;
    private TextView handValue;
    private TextView betView;
    private Game game;
    private ImageView cardback;
    private Button hitButton;
    private Button standButton;
    private WalletTracker walletTracker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerListView = (ListView) findViewById(R.id.player_Cards);
        dealerListView = (ListView) findViewById(R.id.dealer_Cards);
        handValue = (TextView) findViewById(R.id.hand_value_text);
        cardback = (ImageView) findViewById(R.id.card_back);
        standButton = (Button) findViewById(R.id.stand_button);
        hitButton = (Button) findViewById(R.id.hit_button);
        betView = (TextView)findViewById(R.id.betView);

        deck = new Deck();
        player = new Player();
        dealer = new Dealer();
        game = new Game(dealer, player);
        walletTracker = new WalletTracker(this);

        betView.setText("Bet: " + walletTracker.getCurrentBet());


        player.startHand(deck);
        dealer.startHand(deck);

        playerList = player.getCardList();
        dealerList = dealer.getCardList();

        playerHandAdaptor = new HandAdaptor(this, playerList);
        dealerHandAdaptor = new HandAdaptor(this, dealerList);

        playerListView.setAdapter(playerHandAdaptor);
        dealerListView.setAdapter(dealerHandAdaptor);
        handValue.setText("Current Hand Value: " + player.getHandValue());

        playerTurn();


    }

    @Override
    public void onClick(View v) {
    }


    public void onHitClick(View button){
        player.addOnetoHand(deck);
        playerHandAdaptor.notifyDataSetChanged();
        playerListView.setSelection(playerHandAdaptor.getPosition(player.getLastCard()));
        handValue.setText("Current Hand Value: " + player.getHandValue());
        playerTurn();
    }

    public void onStandClick(View button){
        cardback.setVisibility(View.GONE);
        hitButton.setEnabled(false);
        standButton.setEnabled(false);
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
        if(player.getHandValue() > dealer.getHandValue()){
            gameWon(game.displayWinner(1));
        }else{
            gameWon(game.displayWinner(-1));
        }
    }

    private void dealerTurn() {
        if (game.dealerWon() == 1) {
            gameWon(game.displayWinner(1));
        }if(game.dealerWon() == -1){
            gameWon(game.displayWinner(-1));
        }if(game.dealerWon() == 0){
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    dealer.addOnetoHand(deck);
                    dealerHandAdaptor.notifyDataSetChanged();
                }
            }, 1000);
          }
        }


    private void gameWon(String winner) {
        hitButton.setEnabled(false);
        standButton.setEnabled(false);
//        handValue.setText(winner);
        final Intent intent = new Intent(this, WinnerActivity.class);
        intent.putExtra("winner", winner);
        startActivity(intent);
            }

    public void playerTurn(){
            if (game.playerWon() == 1) {
                gameWon(game.displayWinner(1));
            }if(game.playerWon() == -1){
                gameWon(game.displayWinner(-1));
            }
    }

}
