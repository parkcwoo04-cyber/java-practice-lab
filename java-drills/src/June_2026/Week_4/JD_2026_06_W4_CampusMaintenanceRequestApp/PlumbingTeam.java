package src.June_2026.Week_4.JD_2026_06_W4_CampusMaintenanceRequestApp;

public class PlumbingTeam extends MaintenanceTeam {

    final String teamType;

    public PlumbingTeam(String teamID, String baseBuilding) {
        super(teamID, baseBuilding);
        this.teamType = "Plumbing Team";
    }

    @Override
    public void printMaintenanceTeam() {
        System.out.print("[PLUMBING TEAM] ");
        super.printMaintenanceTeam();
    }

    @Override
    public boolean canHandle(MaintenanceRequest request) {
        boolean canHandle = false;

        if (available) {
            if (request.getRequestType() == RequestType.PLUMBING) {
                canHandle = true;
            }
        }

        return canHandle;
    }

    @Override
    public void assignRequest(MaintenanceRequest request) {
        System.out.println(request.getRequestID() + " assigned to Plumbing Team " + this.teamID + ".");
        super.assignRequest(request);
    }

    public String getTeamType() {
        return teamType;
    }
}
