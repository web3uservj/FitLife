package com.service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import com.dao.MemberDAO;
import com.model.Member;


public class MemberService {
	ArrayList<Member> list=new ArrayList<Member>();
	MemberDAO dao=new MemberDAO();
    public boolean addMember(String details) {
    	String[] detailArray=details.split(":");
    	String username=detailArray[0];
    	String name=detailArray[1];
    	long contact=Long.parseLong(detailArray[2]);
    	String email=detailArray[3];
    	String address=detailArray[4];
    	String password=detailArray[5];
    	Member mem=new Member(username,name,contact,email,address,password);
    	MemberDAO dao=new MemberDAO();
    	int result=dao.addMember(mem);
    	if(result>0) {
    		list.add(mem);
    		return true;
    	}
    	
    	else {
    		return false;
    	}
    }
    public boolean addMemberByAdmin(String memberDetails) {
        try {
            String[] fields = memberDetails.split(":");

            if (fields.length != 7) {
                System.out.println("⚠️ Invalid input format. Please provide exactly 7 fields separated by ':'");
                return false;
            }

            String username = fields[0].trim();
            String name = fields[1].trim();
            String contact = fields[2].trim();
            String email = fields[3].trim();
            String address = fields[4].trim();
            String password = fields[5].trim();
            LocalDate dateOfJoining = LocalDate.parse(fields[6].trim());

            Member member = new Member();
            member.setUsername(username);
            member.setName(name);
            member.setContact(Long.parseLong(contact));
            member.setEmail(email);
            member.setAddress(address);
            member.setPassword(password);
            member.setDateOfJoined(dateOfJoining);
            member.setIsActive(true); 

            
            MemberDAO memberDAO = new MemberDAO();
            return memberDAO.addMemberByAdmin(member); // return true if saved successfully

        } catch (DateTimeParseException e) {
            System.out.println("⚠️ Invalid date format. Please use yyyy-MM-dd");
            return false;
        } catch (Exception e) {
            System.out.println("❌ Error while adding member: " + e.getMessage());
            return false;
        }
    }

    
    public int memberLogin(String username, String password) {
        return dao.memberLogin(username, password);
    }

    public  Member viewMemberById(int memberId) {
    	Member mem=dao.viewMemberById(memberId);
    	if(mem==null) {
    		return null;
    	}
    	return mem;
    }
    public  Member viewMemberByUserName(String username) {
    	Member mem=dao.viewMemberByUserName(username);
    	if(mem==null) {
    		return null;
    	}
    	return mem;
    }
    
    public void viewAllMember() {
    	List<Member> list=dao.viewAllMember();
    	for(Member mem:list) {
    		System.out.println(mem);
    	}
    	
    }
    public boolean updateMemberNameById(int memberId, String newName) {
        boolean isUpdated = dao.updateMemberNameById(memberId, newName);

        if (isUpdated) {
            for (Member m : list) {
                if (m.getMemberId() == memberId) {
                    m.setName(newName);
                    break;
                }
            }
        }

        return isUpdated;
    }

    public boolean updateMemberContactById(int memberId, String newContact) {
        if (!newContact.matches("\\d{10}")) {
            System.out.println("Invalid contact number. It must be 10 digits.");
            return false;
        }

        boolean isUpdated = dao.updateMemberContactById(memberId, newContact);

        if (isUpdated) {
            for (Member m : list) {
                if (m.getMemberId() == memberId) {
                    m.setContact(Long.parseLong(newContact));
                    break;
                }
            }
        }

        return isUpdated;
    }

    public boolean updateMemberEmailById(int memberId, String newEmail) {
        if (!newEmail.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            System.out.println("Invalid email format.");
            return false;
        }

        boolean isUpdated = dao.updateMemberEmailById(memberId, newEmail);

        if (isUpdated) {
            for (Member m : list) {
                if (m.getMemberId() == memberId) {
                    m.setEmail(newEmail);
                    break;
                }
            }
        }

        return isUpdated;
    }

    public boolean updateMemberAddressById(int memberId, String newAddress) {
        boolean isUpdated = dao.updateMemberAddressById(memberId, newAddress);

        if (isUpdated) {
            for (Member m : list) {
                if (m.getMemberId() == memberId) {
                    m.setAddress(newAddress);
                    break;
                }
            }
        }

        return isUpdated;
    }

}
