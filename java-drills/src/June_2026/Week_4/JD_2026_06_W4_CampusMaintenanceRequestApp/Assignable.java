package src.June_2026.Week_4.JD_2026_06_W4_CampusMaintenanceRequestApp;

public interface Assignable {
    boolean canHandle(MaintenanceRequest request);
    void assignRequest(MaintenanceRequest request);
}
