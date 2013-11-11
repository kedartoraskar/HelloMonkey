package com.twilio.example.hellomonkey;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SimpleAdapter;

public class ItemListActivity extends ListActivity {

	String[] results = new String[10]; 
	String[] resultsinitializing = {};
	ArrayList<HashMap<String, String>> reminderList = new ArrayList<HashMap<String,String>>();
	
	private static final int MENU_DISPUTE = Menu.FIRST;
	final Context context = this;

	private MenuItem menuItem;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// removes title
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.activity_list);
		
		try {
		// getting JSON string from URL
		JSONObject json =  Util.getJSONStr();
		JSONArray data = json.getJSONArray("data");
		
		for (int i=0; i < data.length(); i++)
		{
			
		        JSONObject objData = data.getJSONObject(i);
		  
				String name = (String) objData.get("name");
				String time = (String) objData.get("time");
				String date = (String) objData.get("date");
		        
		        HashMap<String, String> hmresults = new HashMap<String, String>();
		        hmresults.put("name",name);
		        hmresults.put("date",date);
		        hmresults.put("time",time);
		        reminderList.add(hmresults);		        
			}	
		} catch(JSONException je) {
			
		}
				
		ListAdapter adapter = new SimpleAdapter(this, reminderList,
				R.layout.activity_list,
        //        new String[] {"name","date","time","" }, new int[] {R.id.name, R.id.date, R.id.time, R.id.check});
		new String[] {"name","date","time"}, new int[] {R.id.name, R.id.date, R.id.time});
		
		setListAdapter(adapter); 
		
		
		
		ListView lv = (ListView)findViewById(android.R.id.list);
		//lv.setBackgroundColor(Color.WHITE);
		// Launching new screen on Selecting Single ListItem
		registerForContextMenu(lv); 
		
		
		lv.setOnItemClickListener(new OnItemClickListener() {
 
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
              
            	String name = ((TextView) view.findViewById(R.id.name)).getText().toString();
                String date = ((TextView) view.findViewById(R.id.date)).getText().toString();
                String time = ((TextView) view.findViewById(R.id.time)).getText().toString();
				
               // Toast.makeText(ItemListActivity.this,
            	//    	"name: "+name+ "date: "+date+ "time: "+time, Toast.LENGTH_SHORT).show();
                
                /* replacing with Dialog */
                /*
                Intent i = new Intent(ItemListActivity.this, ItemDetailsActivity.class);
                i.putExtra("name", name);
                i.putExtra("date", date);
                i.putExtra("time", time);
                startActivity(i); */
                
                AlertDialog.Builder itemDetailsBuilder = new AlertDialog.Builder(context);
                itemDetailsBuilder.setTitle("Details");
                itemDetailsBuilder.setMessage("Name: "+name+ "\n" + "Date: "+date+ "\n" + "Time: "+time);
                itemDetailsBuilder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    	dialog.cancel();
                    }
                });
                itemDetailsBuilder.show();
            }
        });	 
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
	    MenuInflater inflater = getMenuInflater();
		getMenuInflater().inflate(R.menu.settingsmenu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {    
    	switch (item.getItemId()) {    
    		case R.id.dispute: 
    			menuItem = item;
    			dispute();
    			break;
    		default:
    			break;
    	}    
    	return true;
    }
	
	
	 public void dispute() {
		
		/* CheckBox checkbox = (CheckBox)findViewById(R.id.check);
		 checkbox.setVisibility(View.VISIBLE); */
		 
		 
			Toast.makeText(ItemListActivity.this,
	            	"hit dispute", Toast.LENGTH_SHORT).show();
		
			Intent talkToAgentInt = new Intent(ItemListActivity.this, HelloMonkeyActivity.class);
			//talkToAgentInt.putExtra("dataParams", jsonObj.toString());
			startActivity(talkToAgentInt);
	 }
}



