package example.codeclan.com.blackjack;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by user on 06/07/2017.
 */

public class HandAdaptor extends ArrayAdapter<Card> {

    public HandAdaptor(Context context, ArrayList<Card> cards){
        super(context, 0, cards);
    }

    @Override
    public View getView(int position, View listItemView, ViewGroup parent){
//        check if existing view is being reused otherwise inflate view
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.card_item, parent, false);
        }

        Card currentCard = getItem(position);
        TextView card_number = (TextView) listItemView.findViewById(R.id.card_number);
        card_number.setText(currentCard.getCardnoDisplay());
        ImageView image = (ImageView) listItemView.findViewById(R.id.cardsuit);
        int suitDrawable = currentCard.getImage();
        image.setImageResource(suitDrawable);

//        String url = currentCard.getUrl();
//        Picasso.with(getContext()).load(url).into(image);

        listItemView.setTag(currentCard);

        return listItemView;

    }


}
