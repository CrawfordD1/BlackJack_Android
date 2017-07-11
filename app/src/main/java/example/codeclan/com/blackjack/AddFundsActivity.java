package example.codeclan.com.blackjack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AddFundsActivity extends Activity {

    private TextView amountInput;
    private WalletTracker tracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_funds);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        amountInput = (TextView) findViewById(R.id.amount_input);
        tracker = new WalletTracker(this);
    }

    public void onAddFundsClicked(View button){
        tracker.addToWallet(amountInput.getText().toString());
        Intent intent = new Intent(this, LaunchActivity.class);
        startActivity(intent);
    }
}
