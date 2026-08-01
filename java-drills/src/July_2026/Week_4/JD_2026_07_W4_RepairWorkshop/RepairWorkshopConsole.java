package src.July_2026.Week_4.JD_2026_07_W4_RepairWorkshop;

public class RepairWorkshopConsole {
    public static void main(String[] args) {
        Workshop workshop = new Workshop();
        RepairJobStorage storage = new RepairJobStorage(workshop);

        System.out.println("=== REGISTRATION ===");
        registerStandardJob(workshop, "RJ-101", "Joon", "Laptop");
        registerUrgentJob(workshop, "RJ-102", "Mina", "Camera");
        registerStandardJob(workshop, "RJ-103", "Shawn", "Game Console");
        registerStandardJob(workshop, "RJ-101", "Keagan", "Monitor");
        registerUrgentJob(workshop, "RJ-104", "", "Camera");
        registerUrgentJob(workshop, "RJ-104", "Andrew", "Iphone");
        System.out.println();

        System.out.println("=== WORKFLOW ===");
        diagnoseJob(workshop, "RJ-101", "SSD Replace", 50);
        repair(workshop, "RJ-101");
        collectRepair(workshop, "RJ-101");
        completeRepair(workshop, "RJ-101");
        collectRepair(workshop, "RJ-101");
        System.out.println();

        diagnoseJob(workshop, "RJ-102", "Lens", 25);
        repair(workshop, "RJ-102");
        completeRepair(workshop, "RJ-102");
        System.out.println();

        diagnoseJob(workshop, "RJ-103", "CPU", 100);
        repair(workshop, "RJ-103");
        System.out.println();

        diagnoseJob(workshop, "RJ-104", "Battery", 30);
        cancelRepair(workshop, "RJ-104");
        System.out.println();

        diagnoseJob(workshop, "RJ-999", "", 90);
        System.out.println();

        System.out.println("=== COMPLETED JOBS ====");
        workshop.printCompletedJobs();

        System.out.println("=== STATUS SUMMARY ===");
        workshop.printStatusSummary();

        storage.saveRepairJob();
        System.out.println();
        System.out.println("=== LOADED JOBS ===");
        loadJob(storage);
    }

    private static void registerStandardJob(Workshop workshop, String jobID, String customerName, String deviceName) {
        try {
            RepairJob job = new StandardRepairJob(jobID, customerName, deviceName);
            workshop.registerJob(job);
        } catch (InvalidWorkshopOperationException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void registerUrgentJob(Workshop workshop, String jobID, String customerName, String deviceName) {
        try {
            RepairJob job = new UrgentRepairJob(jobID, customerName, deviceName);
            workshop.registerJob(job);
        } catch (InvalidWorkshopOperationException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void diagnoseJob(Workshop workshop, String jobID, String description, int cost) {
        try {
            workshop.diagnoseJob(jobID,description, cost);
        } catch (InvalidWorkshopOperationException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void completeRepair(Workshop workshop, String jobID) {
        try {
            workshop.completeRepair(jobID);
        } catch (InvalidWorkshopOperationException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void collectRepair(Workshop workshop, String jobID) {
        try {
            workshop.collectRepair(jobID);
        } catch (InvalidWorkshopOperationException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void repair(Workshop workshop, String jobID) {
        try{
            workshop.repair(jobID);
        } catch (InvalidWorkshopOperationException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void cancelRepair(Workshop workshop, String jobID) {
        try{
            workshop.cancelRepair(jobID);
        } catch (InvalidWorkshopOperationException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void loadJob(RepairJobStorage storage) {
        try {
            storage.loadRepairJob();
        } catch (InvalidWorkshopOperationException e) {
            System.out.println(e.getMessage());
        }
    }
}
