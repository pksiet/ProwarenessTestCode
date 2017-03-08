package com.example.com.bean;

import java.util.List;

public class ContactListBean {

	private List<ContactBean> contactlist;
	private String status;
	public List<ContactBean> getContactlist() {
		return contactlist;
	}
	public void setContactlist(List<ContactBean> contactlist) {
		this.contactlist = contactlist;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
