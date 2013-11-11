package com.twilio.example.hellomonkey;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

public class Util {
	
	
	public static JSONObject getJSONStr() {
		
		HttpURLConnection connection = null;
		InputStream inputStream = null;
		String result = null;
	    JSONObject jObj = null;

		try {
	        connection = (HttpURLConnection) new URL("http://saleisonreminder.appspot.com/rest/reminders/flashsales").openConnection();
	        int status = connection.getResponseCode();
		    
	        if (status==HttpURLConnection.HTTP_OK){
	            InputStream responseStream = connection.getInputStream();
	            BufferedReader reader = null;
	            reader = new BufferedReader(new InputStreamReader(responseStream));
	            
	            StringBuilder sb = new StringBuilder();
	            String line = null;
	            while ((line = reader.readLine()) != null) {
	                sb.append(line + "\n");
	            }
	            responseStream.close();
	            result = sb.toString();
	            
	            
	            //Toast.makeText(ItemListActivity.this,
			    //		"Result: "+result+ Toast.LENGTH_SHORT, 0).show();
			   
	        }
		} catch (Exception e) { 
		}
		finally {
		    try{if(inputStream != null)inputStream.close();}catch(Exception squish){}
		}		
		try {
		jObj = new JSONObject(result);
		} catch(JSONException e){
			
		}
		return jObj;
	}

}
