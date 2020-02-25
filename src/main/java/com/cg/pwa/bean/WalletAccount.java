package com.cg.pwa.bean;

import java.util.ArrayList;
import java.util.List;

public class WalletAccount {

	Integer accountId = null;
	Double accountBalance;

	public enum Status {
		Approved, NotApproved
	};

	List<WalletTransaction> transactionHistory;
	Status type = Status.NotApproved;

	public WalletAccount() {

		this.transactionHistory = new ArrayList<WalletTransaction>();
	}

	/*
	 * public WalletAccount(Integer accountId, Double accountBalance, String status,
	 * List<WalletTransaction> transactionHistory) {
	 * 
	 * this.accountId = accountId; this.accountBalance = accountBalance; this.status
	 * = status; this.transactionHistory = transactionHistory; }
	 */

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(Double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public Status getType() {
		return type;
	}

	public void setType(Status type) {
		this.type = type;
	}

	public List<WalletTransaction> getTransactionHistory() {
		return transactionHistory;
	}

	public void setTransactionHistory(List<WalletTransaction> transactionHistory) {
		this.transactionHistory = transactionHistory;
	}

}
