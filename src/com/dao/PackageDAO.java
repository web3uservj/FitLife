package com.dao;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.model.Package;
import com.model.Plan;
import com.util.DatabaseUtil;

public class PackageDAO {

    public int addPackage(Package pack) {
        int result = 0;
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "INSERT INTO packages (name, price, planId) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, pack.getName());
            stmt.setBigDecimal(2, BigDecimal.valueOf(pack.getPrice()));
            stmt.setInt(3, pack.getPlanId());

            result = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Package getPackageById(int packageId) {
        Package pack = null;
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT p.packageId, p.name AS packageName, p.price, " +
                         "pl.planId, pl.name AS planName, pl.description, pl.durationWeeks " +
                         "FROM packages p JOIN plans pl ON p.planId = pl.planId WHERE p.packageId = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, packageId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                pack = extractPackageFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pack;
    }

    public List<Package> getAllPackages() {
        List<Package> packages = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT p.packageId, p.name AS packageName, p.price, " +
                         "pl.planId, pl.name AS planName, pl.description, pl.durationWeeks " +
                         "FROM packages p JOIN plans pl ON p.planId = pl.planId";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Package pack = extractPackageFromResultSet(rs);
                packages.add(pack);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return packages;
    }

    public boolean updatePackageById(int packageId, Package updatedPackage) {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "UPDATE packages SET name = ?, price = ?, planId = ? WHERE packageId = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, updatedPackage.getName());
            stmt.setBigDecimal(2, BigDecimal.valueOf(updatedPackage.getPrice()));
            stmt.setInt(3, updatedPackage.getPlanId());
            stmt.setInt(4, packageId);

            int rowsAffected = stmt.executeUpdate();
            return DatabaseUtil.checkUpdate(rowsAffected);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deletePackageById(int packageId) {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "DELETE FROM packages WHERE packageId = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, packageId);

            int rowsAffected = stmt.executeUpdate();
            return DatabaseUtil.checkUpdate(rowsAffected);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Package extractPackageFromResultSet(ResultSet rs) throws SQLException {
        Package pack = new Package();
        pack.setPackageId(rs.getInt("packageId"));
        pack.setName(rs.getString("packageName"));
        pack.setPrice(rs.getBigDecimal("price").doubleValue());

        Plan plan = new Plan(
            rs.getString("planName"),
            rs.getString("description"),
            rs.getInt("durationWeeks")
        );
      
        pack.setPlan(plan); 
       

        return pack;
    }
}
