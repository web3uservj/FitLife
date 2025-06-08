package com.dao;

import java.util.List;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import com.model.Billing;
import com.model.Member;
import com.model.Package;
import com.service.PackageService;

public class BillingDAO {
	public boolean createBilling(Billing billing) {
	    String sql = "INSERT INTO billing (memberId, packageId, billingDate, amount, billingStatus) VALUES (?, ?, ?, ?, ?)";

	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setInt(1, billing.getMember().getMemberId());    
	        stmt.setInt(2, billing.getPack().getPackageId());     
	        stmt.setDate(3, java.sql.Date.valueOf(billing.getBillingDate())); 
	        stmt.setBigDecimal(4, BigDecimal.valueOf(billing.getAmount())); 
	        stmt.setString(5, billing.getBillingStatus());

	        int rows = stmt.executeUpdate();
	        return rows > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}
	public Billing getLatestBillForMember(int memberId) {
	    String query = "SELECT * FROM billing WHERE memberId = ? ORDER BY billingDate DESC, billingId DESC LIMIT 1";


	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(query)) {

	        ps.setInt(1, memberId);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            Billing bill = new Billing();

	            bill.setBillId(rs.getInt("billingId"));

	            MemberDAO memberDAO = new MemberDAO();
	            Member member = memberDAO.viewMemberById(memberId);
	            bill.setMember(member);

	           
	            int packageId = rs.getInt("packageId");
	            PackageService packageService = new PackageService(); // Or your DAO
	            Package pack = packageService.getPackageById(packageId);
	            bill.setPack(pack);

	      
	            bill.setBillingDate(rs.getDate("billingDate").toLocalDate());
	            bill.setAmount(rs.getDouble("amount"));
	            bill.setBillingStatus(rs.getString("billingStatus"));

	            return bill;
	        }

	    } catch (SQLException e) {
	        System.out.println("❌ Error fetching latest bill: " + e.getMessage());
	        e.printStackTrace();
	    }

	    return null;
	}

	public boolean cancelBill(int billId) {
	    String query = "UPDATE billing SET billingStatus = ? WHERE billingId = ?";

	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(query)) {

	        ps.setString(1, "Cancelled"); // Or any other standard like "CANCELLED"
	        ps.setInt(2, billId);

	        int rowsUpdated = ps.executeUpdate();
	        return rowsUpdated > 0;

	    } catch (SQLException e) {
	        System.out.println("❌ Error cancelling bill: " + e.getMessage());
	        e.printStackTrace();
	    }

	    return false;
	}

	public boolean updateBillDetails(int billId, double newAmount, String billingStatus) {
	    String query = "UPDATE billing SET amount = ?, billingStatus = ? WHERE billingId = ?";

	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(query)) {

	        ps.setBigDecimal(1, BigDecimal.valueOf(newAmount));
	        ps.setString(2, billingStatus);
	        ps.setInt(3, billId);

	        int rowsUpdated = ps.executeUpdate();
	        return rowsUpdated > 0;

	    } catch (SQLException e) {
	        System.out.println("❌ Error updating bill details: " + e.getMessage());
	        e.printStackTrace();
	    }

	    return false;
	}
	public Billing getBillById(int billId) {
	    String query = "SELECT * FROM billing WHERE billingId = ?";

	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(query)) {

	        ps.setInt(1, billId);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            Billing bill = new Billing();

	            bill.setBillId(rs.getInt("billingId"));

	            MemberDAO memberDAO = new MemberDAO();
	            Member member = memberDAO.viewMemberById(rs.getInt("memberId"));
	            bill.setMember(member);

	            PackageService packageService = new PackageService();
	            Package pack = packageService.getPackageById(rs.getInt("packageId"));
	            bill.setPack(pack);

	            bill.setBillingDate(rs.getDate("billingDate").toLocalDate());
	            bill.setAmount(rs.getDouble("amount"));
	            bill.setBillingStatus(rs.getString("billingStatus"));

	            return bill;
	        }

	    } catch (SQLException e) {
	        System.out.println("❌ Error fetching bill by ID: " + e.getMessage());
	        e.printStackTrace();
	    }

	    return null;
	}
	public List<Billing> getBillsByMemberId(int memberId) {
	    List<Billing> bills = new ArrayList<>();
	    String query = "SELECT * FROM billing WHERE memberId = ? ORDER BY billingDate DESC";

	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(query)) {

	        ps.setInt(1, memberId);
	        ResultSet rs = ps.executeQuery();

	        MemberDAO memberDAO = new MemberDAO();
	        Member member = memberDAO.viewMemberById(memberId);
	        PackageService packageService = new PackageService();

	        while (rs.next()) {
	            Billing bill = new Billing();

	            bill.setBillId(rs.getInt("billingId"));
	            bill.setMember(member);
	            bill.setPack(packageService.getPackageById(rs.getInt("packageId")));
	            bill.setBillingDate(rs.getDate("billingDate").toLocalDate());
	            bill.setAmount(rs.getDouble("amount"));
	            bill.setBillingStatus(rs.getString("billingStatus"));

	            bills.add(bill);
	        }

	    } catch (SQLException e) {
	        System.out.println("❌ Error fetching bills for member: " + e.getMessage());
	        e.printStackTrace();
	    }

	    return bills;
	}
	public List<Billing> getAllBills() {
	    List<Billing> bills = new ArrayList<>();
	    String query = "SELECT * FROM billing ORDER BY billingDate DESC";

	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(query);
	         ResultSet rs = ps.executeQuery()) {

	        MemberDAO memberDAO = new MemberDAO();
	        PackageService packageService = new PackageService();

	        while (rs.next()) {
	            Billing bill = new Billing();

	            bill.setBillId(rs.getInt("billingId"));
	            bill.setMember(memberDAO.viewMemberById(rs.getInt("memberId")));
	            bill.setPack(packageService.getPackageById(rs.getInt("packageId")));
	            bill.setBillingDate(rs.getDate("billingDate").toLocalDate());
	            bill.setAmount(rs.getDouble("amount"));
	            bill.setBillingStatus(rs.getString("billingStatus"));

	            bills.add(bill);
	        }

	    } catch (SQLException e) {
	        System.out.println("❌ Error fetching all bills: " + e.getMessage());
	        e.printStackTrace();
	    }

	    return bills;
	}
	public boolean deleteBill(int billId) {
	    String query = "DELETE FROM billing WHERE billingId = ?";

	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(query)) {

	        ps.setInt(1, billId);
	        int rowsDeleted = ps.executeUpdate();
	        return rowsDeleted > 0;

	    } catch (SQLException e) {
	        System.out.println("❌ Error deleting bill: " + e.getMessage());
	        e.printStackTrace();
	    }

	    return false;
	}
	public int updateBillStatus(Billing bill) {
	    String sql = "UPDATE Billing SET BillingStatus = ? WHERE billingId = ?";

	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setString(1, "Paid");
	        stmt.setInt(2, bill.getBillId());

	        return stmt.executeUpdate(); 

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return 0; 
	}

}
