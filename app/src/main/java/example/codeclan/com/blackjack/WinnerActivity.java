package example.codeclan.com.blackjack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class WinnerActivity extends Activity {

    private ImageView winnerImage;
    private Button playAgainButton;
    private TextView winAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);
       getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        setFinishOnTouchOutside(false);

        WalletTracker tracker = new WalletTracker(this);


        winnerImage = (ImageView) findViewById(R.id.winnerView);
        playAgainButton = (Button) findViewById(R.id.playAgain_button);
        winAmount = (TextView) findViewById(R.id.winAmount);

        winAmount.setText(Integer.toString(Integer.parseInt(tracker.getCurrentBet()) * 2));

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String winner = extras.getString("winner");
        WalletTracker wallet = new WalletTracker(this);

        if(winner.equals("Player Wins!")){
            wallet.playerWin();
            winnerImage.setImageResource(R.drawable.playerwin);
        }else if(winner.equals("Dealer Wins!")) {
            winAmount.setVisibility(View.GONE);
            winnerImage.setImageResource(R.drawable.dealerwin);
        }
    }

    public void onPAclick(View button){
        Intent intent = new Intent(this, LaunchActivity.class);
        startActivity(intent);
    }

    public void onBackPressed(){
    }
}
