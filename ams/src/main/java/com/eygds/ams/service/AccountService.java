package com.eygds.ams.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.eygds.ams.dao.CurAccountDao;
import com.eygds.ams.dao.SavAccountDao;

import com.eygds.ams.CalculateAge;
import com.eygds.ams.exception.AccountNotFoundException;
import com.eygds.ams.model.CurrentAccount;
import com.eygds.ams.model.SavingsAccount;

public class AccountService {

	private final Logger logger = LogManager.getLogger(this.getClass());
	private List<SavingsAccount> savList = new ArrayList<>();
	private List<CurrentAccount> curList = new ArrayList<>();
	private CurAccountDao curDao = new CurAccountDao();
	private SavAccountDao savDao = new SavAccountDao();

	public SavingsAccount addSavAcc(SavingsAccount account) {
		savList = savDao.readDataFromFile();
		String appStatus = "Received";
		logger.info(appStatus);
		if (savList.contains(account)) {
			logger.error("Account with account number " + account.getAccNum() + " already exists.");
			return null;
		} else {
			try {
				if (CalculateAge.getAge(account.getDob()) < 18) {
					appStatus = "Rejected";
					logger.info("Apllication has been " + appStatus);
				} else {
					appStatus = "Accepted";
					Long accNum = AccountService.generateRandom();
					account.setAccNum(accNum);
					logger.info("Your application has been " + appStatus);
					logger.info("Your Account Number is " + accNum);

				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			savList.add(account);
			savDao.writeDataToFile(savList);
			return null;
		}
	}

	public CurrentAccount addCurAcc(CurrentAccount account) {
		curList = curDao.readDataFromFile();
		String appStatus = "Received";
		logger.info(appStatus);
		if (curList.contains(account)) {
			logger.error("Account with account number " + account.getAccNum() + " already exists.");
			return null;
		} else {
			try {
				if (CalculateAge.getAge(account.getDob()) < 18) {
					appStatus = "Rejected";
					logger.info("Application has been " + appStatus);
				} else {
					appStatus = "Accepted";
					Long accNum = AccountService.generateRandom();
					account.setAccNum(accNum);
					logger.info("Your application has been " + appStatus);
					logger.info("Your Account Number is " + accNum);

				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			curList.add(account);
			curDao.writeDataToFile(curList);
			return null;
		}
	}

	public void withdrawal(Long accNum, int pin, int amount, String accType) {
		if (accType == "Savings") {
			savList = savDao.readDataFromFile();
			Optional<SavingsAccount> accOptional = savList.stream().filter(e -> e.getAccNum() == accNum).findAny();
			if (accOptional.isPresent()) {
				SavingsAccount temp = accOptional.get();
				if (temp.getTransCount() > 2) {
					logger.error("Daily limit reached.");
					return;
				} else {
					if (temp.getPin() != pin) {
						logger.error("Incorrect pin.");
						return;
					} else {
						if ((temp.getWithdrawn()) + amount > 50000) {
							logger.error("Daily withdrawal limit reached.");
							return;
						} else {
							if (amount > temp.getBalance() - 1000) {
								logger.warn("No sufficient balance.");
							} else {
								temp.setWithdrawn(temp.getWithdrawn() + amount);
								temp.setBalance(temp.getBalance() - amount);
								savList.set(savList.indexOf(temp), temp);
								logger.info("Balance: " + temp.getBalance());
								return;
							}
						}

					}

				}
			}

			throw new AccountNotFoundException("Account does not exist.");
		}

		else if (accType == "Current") {
			curList = curDao.readDataFromFile();
			Optional<CurrentAccount> accOptional = curList.stream().filter(e -> e.getAccNum() == accNum).findAny();
			if (accOptional.isPresent()) {
				CurrentAccount temp = accOptional.get();

				if (temp.getPin() != pin) {
					logger.error("Incorrect pin.");
					return;
				} else {
					if ((temp.getWithdrawn()) + amount > 500000) {
						logger.error("Daily withdrawal limit reached.");
						return;
					} else {
						temp.setWithdrawn(temp.getWithdrawn() + amount);
						temp.setBalance(temp.getBalance() - amount);
						logger.info("Balance: " + temp.getBalance());
						return;

					}

				}
			}

			throw new AccountNotFoundException("Account does not exist.");
		}

	}

	public void deposit(Long accNum, int amount, String accType) {
		if (accType == "Savings") {
			savList = savDao.readDataFromFile();
			Optional<SavingsAccount> accOptional = savList.stream().filter(e -> e.getAccNum() == accNum).findAny();
			if (accOptional.isPresent()) {
				SavingsAccount temp = accOptional.get();

				temp.setBalance(temp.getBalance() + amount);
				savList.set(savList.indexOf(temp), temp);

				logger.info("Balance: " + temp.getBalance());
				return;

			}
			throw new AccountNotFoundException("Account does not exist.");
		}

		else if (accType == "Current") {

			curList = curDao.readDataFromFile();

			Optional<CurrentAccount> accOptional = curList.stream().filter(e -> e.getAccNum() == accNum).findAny();
			if (accOptional.isPresent()) {
				CurrentAccount temp = accOptional.get();

				temp.setBalance(temp.getBalance() + amount);
				curList.set(curList.indexOf(temp), temp);

				logger.info("Balance: " + temp.getBalance());
				return;
			}

			throw new AccountNotFoundException("Account does not exist.");
		}

	}

	public static long generateRandom() {
		Random random = new Random();
		StringBuilder sb = new StringBuilder();

		// first not 0 digit
		sb.append(random.nextInt(9) + 1);

		// rest of 11 digits
		for (int i = 0; i < 11; i++) {
			sb.append(random.nextInt(10));
		}

		return Long.valueOf(sb.toString()).longValue();
	}
}
