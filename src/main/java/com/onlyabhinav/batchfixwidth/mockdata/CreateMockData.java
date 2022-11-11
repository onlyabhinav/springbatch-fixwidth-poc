package com.onlyabhinav.batchfixwidth.mockdata;

import com.github.javafaker.Faker;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class CreateMockData {

	public static void main(String[] args) {

		for (int i = 0; i <= 100; i++)
		{
			createMock();
			log.info("==================================");
		}
	}

	private static void createMock() {

		Faker faker = new Faker();

		//faker.company().buzzword().;
		String streetName = faker.address().streetName();
		String number = faker.address().buildingNumber();
		String city = faker.address().city();
		String country = faker.address().country();

		log.info(String.format("%s\n%s\n%s\n%s", number, streetName, city, country));
	}

}
