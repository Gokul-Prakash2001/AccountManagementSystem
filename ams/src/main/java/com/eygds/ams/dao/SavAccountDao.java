package com.eygds.ams.dao;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.eygds.ams.model.SavingsAccount;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

public class SavAccountDao {
	
		private Gson gson = new GsonBuilder().setPrettyPrinting().create();
		private List<SavingsAccount> savList = new ArrayList<>();
		private File file = new File("savData.json");
		private FileWriter writer;
		private FileReader reader;

		private void createFile() {
			if (!file.exists()) {
				try {
					file.createNewFile();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}

		public List<SavingsAccount> readDataFromFile() {
			this.createFile();
			try {
				reader = new FileReader(file);
				Type listType = new TypeToken<ArrayList<SavingsAccount>>() {
				}.getType();
				savList = gson.fromJson(reader, listType);
				reader.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (null == savList)
				savList = new ArrayList<>();
			return savList;
		}

		public void writeDataToFile(List<SavingsAccount> savList) {
			this.createFile();
			try {
				writer = new FileWriter(file);
				gson.toJson(savList, writer);
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.readDataFromFile();
		}
	}


