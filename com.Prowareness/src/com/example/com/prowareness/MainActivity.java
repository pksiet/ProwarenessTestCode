package com.example.com.prowareness;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.com.bean.ContactBean;
import com.example.com.bean.ContactListBean;
import com.example.com.bean.RequestBean;
import com.example.com.network.MyListAdapter;
import com.example.com.network.NetworkCall;
import com.example.com.sharepreference.UserPreference;

public class MainActivity extends Activity {
	
	private NetworkCall newt;
	private ListView listview;
	private MyListAdapter myListAdapter;
	private List<ContactBean> contactList = new ArrayList<ContactBean>();
	private UserPreference userPre;
	private ProgressDialog dialog;
	private TextView nodatatv;
		
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        setContentView(R.layout.activity_main);
        
        listview = (ListView) findViewById(R.id.listView);

        nodatatv = (TextView)findViewById(R.id.nodata);
        
        userPre = new UserPreference(this);
        
        servercall();
     
    }
        
    private void servercall(){
    	RequestBean requset = new RequestBean();
		requset.setActivity(MainActivity.this);
		try {
			JSONObject json = new JSONObject();
			json.put("token", "c149c4fac72d3a3678eefab5b0d3a85a");
			
			requset.setJsonObject(json);	
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		newt = new NetworkCall(requset);		
		newt.execute("");
    }
    
    public void showContact(ContactListBean contactlistbn){
    	
    	if(contactlistbn.getStatus().equalsIgnoreCase("success")){
    		
    		this.contactList = contactlistbn.getContactlist();
    		myListAdapter = new MyListAdapter(MainActivity.this,R.layout.inflate_contact_row, contactList);
    		listview.setAdapter(myListAdapter);
    	}
    	isDataAvailable();
    	
    }
    
    public void showAlertPopup(final int pos){
    	AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
    	builder1.setMessage("Are you sure, you want to delete this contact ?");
    	builder1.setCancelable(true);

    	builder1.setPositiveButton(
    	    "Yes",
    	    new DialogInterface.OnClickListener() {
    	        public void onClick(DialogInterface dialog, int id) {
    	        	
    	        	deletecontact(pos);
    	        	
    	            dialog.cancel();
    	        }
    	    });

    	builder1.setNegativeButton(
    	    "No",
    	    new DialogInterface.OnClickListener() {
    	        public void onClick(DialogInterface dialog, int id) {
    	            dialog.cancel();
    	        }
    	    });

    	AlertDialog alert11 = builder1.create();
    	alert11.show();
    }
    
    private void deletecontact(int pos){
    	String uid = contactList.get(pos).getUid();
    	userPre.setDeletedContact(uid);
    	contactList.remove(pos);
    	Toast.makeText(this, "Contact deleted succesfully", Toast.LENGTH_SHORT).show();
    	myListAdapter.notifyDataSetChanged();
    	isDataAvailable();
    }
    
    private void isDataAvailable(){
    	if(contactList.size()>0){
    		nodatatv.setVisibility(View.GONE);
    	}else{
    		nodatatv.setVisibility(View.VISIBLE);
    	}
    }
    
    public void loader(boolean show){
    	if(show){
    	dialog = ProgressDialog.show(MainActivity.this, "", 
                "Loading. Please wait...", true);
    	}else{
    		dialog.dismiss();
    	}
    }
    
}
