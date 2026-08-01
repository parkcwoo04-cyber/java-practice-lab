package src.July_2026.Week_4.JD_2026_07_W4_RepairWorkshop;

import src.July_2026.Week_4.JD_2026_07_W4_EquipmentRentalConsole.RentalStatus;
import src.June_2026.Week_4.JD_2026_06_W4_StudentCourseRegistrationApp.InvalidRegistrationException;

public class RepairJob {
    private String jobID;
    private String customerName;
    private String deviceName;
    private int repairCost;
    private JobStatus status;
    private String diagnosisDescription;
    private int replacePartsCost;
    private String urgency;
    private int finalCharge;

    public RepairJob(String jobID, String customerName, String deviceName) throws InvalidWorkshopOperationException {
        if (jobID == null || jobID.isBlank()) {
            throw new InvalidWorkshopOperationException("Rejected: Job ID cannot be blank.");
        }

        if (customerName == null || customerName.isBlank()) {
            throw new InvalidWorkshopOperationException("Rejected: Customer Name cannot be blank.");
        }

        if (deviceName == null || deviceName.isBlank()) {
            throw new InvalidWorkshopOperationException("Rejected: Device Name cannot be blank.");
        }

        this.jobID = jobID;
        this.customerName = customerName;
        this.deviceName = deviceName;
        this.repairCost = 30;
        this.status = JobStatus.RECEIVED;
        this.diagnosisDescription = "";
        this.replacePartsCost = 0;
        this.urgency = "";
        this.finalCharge = repairCost + replacePartsCost;
    }

    public void printJobDetails() {
        System.out.printf("%s | Customer Name: %s | %s | %s | %s | $%d\n", jobID, customerName, deviceName, getUrgency(), status, finalCharge());
    }

    public boolean isDiagnosed() {
        return status == JobStatus.DIAGNOSED;
    }

    public boolean isInRepair() {
        return status == JobStatus.IN_REPAIR;
    }

    public boolean isReceived() {
        return status == JobStatus.RECEIVED;
    }

    public boolean isCollected() {
        return status == JobStatus.COLLECTED;
    }

    public boolean isCancelled() {
        return status == JobStatus.CANCELLED;
    }

    public int finalCharge() {
        return repairCost + replacePartsCost;
    }

    public boolean isCompleted() {
        return status == JobStatus.COMPLETED;
    }

    public String getJobId() {
        return jobID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public int getRepairCost() {
        return repairCost;
    }

    public JobStatus getStatus() {
        return status;
    }

    public String getDiagnosisDescription() {
        return diagnosisDescription;
    }

    public int getReplacePartsCost() {
        return replacePartsCost;
    }

    public String getUrgency() {
        return urgency;
    }

    public void setRepairCost(int cost) {
        this.repairCost = cost;
    }

    public void setStatus(JobStatus status) {
        this.status = status;
    }

    public void setDiagnosisDescription(String description) {
        this.diagnosisDescription = description;
    }

    public void setReplacePartsCost(int cost) {
        this.replacePartsCost = cost;
    }

    public void setUrgency(String urgency) {
        this.urgency = urgency;
    }

    public void setFinalCharge(int charge) {
        this.finalCharge = charge;
    }
}
