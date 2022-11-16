package com.onlyabhinav.batchfixwidth.mockdata;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.github.javafaker.Faker;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class FakerData {

	private List<String> names;
	private List<String> birthdates;
	private List<String> cities;
	private List<String> buzzwords;
	private List<String> zipcodes;
	private List<String> countries;

	private int max; 
	
	//public FakerData() {}
	public FakerData(int size) {
		initialze(size);
		this.max = size-1;
		
		log.info("FakerData INITIALIZED");
	}
	
	private void initialze(int size) {

		names = new ArrayList<>();
		birthdates = new ArrayList<>();
		cities =  new ArrayList<>();
		buzzwords = new ArrayList<>();
		zipcodes = new ArrayList<>();
		countries =  new ArrayList<>();
		
		if (size < 10)
			size = 100;
		// faker.company().buzzword().;

		for (int i = 0; i <= size; i++) {
			Faker faker = new Faker();
			String name = faker.artist().name();
			String bdate    = faker.date().birthday().toString();
			String city 	= faker.address().city();
			String companyBuzz = faker.company().buzzword();
			String zipcode = faker.address().zipCode();
			String country = faker.address().country();
			
			names.add(name);
			birthdates.add(bdate);
			cities.add(city);
			buzzwords.add(companyBuzz);
			zipcodes.add(zipcode);
			countries.add(country);

		}

	}

	public String getName() {
		return names.get(getAnyIndex());
	}

	public String getBirthdate() {
		return birthdates.get(getAnyIndex());
	}

	public String getCity() {
		return cities.get(getAnyIndex());
	}

	public String getBuzzword() {
		return buzzwords.get(getAnyIndex());
	}

	public String getZipcode() {
		return zipcodes.get(getAnyIndex());
	}

	public String getCountry() {
		return countries.get(getAnyIndex());
	}
	
	private   int getAnyIndex() {
		int min=0;
		//int max=99999;
		
	    Random random = new Random();
	    return random.nextInt(max - min) + min;
	}

}
