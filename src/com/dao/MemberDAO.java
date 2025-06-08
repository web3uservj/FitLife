package com.dao;

import java.util.List;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.model.Member;
import com.util.DatabaseUtil;

public class MemberDAO {
    public int addMember(Member mem) {
    	int result=0;
    	try {
    		Connection conn=DBConnection.getConnection();
    		String sql= "INSERT INTO members (username, name, contact, email, address, password, dateOfJoined, isActive) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    		PreparedStatement stmt = conn.prepareStatement(sql);
    		stmt.setString(1, mem.getUsername());
    		stmt.setString(2, mem.getName());
    		stmt.setLong(3, mem.getContact());
    		stmt.setString(4, mem.getEmail());
    		stmt.setString(5, mem.getAddress());
    		stmt.setString(6, mem.getPassword());
    		stmt.setDate(7, null);  
    		stmt.setBoolean(8, false);

            result=stmt.executeUpdate();
             
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    	}
    	return result;
    }
    public boolean addMemberByAdmin(Member member) {
        String sql = "INSERT INTO members (username, name, contact, email, address, password, dateOfJoined, isActive) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, member.getUsername());
            ps.setString(2, member.getName());
            ps.setLong(3, member.getContact());
            ps.setString(4, member.getEmail());
            ps.setString(5, member.getAddress());
            ps.setString(6, member.getPassword());
            ps.setDate(7, java.sql.Date.valueOf(member.getDateOfJoined()));  
            ps.setBoolean(8, member.isActive());

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            System.out.println("Error adding member to DB: " + e.getMessage());
            return false;
        }
    }

    
    public int memberLogin(String username, String password) {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT memberId, password FROM members WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String dbPassword = rs.getString("password");
                if (dbPassword.equals(password)) {
                    // Login successful, return memberId
                    return rs.getInt("memberId");  // Assuming memberId is an int
                } else {
                    System.out.println("Incorrect password");
                }
            } else {
                System.out.println("Username not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;  // Return -1 if login failed
    }

    
     public Member viewMemberById(int memberId) {
    	
    	try {
    		Connection conn=DBConnection.getConnection();
    		String sql="select * from members where memberId=?";
    		PreparedStatement stmt=conn.prepareStatement(sql);
    		stmt.setInt(1,memberId);
    		ResultSet rs=stmt.executeQuery();
    		if(rs.next()) {
    			Member mem=MemberDAO.checkResult(rs);
        		return mem;
    		}
    		
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	return null;
    } 
     
     public Member viewMemberByUserName(String username) {
    	 
    	try {
    		Connection conn=DBConnection.getConnection();
    		String sql="select * from members where username=?";
    		PreparedStatement stmt=conn.prepareStatement(sql);
    		stmt.setString(1,username);
    		ResultSet rs=stmt.executeQuery();
    		if(rs.next()) {
    			Member mem=MemberDAO.checkResult(rs);
        		return mem;
    		}
    		
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	return null;
    } 
     
     public List<Member> viewAllMember(){
    	 
    	 List<Member> list=new ArrayList<>();
    	 try {
    		 Connection conn=DBConnection.getConnection();
    		 String sql="select * from members";
    		 PreparedStatement stmt=conn.prepareStatement(sql);
    		 ResultSet rs=stmt.executeQuery();
    		 while(rs.next()) {
    			 Member mem=MemberDAO.checkResult(rs);
 	          list.add(mem);
    		 }
    		 return list;
    	 }
    	 catch(Exception e) {
     		e.printStackTrace();
     	}
    	 return list;
     }
     
     public static Member checkResult(ResultSet rs) throws SQLException {
    	    Member mem = new Member();
    	    mem.setMemberId(rs.getInt("memberId"));
    	    mem.setUsername(rs.getString("username"));
    	    mem.setName(rs.getString("name"));
    	    mem.setContact(rs.getLong("contact"));
    	    mem.setEmail(rs.getString("email"));
    	    mem.setAddress(rs.getString("address"));
    	    mem.setPassword(rs.getString("password"));
    	    
    	    java.sql.Date sqlDate = rs.getDate("dateOfJoined");
    	    if (sqlDate != null) {
    	        mem.setDateOfJoined(sqlDate.toLocalDate());
    	    }

    	    mem.setIsActive(rs.getBoolean("isActive"));
    	    return mem;
    	}
     public boolean updateMember(Member member) {
    	  
    	    String query = "UPDATE members SET dateOfJoined = ?, isActive = ? WHERE memberId = ?";
    	    try (Connection conn = DBConnection.getConnection();
    	         PreparedStatement ps = conn.prepareStatement(query)) {

    	        ps.setDate(1, Date.valueOf(member.getDateOfJoined()));
    	        ps.setBoolean(2, member.isActive());
    	        ps.setInt(3, member.getMemberId());

    	        int rowsUpdated = ps.executeUpdate();
    	        return rowsUpdated > 0;

    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	        return false;
    	    }
    	}


     public boolean updateMemberNameById(int memberId, String newName) {
    	    try {
    	        Connection conn = DBConnection.getConnection();
    	        String sql = "UPDATE members SET name = ? WHERE memberId = ?";
    	        PreparedStatement stmt = conn.prepareStatement(sql);
    	        stmt.setString(1, newName);
    	        stmt.setInt(2, memberId);

    	        int rowsAffected = stmt.executeUpdate();
    	        return DatabaseUtil.checkUpdate(rowsAffected);

    	    } catch (Exception e) {
    	        e.printStackTrace();
    	    }
    	    return false;
    	}

    	public boolean updateMemberContactById(int memberId, String newContact) {
    	    try {
    	        Connection conn = DBConnection.getConnection();
    	        String sql = "UPDATE members SET contact = ? WHERE memberId = ?";
    	        PreparedStatement stmt = conn.prepareStatement(sql);
    	        stmt.setString(1, newContact);
    	        stmt.setInt(2, memberId);

    	        int rowsAffected = stmt.executeUpdate();
    	        return DatabaseUtil.checkUpdate(rowsAffected);

    	    } catch (Exception e) {
    	        e.printStackTrace();
    	    }
    	    return false;
    	}

    	public boolean updateMemberEmailById(int memberId, String newEmail) {
    	    try {
    	        Connection conn = DBConnection.getConnection();
    	        String sql = "UPDATE members SET email = ? WHERE memberId = ?";
    	        PreparedStatement stmt = conn.prepareStatement(sql);
    	        stmt.setString(1, newEmail);
    	        stmt.setInt(2, memberId);

    	        int rowsAffected = stmt.executeUpdate();
    	        return DatabaseUtil.checkUpdate(rowsAffected);

    	    } catch (Exception e) {
    	        e.printStackTrace();
    	    }
    	    return false;
    	}

    	public boolean updateMemberAddressById(int memberId, String newAddress) {
    	    try {
    	        Connection conn = DBConnection.getConnection();
    	        String sql = "UPDATE members SET address = ? WHERE memberId = ?";
    	        PreparedStatement stmt = conn.prepareStatement(sql);
    	        stmt.setString(1, newAddress);
    	        stmt.setInt(2, memberId);

    	        int rowsAffected = stmt.executeUpdate();
    	        return DatabaseUtil.checkUpdate(rowsAffected);

    	    } catch (Exception e) {
    	        e.printStackTrace();
    	    }
    	    return false;
    	}

}
