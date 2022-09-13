package com.eygds.ams;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.eygds.ams.model.CurrentAccount;
import com.eygds.ams.model.SavingsAccount;
import com.eygds.ams.service.AccountService;

public class BAM {


	private static final Logger logger = LogManager.getLogger(BAM.class);
	private static AccountService service = new AccountService();

	public static void selectOption() {

		try {

			System.out.println("\nPlease select an option to continue:");
			System.out.println(
					"\n1. Create new account\n2. Cash Withdrawal \n3. Cash Deposit\n4. Exit");
			Scanner sc = new Scanner(System.in);
			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				System.out.println("Please enter your details:");
				System.out.println("Full Name:");
				String name = sc.next();
				System.out.println("Address:");
				String address = sc.next();
				System.out.println("Mobile Number:");
				Long mob = sc.nextLong();
				System.out.println("Date of Birth:");
				String dob = sc.next();
				System.out.println("Email:");
				String email = sc.next();
				System.out.println("Select account type:\n1. Savings Account \n2. Current Account ");
				int selection = sc.nextInt();
//				String acctype;
				if (selection == 1) {
//					acctype = "Savings Account";
					SavingsAccount account = new SavingsAccount(name, address, mob, dob, email);
					service.addSavAcc(account);
					System.out.println("\nAccount Details:\n" + account.toString());
				}
				else if (selection == 2) {
//					acctype = "Current Account";
					CurrentAccount account = new CurrentAccount(name, address, mob, dob, email);
					service.addCurAcc(account);
					System.out.println("\nAccount Details:\n" + account.toString());
				}
				else {
					logger.error("Wrong input!");
				}
				BAM.selectOption();
			
			case 2:
				System.out.println("Please enter account number:");
				Long accNum =  sc.nextLong();
				System.out.println("Enter account type:\n Saving Acoount Current Account");
				String accType =  sc.next();
				System.out.println("Please enter your pin:");
				int pin =  sc.nextInt();
				System.out.println("Enter amount:");
				int amount =  sc.nextInt();
				service.withdrawal(accNum, pin, amount, accType);
				BAM.selectOption();
				break;
			case 3:
				System.out.println("Please enter account number:");
				Long accNum1 =  sc.nextLong();
				System.out.println("Enter account type:\n Savings Account\n Current Account");
				String accType1 =  sc.next();
				System.out.println("Enter amount:");
				int amount1 =  sc.nextInt();
				service.deposit(accNum1, amount1, accType1);
				BAM.selectOption();
				break;
			
			case 4:
				BAM.exitApp();
				break;
			default:
				logger.error("Wrong input!");
				BAM.selectOption();
				break;
			}
			sc.close();
		} catch (Exception e) {

			logger.error(e.getMessage());
			BAM.selectOption();
		}
	}

	private static void exitApp() {
		System.out.println("Thanks for using this app!");
		System.exit(0);
	}
}