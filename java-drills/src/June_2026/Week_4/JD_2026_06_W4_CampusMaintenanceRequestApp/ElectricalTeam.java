package src.June_2026.Week_4.JD_2026_06_W4_CampusMaintenanceRequestApp;

public class ElectricalTeam extends MaintenanceTeam {
    final String teamType;

    public ElectricalTeam(String teamID, String baseBuilding) {
        super(teamID, baseBuilding);
        this.teamType = "Electrical Team";
    }

    @Override
    public void printMaintenanceTeam() {
    System.out.print("[ELECTRICAL TEAM] ");
    super.printMaintenanceTeam();
    }

    @Override
    public boolean canHandle(MaintenanceRequest request) {
        boolean canHandle = false;

        if (available) {
            if (request.getRequestType() == RequestType.ELECTRICAL) {
                canHandle = true;
            }
        }

        return canHandle;
    }

    @Override
    public void assignRequest(MaintenanceRequest request) {
        System.out.println(request.getRequestID() + " assigned to Electrical Team " + this.teamID + ".");
        super.assignRequest(request);
    }

    @Override
    public String getTeamType() {
        return teamType;
    }

}
