package com.service;

import java.util.ArrayList;
import java.util.List;

import com.dao.PlanDAO;
import com.model.Plan;

public class PlanService {
    ArrayList<Plan> list = new ArrayList<>();
    PlanDAO dao = new PlanDAO();

    public boolean addPlan(String details) {
        String[] detailArray = details.split(":");

        if (detailArray.length != 3) {
            System.out.println("Invalid input. Expected format: name:description:durationWeeks");
            return false;
        }

        String name = detailArray[0];
        String description = detailArray[1];
        int durationWeeks;

        try {
            durationWeeks = Integer.parseInt(detailArray[2]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid duration format.");
            return false;
        }

        Plan plan = new Plan(name, description, durationWeeks);
        int result = dao.addPlan(plan);

        if (result > 0) {
            list.add(plan);
            return true;
        } else {
            return false;
        }
    }

    public Plan getPlanById(int planId) {
        return dao.getPlanById(planId);
    }

    public void viewAllPlans() {
        List<Plan> plans = dao.getAllPlans();
        for (Plan plan : plans) {
            System.out.println(plan);
        }
    }

    public boolean updatePlanNameById(int planId, String newName) {
        Plan plan = dao.getPlanById(planId);
        if (plan == null) {
            System.out.println("Plan not found.");
            return false;
        }

        plan.setName(newName);
        boolean isUpdated = dao.updatePlanById(planId, plan);

        if (isUpdated) {
            for (Plan p : list) {
                if (p.getName().equals(plan.getName())) {
                    p.setName(newName);
                    break;
                }
            }
        }

        return isUpdated;
    }

    public boolean updatePlanDescriptionById(int planId, String newDescription) {
        Plan plan = dao.getPlanById(planId);
        if (plan == null) {
            System.out.println("Plan not found.");
            return false;
        }

        plan.setDescription(newDescription);
        boolean isUpdated = dao.updatePlanById(planId, plan);

        if (isUpdated) {
            for (Plan p : list) {
                if (p.getName().equals(plan.getName())) {
                    p.setDescription(newDescription);
                    break;
                }
            }
        }

        return isUpdated;
    }

    public boolean updatePlanDurationById(int planId, int newDurationWeeks) {
        if (newDurationWeeks <= 0) {
            System.out.println("Duration must be positive.");
            return false;
        }

        Plan plan = dao.getPlanById(planId);
        if (plan == null) {
            System.out.println("Plan not found.");
            return false;
        }

        plan.setDurationWeeks(newDurationWeeks);
        boolean isUpdated = dao.updatePlanById(planId, plan);

        if (isUpdated) {
            for (Plan p : list) {
                if (p.getName().equals(plan.getName())) {
                    p.setDurationWeeks(newDurationWeeks);
                    break;
                }
            }
        }

        return isUpdated;
    }

    public boolean deletePlanById(int planId) {
        boolean isDeleted = dao.deletePlanById(planId);

        if (isDeleted) {
            list.removeIf(p -> dao.getPlanById(planId) == null);
        }

        return isDeleted;
    }
}
