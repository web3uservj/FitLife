package com.ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

import com.dao.MemberDAO;
import com.model.Billing;
import com.model.Member;
import com.model.Plan;
import com.model.Package;
import com.model.Trainer;
import com.model.Schedule;
import com.service.BillingService;
import com.service.MemberService;
import com.service.TrainerService;
import com.service.PlanService;
import com.service.PackageService;
import com.service.ScheduleService;
import com.util.ApplicationUtil;

public class MainUI {
    private static final Scanner sc = new Scanner(System.in);
    private static final MemberService memService = new MemberService();
    private static final TrainerService trainerService = new TrainerService();
    private static final PlanService planService = new PlanService();
    private static final PackageService packageService = new PackageService();

    static ScheduleService scheduleService = new ScheduleService();
    static BillingService billingService = new BillingService();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Main Menu ===");
            System.out.println("1. Admin Login");
            System.out.println("2. User Login");
            System.out.println("3. User Registration");
            System.out.println("4. Trainer Login");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    adminLogin();
                    break;
                case 2:
                    userLogin();
                    break;
                case 3:
                    userRegistration();
                    break;
                case 4:
                    trainerLogin();
                    break;
                default:
                    System.out.println("⚠️ Invalid option. Please choose between 1 and 4.");
            }
        }
    }

    private static void adminLogin() {
    	sc.nextLine();
        System.out.print("Admin Username: ");
        String adminUsername = sc.nextLine();
        System.out.print("Admin Password: ");
        String adminPassword = sc.nextLine();
        if (adminUsername.equalsIgnoreCase("admin") && adminPassword.equals("12345")) {
            adminAccess();
        } else {
            System.out.println("❌ Invalid admin credentials.");
        }
    }

    private static void userLogin() {
    	sc.nextLine();
        System.out.print("Username: ");
        String username = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();
        
        int memberId = memService.memberLogin(username, password);
        
        if (memberId != -1) {
            System.out.println("✅ Login successful. Member ID: " + memberId);
            userAccess(memberId);  // or pass memberId if you want
        } else {
            System.out.println("❌ Invalid login. Please try again.");
        }
    }


    private static void trainerLogin() {
    	sc.nextLine();
        System.out.print("Trainer Username: ");
        String username = sc.nextLine();
        System.out.print("Trainer Password: ");
        String password = sc.nextLine();
        if (trainerService.trainerLogin(username, password)) {
            System.out.println("✅ Login successful.");
            trainerAccess(username);
        } else {
            System.out.println("❌ Invalid login. Please try again.");
        }
    }

    private static void userRegistration() {
    	sc.nextLine();
        System.out.print("Enter details (username:name:contact:email:address:password): ");
        String userDetails = sc.nextLine();
        String[] details = userDetails.split(":");
        if (ApplicationUtil.validateDetails(details)) {
            if (memService.addMember(userDetails)) {
                System.out.println("✅ Registration successful. Please log in.");
            }
        } else {
            System.out.println("❌ Invalid input format or missing fields.");
        }
    }

    public static void adminAccess() {
        while (true) {
            System.out.println("\n=== Admin Panel ===");
            System.out.println("1. Member");
            System.out.println("2. Trainer");
            System.out.println("3. Plan");
            System.out.println("4. Package");    
            System.out.println("5. Schedule");   
            System.out.println("6. Billing");    
            System.out.println("7. Logout");     
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    memberMenu();
                    break;
                case 2:
                    trainerMenu();
                    break;
                case 3:
                    planMenu();
                    break;
                case 4:
                    packageMenu();   
                    break;
                case 5:
                    scheduleMenu(); 
                    break;
                case 6:
                    billingMenu();   
                    break;
                case 7:
                    System.out.println("Logging out from admin...");
                    return;
                default:
                    System.out.println("⚠️ Invalid choice. Try again.");
            }
        }
    }


    public static void memberMenu() {
        while (true) {
            System.out.println("\n--- Member Menu ---");
            System.out.println("1. Add Member");
            System.out.println("2. View Member by ID");
            System.out.println("3. View All Members");
            System.out.println("4. Back to Admin Panel");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addMember();  
                    break;
                case 2:
                    viewMemberById();
                    break;
                case 3:
                    memService.viewAllMember();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("⚠️ Invalid choice. Try again.");
            }
        }
    }


    public static void trainerMenu() {
        while (true) {
            System.out.println("\n--- Trainer Menu ---");
            System.out.println("1. Add Trainer");
            System.out.println("2. View Trainer by ID");
            System.out.println("3. View All Trainers");
            System.out.println("4. Back to Admin Panel");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addTrainer();
                    break;
                case 2:
                    viewTrainerById();
                    break;
                case 3:
                    trainerService.viewAllTrainer();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("⚠️ Invalid choice. Try again.");
            }
        }
    }
    public static void planMenu() {
        while (true) {
            System.out.println("\n--- Plan Menu ---");
            System.out.println("1. Add Plan");
            System.out.println("2. Update Plan");
            System.out.println("3. View All Plans");
            System.out.println("4. Back to Admin Panel");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addPlan();
                    break;
                case 2:
                    updatePlan();
                    break;
                case 3:
                    planService.viewAllPlans();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("⚠️ Invalid choice. Try again.");
            }
        }
    }
    public static void billingMenu() {
        BillingService billingService = new BillingService();
        
        while (true) {
            System.out.println("\n--- Billing Menu ---");
            System.out.println("1. Update Bill Details");
            System.out.println("2. View Bill by ID");
            System.out.println("3. Get All Bills by Member ID");
            System.out.println("4. Get All Bills");
            System.out.println("5. Delete Bill");
            System.out.println("6. Back to Admin Panel");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Bill ID to update: ");
                    int updateBillId = sc.nextInt();
                    System.out.print("Enter new amount: ");
                    double newAmount = sc.nextDouble();
                    sc.nextLine(); // consume newline
                    System.out.print("Enter new billing status (e.g., PAID, CANCELLED): ");
                    String newStatus = sc.nextLine();

                    boolean updated = billingService.updateBillDetails(updateBillId, newAmount, newStatus);
                    if (updated) {
                        System.out.println("✅ Bill updated successfully.");
                    } else {
                        System.out.println("❌ Failed to update bill.");
                    }
                    break;

                case 2:
                    System.out.print("Enter Bill ID to view: ");
                    int billId = sc.nextInt();
                    Billing bill = billingService.getBillById(billId);
                    if (bill != null) {
                        System.out.println(bill);
                    } else {
                        System.out.println("❌ Bill not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter Member ID to get bills: ");
                    int memberId = sc.nextInt();
                    List<Billing> bills = billingService.getBillsByMemberId(memberId);
                    if (bills.isEmpty()) {
                        System.out.println("No bills found for this member.");
                    } else {
                        for (Billing b : bills) {
                            System.out.println(b);
                            System.out.println("--------------------");
                        }
                    }
                    break;

                case 4:
                    List<Billing> allBills = billingService.getAllBills();
                    if (allBills.isEmpty()) {
                        System.out.println("No bills found.");
                    } else {
                        for (Billing b : allBills) {
                            System.out.println(b);
                            System.out.println("--------------------");
                        }
                    }
                    break;

                case 5:
                    System.out.print("Enter Bill ID to delete: ");
                    int deleteBillId = sc.nextInt();
                    boolean deleted = billingService.deleteBill(deleteBillId);
                    if (deleted) {
                        System.out.println("✅ Bill deleted successfully.");
                    } else {
                        System.out.println("❌ Failed to delete bill.");
                    }
                    break;

                case 6:
                    return;  

                default:
                    System.out.println("⚠️ Invalid choice. Try again.");
            }
        }
    }

    public static void packageMenu() {
        PackageService packageService = new PackageService();
       

        while (true) {
            System.out.println("\n=== Package Menu ===");
            System.out.println("1. Add Package");
            System.out.println("2. View Package by ID");
            System.out.println("3. View All Packages");
            System.out.println("4. Update Package");
            System.out.println("5. Delete Package");
            System.out.println("6. Back to Admin Panel");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter package details (name:price:planId): ");
                    sc.nextLine(); 
                    String details = sc.nextLine();
                    boolean added = packageService.addPackage(details);
                    System.out.println(added ? "✅ Package added successfully." : "❌ Failed to add package.");
                    break;

                case 2:
                    System.out.print("Enter package ID: ");
                    int id = sc.nextInt();
                    Package p = packageService.getPackageById(id);
                    System.out.println(p != null ? p : "❌ Package not found.");
                    break;

                case 3:
                    List<Package> allPackages = packageService.getAllPackages();
                    if (allPackages.isEmpty()) {
                        System.out.println("No packages available.");
                    } else {
                        allPackages.forEach(System.out::println);
                    }
                    break;

                case 4:
                    System.out.print("Enter package ID to update: ");
                    int updateId = sc.nextInt();
                    System.out.print("Enter new details (name:price:planId): ");
                    sc.nextLine();
                    String updateDetails = sc.nextLine();
                    boolean updated = packageService.updatePackageById(updateId, updateDetails);
                    System.out.println(updated ? "✅ Package updated." : "❌ Failed to update package.");
                    break;

                case 5:
                    System.out.print("Enter package ID to delete: ");
                    int deleteId = sc.nextInt();
                    boolean deleted = packageService.deletePackageById(deleteId);
                    System.out.println(deleted ? "✅ Package deleted." : "❌ Failed to delete package.");
                    break;

                case 6:
                    return;

                default:
                    System.out.println("⚠️ Invalid choice.");
            }
        }
    }
    public static void scheduleMenu() {
        ScheduleService scheduleService = new ScheduleService();
        

        while (true) {
            System.out.println("\n=== Schedule Menu ===");
            System.out.println("1. View Schedule by ID");
            System.out.println("2. View Schedules by Member ID");
            System.out.println("3. View Schedules by Trainer ID");
            System.out.println("4. View All Schedules");
            System.out.println("5. Update Session");
            System.out.println("6. Back to Admin Panel");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Schedule ID: ");
                    int id = sc.nextInt();
                    Schedule s = scheduleService.getScheduleById(id);
                    System.out.println(s != null ? s : "❌ Schedule not found.");
                    break;

                case 2:
                    System.out.print("Enter Member ID: ");
                    int memberId = sc.nextInt();
                    List<Schedule> memberSchedules = scheduleService.getSchedulesByMemberId(memberId);
                    if (memberSchedules.isEmpty()) {
                        System.out.println("No schedules found.");
                    } else {
                        memberSchedules.forEach(System.out::println);
                    }
                    break;

                case 3:
                    System.out.print("Enter Trainer ID: ");
                    int trainerId = sc.nextInt();
                    List<Schedule> trainerSchedules = scheduleService.getSchedulesByTrainerId(trainerId);
                    if (trainerSchedules.isEmpty()) {
                        System.out.println("No schedules found.");
                    } else {
                        trainerSchedules.forEach(System.out::println);
                    }
                    break;

                case 4:
                    List<Schedule> allSchedules = scheduleService.getAllSchedules();
                    if (allSchedules.isEmpty()) {
                        System.out.println("No schedules available.");
                    } else {
                        allSchedules.forEach(System.out::println);
                    }
                    break;

                case 5:
                    System.out.print("Enter Schedule ID: ");
                    int scheduleId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Session Date (yyyy-MM-dd): ");
                    String inputDate = sc.nextLine();
                    LocalDate sessionDate = ApplicationUtil.getValidSessionDate(inputDate);

                    if (sessionDate == null) {
                        System.out.println("⚠️ Invalid session date. Must be today or in the future.");
                        return;
                    }

                    System.out.print("Enter Session Time (HH:mm): ");
                    String inputTime = sc.nextLine();
                    LocalTime sessionTime = ApplicationUtil.getValidSessionTime(inputTime);

                    if (sessionTime == null) {
                        System.out.println("⚠️ Invalid session time. Format must be HH:mm (e.g., 10:30).");
                        return;
                    }

                    
                    LocalDateTime newTime = LocalDateTime.of(sessionDate, sessionTime);

                    
                    if (newTime.isBefore(LocalDateTime.now())) {
                        System.out.println("❌ Session time must be in the future.");
                        return;
                    }

                    System.out.print("Enter new duration in minutes: ");
                    int newDuration = sc.nextInt();

                    boolean updated = scheduleService.updateSession(scheduleId, newTime, newDuration);
                    System.out.println(updated ? "✅ Session updated." : "❌ Failed to update session.");
                    break;

                case 6:
                    return;

                default:
                    System.out.println("⚠️ Invalid choice.");
            }
        }
    }

    public static void userAccess(int memberId) {
        while (true) {
            System.out.println("\n=== User Dashboard ===");
            System.out.println("1. Member");
            System.out.println("2. Trainer");
            System.out.println("3. Book Session");
            System.out.println("4. Plan");
            System.out.println("5. Package");
            System.out.println("6. Schedule");
            System.out.println("7. Billing");
            System.out.println("8. Logout");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    userMemberMenu(memberId);
                    break;
                case 2:
                    userTrainerMenu();
                    break;
                case 4:
                    userPlanMenu();
                    break;
                case 3:
                    bookSession(memberId); 
                    break;
                case 5:
                    userPackageMenu();
                    break;
                case 6:
                    userScheduleMenu(memberId);
                    break;
                case 7:
                    userBillingMenu(memberId);
                    break;
                case 8:
                    System.out.println("Logging out from user...");
                    return;
                default:
                    System.out.println("⚠️ Invalid option. Try again.");
            }
        }
    }

    public static void userMemberMenu(int memberId) {
        while (true) {
            System.out.println("\n--- Member Menu ---");
            System.out.println("1. Update Your Details");
            System.out.println("2. View Member by ID");
            System.out.println("3. View All Members");
            System.out.println("4. Back to User Dashboard");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    updateMemberProfile(memberId);
                    break;
                case 2:
                    viewMemberById();
                    break;
                case 3:
                    memService.viewAllMember();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("⚠️ Invalid option. Try again.");
            }
        }
    }
    public static void userTrainerMenu() {
        while (true) {
            System.out.println("\n--- Trainer Menu ---");
            System.out.println("1. View Trainer by ID");
            System.out.println("2. View All Trainers");
            System.out.println("3. Back to User Dashboard");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    viewTrainerById();
                    break;
                case 2:
                    trainerService.viewAllTrainer();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("⚠️ Invalid option. Try again.");
            }
        }
    }
    public static void userPlanMenu() {
        PlanService planService = new PlanService();

        while (true) {
            System.out.println("\n--- Plan Menu ---");
            System.out.println("1. View Plan by ID");
            System.out.println("2. View All Plans");
            System.out.println("3. Back to User Dashboard");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Plan ID: ");
                    int planId = sc.nextInt();
                    Plan plan = planService.getPlanById(planId);
                    System.out.println(plan != null ? plan : "❌ Plan not found.");
                    break;

                case 2:
                	planService.viewAllPlans();
                    break;

                case 3:
                    return;

                default:
                    System.out.println("⚠️ Invalid choice. Try again.");
            }
        }
    }

    public static void bookSession(int memberId) {

        System.out.print("Enter Trainer ID: ");
        int trainerId = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Session Date (yyyy-MM-dd): ");
        String inputDate = sc.nextLine();
        LocalDate sessionDate = ApplicationUtil.getValidSessionDate(inputDate);

        if (sessionDate == null) {
            System.out.println("⚠️ Invalid session date. Ensure it's in format yyyy-MM-dd and not in the past.");
            return; 
        }

        System.out.print("Enter Session Time (HH:mm): ");
        String inputTime = sc.nextLine();
        LocalTime sessionTime = ApplicationUtil.getValidSessionTime(inputTime);

        if (sessionTime == null) {
            System.out.println("⚠️ Invalid session time. Please enter in format HH:mm (e.g., 10:30).");
            return; 
        }

        LocalDateTime sessionDateTime = LocalDateTime.of(sessionDate, sessionTime);
       

        System.out.print("Enter Duration in minutes: ");
        int duration = sc.nextInt();

        Schedule schedule = scheduleService.validateSession(memberId, trainerId, sessionDate, sessionDateTime, duration);
        if (schedule == null) {
            System.out.println("❌ Could not create session. Try different time or trainer.");
            return;
        }

        System.out.print("Enter Package ID to Book Session: ");
        int packageId = sc.nextInt();
        sc.nextLine();

       
        Package pkg = packageService.getPackageById(packageId); // Assume this method exists
        if (pkg == null) {
            System.out.println("❌ Invalid Package ID.");
            return;
        }

        System.out.println(pkg);
      

        System.out.print("\nSelect an option:\n1. Proceed to Bill Payment\n2. Cancel\nYour choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        if (choice != 1) {
            System.out.println("❌ Session booking cancelled.");
            return;
        }

        // Process billing
        boolean billingCreated = billingService.processBilling(memberId, schedule, packageId); // Create bill first
        if (!billingCreated) {
            System.out.println("❌ Failed to create bill.");
            return;
        }

        // Fetch and show created bill
        Billing bill = billingService.getLatestBillForMember(memberId); // Assume this fetches the most recent bill
        if (bill == null) {
            System.out.println("❌ Bill not found after creation.");
            return;
        }

        System.out.println(bill);
       

        System.out.print("\nConfirm Billing?\n1. Confirm\n2. Cancel\nYour choice: ");
        int confirm = sc.nextInt();
        sc.nextLine();

        if (confirm != 1) {
            billingService.cancelBill(bill.getBillId()); // Optional: cancel the created bill
            System.out.println("❌ Billing cancelled. Session not booked.");
            return;
        }
        if(billingService.updateBillStatus(bill)){
        	System.out.println("Billing status changed.");
        	System.out.println(billingService.getBillById(bill.getBillId()));
        }
        

        // Finalize session
        boolean sessionCreated = scheduleService.createSession(schedule);
        if (sessionCreated) {
            MemberDAO memberDAO = new MemberDAO();
            Member member = memberDAO.viewMemberById(memberId);

            if (member != null) {
                member.setDateOfJoined(LocalDate.now());
                member.setIsActive(true);
                boolean updated = memberDAO.updateMember(member);

                if (updated) {
                    System.out.println("✅ Member status updated.");
                } else {
                    System.out.println("⚠️ Session booked, but failed to update member info.");
                }
            }

            System.out.println("✅ Session successfully booked and stored.");
        } else {
            System.out.println("❌ Billing done but failed to store session.");
        }
    }
    public static void userPackageMenu() {
        PackageService packageService = new PackageService();

        while (true) {
            System.out.println("\n--- Package Menu ---");
            System.out.println("1. View Package by ID");
            System.out.println("2. View All Packages");
            System.out.println("3. Back to User Dashboard");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Package ID: ");
                    int packageId = sc.nextInt();
                    Package p = packageService.getPackageById(packageId);
                    System.out.println(p != null ? p : "❌ Package not found.");
                    break;

                case 2:
                    List<Package> allPackages = packageService.getAllPackages();
                    if (allPackages.isEmpty()) {
                        System.out.println("No packages available.");
                    } else {
                        allPackages.forEach(System.out::println);
                    }
                    break;

                case 3:
                    return;

                default:
                    System.out.println("⚠️ Invalid choice. Try again.");
            }
        }
    }

    public static void userScheduleMenu(int memberId) {
        ScheduleService scheduleService = new ScheduleService();

        while (true) {
            System.out.println("\n--- Schedule Menu ---");
            System.out.println("1. View Schedule by ID");
            System.out.println("2. View My Schedules");
            System.out.println("3. View All Schedules");
            System.out.println("4. Back to User Dashboard");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Schedule ID: ");
                    int scheduleId = sc.nextInt();
                    Schedule s = scheduleService.getScheduleById(scheduleId);
                    System.out.println(s != null ? s : "❌ Schedule not found.");
                    break;

                case 2:
                    List<Schedule> mySchedules = scheduleService.getSchedulesByMemberId(memberId);
                    if (mySchedules.isEmpty()) {
                        System.out.println("No schedules found for you.");
                    } else {
                        mySchedules.forEach(System.out::println);
                    }
                    break;

                case 3:
                    List<Schedule> allSchedules = scheduleService.getAllSchedules();
                    if (allSchedules.isEmpty()) {
                        System.out.println("No schedules available.");
                    } else {
                        allSchedules.forEach(System.out::println);
                    }
                    break;

                case 4:
                    return;

                default:
                    System.out.println("⚠️ Invalid choice. Try again.");
            }
        }
    }


    public static void userBillingMenu(int memberId) {
        BillingService billingService = new BillingService();

        while (true) {
            System.out.println("\n--- Billing Menu ---");
            System.out.println("1. View Bill by ID");
            System.out.println("2. View My Bills");
            System.out.println("3. View All Bills");
            System.out.println("4. Back to User Dashboard");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Bill ID: ");
                    int billId = sc.nextInt();
                    Billing bill = billingService.getBillById(billId);
                    System.out.println(bill != null ? bill : "❌ Bill not found.");
                    break;

                case 2:
                    List<Billing> myBills = billingService.getBillsByMemberId(memberId);
                    if (myBills.isEmpty()) {
                        System.out.println("No bills found for you.");
                    } else {
                        myBills.forEach(System.out::println);
                    }
                    break;

                case 3:
                    List<Billing> allBills = billingService.getAllBills();
                    if (allBills.isEmpty()) {
                        System.out.println("No bills available.");
                    } else {
                        allBills.forEach(System.out::println);
                    }
                    break;

                case 4:
                    return;

                default:
                    System.out.println("⚠️ Invalid choice. Try again.");
            }
        }
    }

    public static void trainerAccess(String username) {
        while (true) {
            System.out.println("\n=== Trainer Dashboard ===");
            System.out.println("1. Trainer");
            System.out.println("2. Member");
            System.out.println("3. Logout");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    trainerMenu(username);
                    break;
                case 2:
                    trainerMemberMenu();
                    break;
                case 3:
                    System.out.println("Logging out from trainer...");
                    return;
                default:
                    System.out.println("⚠️ Invalid option. Try again.");
            }
        }
    }

    public static void trainerMenu(String username) {
        while (true) {
            System.out.println("\n--- Trainer Menu ---");
            System.out.println("1. View My Profile");
            System.out.println("2. Update Trainer Details");
            System.out.println("3. Back to Trainer Dashboard");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    Trainer trainer = trainerService.viewTrainerByUserName(username);
                    if (trainer != null) {
                        System.out.println("👤 Your Profile:\n" + trainer);
                    } else {
                        System.out.println("Trainer not found.");
                    }
                    break;
                case 2:
                    updateTrainerProfile(username);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("⚠️ Invalid option. Try again.");
            }
        }
    }

    public static void trainerMemberMenu() {
        while (true) {
            System.out.println("\n--- Member Menu ---");
            System.out.println("1. View Member by ID");
            System.out.println("2. View All Members");
            System.out.println("3. Back to Trainer Dashboard");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    viewMemberById();
                    break;
                case 2:
                    memService.viewAllMember();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("⚠️ Invalid option. Try again.");
            }
        }
    }

    public static void addMember() {
        System.out.print("How many members do you want to add? ");
        int count = sc.nextInt();
        sc.nextLine(); 

        for (int i = 1; i <= count; i++) {
            System.out.println("\n--- Enter Member " + i + " Details ---");
            System.out.print("Enter details (username:name:contact:email:address:password:dateOfJoining[yyyy-MM-dd]): ");
            String input = sc.nextLine();

            boolean success = memService.addMemberByAdmin(input);

            if (success) {
                System.out.println("✅ Member " + i + " added successfully.");
            } else {
                System.out.println("❌ Failed to add Member " + i + ". Check input format and try again.");
                i--; 
            }
        }
    }

    private static void viewMemberById() {
        System.out.print("Enter Member ID: ");
        int id = sc.nextInt();
        Member member = memService.viewMemberById(id);
        System.out.println(member == null ? "❌ No member found." : "✅ Member:\n" + member);
    }

    private static void viewTrainerById() {
        System.out.print("Enter Trainer ID: ");
        int id = sc.nextInt();
        Trainer trainer = trainerService.viewTrainerById(id);
        System.out.println(trainer == null ? "❌ No trainer found." : "✅ Trainer:\n" + trainer);
    }

    private static void addTrainer() {
    	sc.nextLine();
        System.out.print("Enter trainer details (name:specialization:contact:email:isActive): ");
        String details = sc.nextLine();
        String[] fields = details.split(":");
        if (ApplicationUtil.validateDetails(fields)) {
            boolean success = trainerService.addTrainer(details);
            System.out.println(success ? "✅ Trainer added." : "❌ Failed to add trainer.");
        } else {
            System.out.println("❌ Invalid trainer format.");
        }
    }

    private static void addPlan() {
        System.out.print("Enter plan details (name:description:duration): ");
        sc.nextLine();
        String details = sc.nextLine();
        String[] fields = details.split(":");
        if (fields.length == 3) {
            boolean success = planService.addPlan(details);
            System.out.println(success ? "✅ Plan added successfully." : "❌ Failed to add plan.");
        } else {
            System.out.println("❌ Invalid plan details format.");
        }
    }

    private static void updatePlan() {
    	sc.nextLine();
        System.out.print("Enter Plan ID to update: ");
        int planId = Integer.parseInt(sc.nextLine().trim());

        Plan plan = planService.getPlanById(planId);
        if (plan == null) {
            System.out.println("❌ Plan with ID " + planId + " not found.");
            return;
        }

        System.out.println("\nCurrent Plan Details:");
        System.out.println(plan);

        String[] fields = {"Name", "Duration (weeks)", "Description"};
        boolean continueUpdating = true;

        while (continueUpdating) {
            System.out.println("\nSelect field to update:");
            for (int i = 0; i < fields.length; i++) {
                System.out.println((i + 1) + ". " + fields[i]);
            }
            System.out.println((fields.length + 1) + ". Finish updating");

            System.out.print("Enter choice: ");
            int choice = Integer.parseInt(sc.nextLine().trim());

            switch (choice) {
                case 1: // Update Name
                    System.out.print("Enter new Name: ");
                    String newName = sc.nextLine().trim();
                    if (newName.isEmpty()) {
                        System.out.println("❌ Name cannot be empty.");
                        break;
                    }
                    boolean nameUpdated = planService.updatePlanNameById(planId, newName);
                    System.out.println(nameUpdated ? "✅ Plan name updated." : "❌ Failed to update plan name.");
                    break;

                case 2: // Update Duration
                    System.out.print("Enter new Duration (weeks): ");
                    String durationInput = sc.nextLine().trim();
                    int newDuration;
                    try {
                        newDuration = Integer.parseInt(durationInput);
                        if (newDuration <= 0) {
                            System.out.println("❌ Duration must be positive.");
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("❌ Invalid duration format.");
                        break;
                    }
                    boolean durationUpdated = planService.updatePlanDurationById(planId, newDuration);
                    System.out.println(durationUpdated ? "✅ Plan duration updated." : "❌ Failed to update plan duration.");
                    break;

                case 3: // Update Description
                    System.out.print("Enter new Description: ");
                    String newDescription = sc.nextLine().trim();
                    if (newDescription.isEmpty()) {
                        System.out.println("❌ Description cannot be empty.");
                        break;
                    }
                    boolean descUpdated = planService.updatePlanDescriptionById(planId, newDescription);
                    System.out.println(descUpdated ? "✅ Plan description updated." : "❌ Failed to update plan description.");
                    break;

                case 4:
                    continueUpdating = false;
                    System.out.println("Finished updating plan.");
                    break;

                default:
                    System.out.println("⚠️ Invalid choice, please select again.");
            }
        }
    }



    private static void updateMemberProfile(final int memberId) {
        Member member = memService.viewMemberById(memberId);
        System.out.println("\nYour Profile:\n" + member);

        updateFieldsMenu(
            new String[]{"Name", "Contact", "Email", "Address"},
            new UpdateProvider() {
                public UpdateFunction provide(String ignored) {
                    return new UpdateFunction() {
                        public boolean update(int fieldIndex, String value) {
                            switch (fieldIndex) {
                                case 0:
                                    return memService.updateMemberNameById(memberId, value);
                                case 1:
                                    return memService.updateMemberContactById(memberId, value);
                                case 2:
                                    return memService.updateMemberEmailById(memberId, value);
                                case 3:
                                    return memService.updateMemberAddressById(memberId, value);
                                default:
                                    return false;
                            }
                        }
                    };
                }
            }
        );
    }

    private static void updateTrainerProfile(final String username) {
        updateFieldsMenu(
            new String[]{"Name", "Specialization", "Contact", "Email"},
            new UpdateProvider() {
                public UpdateFunction provide(String ignored) {
                    return new UpdateFunction() {
                        public boolean update(int fieldIndex, String value) {
                            switch (fieldIndex) {
                                case 0:
                                    return trainerService.updateTrainerNameByUsername(username, value);
                                case 1:
                                    return trainerService.updateTrainerSpecializationByUsername(username, value);
                                case 2:
                                    return trainerService.updateTrainerContactByUsername(username, value);
                                case 3:
                                    return trainerService.updateTrainerEmailByUsername(username, value);
                                default:
                                    return false;
                            }
                        }
                    };
                }
            }
        );
    }

    
    interface UpdateFunction {
        boolean update(int fieldIndex, String value);
    }

    interface UpdateProvider {
        UpdateFunction provide(String username);
    }

    private static void updateFieldsMenu(String[] fields, UpdateProvider updaterProvider) {
        while (true) {
            System.out.println("\nUpdate Options:");
            for (int i = 0; i < fields.length; i++) {
                System.out.println((i + 1) + ". " + fields[i]);
            }
            System.out.println((fields.length + 1) + ". Exit");
            System.out.print("Select an option: ");
            int choice = sc.nextInt();
            if (choice == fields.length + 1) break;
            if (choice >= 1 && choice <= fields.length) {
                System.out.print("Enter new " + fields[choice - 1] + ": ");
                String newValue = sc.next();
                boolean success = updaterProvider.provide("").update(choice - 1, newValue);
                System.out.println(success ? "✅ " + fields[choice - 1] + " updated successfully." : "❌ Update failed.");
            } else {
                System.out.println("⚠️ Invalid option.");
            }
        }
    }
}
