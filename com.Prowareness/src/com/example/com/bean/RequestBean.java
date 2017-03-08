package com.example.com.bean;

import org.json.JSONObject;

import android.app.Activity;

@SuppressWarnings("rawtypes")
public class RequestBean {

	private Activity activity;
	private JSONObject jsonObject;
	
	
	public Activity getActivity() {
		return activity;
	}
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	public JSONObject getJsonObject() {
		return jsonObject;
	}
	public void setJsonObject(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
	}
	
}
