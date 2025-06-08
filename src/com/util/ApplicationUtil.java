package com.util;
import java.time.LocalDate;
import java.time.LocalTime;

import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class ApplicationUtil {
	 private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

		    public static boolean validateDetails(String[] detailArray) {
		        if (detailArray == null) {
		            System.out.println("Invalid input: Insufficient data.");
		            return false;
		        }
		       
		        String contactStr = detailArray[2];
		        if (!contactStr.matches("\\d{10}")) {
		            System.out.println("Invalid contact number: must be exactly 10 digits.");
		            return false;
		        }

		        
		        if (!EMAIL_PATTERN.matcher(detailArray[3]).matches()) {
		            System.out.println("Invalid email format.");
		            return false;
		        }

		        
		        for (int i = 0; i < detailArray.length; i++) {
		            if (detailArray[i] == null || detailArray[i].trim().isEmpty()) {
		                System.out.println("Field " + i + " cannot be empty.");
		                return false;
		            }
		        }

		        
		        return true;
		    }
		    
		    public static String createTrainerUserNameCredential(String name,String specialization,long contact) {
		    	 String namePart = name.toLowerCase().replaceAll(" ", "").substring(0,2);
		    	 String specPart = specialization.toLowerCase().replaceAll(" ", "").substring(0, 3);
		         String contactStr = String.valueOf(contact);
		        return namePart + specPart + contactStr.substring(contactStr.length() - 5);
		    }
		    public static String createTrainerPasswordCredential(String name,String specialization,long contact) {
		    	 String namePart = name.toLowerCase().replaceAll(" ", "").substring(0,2);
		         String contactStr = String.valueOf(contact);
		        return namePart  + contactStr.substring(5);
		    }
		    public static LocalDate getValidSessionDate(String inputDate) {
		        try {
		            LocalDate date = LocalDate.parse(inputDate);
		            if (date.isBefore(LocalDate.now())) {
		               return null;
		            }
		            return date;
		        } catch (DateTimeParseException e) {
		            return null;
		        }
		    }
		    
		    public static LocalTime getValidSessionTime(String input) {
		        try {
		            return LocalTime.parse(input);
		        } catch (DateTimeParseException e) {
		            return null;
		        }
		    }
}
