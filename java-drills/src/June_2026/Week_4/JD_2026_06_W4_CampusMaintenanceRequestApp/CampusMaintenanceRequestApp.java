package src.June_2026.Week_4.JD_2026_06_W4_CampusMaintenanceRequestApp;

import com.sun.tools.javac.Main;

import java.util.ArrayList;

public class CampusMaintenanceRequestApp {
    public static void main(String[] args) {
        ArrayList<MaintenanceRequest> requests = new ArrayList<>();
        ArrayList<MaintenanceTeam> teams = new ArrayList<>();
        MaintenanceCenter center = new MaintenanceCenter(requests, teams);

        System.out.println("Campus Maintenance Request System");
        System.out.println("---------------------------------");
        System.out.println();

        System.out.println("Registering Teams...");
        center.registerMaintenanceTeam(new ElectricalTeam("E-001", "Electric Maintenance"));
        center.registerMaintenanceTeam(new PlumbingTeam("P-001", "Purdue Hall"));
        center.registerMaintenanceTeam(new CleaningTeam("C-001", "Hawkins Hall"));
        System.out.println();

        center.printMaintenanceTeams();

        System.out.println("Reporting Requests...");
        center.reportRequest(new MaintenanceRequest("R-1001", "Daniel", "Library", RequestType.ELECTRICAL, 2));
        center.reportRequest(new MaintenanceRequest("R-1002", "Wilson", null, RequestType.PLUMBING, 6));
        center.reportRequest(new MaintenanceRequest("R-1003", "Sarah", "Wiley", RequestType.CLEANING, 4));
        center.reportRequest(new MaintenanceRequest("R-1004", "Dylan", "Oakley", RequestType.PLUMBING, 2));
        center.reportRequest(new MaintenanceRequest("R-1005", "Pedro", "Engineering Hall", RequestType.PLUMBING, 4));
        System.out.println();

        center.printMaintenanceRequests();

        center.assignRequests();

        center.showFinalAssignmentReport();

        center.printTeamSummary();
    }
}
