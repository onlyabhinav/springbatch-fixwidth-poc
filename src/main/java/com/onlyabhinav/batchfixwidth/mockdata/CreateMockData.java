package com.onlyabhinav.batchfixwidth.mockdata;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import com.github.javafaker.Faker;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class CreateMockData {

	private static BufferedWriter writer;

	public static void main(String[] args) throws IOException {

		int progress_chunk = 50;
		int row_count = 10_000;
		String filename = "mockfile_" + row_count + "_"+getRandomNumber()+".txt";

		writer = new BufferedWriter(new FileWriter(filename));

		for (int i = 0; i <= row_count; i++) {
			createMock();

			if (i % progress_chunk == 0)
				log.info("File={} written [{}] of [{}]",filename, i, row_count);
		}

		writer.close();
	}

	private static void createMock() {

		Faker faker = new Faker();

		// faker.company().buzzword().;

		String nameName = faker.artist().name();
		String bdate = faker.date().birthday().toString();
		String city = faker.address().city();
		String companyBuzz = faker.company().buzzword();
		String zipcode = faker.address().zipCode();
		String country = faker.address().country();

		String mockRow = String.format("%-20s | %20s | %20s | %20s | %50s | %20s\n", nameName, zipcode, companyBuzz, city,
				country, bdate);

		try {
			writeToFile(mockRow);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void writeToFile(String mockRow) throws IOException {

		writer.write(mockRow);

	}
	
	private static  int getRandomNumber() {
		int min=10000;
		int max=99999;
		
	    Random random = new Random();
	    return random.nextInt(max - min) + min;
	}
}
