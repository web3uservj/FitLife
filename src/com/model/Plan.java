package com.model;

public class Plan {
    
    private int planId;                 
    private String name;
    private String description;
    private int durationWeeks;

    public Plan(int planId, String name, String description, int durationWeeks) {
        this.planId = planId;
        this.name = name;
        this.description = description;
        this.durationWeeks = durationWeeks;
    }
    public Plan(String name, String description, int durationWeeks) {
        this.name = name;
        this.description = description;
        this.durationWeeks = durationWeeks;
    }


    public int getPlanId() {
        return planId;
    }

    // No setter for planId to keep IDs immutable

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDurationWeeks() {
        return durationWeeks;
    }

    public void setDurationWeeks(int durationWeeks) {
        this.durationWeeks = durationWeeks;
    }

    @Override
    public String toString() {
        final String CYAN = "\u001B[36m";
        final String GREEN = "\u001B[32m";
        final String RESET = "\u001B[0m";

        return "\n" +
            CYAN + "《 Plan Details 》" + RESET + "\n" +
            "────────────────────────────────────────────\n" +
            GREEN + "Plan ID       " + RESET + ": " + planId + "\n" +
            GREEN + "Name          " + RESET + ": " + name + "\n" +
            GREEN + "Description   " + RESET + ": " + description + "\n" +
            GREEN + "Duration      " + RESET + ": " + durationWeeks + " week(s)\n" +
            "────────────────────────────────────────────";
    }
}
