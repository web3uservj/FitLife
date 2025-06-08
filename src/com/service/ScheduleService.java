package com.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.dao.MemberDAO;
import com.dao.ScheduleDAO;
import com.dao.TrainerDAO;
import com.exception.TrainerNotAvailableException;
import com.model.Member;
import com.model.Schedule;
import com.model.Trainer;

public class ScheduleService {
	private final ScheduleDAO scheduleDAO;

    public ScheduleService() {
        this.scheduleDAO = new ScheduleDAO();
    }

	public Schedule validateSession(int memberId, int trainerId, LocalDate sessionDate, LocalDateTime sessionTime, int duration) {
	    ScheduleDAO scheduleDAO = new ScheduleDAO();
	    MemberDAO memberDAO = new MemberDAO();
	    TrainerDAO trainerDAO = new TrainerDAO();

	    Member member = memberDAO.viewMemberById(memberId);
	    Trainer trainer = trainerDAO.viewTrainerById(trainerId);

	    if (member == null || trainer == null) {
	        System.out.println("❌ Member or Trainer not found.");
	        return null;
	    }

	    
	    try {
	        scheduleDAO.checkTrainerAvailability(trainerId, sessionTime, duration);
	    } catch (TrainerNotAvailableException e) {
	        System.out.println(e.getMessage());
	        return null; 
	    }


	    
	    Schedule schedule = new Schedule();
	    schedule.setMember(member);
	    schedule.setTrainer(trainer);
	    schedule.setSessionDate(sessionDate);
	    schedule.setSessionTime(sessionTime);
	    schedule.setDurationMinutes(duration);

	    return schedule;
	}
   public boolean createSession(Schedule schedule) {
	   ScheduleDAO scheduleDAO = new ScheduleDAO();
	   return scheduleDAO.createSession(schedule);
   }
   public boolean updateSession(int scheduleId, LocalDateTime newSessionTime, int newDurationMinutes) {
       return scheduleDAO.updateSession(scheduleId, newSessionTime, newDurationMinutes);
   }

   public Schedule getScheduleById(int scheduleId) {
       return scheduleDAO.getScheduleById(scheduleId);
   }
   public List<Schedule> getSchedulesByMemberId(int memberId) {
       return scheduleDAO.getSchedulesByMemberId(memberId);
   }
   public List<Schedule> getSchedulesByTrainerId(int trainerId) {
       return scheduleDAO.getSchedulesByTrainerId(trainerId);
   }

  
   public List<Schedule> getAllSchedules() {
       return scheduleDAO.getAllSchedules();
   }

}
