package edu.gatech.financialapplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.AsyncTask;

public class CurrencyConverter extends AsyncTask<Void, Void, Void> {
	private static final String url = "http://openexchangerates.org/api/latest.json?app_id=c4822f940d584ee0a85a269c50f1006a";
	public Map<String, Double> currencyList;

	public String readCurrencyFeed() {
		return new ServiceHandler().makeServiceCall(url, ServiceHandler.GET);
	}

	@Override
	protected Void doInBackground(Void... params) {
		String currencyFeed = readCurrencyFeed();
		currencyList = new HashMap<String, Double>();
		try {
			JSONObject jsonObj = new JSONObject(currencyFeed);
			JSONObject jsonObject = jsonObj.getJSONObject("rates");
			JSONArray jsonArray = jsonObject.names();
			for (int i = 0; i < jsonArray.length(); i++) {
				currencyList.put(jsonArray.get(i).toString(),
						jsonObject.getDouble(jsonArray.get(i).toString()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Double getExchangeValue(String currency) {
		if (currencyList == null) {
			try {
				Thread.currentThread();
				Thread.sleep(1000); // okay let's wait for a second
				return currencyList.get(currency);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 		
		return currencyList.get(currency);
	}

	public ArrayList<String> getExchangeCodes() {
		ArrayList<String> s = new ArrayList<String>();
		if (currencyList == null) {
			try {
				Thread.currentThread();
				Thread.sleep(1000); // okay let's wait for a second
				for(Map.Entry<String, Double> e : currencyList.entrySet()){
					s.add(e.getKey());
				}
				return s;

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 	
		for(Map.Entry<String, Double> e : currencyList.entrySet()){
			s.add(e.getKey());
		}
		return s;
	}
	
	public Map<String, Double> getEntireMap() {
		if (currencyList == null) {
			try {
				Thread.currentThread();
				Thread.sleep(1000); // okay let's wait for a second
				
				return currencyList;

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 	
		return currencyList;
	}
}
