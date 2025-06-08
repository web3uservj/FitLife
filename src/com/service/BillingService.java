package com.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.dao.BillingDAO;
import com.dao.MemberDAO;
import com.dao.PackageDAO;
import com.model.Billing;
import com.model.Member;
import com.model.Package;
import com.model.Schedule;

public class BillingService {

    private BillingDAO billingDAO = new BillingDAO();
    private MemberDAO memberDAO = new MemberDAO();
    private PackageDAO packageDAO = new PackageDAO();

    public boolean processBilling(int memberId, Schedule schedule, int packageId) {
      
        Member member = memberDAO.viewMemberById(memberId);
        Package pack = packageDAO.getPackageById(packageId);

        if (member == null || pack == null || schedule == null) {
            System.out.println("❌ Invalid input: member, package, or schedule not found.");
            return false;
        }

        
        BigDecimal amount = BigDecimal.valueOf(pack.getPrice());

       
        Billing billing = new Billing();
        billing.setMember(member);
        billing.setPack(pack);
        billing.setBillingDate(LocalDate.now());
        billing.setAmount(amount.doubleValue());
        billing.setBillingStatus("Pending"); // e.g., "PAID", "UNPAID", "OVERDUE"

        // Persist billing
        return billingDAO.createBilling(billing);
    }
    
    public Billing getLatestBillForMember(int memberId) {
        return billingDAO.getLatestBillForMember(memberId);
    }
    public boolean cancelBill(int billId) {
        return billingDAO.cancelBill(billId);
        
    }
    public Billing getBillById(int billId) {
        return billingDAO.getBillById(billId);
    }

    public List<Billing> getBillsByMemberId(int memberId) {
        return billingDAO.getBillsByMemberId(memberId);
    }

    public List<Billing> getAllBills() {
        return billingDAO.getAllBills();
    }
    public boolean updateBillDetails(int billId, double newAmount, String billingStatus) {
        return billingDAO.updateBillDetails(billId, newAmount, billingStatus);
    }

    public boolean deleteBill(int billId) {
        return billingDAO.deleteBill(billId);
    }

	public boolean updateBillStatus(Billing bill) {
		
		int result=billingDAO.updateBillStatus(bill);
		return result>0?true:false;
	}
}

