package example.codeclan.com.blackjack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class WinnerActivity extends Activity {

    private ImageView winnerImage;
    private Button playAgainButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);

        winnerImage = (ImageView) findViewById(R.id.winnerView);
        playAgainButton = (Button) findViewById(R.id.playAgain_button);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String winner = extras.getString("winner");
        WalletTracker wallet = new WalletTracker(this);

        if(winner.equals("Player Wins!")){
            wallet.playerWin();
            winnerImage.setImageResource(R.drawable.diamonds);
        }else if(winner.equals("Dealer Wins!")) {
            winnerImage.setImageResource(R.drawable.card_back);
        }
    }

    public void onPAclick(View button){
        Intent intent = new Intent(this, LaunchActivity.class);
        startActivity(intent);
    }
}
