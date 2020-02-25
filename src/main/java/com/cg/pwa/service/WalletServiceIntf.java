package com.cg.pwa.service;

import com.cg.pwa.bean.WalletUser;

public interface WalletServiceIntf {
	public String registerUser(WalletUser user);

	public WalletUser loginUser(String id, String pass);

	public String myAccount(WalletUser user);

	public String addMoneyToWallet(WalletUser user, double amount);

	public String sendMoney(WalletUser user, double amount, Integer accountId);

	double showWalletBalance(WalletUser user);

	public void showTransactions(WalletUser users);

	public void showNonApprovedAccounts();

	public void showApprovedAccounts();

	public void approveAccount(int appAccID);

	public void showAccountDetails();
}
