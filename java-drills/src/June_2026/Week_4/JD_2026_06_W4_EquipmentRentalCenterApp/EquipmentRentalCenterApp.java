package src.June_2026.Week_4.JD_2026_06_W4_EquipmentRentalCenterApp;

import java.util.ArrayList;

public class EquipmentRentalCenterApp {
    public static void main(String[] args) {
        ArrayList<RentalRequest> requests = new ArrayList<RentalRequest>();
        ArrayList<Equipment> equipments = new ArrayList<>();
        RentalCenter center = new RentalCenter(equipments, requests);

        System.out.println("=== Registering Equipments ===");
        center.registerEquipment(new Camera("C-001"));
        center.registerEquipment(new Laptop("L-001"));
        center.registerEquipment(new Projector("P-001"));
        center.registerEquipment(new Camera("C-002"));
        center.registerEquipment(new Microphone("M-001"));
        System.out.println();

        center.printEquipmentList();

        System.out.println("=== Submitting Rental Requests ===");
        center.reportRequest(new RentalRequest("R-001", "Daniel", RequestType.CODING_WORKSHOP, 50));
        center.reportRequest(new RentalRequest("R-002", "David", RequestType.MEDIA_PROJECT, 50));
        center.reportRequest(new RentalRequest("R-003", "Sean", RequestType.PRESENTATION, 14));
        center.reportRequest(new RentalRequest("R-004", null, RequestType.PRESENTATION, 15));
        center.reportRequest(new RentalRequest("R-005", "Marco", RequestType.EVENT, 16));
        center.reportRequest(new RentalRequest("R-006", "Pedro", RequestType.EVENT, 17));
        System.out.println();

        center.requestSummary();

        center.assignRequest();

        center.requestSummary();

        center.rentTo();

        center.printEquipmentList();

        System.out.println("=== Returning Equipment ===");
        center.returnEquipment("C-001");
        System.out.println();

        center.requestSummary();
        center.printEquipmentList();
    }
}
