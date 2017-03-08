package com.example.com.network;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.com.bean.ContactBean;
import com.example.com.prowareness.MainActivity;
import com.example.com.prowareness.R;

public class MyListAdapter extends ArrayAdapter<ContactBean> {

	
	private int layoutResourceId;
	private List<ContactBean> data;
	private LayoutInflater mInflater;
	private Activity mActivity;
	
	public MyListAdapter(Activity mActivity, int layoutResourceId,
			List<ContactBean> data) {
		super(mActivity, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.data = data;
		this.mActivity = mActivity;
		mInflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(final int position, View convertView,ViewGroup parent) {

		View row = convertView;
		WeatherHolder holder = null;
		if (row == null) {
			holder = new WeatherHolder();
			LayoutInflater inflater = (mActivity).getLayoutInflater();
			row = inflater.inflate(R.layout.inflate_contact_row, parent, false);
			
			holder.tvname = (TextView) row.findViewById(R.id.name);
			holder.btndelete = (Button) row.findViewById(R.id.delete);
			
			row.setTag(holder);
		} else {
			holder = (WeatherHolder) row.getTag();
		}
	
		holder.tvname.setText(data.get(position).getName());
		holder.btndelete.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				((MainActivity) mActivity).showAlertPopup(position);
				
			}
		});
		
	
//		imageLoader.DisplayBanner(NetworkCall.IMAGE_URL_new+ data.get(position).getMapImage(), holder.imageView,70, 70);


		return row;
	}
	
	class WeatherHolder {
		private TextView tvname;
		private Button btndelete;
	}
	
}
