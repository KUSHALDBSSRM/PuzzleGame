package com.example.puzzle8;


import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.Menu;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class welcome extends Activity {
	private Button btn1,btn2,btn3,btn4;
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        btn1=(Button)findViewById(R.id.button1);
        btn1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent("com.example.puzzle8.PuzzleActivity");
				startActivity(i);
					
			}
		});
			
        btn2=(Button)findViewById(R.id.button2);
        	btn2.setOnClickListener(new View.OnClickListener() {
    			
    			@Override
    			public void onClick(View arg0) {
    				// TODO Auto-generated method stub
    				Intent in = new Intent("com.example.puzzle8.credits");
    				startActivity(in);
    							
    			}
    		});
    		
        
        btn3=(Button)findViewById(R.id.button3);
        btn3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent in1 = new Intent("com.example.puzzle8.rating");
				startActivity(in1);
								
			}
		});
		
			Button btn4 = (Button) findViewById(R.id.button4);
			btn4.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					AlertDialog.Builder ab = new AlertDialog.Builder(welcome.this);
					ab.setMessage("Do you want to close the App").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub
							finish();
						}
					}).setNegativeButton("No",new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub
							arg0.cancel();
						}
					});
					AlertDialog al = ab.create();
					al.setTitle("Alert!!!!");
					al.show();
				}
				
			});
			Button btn5 = (Button) findViewById(R.id.button5);
			btn5.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(welcome.this,Preference.class);
					startActivity(intent);

			
				}});  
	}
	}
       
       
	