package src.June_2026.Week_4.JD_2026_06_W4_CampusMaintenanceRequestApp;

public abstract class MaintenanceTeam implements Assignable {
    String teamID;
    String baseBuilding;
    boolean available;
    int completedRequestCount;

    public MaintenanceTeam(String teamID, String baseBuilding) {
        this.teamID = teamID;
        this.baseBuilding = baseBuilding;
        this.available = true;
        this.completedRequestCount = 0;
    }

    @Override
    public boolean canHandle(MaintenanceRequest request) {
        return false;
    }

    @Override
    public void assignRequest(MaintenanceRequest request) {
        this.setAvailable(false);
        request.setAssigned(true);
    }

    public void printMaintenanceTeam() {
        System.out.printf("Team ID: %s | Base Building: %s | Available: %s | CompletedRequestCount: %d\n", teamID, baseBuilding, available, completedRequestCount);
    }

    public abstract String getTeamType();

    public String getTeamID() {
        return teamID;
    }

    public String getBaseBuilding() {
        return baseBuilding;
    }

    public boolean isAvailable() {
        return available;
    }

    public int getCompletedRequestCount() {
        return completedRequestCount;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setCompletedRequestCount(int completedRequestCount) {
        this.completedRequestCount = completedRequestCount;
    }

    public void setTeamID(String teamID) {
        this.teamID = teamID;
    }

    public void setBaseBuilding(String baseBuilding) {
        this.baseBuilding = baseBuilding;
    }
}
