package com.twilio.example.hellomonkey;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.twilio.client.Connection;
import com.twilio.client.Device;
import com.twilio.client.Twilio;

public class MonkeyPhone implements Twilio.InitListener {
	private Device device;
	private Connection connection;
	private String TAG = "MonkeyPhone";

	/* ... other methods ... */

	public MonkeyPhone(Context context) {
		Twilio.initialize(context, this /* Twilio.InitListener */);
	}

	@Override
	/* Twilio.InitListener method */
	public void onInitialized() {
		Log.d(TAG, "Twilio SDK is ready");
		try {
			String capabilityToken = HttpHelper.httpGet("http://mysterious-escarpment-4589.herokuapp.com/token");
			//HttpHelper.httpGet("http://1.twilioclientcapabilitytoken.appspot.com/tccapabilitytokenserver");
			device = Twilio
					.createDevice(capabilityToken, null /* DeviceListener */);
			Log.d(TAG, "capability token: " + capabilityToken);
			Log.d(TAG, "device created: " + device.toString());
		} catch (Exception e) {
			Log.e(TAG,
					"Failed to obtain capability token: "
							+ e.getLocalizedMessage());
		}
	}

	public void disconnect() {
		if (connection != null) {
			Log.d(TAG, "Connection state before disconnecting: "+connection.getState());
			connection.disconnect();
			connection = null;
		}
	}

	public void connect(String phoneNumber, Map<String, String> dataParams) {
		
		JSONObject jsonObj = new JSONObject(dataParams);
		try {
			Map<String, String> parameters = new HashMap<String, String>();
			parameters.put("phonenumber", phoneNumber);
			parameters.put("dataparams", jsonObj.toString());
			connection = device.connect(parameters, null /* ConnectionListener */);
			if (connection == null)
				Log.w(TAG, "Failed to create new connection");
			else
				Log.d(TAG, "Connection is not null - " + connection.getState());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void finalize() {
		if (connection != null) {
			Log.d(TAG, "Connection state before disconnecting: "+connection.getState());
			connection.disconnect();
		}
		if (device != null)
			device.release();
	}

	@Override
	public void onError(Exception arg0) {
		// TODO Auto-generated method stub
		Log.e(TAG, "Twilio SDK couldn't start: " + arg0.getLocalizedMessage());
	}
}