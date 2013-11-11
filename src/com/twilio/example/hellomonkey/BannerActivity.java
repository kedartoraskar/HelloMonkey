package com.twilio.example.hellomonkey;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

public class BannerActivity extends Activity implements View.OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// removes title
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.activity_banner);
		
		// set the Twilio Banner 
		ImageView twilioBannerIV = (ImageView)findViewById(R.id.banner);
		twilioBannerIV.setImageResource(R.drawable.twiliobanner);
	
		Button bannerBtn = (Button) findViewById(R.id.bannerBtn);
		bannerBtn.setOnClickListener(new View.OnClickListener() {
		
		public void onClick(View v) {
			Intent activityListStartIntent = new Intent(BannerActivity.this, ItemListActivity.class);
			Log.d("ItemListActivity", "Enter pressed: ");
			startActivity(activityListStartIntent);			
		 }
		});	
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.banner, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}
