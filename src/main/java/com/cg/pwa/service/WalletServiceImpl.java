package com.cg.pwa.service;

import java.time.LocalDateTime;

import java.util.List;

import com.cg.pwa.bean.WalletTransaction;
import com.cg.pwa.bean.WalletUser;
import com.cg.pwa.bean.WalletAccount.*;
import com.cg.pwa.dao.WalletDaoImpl;
import com.cg.pwa.exception.IncorrectUserIdPasswordException;
import com.cg.pwa.exception.LowBalanceException;

public class WalletServiceImpl implements WalletServiceIntf  {

	WalletDaoImpl wDaoImpl=new WalletDaoImpl();
	
	int adminID = 212;
	String adminPassword = "admin123";
	// WalletTransaction wTransaction;

	//List<WalletUser> uList = new ArrayList<WalletUser>();
	List<WalletUser> uList=wDaoImpl.getUserData();

	public String registerUser(WalletUser user) {

		wDaoImpl.setUserDAta(user);

		return "Wallet Account created Successfully \n Your accountId is  " + user.getwAccount().getAccountId();

	}


	public WalletUser loginUser(String id, String pass) {
		boolean flag = false;
		WalletUser currentUser = null;
		for (WalletUser u : uList) {
			if (u.getLoginName().equals(id) && u.getPassword().equals(pass)) {
				if (u.getwAccount().getType().equals(Status.Approved)) {
					flag = true;
					currentUser = u;
					break;
				} else {
					System.out.println("\nAccount Not Approved By Admin\n");
					return null;

				}
			}
		}

		if (flag) {

			return currentUser;
		} else {
			try {
				throw new IncorrectUserIdPasswordException("Incorrect UserId or Password !");
			} catch (IncorrectUserIdPasswordException le) {
				System.out.println(le);

			}
			return null;
		}

	}


	public String myAccount(WalletUser user) {

		return "Welcome User : " + user.getUserName() + " \n Phone no. : " + user.getPhoneNumber() + "\n Account Id : "
				+ user.getwAccount().getAccountId() + " \n Account Balance : " + user.getwAccount().getAccountBalance();

	}

	public String addMoneyToWallet(WalletUser user, double amount) {
		user.getwAccount().setAccountBalance(user.getwAccount().getAccountBalance() + amount);

		int tid = (int) (Math.random() * 1234 + 9999);
		LocalDateTime date = LocalDateTime.now();
		String description = amount + " Added Successfully to Wallet";

		user.setwTransaction(
				new WalletTransaction(tid, description, date, amount, user.getwAccount().getAccountBalance()));
		user.getwAccount().getTransactionHistory().add(user.getwTransaction());

		return description;
	}

	public String sendMoney(WalletUser user, double amount, Integer accountId) {
		WalletUser send = null;

		String msg = null;

		for (WalletUser u : uList) {
			if ((u.getwAccount().getAccountId()).equals(accountId)) {
				send = u;
			}
		}

		if (user.getwAccount().getAccountBalance() < amount) {
			try {
				throw new LowBalanceException("\"Your Account balance is low ......Can't send money\"");
			} catch (LowBalanceException le) {
				msg = String.valueOf(le);

			}

		} else {

			if (send != null) {

				if (send.getwAccount().getType().equals(Status.Approved)) {

					send.getwAccount().setAccountBalance(send.getwAccount().getAccountBalance() + amount);
					user.getwAccount().setAccountBalance(user.getwAccount().getAccountBalance() - amount);
					msg = amount + " Transferred to " + send.getUserName() + "(" + send.getwAccount().getAccountId()
							+ ")";

					int tid = (int) (Math.random() * 1234 + 9999);
					LocalDateTime date = LocalDateTime.now();
					user.setwTransaction(
							new WalletTransaction(tid, msg, date, amount, user.getwAccount().getAccountBalance()));
					user.getwAccount().getTransactionHistory().add(user.getwTransaction());

				} else {
					msg = " receiver's Account not approved ........can't send money";

				}
			} else {
				msg = "Transaction failed...........Invalid Account_Id";
			}
		}
		
		
		
		
		return msg;
	}

	
	public double showWalletBalance(WalletUser user) {

		return user.getwAccount().getAccountBalance();
	}

	public void showTransactions(WalletUser user) {
		List<WalletTransaction> tlist = user.getwAccount().getTransactionHistory();
		System.out.println("My Transactions......");
		for (WalletTransaction wt : tlist) {
			if (wt.getTransactionId() != null) {

				System.out.println("Transaction_Id : " + wt.getTransactionId() + "    Date : "
						+ wt.getDateOfTransaction() + "   " + wt.getDescription());
			}
		}

	}

	public String adminLogin(int aid, String apass) {
		if (aid == adminID && apass.equals(adminPassword))
			return "Logged In";
		else
			return "Not Logged In";
	}

	public void showNonApprovedAccounts() {
		System.out.println("Account ID \t\tUser ID \t\tLogin Name");
		for (WalletUser user : uList) {
			if (user.getwAccount().getType().equals(Status.NotApproved)) {
				System.out.println(user.getwAccount().getAccountId() + "\t\t\t" + user.getUserId() + "\t\t\t"
						+ user.getLoginName());
			}
		}

	}

	
	public void showApprovedAccounts() {
		System.out.println("Account ID \t\tUser ID \t\tLogin Name");
		for (WalletUser user : uList) {
			if (user.getwAccount().getType().equals(Status.Approved)) {

				System.out.println(user.getwAccount().getAccountId() + "\t\t\t" + user.getUserId() + "\t\t\t"
						+ user.getLoginName());

			}
		}
	}

	public void approveAccount(int appAccID) {
		for (WalletUser user : uList) {
			if (user.getwAccount().getAccountId() == appAccID) {
				user.getwAccount().setType(Status.Approved);
				;
			}
		}

	}

	public void showAccountDetails() {
		for (WalletUser user : uList) {
			System.out.println(user.getwAccount().getAccountId());

		}

	}
}
