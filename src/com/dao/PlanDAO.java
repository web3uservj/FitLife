package com.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.model.Plan;
import com.util.DatabaseUtil;

public class PlanDAO {

    public int addPlan(Plan plan) {
        int result = 0;
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "INSERT INTO plans (name, description, durationWeeks) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, plan.getName());
            stmt.setString(2, plan.getDescription());
            stmt.setInt(3, plan.getDurationWeeks());

            result = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Plan getPlanById(int id) {
        Plan plan = null;
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM plans WHERE planId = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                plan = extractPlanFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return plan;
    }

    public List<Plan> getAllPlans() {
        List<Plan> plans = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM plans";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Plan plan = extractPlanFromResultSet(rs);
                plans.add(plan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return plans;
    }

    public boolean updatePlanById(int planId, Plan updatedPlan) {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "UPDATE plans SET name = ?, description = ?, durationWeeks = ? WHERE planId = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, updatedPlan.getName());
            stmt.setString(2, updatedPlan.getDescription());
            stmt.setInt(3, updatedPlan.getDurationWeeks());
            stmt.setInt(4, planId);

            int rowsAffected = stmt.executeUpdate();
            return DatabaseUtil.checkUpdate(rowsAffected);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deletePlanById(int planId) {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "DELETE FROM plans WHERE planId = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, planId);

            int rowsAffected = stmt.executeUpdate();
            return DatabaseUtil.checkUpdate(rowsAffected);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Plan extractPlanFromResultSet(ResultSet rs) throws SQLException {
        int planId = rs.getInt("planId"); 
        String name = rs.getString("name");
        String description = rs.getString("description");
        int durationWeeks = rs.getInt("durationWeeks");

        return new Plan(planId, name, description, durationWeeks);
    }

}
