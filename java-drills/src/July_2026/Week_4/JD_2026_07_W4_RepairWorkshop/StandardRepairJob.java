package src.July_2026.Week_4.JD_2026_07_W4_RepairWorkshop;

public class StandardRepairJob extends RepairJob {
    public StandardRepairJob(String jobID, String customerName, String deviceName) throws InvalidWorkshopOperationException {
        super(jobID, customerName, deviceName);
        setUrgency("STANDARD");
    }
}
