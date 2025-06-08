package com.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Schedule {

    private static int idCounter = 0;  

    private int scheduleId;
    private Member member;
    private Trainer trainer;
    private LocalDate sessionDate;
    private LocalDateTime sessionTime;
    private int durationMinutes;

    public Schedule() {}
    
    public Schedule(Member member, Trainer trainer, LocalDate sessionDate, LocalDateTime sessionTime, int durationMinutes) {
        this.scheduleId = ++idCounter;  
        this.member = member;
        this.trainer = trainer;
        this.sessionDate = sessionDate;
        this.sessionTime = sessionTime;
        this.durationMinutes = durationMinutes;
    }

    // Getters and setters
    public int getScheduleId() {
        return scheduleId;
    }
    public void setScheuleId(int scheduleId) {
    	this.scheduleId=scheduleId;
    }
    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public LocalDate getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(LocalDate sessionDate) {
        this.sessionDate = sessionDate;
    }

    public LocalDateTime getSessionTime() {
        return sessionTime;
    }

    public void setSessionTime(LocalDateTime sessionTime) {
        this.sessionTime = sessionTime;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }
    @Override
    public String toString() {
        final String CYAN = "\u001B[36m";
        final String GREEN = "\u001B[32m";
        final String RESET = "\u001B[0m";

        return "\n" +
            CYAN + "《 Schedule Details 》" + RESET + "\n" +
            "────────────────────────────────────────────\n" +
            GREEN + "Schedule ID     " + RESET + ": " + scheduleId + "\n" +
            GREEN + "Member          " + RESET + ": " + (member != null ? member.getName() : "N/A") + "\n" +
            GREEN + "Trainer         " + RESET + ": " + (trainer != null ? trainer.getName() : "N/A") + "\n" +
            GREEN + "Session Date    " + RESET + ": " + sessionDate + "\n" +
            GREEN + "Session Time    " + RESET + ": " + (sessionTime != null ? sessionTime.toLocalTime() : "N/A") + "\n" +
            GREEN + "Duration        " + RESET + ": " + durationMinutes + " minutes\n" +
            "────────────────────────────────────────────";
    }

}
