/*
 *  Copyright (c) 2011 by Twilio, Inc., all rights reserved.
 *
 *  Use of this software is subject to the terms and conditions of 
 *  the Twilio Terms of Service located at http://www.twilio.com/legal/tos
 */

package com.twilio.example.hellomonkey;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;

public class HelloMonkeyActivity extends Activity implements
		View.OnClickListener {
	private MonkeyPhone phone;
	private EditText numberField;
	
	/* data parameter fields */
	private String name;
	private String time;
	private String date;
	
	Map<String, String> dataParams = new HashMap<String, String>();

	@Override
	public void onCreate(Bundle bundle) {
		
		// removes title
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		super.onCreate(bundle);
		
		// get parameters		
		Intent intent = getIntent();
		name = intent.getStringExtra("name");
		time = intent.getStringExtra("time");
		date = intent.getStringExtra("date");
		dataParams.put("name", name);
		dataParams.put("time", time);
		dataParams.put("date", date);
		
		setContentView(R.layout.main);
		
		phone = new MonkeyPhone(getApplicationContext());
		Log.d("Monkey", "phone" + phone.toString());
		Log.d("Monkey", "name: "+name);
		Log.d("Monkey", "time: "+time);
		Log.d("Monkey", "date: "+date);
		ImageButton dialButton = (ImageButton) findViewById(R.id.dialButton);
		dialButton.setOnClickListener(this);

		ImageButton hangupButton = (ImageButton) findViewById(R.id.hangupButton);
		hangupButton.setOnClickListener(this);

		numberField = (EditText) findViewById(R.id.numberField);
	}

	@Override
	public void onClick(View view) {
		if (view.getId() == R.id.dialButton) {
			phone.connect(numberField.getText().toString(), dataParams);
			Log.d("Monkey", "Dial Button pressed: "+numberField.getText().toString());

		} else if (view.getId() == R.id.hangupButton)
			phone.disconnect();
	}

}