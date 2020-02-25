package com.cg.pwa.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.cg.pwa.bean.WalletUser;
import com.cg.pwa.exception.IncorrectUserIdPasswordException;
import com.cg.pwa.exception.InvalidChoiceException;

import com.cg.pwa.service.WalletServiceImpl;

public class PaymentWallet {
	InputStreamReader isr;
	BufferedReader bsr;
	List<WalletUser> userList;
	WalletServiceImpl wCon;
	WalletUser currentUser;

	public static void main(String[] args) {

		PaymentWallet pWallet = new PaymentWallet();
		pWallet.isr = new InputStreamReader(System.in);
		pWallet.bsr = new BufferedReader(pWallet.isr);

		pWallet.wCon = new WalletServiceImpl();
		pWallet.paymentWallet();
	}

	public void paymentWallet() {

		try {

			System.out.println(
					".....*.....Welcome to Payment Wallet....*..... \n  1. Login as User\n  2. Register \n 3. Exit from Application \n 4.Login as Admin ");
			int choice = Integer.parseInt(bsr.readLine());
			switch (choice) {
			case 1:
				loginUser();
				break;
			case 2:
				registerUser();
				break;
			case 3:
				System.exit(0);
				break;
			case 4:
				adminLogin();
				break;
			default:
				try {
					throw new InvalidChoiceException("Invalid input.........Please Choose a valid Option");
				} catch (InvalidChoiceException le) {
					System.out.println(le);

				}
				paymentWallet();
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void adminLogin() {

		try {

			System.out.println("\nEnter the Admin UserId :");
			int aid = Integer.parseInt(bsr.readLine());
			System.out.println("Enter the Admin Password :");
			String apass = bsr.readLine();
			String isSignedIn = wCon.adminLogin(aid, apass);

			if (isSignedIn.equalsIgnoreCase("Logged In")) {
				System.out.println("\nWelcome to Admin Panel !");
				homePageAdmin();
			}

			else {
				try {
					throw new IncorrectUserIdPasswordException("Incorrect UserId or Password !");
				} catch (IncorrectUserIdPasswordException le) {
					System.out.println(le);

				}
				System.out.println("Please Login again");
				paymentWallet();
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void homePageAdmin() {
		try {
			int input = 0;
			do {
				System.out.println(
						"\nEnter Your choice : \n 1. Show Non-Approved Accounts\n 2. Show Approved Accounts\n 3. Approve Account\n 4. Show Accounts\n 5. Log Out ");
				input = Integer.parseInt(bsr.readLine());
				switch (input) {
				case 1:
					wCon.showNonApprovedAccounts();
					break;
				case 2:
					
					wCon.showApprovedAccounts();
					
					break;
				case 3:
					System.out.println("\nEnter the Account ID you want to approve");
					int appAccID = Integer.parseInt(bsr.readLine());
					wCon.approveAccount(appAccID);
					break;
				case 4:
					wCon.showAccountDetails();

					break;
				case 5:
					System.out.println("\nYou Have Been Logged Out As Admin\n");
					paymentWallet();
					break;
				default:
					try {
						throw new InvalidChoiceException("Invalid input.........Please Choose a valid option");
					} catch (InvalidChoiceException le) {
						System.out.println(le);

					}
					
					adminLogin();
					break;
				}
			} while (input != 5);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void registerUser() {

		try {
			System.out.print("Enter your userId : ");
			Integer userId = Integer.parseInt(bsr.readLine());
			System.out.print("Enter your userName : ");
			String userName = bsr.readLine();
			System.out.print("Enter your password : ");
			String password = bsr.readLine();
			System.out.print("Enter your phoneNumber : ");
			String phoneNumber = bsr.readLine();
			System.out.print("Enter your loginName : ");
			String loginName = bsr.readLine();

			String registrationStatus = wCon
					.registerUser(new WalletUser(userId, userName, password, phoneNumber, loginName));
			System.out.println(registrationStatus);
			paymentWallet();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loginUser() {

		try {
			System.out.print("Enter your loginName : ");
			String id = bsr.readLine();
			System.out.print("Enter your password : ");
			String password = bsr.readLine();

			currentUser = wCon.loginUser(id, password);

			if (currentUser != null) {

				try {

					int choice1 = 0;
					do {
						System.out.println(
								"1. Send Money \n 2. Add Money to Wallet \n 3. Show Wallet Balance \n 4. Show Transactions \n 5.My Account \n 6. LogOut ");
						choice1 = Integer.parseInt(bsr.readLine());
						switch (choice1) {
						case 1:
							System.out.println("Enter amount you want to Send");
							double amount = Double.parseDouble(bsr.readLine());

							System.out.println("Enter accountId ");
							Integer accountId = Integer.parseInt(bsr.readLine());
							String msg = wCon.sendMoney(currentUser, amount, accountId);
							System.out.println(msg);

							break;
						case 2:
							System.out.println("Enter amount you want to add in your wallet");
							amount = Double.parseDouble(bsr.readLine());
							String walletAddedMoney = wCon.addMoneyToWallet(currentUser, amount);
							System.out.println(walletAddedMoney);

							break;
						case 3:

							double walletBalance = wCon.showWalletBalance(currentUser);
							System.out.println(walletBalance);

							break;
						case 4:
							wCon.showTransactions(currentUser);

							break;

						case 5:
							String myAccount = wCon.myAccount(currentUser);
							System.out.println(myAccount);

							break;

						case 6:
							paymentWallet();
							break;
						default:
							try {
								throw new InvalidChoiceException("Invalid input...........Please choose a valid option");
							} catch (InvalidChoiceException le) {
								System.out.println(le);

							}
							//loginUser();
							paymentWallet();

						}
					} while (choice1 != 6);

				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {
				
				System.out.println("Please Login again");
				paymentWallet();
			}

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
