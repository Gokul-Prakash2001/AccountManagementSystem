package com.eygds.ams.model;

public class CurrentAccount {

		private String name;
		private String address;
		private Long mob ;
		private String dob;
		private String email;
		private int withdrawn;
		private int balance;
		private Long accNum;
		int pin;
		
		public int getPin() {
			return pin;
		}

		public void setPin(int pin) {
			this.pin = pin;
		}

		public CurrentAccount() {
			super();
		}

		public CurrentAccount(String name, String address, Long mob, String dob, String email) {
			super();
			this.name = name;
			this.address = address;
			this.mob = mob;
			this.dob = dob;
			this.email = email;
			this.balance = 0;
			this.withdrawn = 0;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public Long getMob() {
			return mob;
		}

		public void setMob(Long mob) {
			this.mob = mob;
		}

		public String getDob() {
			return dob;
		}

		public void setDob(String dob) {
			this.dob = dob;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public int getWithdrawn() {
			return withdrawn;
		}

		public void setWithdrawn(int withdrawn) {
			this.withdrawn = withdrawn;
		}

		public int getBalance() {
			return balance;
		}

		public void setBalance(int balance) {
			this.balance = balance;
		}

	
		public Long getAccNum() {
			return accNum;
		}

		public void setAccNum(Long accNum) {
			this.accNum = accNum;
		}
		@Override
		public String toString() {
			return "SavingsAccount [name=" + name + ", address=" + address + ", mob=" + mob + ", dob=" + dob + ", email="
					+ email + ", balance=" + balance + "]";
		}
		
		
		

		
		
		
	}


