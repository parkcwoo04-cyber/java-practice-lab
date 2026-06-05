package src.June_2026.Week_1.JD_2026_06_W1_EquipmentRental;

public class EquipmentRentalApp {
    public static void main(String[] args) {
        Equipment[] equipments = {
                new Equipment("CAM-01", "DSLR Camera", "Camera", false),
                new Equipment("MIC-02", "Wireless Mic", "Audio", false),
                new Equipment("TRI-01", "Tripod", "Support", false),
                new Equipment("TRI-02", "Metal Tripod", "Support", false),
                new Equipment("CAM-03", "Film Camera", "Camera", false),
        };

        RentalDesk rentalDesk = new RentalDesk(equipments);

        System.out.println("===== Available Equipment =====");
        rentalDesk.printAvailableRentals();
        System.out.println();

        rentalDesk.rentEquipment("cam-01");

        rentalDesk.rentEquipment("cam-01");

        rentalDesk. rentEquipment("MIC-02");

        rentalDesk.rentEquipment("tri-02");

        rentalDesk.returnItem("cam-01");

        rentalDesk.returnRentedEquipmentsCount();
    }
}
