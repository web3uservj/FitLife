package com.service;

import java.util.ArrayList;
import java.util.List;

import com.model.Package;
import com.model.Plan;
import com.dao.PackageDAO;

public class PackageService {

    private final List<Package> localPackageCache = new ArrayList<>();
    private final PackageDAO packageDAO = new PackageDAO();
    private final PlanService planService = new PlanService();

   
    public boolean addPackage(String details) {
        try {
            String[] detailArray = details.split(":");
            if (detailArray.length != 3) {
                System.err.println("Invalid input. Format should be: name:price:planId");
                return false;
            }

            String name = detailArray[0];
            double price = Double.parseDouble(detailArray[1]);
            int planId = Integer.parseInt(detailArray[2]);

            
            Plan plan = planService.getPlanById(planId);
            if (plan == null) {
                System.err.println("Plan with ID " + planId + " does not exist.");
                return false;
            }

            Package pack = new Package(name, price, plan);
            int result = packageDAO.addPackage(pack);
            if (result > 0) {
                localPackageCache.add(pack);
                return true;
            }

        } catch (NumberFormatException e) {
            System.err.println("Invalid number format: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    
    public Package getPackageById(int packageId) {
        return packageDAO.getPackageById(packageId);
    }

    
    public List<Package> getAllPackages() {
        return packageDAO.getAllPackages();
    }

   
    public boolean updatePackageById(int packageId, String details) {
        try {
            String[] detailArray = details.split(":");
            if (detailArray.length != 3) {
                System.err.println("Invalid input. Format should be: name:price:planId");
                return false;
            }

            String name = detailArray[0];
            double price = Double.parseDouble(detailArray[1]);
            int planId = Integer.parseInt(detailArray[2]);

            Plan plan = planService.getPlanById(planId);
            if (plan == null) {
                System.err.println("Plan with ID " + planId + " does not exist.");
                return false;
            }

            Package updatedPackage = new Package(name, price, plan);
            return packageDAO.updatePackageById(packageId, updatedPackage);

        } catch (NumberFormatException e) {
            System.err.println("Invalid number format: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Delete a package by its ID
    public boolean deletePackageById(int packageId) {
        return packageDAO.deletePackageById(packageId);
    }
}
