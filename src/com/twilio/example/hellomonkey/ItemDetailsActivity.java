/*
 *  Copyright (c) 2011 by Twilio, Inc., all rights reserved.
 *
 *  Use of this software is subject to the terms and conditions of 
 *  the Twilio Terms of Service located at http://www.twilio.com/legal/tos
 */

package com.twilio.example.hellomonkey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.twilio.client.Connection;
import com.twilio.client.Device;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ItemDetailsActivity extends Activity implements View.OnClickListener {
	private MonkeyPhone phone;
	private EditText numberField;
	
	/* data parameter fields */
	private String name;
	private String time;
	private String date;
	
	TableLayout dataTable;
	
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
		
		setContentView(R.layout.activity_itemdetails);
	       
		TextView namtTxt = (TextView)findViewById(R.id.name);
		namtTxt.setText(name);
		
		TextView dateTxt = (TextView)findViewById(R.id.date);
		dateTxt.setText(date);
		
		TextView timeTxt = (TextView)findViewById(R.id.time);
		timeTxt.setText(time);
		
		Button talkToAgentBtn = (Button)findViewById(R.id.talktoagentBtn);
		talkToAgentBtn.setOnClickListener(this);
		
	}
	
	@Override
	public void onClick(View view) {
		JSONObject jsonObj = new JSONObject(dataParams);
        Intent talkToAgentInt = new Intent(ItemDetailsActivity.this, HelloMonkeyActivity.class);
		talkToAgentInt.putExtra("dataParams", jsonObj.toString());
		startActivity(talkToAgentInt);
		
	
	}

}