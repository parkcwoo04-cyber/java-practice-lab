package src.July_2026.Week_4.JD_2026_07_W4_EquipmentRentalConsole;

import java.util.ArrayList;

public class RentalService {
    private ArrayList<Equipment> equipmentList;

    public RentalService(){
        this.equipmentList = new ArrayList<>();
    }

    public void registerEquipment(Equipment equipment){
        for (Equipment equipment1 : equipmentList) {
            if (equipment1.getId().equals(equipment.getId())){
                System.out.println("Registration failed: Equipment ID already exists: " + equipment.getId());
                return;
            }
        }

        equipmentList.add(equipment);
        System.out.println("Registered: " + equipment.getId() + " " + equipment.getName());
    }

    public void printEquipmentList() {
        System.out.println("=== Equipment List ===");
        for (Equipment equipment1 : equipmentList) {
            equipment1.printEquipmentDetails();
        }
        System.out.println();
    }

    public void rentEquipment(String equipmentId, String renter){
        boolean rent = false;
        try {
            for (Equipment equipment : equipmentList) {
                if (equipment.getId().equals(equipmentId)) {
                    if (equipment.available()) {
                        System.out.println("Rental completed: " + equipmentId + " -> " + renter);
                        rent = true;
                        equipment.setStatus(RentalStatus.RENTED);
                        equipment.setRenter(renter);
                    } else {
                        throw new InvalidRentalOperationException(equipmentId + " is not available.");
                    }
                }
            }

            if (!rent) {
                throw new InvalidRentalOperationException("Equipment ID doesn't exist.");
            }
        } catch (InvalidRentalOperationException e) {
            System.out.println(e.getMessage());
        }
    }

    public void maintenanceEquipment(String equipmentId) {
        boolean matched = false;
        try {
            for (Equipment equipment : equipmentList) {
                if (equipment.getId().equals(equipmentId)) {
                    if (equipment.available()) {
                        System.out.println("Maintenance update completed: " + equipmentId + " is now under maintenance.");
                        equipment.setStatus(RentalStatus.MAINTENANCE);
                        matched = true;
                    } else if (equipment.rented()) {
                        throw new InvalidRentalOperationException("Maintenance update failed: Rented equipment cannot enter maintenance.");
                    } else if (equipment.maintenance()) {
                        throw new InvalidRentalOperationException("Maintenance update failed: This equipment is already under maintenance.");
                    }
                }
            }

            if (!matched) {
                throw new InvalidRentalOperationException("Equipment ID doesn't exist.");
            }
        } catch (InvalidRentalOperationException e) {
            System.out.println(e.getMessage());
        }
    }

    public void returnEquipment(String equipmentId, int rentalDays) {
        boolean matched = false;
        try {
            if (rentalDays <= 0) {
                throw new InvalidRentalOperationException("Rental days must be greater than 0.");
            }

            for (Equipment equipment : equipmentList) {
                if (equipment.getId().equals(equipmentId)) {
                    if (equipment.rented()) {
                        System.out.println("Return completed: " + equipmentId);
                        System.out.println("Rental days: " + rentalDays);
                        System.out.println("Fee: $" + rentalDays*equipment.getPrice());
                        System.out.println();
                        equipment.setStatus(RentalStatus.AVAILABLE);
                        equipment.setRenter("-");
                        matched = true;
                    } else {
                        throw new InvalidRentalOperationException("Return failed: " + equipmentId + " is not currently rented.");
                    }
                }
            }
            if (!matched) {
                throw new InvalidRentalOperationException("Equipment ID doesn't exist.");
            }
        } catch (InvalidRentalOperationException e) {
            System.out.println(e.getMessage());
        }
    }

    public void printMaintenanceEquipment() {
        System.out.println("=== MAINTENANCE ITEMS ===");
        for (Equipment equipment : equipmentList) {
            if (equipment.maintenance()) {
                equipment.printEquipmentDetails();
            }
        }
        System.out.println();
    }

    public void printStatusSummary() {
        int available = 0;
        int rented = 0;
        int maintenance = 0;

        System.out.println("=== STATUS SUMMARY ===");
        for (Equipment equipment : equipmentList) {
            if (equipment.available()) {
                available++;
            } else if (equipment.rented()) {
                rented++;
            } else if (equipment.maintenance()) {
                maintenance++;
            }
        }
        System.out.println("AVAILABLE: " + available);
        System.out.println("RENTED: " + rented);
        System.out.println("MAINTENANCE: " + maintenance);
    }
}
