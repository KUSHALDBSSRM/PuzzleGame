package com.example.puzzle8;

import java.util.concurrent.TimeUnit;



import android.os.Bundle;
import android.os.CountDownTimer;
import android.app.Activity;
import android.view.Menu;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PuzzleActivity extends Activity {
	static final int REQUEST_IMAGE_CAPTURE = 1;
    private Bitmap imageBitmap = null;
	private PuzzleBoardView boardView;
    ImageView imgV;
    private Button btn,btn1;
	private TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle);
        RelativeLayout container = (RelativeLayout) findViewById(R.id.puzzle_container);
        boardView = new PuzzleBoardView(this);

                // Some setup of the view.
                
       boardView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
       container.addView(boardView);
       imgV = (ImageView)findViewById(R.id.imageView1);
       SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(PuzzleActivity.this);
	   String username = prefs.getString("username", "Default Nickname");
	   boolean checkbox = prefs.getBoolean("checkbox", false);
	   TextView txt1 = (TextView) findViewById(R.id.textView2);
	   Toast.makeText(getApplicationContext(), "WELCOME TO THE GAME \n"+ username,Toast.LENGTH_LONG).show();
	   Toast.makeText(getBaseContext(), "Keep me logged in:"+ String.valueOf(checkbox), Toast.LENGTH_LONG).show();
	   btn1 = (Button) findViewById(R.id.button1);
	   btn1.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			txt = (TextView) findViewById(R.id.textView1);
		    txt.setText("00:03:00");
		     final CounterClass timer = new CounterClass(180000,1000);
		     timer.start();
		    	
		}
	});
	  
       
    }


   
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    

public boolean onOptionsItemSelected(MenuItem item) {
    
	// Handle action bar item clicks here. The action bar will
	        
	// automatically handle clicks on the Home/Up button, so long
	        
	// as you specify a parent activity in AndroidManifest.xml.
	        
	int id = item.getItemId();

	        
	//noinspection SimplifiableIfStatement
	        
	if (id == R.id.action_settings) {
	            
	return true;
	        
	}

	        
	return super.onOptionsItemSelected(item);
	    
	}



	    
	public void dispatchTakePictureIntent(View view) 
	{
	        
	Intent takePic = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	        
	if (takePic.resolveActivity(getPackageManager()) != null) {
	            
	startActivityForResult(takePic, REQUEST_IMAGE_CAPTURE);
	        
	}
	    
	}
	    
	@Override
	     
	protected void onActivityResult(int requestCode, int resultCode, Intent data) 
	{
	        
	if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) 
	{
	            
	Bundle extras = data.getExtras();
     
	final Bitmap imageBitmap = (Bitmap) extras.get("data");

	boardView.initialize(imageBitmap, null);
	 btn = (Button) findViewById(R.id.button2);
	   btn.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent in = new Intent();
			in.setClass(PuzzleActivity.this, original.class);
			in.putExtra("Bitmap", imageBitmap);
            startActivity(in);
		}
	});
	
    

	        
	}
	    
	}

	    
	public void shuffleImage(View view) 
	{
	        
	
		boardView.shuffle();
		 
	    
	}

	     
	public void solve(View view) 
	{
	      
	boardView.solve();

	    
	}
	public class CounterClass extends CountDownTimer{

		public CounterClass(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onFinish() {
			// TODO Auto-generated method stub
			txt.setText("Completed");
		}

		@Override
		public void onTick(long arg0) {
			// TODO Auto-generated method stub
			long millis= arg0;
			String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),TimeUnit.MILLISECONDS.toMinutes(millis)- TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), TimeUnit.MILLISECONDS.toSeconds(millis)- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
			txt.setText(hms);
			
		}
		
	}

	}
