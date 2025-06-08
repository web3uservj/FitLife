package com.dao;

import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.exception.TrainerNotAvailableException;
import com.model.Schedule;

public class ScheduleDAO {
	
	 public void checkTrainerAvailability(int trainerId, LocalDateTime sessionStart, int durationMinutes)
	            throws TrainerNotAvailableException {

	        String sql = "SELECT COUNT(*) FROM Schedule s " +
	                     "JOIN trainers t ON s.trainerId = t.trainerId " +
	                     "WHERE s.trainerId = ? " +
	                     "AND t.isActive = 1 " +
	                     "AND (? < DATE_ADD(s.session_time, INTERVAL s.duration_minutes MINUTE)) " +
	                     "AND (s.session_time < ?)";

	        try (Connection conn = DBConnection.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {

	            stmt.setInt(1, trainerId);
	            stmt.setTimestamp(2, Timestamp.valueOf(sessionStart));
	            stmt.setTimestamp(3, Timestamp.valueOf(sessionStart.plusMinutes(durationMinutes)));

	            ResultSet rs = stmt.executeQuery();
	            if (rs.next() && rs.getInt(1) > 0) {
	                throw new TrainerNotAvailableException("⚠️ Trainer is not available for the selected time slot.");
	            }

	        } catch (SQLException e) {
	            e.printStackTrace(); // Or handle appropriately
	        }
	    }

	public boolean createSession(Schedule schedule) {
	    String sql = "INSERT INTO Schedule (memberId, trainerId, session_date, session_time, duration_minutes) VALUES (?, ?, ?, ?, ?)";
	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        
	        stmt.setInt(1, schedule.getMember().getMemberId());
	        stmt.setInt(2, schedule.getTrainer().getTrainerId());
	        stmt.setDate(3, java.sql.Date.valueOf(schedule.getSessionDate())); // convert LocalDate to sql.Date
	        stmt.setTimestamp(4, java.sql.Timestamp.valueOf(schedule.getSessionTime())); // convert LocalDateTime to sql.Timestamp
	        stmt.setInt(5, schedule.getDurationMinutes());

	        int rows = stmt.executeUpdate();
	        return rows > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}
	public boolean updateSession(int scheduleId, LocalDateTime newSessionTime, int newDurationMinutes) {
	    String sql = "UPDATE Schedule SET session_date = ?, session_time = ?, duration_minutes = ? WHERE scheduleId = ?";
	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        LocalDate sessionDate = newSessionTime.toLocalDate(); // Extract date

	        stmt.setDate(1, Date.valueOf(sessionDate)); // session_date
	        stmt.setTimestamp(2, Timestamp.valueOf(newSessionTime)); // session_time
	        stmt.setInt(3, newDurationMinutes); // duration_minutes
	        stmt.setInt(4, scheduleId); // scheduleId

	        int rowsUpdated = stmt.executeUpdate();
	        return rowsUpdated > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}

	public Schedule getScheduleById(int scheduleId) {
	    String sql = "SELECT * FROM Schedule WHERE scheduleId = ?";
	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setInt(1, scheduleId);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            return mapResultSetToSchedule(rs);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	public List<Schedule> getSchedulesByMemberId(int memberId) {
	    List<Schedule> schedules = new ArrayList<>();
	    String sql = "SELECT * FROM Schedule WHERE memberId = ? ORDER BY session_time";
	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setInt(1, memberId);
	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            schedules.add(mapResultSetToSchedule(rs));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return schedules;
	}
	
	public List<Schedule> getSchedulesByTrainerId(int trainerId) {
	    List<Schedule> schedules = new ArrayList<>();
	    String sql = "SELECT * FROM Schedule WHERE trainerId = ? ORDER BY session_time";
	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setInt(1, trainerId);
	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            schedules.add(mapResultSetToSchedule(rs));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return schedules;
	}
	public List<Schedule> getAllSchedules() {
	    List<Schedule> schedules = new ArrayList<>();
	    String sql = "SELECT * FROM Schedule ORDER BY session_time";
	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            schedules.add(mapResultSetToSchedule(rs));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return schedules;
	}
	private Schedule mapResultSetToSchedule(ResultSet rs) throws SQLException {
	    Schedule schedule = new Schedule();
	    schedule.setScheuleId(rs.getInt("scheduleId"));

	    // Optional: Replace with real DAO lookups
	    int memberId = rs.getInt("memberId");
	    int trainerId = rs.getInt("trainerId");

	    MemberDAO memberDAO = new MemberDAO();
	    TrainerDAO trainerDAO = new TrainerDAO();

	    schedule.setMember(memberDAO.viewMemberById(memberId));
	    schedule.setTrainer(trainerDAO.viewTrainerById(trainerId));

	    schedule.setSessionDate(rs.getDate("session_date").toLocalDate());
	    schedule.setSessionTime(rs.getTimestamp("session_time").toLocalDateTime());
	    schedule.setDurationMinutes(rs.getInt("duration_minutes"));

	    return schedule;
	}


}
