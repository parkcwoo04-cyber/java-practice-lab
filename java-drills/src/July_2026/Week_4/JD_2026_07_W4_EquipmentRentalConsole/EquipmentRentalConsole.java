package src.July_2026.Week_4.JD_2026_07_W4_EquipmentRentalConsole;

public class EquipmentRentalConsole {
    public static void main(String[] args) {
        RentalService rentalService = new RentalService();

        System.out.println("=== EQUIPMENT REGISTRATIONS ====");
        rentalService.registerEquipment(new Equipment("EQ-101", "Cordless Drill", 30));
        rentalService.registerEquipment(new Equipment("EQ-102", "Projector", 25));
        rentalService.registerEquipment(new Equipment("EQ-103", "Mirrorless Camera", 35));
        rentalService.registerEquipment(new Equipment("EQ-101", "Cordless Drill", 35));
        System.out.println();

        rentalService.printEquipmentList();

        System.out.println("=== RENTAL OPERATIONS ===");
        rentalService.rentEquipment("EQ-101", "Mina");
        rentalService.rentEquipment("EQ-101", "Richard");
        rentalService.maintenanceEquipment("EQ-101");
        rentalService.maintenanceEquipment("EQ-102");
        rentalService.rentEquipment("EQ-103", "Joon");
        System.out.println();

        rentalService.returnEquipment("EQ-101", 3);
        rentalService.returnEquipment("EQ-102", 5);
        rentalService.returnEquipment("EQ-101", -2);
        System.out.println();

        rentalService.printMaintenanceEquipment();

        rentalService.printEquipmentList();

        rentalService.printStatusSummary();
    }
}
