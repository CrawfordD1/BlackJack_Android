package example.codeclan.com.blackjack;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class LaunchActivity extends Activity {

    private TextView betText;
    private SeekBar betBar;
    private WalletTracker walletTracker;
    private TextView walletView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        walletTracker = new WalletTracker(this);

        betText = (TextView) findViewById(R.id.bet_view);
        betBar = (SeekBar) findViewById(R.id.bet_Bar);
        walletView = (TextView) findViewById(R.id.walletView);

        betBar.setMax(Integer.parseInt(walletTracker.getPlayerWallet()));

        walletView.setText("Wallet: " + walletTracker.getPlayerWallet());
        String seekBarText = "Bet: " + Integer.toString(betBar.getProgress());
        betText.setText(seekBarText);

        betBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                betText.setText("Bet: " + Integer.toString(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    public void onStartClick(View button){
        walletTracker.makeBet(Integer.toString(betBar.getProgress()));
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onAddFundsClick(View button){
//        walletTracker.setWallet();

        Intent intent = new Intent(this, AddFundsActivity.class);
        startActivity(intent);
//
//        betBar.setMax(Integer.parseInt(walletTracker.getPlayerWallet()));
//        walletView.setText("Wallet: " + walletTracker.getPlayerWallet());


    }
}
