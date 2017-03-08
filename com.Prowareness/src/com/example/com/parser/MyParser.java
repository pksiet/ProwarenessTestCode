package com.example.com.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;

import com.example.com.bean.ContactBean;
import com.example.com.bean.ContactListBean;
import com.example.com.sharepreference.UserPreference;

public class MyParser {

	private UserPreference userPre;
	private Activity mActivity;
	
	public MyParser (Activity mActivity){
		this.mActivity = mActivity; 
	}
	
	
	public ContactListBean parseContact(String response){
		ContactListBean clb = new ContactListBean();
		
		try{
		
		JSONObject jObj = new JSONObject(response);
		
		String status = jObj.getString("status");
		
		clb.setStatus(status);
		
		if(status.equalsIgnoreCase("success")){
		
			JSONArray jArr = jObj.getJSONArray("result");
			
			
			List<ContactBean> cblist = new ArrayList<ContactBean>();
			
			List<String> deleted= getDeletedContact();
			 
			for (int i = 0; i < jArr.length(); i++) {
								
				if(!deleted.contains(jArr.getJSONObject(i).getString("uid")))
				{
				ContactBean cb = new ContactBean();
				cb.setName(jArr.getJSONObject(i).getString("name"));
				cb.setUid(jArr.getJSONObject(i).getString("uid"));
				cblist.add(cb);
				}
			}
			
			clb.setContactlist(cblist);
			
		}
		
		}catch(JSONException je){
			je.printStackTrace();
		}
		
		return clb;
	}
	
	private List<String> getDeletedContact(){
		 userPre = new UserPreference(mActivity);
		 String deleted = userPre.getDeletedContact();
		 
		 
		 List<String> list = Arrays.asList(deleted.split(","));
		 
		 return list;
	}
	
}
