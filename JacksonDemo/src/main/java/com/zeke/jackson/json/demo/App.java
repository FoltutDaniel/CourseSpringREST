package com.zeke.jackson.json.demo;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class App {

	public static void main(String[] args) {
		
		try {
			//create obj mapper
			ObjectMapper mapper = new ObjectMapper();
			
			//read JSON file and map conver to java obj
			Student theStudent =
					mapper.readValue(new File("data/sample-full.json"), Student.class);
			
			System.out.println(theStudent.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
