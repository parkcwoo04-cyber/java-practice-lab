package src.June_2026.Week_1.JD_2026_06_W1_EquipmentRental;

public class RentalDesk {
    Equipment[] equipments;

    public RentalDesk(Equipment[] equipments) {
        this.equipments = equipments;
    }

    public void printAvailableRentals() {
        for (Equipment equipment : this.equipments) {
            if(!equipment.getIsRented()) {
                equipment.printRentalInfo();
            }
        }
    }

    private boolean searchItem(String code) {
        boolean found = false;

        for (Equipment equipment : this.equipments) {
            if ((equipment.getCode().trim()).equalsIgnoreCase(code.trim())) {
                found = true;
            }
        }

        return found;
    }

    public void rentEquipment(String code) {
        boolean found = searchItem(code);
        String item = null;
        boolean rented = false;
        System.out.println("Rental request: " + code);

        for (Equipment equipment : this.equipments) {
            if (code.trim().equalsIgnoreCase(equipment.getCode().trim())) {
                item = equipment.getName();
                rented = equipment.getIsRented();
                equipment.setRented(true);
            }
        }

        if (found) {
            if (!rented) {
                System.out.println(item + " has been rented");

            } else {
                System.out.println("Rental failed. " + item + " is already rented.");
            }
        } else {
            System.out.println(code + " does not exist.");
        }
        System.out.println();
    }

    public void returnItem(String code) {
        boolean found = searchItem(code);
        String item = null;
        boolean rented = false;
        System.out.println("Return request: " + code);

        for (Equipment equipment : this.equipments) {
            if (code.trim().equalsIgnoreCase(equipment.getCode().trim())) {
                item = equipment.getName();
                rented = equipment.getIsRented();
                equipment.setRented(false);
            }
        }

        if (found) {
            if (rented) {
                System.out.println(item + " has been returned.");
            } else {
                System.out.println("Return failed. " + item + " is not rented.");
            }
        } else {
            System.out.println(code + " does not exist.");
        }
        System.out.println();
    }

    private int countRentedEquipments() {
        int count = 0;
        for (Equipment equipment : this.equipments) {
            if (equipment.getIsRented()) {
                count++;
            }
        }

        return count;
    }

    public void returnRentedEquipmentsCount() {
        System.out.println("Currently rented equipment count: "+ countRentedEquipments());
    }
}
