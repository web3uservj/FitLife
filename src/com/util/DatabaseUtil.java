package com.util;

public class DatabaseUtil {
	public static boolean checkUpdate(int rowsAffected) {
   	 boolean isUpdated = false;
   	 if (rowsAffected > 0) {
	            System.out.println("Member detail updated successfully.");
	            isUpdated = true;
	        } else {
	            System.out.println("No member found with the given username.");
	        }
	    
	    return isUpdated;
    }
	
}
