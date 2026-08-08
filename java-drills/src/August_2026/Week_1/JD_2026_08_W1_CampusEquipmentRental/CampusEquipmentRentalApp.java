package src.August_2026.Week_1.JD_2026_08_W1_CampusEquipmentRental;

import java.util.ArrayList;
import java.util.Scanner;

public class CampusEquipmentRentalApp {
    public static void main(String[] args) {
        RentalService service = new RentalService();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Welcome Campus Rental App ===");
        System.out.println("Loading past rental history...");
        service.loadFile();

        createCamera(service, "C101", "Mirrorless Camera");
        createCamera(service, "C102", "Video Camera");
        createCamera(service, "C101", "Digital Camera");
        createLaptop(service, "D101", "Editing Laptop");
        createLaptop(service, "D102", "Coding Laptop");

        boolean running = true;

        while (running) {
            switch (mainMenu(scanner)) {
                case 1:
                    printAllEquipment(service);
                    break;

                case 2:
                    printAvailableEquipment(service);
                    break;

                case 3:
                    try {
                        Equipment rentalEquipment = selectFromAvailableEquipment(service, scanner);
                        rentEquipment(service, scanner, rentalEquipment);
                    } catch (InvalidRentalException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 4:
                    try {
                        Equipment equipment = selectFromRentedEquipment(service, scanner);
                        returnEquipment(service, scanner, equipment);
                    } catch (InvalidRentalException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 5:
                    printRentalHistory(service);
                    break;

                case 6:
                    System.out.println("Saving Rental History...");
                    service.saveFile();
                    System.out.println("Program terminated.");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid input. Try again.");
                    break;
            }


        }

    }

    private static int mainMenu(Scanner scanner) {
        System.out.println("=== Campus Rental Main Menu ===");
        System.out.println("1. View all equipment");
        System.out.println("2. View available equipment");
        System.out.println("3. Rent equipment");
        System.out.println("4. Return equipment");
        System.out.println("5. View rent history");
        System.out.println("6. Exit");

        int choice = scanner.nextInt();
        return choice;
    }

    private static void printAllEquipment(RentalService service) {
        ArrayList<Equipment> equipments = service.getEquipmentList();
        for (Equipment equipment : equipments) {
            System.out.println(equipment.printInfo());
        }
        System.out.println();
    }

    private static void createCamera(RentalService service, String equipmentID, String equipmentName) {
        try {
            service.addEquipment(new CameraEquipment(equipmentID, equipmentName));
        } catch (InvalidRentalException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private static void createLaptop(RentalService service, String equipmentID, String equipmentName) {
        try {
            service.addEquipment(new LaptopEquipment(equipmentID, equipmentName));
        } catch (InvalidRentalException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private static void printAvailableEquipment(RentalService service) {
        ArrayList<Equipment> availableEquipments = service.getAvailableEquipmentList();

        if (availableEquipments.isEmpty()) {
            System.out.println("There are no available equipments");
            return;
        }

        for (Equipment equipment : availableEquipments) {
            System.out.println(equipment.printInfo());
        }
        System.out.println();
    }

    private static Equipment selectFromAvailableEquipment(RentalService service, Scanner scanner) throws InvalidRentalException {
        ArrayList<Equipment> availableEquipments = service.getAvailableEquipmentList();
        int index = 1;
        for (Equipment equipment : availableEquipments) {
            System.out.println(index + ". " + equipment.printInfo());
            index++;
        }
        int choice = scanner.nextInt();

        if (choice < 1 || choice > availableEquipments.size()) {
            throw new InvalidRentalException("Invalid Input");
        }

        return availableEquipments.get(choice - 1);
    }

    private static void rentEquipment(RentalService service, Scanner scanner, Equipment equipment) {
        try {
            System.out.println("Enter Student Name:");
            scanner.nextLine();
            String studentName = scanner.nextLine();

            if (service.validateRental(equipment)) {
                System.out.println("Enter Rental Days:");
                int days = scanner.nextInt();
                service.rentCamera(equipment.getEquipmentID(), studentName, days);
            } else {
                System.out.println("Enter Purpose");
                String purpose = scanner.nextLine();
                service.rentLaptop(equipment.getEquipmentID(), studentName, purpose);
            }
            System.out.println();
        } catch (InvalidRentalException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private static Equipment selectFromRentedEquipment(RentalService service, Scanner scanner) throws InvalidRentalException {
        ArrayList<Equipment> rentedEquipments = service.getRentedEquipmentList();
        int index = 1;
        for (Equipment equipment : rentedEquipments) {
            System.out.println(index + ". " + equipment.printInfo());
            index++;
        }
        int choice = scanner.nextInt();

        if (choice < 1 || choice > rentedEquipments.size()) {
            throw new InvalidRentalException("Invalid Input");
        }

        return rentedEquipments.get(choice - 1);
    }

    private static void returnEquipment(RentalService service, Scanner scanner, Equipment equipment) {
        service.returnEquipment(equipment);
        System.out.println("Return Successful: " + equipment.getEquipmentID());
    }

    private static void printRentalHistory(RentalService service) {
        System.out.println("=== Rental History ===");
        for (RentalRecord record : service.getHistory()) {
            System.out.println(record.getRentalRecord());
        }
    }
}
