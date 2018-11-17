package com.nagarro.input;

import com.nagarro.model.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

public class InputValidator {
	static SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
	public static String ValidateSource(String readLine) {
		// TODO Auto-generated method stub
		synchronized(DirectoryReader.flightInfo) 
		{
			for (HashSet<Flight> flightSet : DirectoryReader.flightInfo.values())
			{
				for (Flight f : flightSet) {
					if (f.getDepLoc().equalsIgnoreCase(readLine));
						return f.getDepLoc() ;
				}
			}
		}
		System.err.print("Flights from no such stations found, Kindly Enter Again: ");
		return null;
	}

	public static String ValidateDestination(String readLine) {
		// TODO Auto-generated method stub
		synchronized(DirectoryReader.flightInfo) 
		{
			for (HashSet<Flight> flightSet : DirectoryReader.flightInfo.values())
			{
				for (Flight f : flightSet) {
					if (f.getArrLoc().equalsIgnoreCase(readLine));
						return f.getArrLoc() ;
				}
			}
		}
		System.err.print("Flights from no such stations found, Kindly Enter Again: ");
		return null;
	}

	public static Date ValidateDate(String readLine) {
		// TODO Auto-generated method stub
		Date validTill = null;
		try{
			validTill = dateformat.parse(readLine);
		}
		catch (ParseException e) {
			System.err.print("Date not in appropriate(dd-MM-yyyy) format, Enter Again: ");
		}
		return validTill;
	}

	public static String ValidateClass(String readLine) {
		if (readLine.equalsIgnoreCase("E")||readLine.equalsIgnoreCase("EB"))
			return readLine.toUpperCase() ;
		else
		{
			System.err.print("Flight Class entered Inappropriately, Enter Again :");
			return null;
		}
	}


	public static int ValidatePreference(int parseInt) {
		// TODO Auto-generated method stub
		if((parseInt==1)||(parseInt==2))
			return parseInt ;
		else
		{
			System.err.print("Output preference entered Inappropriately, Enter Again : ");
			return 0;
		}
	}
	
}
