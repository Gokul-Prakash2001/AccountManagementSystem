package com.eygds.ams.dao;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.eygds.ams.model.CurrentAccount;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

public class CurAccountDao {
	
		private Gson gson = new GsonBuilder().setPrettyPrinting().create();
		private List<CurrentAccount> curList = new ArrayList<>();
		private File file = new File("curData.json");
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

		public List<CurrentAccount> readDataFromFile() {
			this.createFile();
			try {
				reader = new FileReader(file);
				Type listType = new TypeToken<ArrayList<CurrentAccount>>() {
				}.getType();
				curList = gson.fromJson(reader, listType);
				reader.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (null == curList)
				curList = new ArrayList<>();
			return curList;
		}

		public void writeDataToFile(List<CurrentAccount> curList) {
			this.createFile();
			try {
				writer = new FileWriter(file);
				gson.toJson(curList, writer);
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.readDataFromFile();
		}
	}


