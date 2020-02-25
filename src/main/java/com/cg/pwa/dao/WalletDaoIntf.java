package com.cg.pwa.dao;

import java.util.List;

import com.cg.pwa.bean.WalletUser;

public interface WalletDaoIntf {
   public List<WalletUser> getUserData();
   public void setUserDAta(WalletUser user);
   
}
