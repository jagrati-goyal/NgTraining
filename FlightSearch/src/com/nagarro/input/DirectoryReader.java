package com.nagarro.input;

import com.opencsv.*;
import com.nagarro.model.*;
import java.io.*;
import java.nio.file.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DirectoryReader {
	final static SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
	public static HashMap<String, HashSet<Flight>> flightInfo = new HashMap<>();

	public static void readDir(String directory) {
		HashSet<Flight> flight_set;
		try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(directory), "*.{csv}")) {
			for (Path path : directoryStream) {
				flight_set = readFile(path.toString());
				flightInfo.put(path.toString(), flight_set);
			}
		} catch (IOException ex) {
		}
		// System.out.println("Read All CSV Files");
		System.out.println(flightInfo.keySet());
	}

	public static HashSet<Flight> readFile(String file) {
		//BufferedReader reader = null;
		CSVReader reader = null;
		CSVParser parser = new CSVParserBuilder().withSeparator('|').build();
		HashSet<Flight> flightSet = new HashSet<>();
		try {
			reader = new CSVReaderBuilder(new FileReader(file)).withSkipLines(1).withCSVParser(parser).build();
			 List<String[]> allData = reader.readAll();
			 
			 for (String[] row : allData) {
				 Flight f = new Flight();
				 f.setFlightNo(row[0]);
				 f.setDepLoc(row[1]);
				 f.setArrLoc(row[2]);
				 Date validTill = new Date();
					try {
						validTill = dateformat.parse(row[3]);
					} catch (ParseException e) {
						System.err.print("Date not in appropriate(dd-MM-yyyy) format");
					}
				 f.setValidTill(validTill);
				 f.setFlightTime(row[4]);
				 f.setFlightDuration(Double.parseDouble(row[5]));
				 f.setFare(Integer.parseInt(row[6]));
				 String avail = row[7];
					Boolean seatAvailability;
					if (avail.charAt(0) == 'Y') {
						seatAvailability = true;
					} else {
						seatAvailability = false;
					}
					f.setSeatAvailability(seatAvailability);
					f.setFlightClass(row[8]);
				 flightSet.add(f);
				 
			 }
//			String line = reader.readLine();
//			line = reader.readLine();
//			while (line != null) {
//				Flight f = manipulateLine(line);
//				line = reader.readLine();
//				flightSet.add(f);

			}
		 catch (Exception e) {
			e.printStackTrace();
		}
		return flightSet;

	}

//	private static Flight manipulateLine(String line) {
//		StringTokenizer st = new StringTokenizer(line, "|");
////use CSV reader
//		String flightNo = st.nextToken();
//		String depLoc = st.nextToken();
//		String arrLoc = st.nextToken();
//
//		String validTillDate = st.nextToken();
//		Date validTill = new Date();
//		try {
//			validTill = dateformat.parse(validTillDate);
//		} catch (ParseException e) {
//			System.err.print("Date not in appropriate(dd-MM-yyyy) format");
//		}
//
//		String flightTime = st.nextToken();
//		Double flightDuration = Double.parseDouble(st.nextToken());
//		int fare = Integer.parseInt(st.nextToken());
//
//		String avail = st.nextToken();
//		Boolean seatAvailability;
//		if (avail.charAt(0) == 'Y') {
//			seatAvailability = true;
//		} else {
//			seatAvailability = false;
//		}
//
//		String flightClass = st.nextToken();
//
//		return new Flight(flightNo, depLoc, arrLoc, fare, validTill, flightTime, flightDuration, seatAvailability,
//				flightClass);
//	}

}
