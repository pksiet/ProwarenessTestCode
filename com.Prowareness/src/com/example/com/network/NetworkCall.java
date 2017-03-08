package com.example.com.network;

import org.json.JSONObject;

import android.R.integer;
import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.example.com.bean.ContactListBean;
import com.example.com.bean.RequestBean;
import com.example.com.parser.MyParser;
import com.example.com.prowareness.MainActivity;


public class NetworkCall extends AsyncTask<String, integer, Object> 
{

	private RequestBean request;
	@SuppressWarnings("unused")
	private Activity activity;
	
	private static final String URL = "http://139.162.152.157/contactlist.php";
	public static final String token = "c149c4fac72d3a3678eefab5b0d3a85a";
	private MyParser myParser;
	
	
	public NetworkCall(RequestBean requset)
	{
		if(requset!=null){

			this.request = requset;
			this.activity = requset.getActivity();
			myParser = new MyParser(activity);
			
		}else{
			Log.e("Network Clas","no request parameter found");
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected Object doInBackground(String... arg0) 
	{
	ContactListBean clb = callServcie(request.getJsonObject());
	return clb;
	}

	@Override
	protected void onCancelled() 
	{
		super.onCancelled();
	}


	@Override
	protected void onPostExecute(Object result) 
	{
		super.onPostExecute(result);
	
		((MainActivity) activity).loader(false);
		
		if(activity instanceof MainActivity){

			ContactListBean data = (ContactListBean)result;
			
			((MainActivity) activity).showContact(data);
		}
		
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		
		((MainActivity) activity).loader(true);

	}
	
	private ContactListBean callServcie(JSONObject json){
		ContactListBean contactlistbn = new ContactListBean();
		String response = NetworkConnection.hitserver(json,URL);

		if(response == null){
			contactlistbn.setStatus("Something went wrong, Please check api call");
		}else{
			contactlistbn = myParser.parseContact(response);
		}
		return contactlistbn;
	}
	

}
