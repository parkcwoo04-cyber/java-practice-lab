package src.June_2026.Week_4.JD_2026_06_W4_CampusMaintenanceRequestApp;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class MaintenanceCenter {
    ArrayList<MaintenanceRequest> maintenanceRequests = new ArrayList<>();
    ArrayList<MaintenanceTeam> maintenanceTeams = new ArrayList<>();
    LinkedHashMap<MaintenanceRequest, MaintenanceTeam> maintenanceRequestsMap = new LinkedHashMap<>();

    public MaintenanceCenter(ArrayList<MaintenanceRequest> maintenanceRequests, ArrayList<MaintenanceTeam> maintenanceTeams) {
        this.maintenanceRequests = maintenanceRequests;
        this.maintenanceTeams = maintenanceTeams;
    }

    public void printMaintenanceRequests(){
        System.out.print("=== Maintenance Requests ===");
        for(MaintenanceRequest maintenanceRequest : maintenanceRequests){
            maintenanceRequest.printRequest();
        }
        System.out.println();
    }

    public void reportRequest(MaintenanceRequest maintenanceRequest){
        try {
            if (maintenanceRequest.getSeverityLevel() > 5 || maintenanceRequest.getSeverityLevel() < 1) {
                throw new InvalidRequestException("Request " + maintenanceRequest.getRequestID() + " rejected: Severity must be between 1 and 5.");
            } else if (maintenanceRequest.getLocation() == null) {
                throw new InvalidRequestException("Request " + maintenanceRequest.getRequestID() + " rejected: Location cannot be blank.");
            } else if (maintenanceRequest.getRequestType() == null) {
                throw new InvalidRequestException("Request " + maintenanceRequest.getRequestID() + " rejected: RequestType cannot be blank.");
            } else if (maintenanceRequest.getRequestorName() == null) {
                throw new InvalidRequestException("Request " + maintenanceRequest.getRequestID() + " rejected: RequestorName cannot be blank.");
            } else {
                System.out.println("Request " + maintenanceRequest.getRequestID() + " accepted.");
                maintenanceRequests.add(maintenanceRequest);
            }
        } catch (InvalidRequestException e) {
            System.out.println(e.getMessage());
        }
    }

    public void registerMaintenanceTeam(MaintenanceTeam maintenanceTeam) {
        maintenanceTeams.add(maintenanceTeam);
        if (maintenanceTeam instanceof ElectricalTeam){
            System.out.print("[ELECTRICAL TEAM] ");
        } else if (maintenanceTeam instanceof PlumbingTeam) {
            System.out.print("[PLUMBING TEAM] ");
        } else if (maintenanceTeam instanceof CleaningTeam) {
            System.out.print("[CLEANING TEAM] ");
        }
        System.out.println(maintenanceTeam.getTeamID() + " registered.");
    }

    public void printMaintenanceTeams() {
        System.out.println("=== [MAINTENANCE TEAM] ===");
        for (MaintenanceTeam maintenanceTeam : maintenanceTeams){
            maintenanceTeam.printMaintenanceTeam();
        }
        System.out.println();
    }

    public void assignRequests() {
        System.out.println("Assigning Requests...");
        for (MaintenanceRequest maintenanceRequest : this.urgentMaintenanceRequests()){
            for (MaintenanceTeam maintenanceTeam : maintenanceTeams){
                if (maintenanceTeam.canHandle(maintenanceRequest)){
                    System.out.print("[URGENT] ");
                    maintenanceTeam.assignRequest(maintenanceRequest);
                    maintenanceRequestsMap.put(maintenanceRequest, maintenanceTeam);
                }
            }
        }

        for (MaintenanceRequest maintenanceRequest : maintenanceRequests) {
            if (!maintenanceRequestsMap.containsKey(maintenanceRequest)) {
                for (MaintenanceTeam maintenanceTeam : maintenanceTeams) {
                    if (maintenanceTeam.canHandle(maintenanceRequest)) {
                        maintenanceTeam.assignRequest(maintenanceRequest);
                        maintenanceRequestsMap.put(maintenanceRequest, maintenanceTeam);
                    }
                }
            }
        }

        for (MaintenanceRequest maintenanceRequest : maintenanceRequests) {
            if (!maintenanceRequest.isAssigned()) {
                System.out.println(maintenanceRequest.getRequestID() + " cannot be assigned. Because there are no team to handle the request.");
            }
        }
        System.out.println();
    }

    public ArrayList<MaintenanceRequest> urgentMaintenanceRequests() {
        ArrayList<MaintenanceRequest> urgentMaintenanceRequests = new ArrayList<>();
        for (MaintenanceRequest maintenanceRequest : maintenanceRequests){
            if (maintenanceRequest.isUrgent()){
                urgentMaintenanceRequests.add(maintenanceRequest);
            }
        }
        return urgentMaintenanceRequests;
    }

    public void showFinalAssignmentReport() {
        System.out.println("=== Final Assignment Report ===");
        for (MaintenanceRequest maintenanceRequest : maintenanceRequestsMap.keySet()){
            MaintenanceTeam team = maintenanceRequestsMap.get(maintenanceRequest);
            System.out.printf("%s | %s | %s | Severity %d | Assigned to %s %s\n", maintenanceRequest.getRequestID(), maintenanceRequest.getRequestType(), maintenanceRequest.getLocation(), maintenanceRequest.getSeverityLevel(), team.getTeamType(), team.getTeamID());
        }
        System.out.println();
    }

    public void printTeamSummary() {
        System.out.println("=== Team Summary ===");
        for (MaintenanceTeam maintenanceTeam : maintenanceTeams){
            System.out.printf("%s %s | Available: %s | Completed: %d\n", maintenanceTeam.getTeamType(), maintenanceTeam.getTeamID(), maintenanceTeam.isAvailable(), maintenanceTeam.getCompletedRequestCount());
        }
        System.out.println();
    }
}
