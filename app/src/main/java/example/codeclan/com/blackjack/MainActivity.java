package example.codeclan.com.blackjack;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnClickListener{

    private Deck deck;
    private Player player;
    private ArrayList<Card> list;
    private HandAdaptor handAdaptor;
    private ListView listView;
    private TextView handValue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.player_Cards);
        handValue = (TextView) findViewById(R.id.hand_value_text);
        deck = new Deck();
        player = new Player();



        player.startHand(deck);
        list = player.getCardList();
        handAdaptor = new HandAdaptor(this, list);

        listView.setAdapter(handAdaptor);
        handValue.setText("Current Hand Value: " + player.getHandValue());
    }

    @Override
    public void onClick(View v) {
    }

    public void onHitClick(View button){
        player.addOnetoHand(deck);
        handAdaptor.notifyDataSetChanged();
        listView.setSelection(handAdaptor.getPosition(player.getLastCard()));
        handValue.setText("Current Hand Value: " + player.getHandValue());

    }
}
