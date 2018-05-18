package com.example.puzzle8;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

public class original extends Activity {
	private ImageView img;
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.original);
	        img  = (ImageView) findViewById(R.id.imageView3);
	       Bitmap bitmap = (Bitmap) this.getIntent().getParcelableExtra("Bitmap");
	       img.setImageBitmap(bitmap);
	        
	   

}
}
