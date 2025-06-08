package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.Trainer;
import com.util.DatabaseUtil;

public class TrainerDAO {
	public int addTrainer(Trainer trainer) {
    	int result=0;
    	try {
    		Connection conn=DBConnection.getConnection();
    		String sql= "INSERT INTO trainers (username, name, specialization, contact, email, isActive, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
    		PreparedStatement stmt = conn.prepareStatement(sql);
    		stmt.setString(1, trainer.getUserName());
    		stmt.setString(2, trainer.getName());
    		stmt.setLong(4, trainer.getContact());
    		stmt.setString(5, trainer.getEmail());
    		stmt.setString(3, trainer.getSpecialization());
    		stmt.setBoolean(6,trainer.getIsActive());
    		stmt.setString(7, trainer.getPassword());
            result=stmt.executeUpdate();
             
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    	}
    	return result;
    }
	public boolean trainerLogin(String username,String password) {
    	try {
    		Connection conn =DBConnection.getConnection();
    		String sql="SELECT * FROM trainers WHERE username=?";
    		PreparedStatement stmt=conn.prepareStatement(sql);
    		stmt.setString(1,username);
    	    ResultSet rs=stmt.executeQuery();
    	    if(rs.next()) {
    	    	String dbPassword=rs.getString("password");
    	    	if(dbPassword.equals(password)) {
    	    		//System.out.println("Login successfull");
    	    		return true;
    	    	}
    	    	else {
    	    		System.out.println("Incorrect password");
    	    	}
    	    }
    	    else {
    	    	System.out.println("username not found");
    	    }
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	return false;
    }
       public Trainer viewTrainerById(int trainerId) {
    	
    	try {
    		Connection conn=DBConnection.getConnection();
    		String sql="select * from trainers where trainerId=?";
    		PreparedStatement stmt=conn.prepareStatement(sql);
    		stmt.setInt(1,trainerId);
    		ResultSet rs=stmt.executeQuery();
    		if(rs.next()) {
    			Trainer trainer=TrainerDAO.checkResult(rs);
        		return trainer;
    		}
    		
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	return null;
    } 
       
       public Trainer viewTrainerByUserName(String username) {
      	 
       	try {
       		Connection conn=DBConnection.getConnection();
       		String sql="select * from trainers where username=?";
       		PreparedStatement stmt=conn.prepareStatement(sql);
       		stmt.setString(1,username);
       		ResultSet rs=stmt.executeQuery();
       		if(rs.next()) {
       			 Trainer trainer=TrainerDAO.checkResult(rs);
           		return trainer;
       		}
       		
       	}
       	catch(Exception e) {
       		e.printStackTrace();
       	}
       	return null;
       } 
       public List<Trainer> viewAllTrainer(){
      	 
      	 List<Trainer> list=new ArrayList<>();
      	 try {
      		 Connection conn=DBConnection.getConnection();
      		 String sql="select * from trainers";
      		 PreparedStatement stmt=conn.prepareStatement(sql);
      		 ResultSet rs=stmt.executeQuery();
      		 while(rs.next()) {
      			 Trainer trainer=TrainerDAO.checkResult(rs);
   	          list.add(trainer);
      		 }
      		 return list;
      	 }
      	 catch(Exception e) {
       		e.printStackTrace();
       	}
      	 return list;
       }
       
       public static Trainer checkResult(ResultSet rs) throws SQLException {
   	    Trainer trainer = new Trainer();
   	    trainer.setTrainerId(rs.getInt("trainerId"));
   	    trainer.setUserName(rs.getString("username"));
   	    trainer.setName(rs.getString("name"));
   	    trainer.setSpecialization(rs.getString("specialization"));
   	    trainer.setContact(rs.getLong("contact"));
   	    trainer.setEmail(rs.getString("email"));
   	    trainer.setIsActive(rs.getBoolean("isActive"));
   	    trainer.setPassword(rs.getString("password"));
   	    return trainer;
   	}
       public boolean updateTrainerNameByUsername(String username, String newName) {
    	   
   	    try {
   	        Connection conn = DBConnection.getConnection();
   	        String sql = "UPDATE trainers SET name = ? WHERE username = ?";
   	        PreparedStatement stmt = conn.prepareStatement(sql);
   	        stmt.setString(1, newName);
   	        stmt.setString(2, username);

   	        int rowsAffected = stmt.executeUpdate();
    	       return DatabaseUtil.checkUpdate(rowsAffected);
    	        
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	    }
    	    return false;
   	    
   	}

    public boolean updateTrainerContactByUsername(String username, String newContact) {
   	   
   	    try {
   	        Connection conn = DBConnection.getConnection();
   	        String sql = "UPDATE trainers SET contact = ? WHERE username = ?";
   	        PreparedStatement stmt = conn.prepareStatement(sql);
   	        stmt.setString(1, newContact);
   	        stmt.setString(2, username);

   	        int rowsAffected = stmt.executeUpdate();
    	       return DatabaseUtil.checkUpdate(rowsAffected);
    	        
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	    }
    	    return false;
   	}
    public boolean updateTrainerEmailByUsername(String username, String newEmail) {
   	   
   	    try {
   	        Connection conn = DBConnection.getConnection();
   	        String sql = "UPDATE trainers SET email = ? WHERE username = ?";
   	        PreparedStatement stmt = conn.prepareStatement(sql);
   	        stmt.setString(1, newEmail);
   	        stmt.setString(2, username);

   	        int rowsAffected = stmt.executeUpdate();
    	       return DatabaseUtil.checkUpdate(rowsAffected);
    	        
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	    }
    	    return false;
   	}
    public boolean updateTrainerSpecializationByUsername(String username, String newSpecialization) {
   	    
   	    try {
   	        Connection conn = DBConnection.getConnection();
   	        String sql = "UPDATE trainers SET specialization = ? WHERE username = ?";
   	        PreparedStatement stmt = conn.prepareStatement(sql);
   	        stmt.setString(1, newSpecialization);
   	        stmt.setString(2, username);

   	        int rowsAffected = stmt.executeUpdate();
   	       return DatabaseUtil.checkUpdate(rowsAffected);
   	        
   	    } catch (Exception e) {
   	        e.printStackTrace();
   	    }
   	    return false;
   	}

}
      
