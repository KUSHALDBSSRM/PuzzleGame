package com.example.puzzle8;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.Toast;

public class rating extends Activity{
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rating);
        RatingBar rb ;
        rb = (RatingBar)findViewById(R.id.ratingBar1);
        rb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
			
			@Override
			public void onRatingChanged(RatingBar arg0, float arg1, boolean arg2) {
				// TODO Auto-generated method stub
				Toast.makeText(getBaseContext(), "ThankYou for giving us this rating"+String.valueOf(arg1), Toast.LENGTH_LONG).show();
			}
		});
        
               
       }
	

}
