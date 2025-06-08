package com.model;

public class Package {
    private static int idCounter = 0;
    private int packageId;
    private String name;
    private double price;
    private Plan plan; // Reference to Plan object

    public Package(String name, double price, Plan plan) {
        this.packageId = ++idCounter;
        this.name = name;
        this.price = price;
        this.plan = plan;
    }
    public Package(){}

    public int getPackageId() {
        return packageId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Plan getPlan() {
        return plan;
    }

    public int getPlanId() {
        return plan.getPlanId(); 
    }

	public static int getIdCounter() {
		return idCounter;
	}

	public static void setIdCounter(int idCounter) {
		Package.idCounter = idCounter;
	}

	public void setPackageId(int packageId) {
		this.packageId = packageId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}
    
	@Override
	public String toString() {
	    final String CYAN = "\u001B[36m";
	    final String GREEN = "\u001B[32m";
	    final String RESET = "\u001B[0m";

	    return "\n" +
	        CYAN + "《 Package Details 》" + RESET + "\n" +
	        "────────────────────────────────────────────\n" +
	        GREEN + "Package ID    " + RESET + ": " + packageId + "\n" +
	        GREEN + "Name          " + RESET + ": " + name + "\n" +
	        GREEN + "Price         " + RESET + ": ₹" + String.format("%.2f", price) + "\n" +
	        GREEN + "Plan Info     " + RESET + ": " + (plan != null ? plan.getName() + " (" + plan.getDurationWeeks() + " weeks)" : "N/A") + "\n" +
	        "────────────────────────────────────────────";
	}


    
}
