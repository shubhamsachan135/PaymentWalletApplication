package com.cg.pwa.dao;

import java.util.ArrayList;
import java.util.List;

import com.cg.pwa.bean.WalletUser;

public  class  WalletDaoImpl implements WalletDaoIntf {

	List<WalletUser> uList=new ArrayList<WalletUser>();
		
	public List<WalletUser> getUserData() {
		
		return uList;
	}

	public void setUserDAta(WalletUser user) {
		uList.add(user);
		
	}
	

}
