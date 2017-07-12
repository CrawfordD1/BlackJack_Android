package example.codeclan.com.blackjack;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by user on 07/07/2017.
 */

public class WalletTracker {


    Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public static final String DEFAULT = "0";

    public WalletTracker(Context context){
        this.context = context;
        this.sharedPreferences = context.getSharedPreferences("walletData", Context.MODE_PRIVATE);
        this.editor = sharedPreferences.edit();
    }


    public void resetTracker(){
        editor.putString("wallet", Integer.toString(0));
        editor.putString("bet", Integer.toString(0));
        editor.apply();
    }


    public String getPlayerWallet() {
        return sharedPreferences.getString("wallet", DEFAULT);
    }

    public String getCurrentBet() {
        return sharedPreferences.getString("bet", DEFAULT);
    }



    public void addToWallet(String input) {
        String wallet = getPlayerWallet();
        int result = Integer.parseInt(wallet) + Integer.parseInt(input);
        editor.putString("wallet", Integer.toString(result));
        editor.apply();
    }

    public void playerWin() {
        String wallet = getPlayerWallet();
        int bet = Integer.parseInt(getCurrentBet());
        int result = Integer.parseInt(wallet) + (bet * 2);
        editor.putString("wallet", Integer.toString(result));
        editor.apply();
    }

    public void makeBet(String bet) {
        String wallet = sharedPreferences.getString("wallet", DEFAULT);
        int result = Integer.parseInt(wallet) - Integer.parseInt(bet);
        editor.putString("wallet", Integer.toString(result));
        editor.putString("bet", bet);
        editor.apply();
    }

}

