package src.June_2026.Week_4.JD_2026_06_W4_EquipmentRentalCenterApp;

import src.June_2026.Week_4.JD_2026_06_W4_CampusMaintenanceRequestApp.InvalidRequestException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class RentalCenter {
    ArrayList<Equipment> equipments;
    ArrayList<RentalRequest> requests;
    LinkedHashMap<RentalRequest, Equipment> assignedRequests = new LinkedHashMap<>();

    public RentalCenter(ArrayList<Equipment> equipments, ArrayList<RentalRequest> requests) {
        this.equipments = equipments;
        this.requests = requests;
    }

    public void reportRequest(RentalRequest request) {
        try {
            if (request.validateRequest()) {
                System.out.printf("Request %s submitted.\n", request.getRentalId());
                requests.add(request);
            }
        } catch (InvalidRequestException e) {
            System.out.println(e.getMessage());
        }
    }

    public void requestSummary() {
        System.out.println("=== Request Summary ===");
        for (RentalRequest request : requests) {
            request.showRequest();
        }
        System.out.println();
    }

    public void registerEquipment(Equipment equipment) {
        equipments.add(equipment);
        System.out.printf("%s %s registered.\n", equipment.getEquipmentType(), equipment.getEquipmentID());
    }

    public void printEquipmentList() {
        System.out.println("=== Equipment List ===");
        for (Equipment equipment : equipments) {
            equipment.showInfo();
        }
        System.out.println();
    }

    public void assignRequest() {
        System.out.println("=== Assigning Equipment ===");
        for (RentalRequest request : requests) {
            for (Equipment equipment : equipments) {
                if (equipment.canSupport(request)) {
                    equipment.assignTo(request);
                    assignedRequests.put(request, equipment);
                    break;
                }
            }
        }

        for (RentalRequest request : requests) {
            if (!assignedRequests.containsKey(request)) {
                System.out.println("No available equipment for " + request.getRentalId() + ".");
            }
        }
        System.out.println();
        System.out.println("=== Assignment Report ===");
        for (RentalRequest request : assignedRequests.keySet()) {
                System.out.printf("%s | %s | %s | %s %s\n", request.getRentalId(), request.getRequestType(), request.getStudentName(), assignedRequests.get(request).equipmentType, assignedRequests.get(request).equipmentID);
        }
        System.out.println();
    }

    public void rentTo() {
        System.out.println("Renting Equipments...");
        for (RentalRequest request : assignedRequests.keySet()) {
            Equipment equipment = assignedRequests.get(request);
            equipment.rentTo(request);
        }
        System.out.println();
    }

    public void returnEquipment(String equipmentID) {
        boolean found = false;
        for (Map.Entry<RentalRequest, Equipment> entry : assignedRequests.entrySet()) {
            if (equipmentID.equals(entry.getValue().equipmentID)) {
                RentalRequest request = entry.getKey();
                Equipment equipment = entry.getValue();
                equipment.markReturned();
                request.markReturned();
                System.out.printf("%s %s returned.\n", equipment.getEquipmentType(), equipment.getEquipmentID());
                assignedRequests.remove(request);
                requests.remove(request);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println(equipmentID + " not found.");
        }
    }
}
