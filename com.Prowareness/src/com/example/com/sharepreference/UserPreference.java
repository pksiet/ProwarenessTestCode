package com.example.com.sharepreference;

import java.util.Set;

import android.content.Context;
import android.content.SharedPreferences;

public class UserPreference {
	private Context context;
	private SharedPreferences mSharedPreferences ;

	private SharedPreferences.Editor editor;
	
	private static final String sharePrefrence = "prowareness";

	public UserPreference(Context context){
		this.context = context;
		mSharedPreferences = context.getSharedPreferences(sharePrefrence, Context.MODE_PRIVATE);
	}


	public String getDeletedContact() {
		return mSharedPreferences.getString("uid","");
	}
	
	public void setDeletedContact(String uid)
	{
		
		String udids =  getDeletedContact();
		
		editor = mSharedPreferences.edit();
		
		if(udids.equalsIgnoreCase("")){
			
			editor.putString("uid", uid);
			
		}else{
		
			StringBuilder sb = new StringBuilder(udids);
			sb.append(",").append(uid);
			
			editor.putString("uid", sb.toString());
		}
		
		editor.commit();
		
	}
	
	public void cleanUserPreference() {
				editor = mSharedPreferences.edit();
				editor.putString("uid", "");
				editor.commit();
	}
}