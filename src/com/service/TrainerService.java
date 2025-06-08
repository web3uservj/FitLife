package com.service;

import java.util.ArrayList;
import java.util.List;

import com.dao.TrainerDAO;
import com.model.Trainer;
import com.util.ApplicationUtil;



public class TrainerService {
	ArrayList<Trainer> list=new ArrayList<Trainer>();
	TrainerDAO dao=new TrainerDAO();
	public boolean addTrainer(String details) {
		String[] detailArray=details.split(":");
		String name=detailArray[0];
		String specialization=detailArray[1];
		long contact=Long.parseLong(detailArray[2]);
    	String email=detailArray[3];
    	String username=ApplicationUtil.createTrainerUserNameCredential(name, specialization, contact);
    	String password=ApplicationUtil.createTrainerPasswordCredential(name,specialization,contact);
    	Trainer trainer=new Trainer(username,name,specialization,contact,email,true,password);
    	int result=dao.addTrainer(trainer);
    	if(result>0) {
    		list.add(trainer);
    		return true;
    	}
    	
    	else {
    		return false;
    	}
    	
	}
	public boolean trainerLogin(String username,String password){
    	return dao.trainerLogin(username,password);
    }
	
	public  Trainer viewTrainerById(int trainerId) {
    	Trainer trainer=dao.viewTrainerById(trainerId);
    	if(trainer==null) {
    		return null;
    	}
    	return trainer;
    }
    public  Trainer viewTrainerByUserName(String username) {
    	Trainer trainer=dao.viewTrainerByUserName(username);
    	if(trainer==null) {
    		return null;
    	}
    	return trainer;
    }
    
    public void viewAllTrainer() {
    	List<Trainer> list=dao.viewAllTrainer();
    	for(Trainer trainer:list) {
    		System.out.println(trainer);
    	}
    	
    }
    public boolean updateTrainerNameByUsername(String username, String newName) {
        boolean isUpdated = dao.updateTrainerNameByUsername(username, newName);

        if (isUpdated) {
            
            for (Trainer m : list) {
                if (m.getUserName().equals(username)) {
                    m.setName(newName);
                    break;
                }
            }
        }

        return isUpdated;
    }
    public boolean updateTrainerContactByUsername(String username, String newContact) {
        
        if (!newContact.matches("\\d{10}")) {
            System.out.println("Invalid contact number. It must be 10 digits.");
            return false;
        }

        boolean isUpdated = dao.updateTrainerContactByUsername(username, newContact);

        if (isUpdated) {
            for (Trainer m : list) {
                if (m.getUserName().equals(username)) {
                    m.setContact(Long.parseLong(newContact));
                    break;
                }
            }
        }

        return isUpdated;
    }
    public boolean updateTrainerEmailByUsername(String username, String newEmail) {
        
        if (!newEmail.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            System.out.println("Invalid email format.");
            return false;
        }

        boolean isUpdated = dao.updateTrainerEmailByUsername(username, newEmail);

        if (isUpdated) {
            for (Trainer m : list) {
                if (m.getUserName().equals(username)) {
                    m.setEmail(newEmail);
                    break;
                }
            }
        }

        return isUpdated;
    }
    public boolean updateTrainerSpecializationByUsername(String username, String newSpecialization) {
        boolean isUpdated = dao.updateTrainerSpecializationByUsername(username, newSpecialization);

        if (isUpdated) {
            for (Trainer m : list) {
                if (m.getUserName().equals(username)) {
                    m.setSpecialization(newSpecialization);
                    break;
                }
            }
        }

        return isUpdated;
    }


	
	
}
