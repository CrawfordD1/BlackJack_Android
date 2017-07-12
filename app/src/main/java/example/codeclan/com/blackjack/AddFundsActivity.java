package example.codeclan.com.blackjack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class AddFundsActivity extends Activity {

    private TextView passInput;
    private TextView ccInput;
    private TextView amountInput;
    private WalletTracker tracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_funds);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        amountInput = (TextView) findViewById(R.id.amount_input);
        ccInput = (TextView) findViewById(R.id.cc_input);
        passInput = (TextView) findViewById(R.id.password_input);
        tracker = new WalletTracker(this);
    }

    public void onAddFundsClicked(View button){
        boolean amountGood = false;
        boolean ccGood = false;
        boolean passGood = false;


        if ((!amountInput.getText().toString().trim().equals("")) && (!passInput.getText().toString().trim().equals("")) && (ccInput.length() == 3)){
            amountGood = true;
            ccGood = true;
            passGood = true;
        }

        if (amountGood && ccGood && passGood){
            tracker.addToWallet(amountInput.getText().toString());
            Intent intent = new Intent(this, LaunchActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Funds Added!", Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(this, "Amount & Password required, CC must be 16 characters", Toast.LENGTH_LONG).show();
        }



    }
}
