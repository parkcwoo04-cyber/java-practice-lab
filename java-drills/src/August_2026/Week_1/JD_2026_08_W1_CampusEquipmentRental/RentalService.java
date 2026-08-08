package src.August_2026.Week_1.JD_2026_08_W1_CampusEquipmentRental;

import src.August_2026.Week_1.JD_2026_08_W1_CourseRegistrationSystem.InvalidRegistrationException;

import java.io.*;
import java.util.ArrayList;

public class RentalService implements Rentable{
    private ArrayList<Equipment> equipmentList;
    private ArrayList<RentalRecord> history;
    private final String fileName = "/Users/parkcwoo04/Desktop/GitHub/java-practice-lab/java-drills/src/August_2026/Week_1/JD_2026_08_W1_CampusEquipmentRental/History.txt";

    public RentalService() {
        equipmentList = new ArrayList<>();
        history = new ArrayList<>();
    }

    @Override
    public void addEquipment(Equipment equipment) throws InvalidRentalException {
        for (Equipment equipment1 : equipmentList) {
            if (equipment1.getEquipmentID().equalsIgnoreCase(equipment.getEquipmentID())) {
                throw new InvalidRentalException("Error: Same equipment ID already exist");
            }
        }
        equipmentList.add(equipment);
    }

    @Override
    public void removeEquipment(Equipment equipment) {
        equipmentList.remove(equipment);
    }

    @Override
    public Equipment foundEquipmentByID(String equipmentID) {
        for (Equipment equipment : equipmentList) {
            if (equipment.getEquipmentID().equalsIgnoreCase(equipmentID)) {
                return equipment;
            }
        }
        return null;
    }

    @Override
    public void rentCamera(String equipmentID, String studentName, int days) throws InvalidRentalException {
        CameraEquipment equipment = (CameraEquipment) foundEquipmentByID(equipmentID);

        if (equipment == null) {
            throw new InvalidRentalException("Error: " + equipmentID + " does not exist.");
        }

        if (equipment.isRented()) {
            throw new InvalidRentalException("Error: " + equipmentID + " is already rented.");
        }

        if (studentName == null || studentName.isBlank()) {
            throw new InvalidRentalException("Error: " + studentName + " cannot be blank.");
        }

        if (days > equipment.getMaximumRentalDays()) {
            throw new InvalidRentalException("Error: " + equipmentID + " has reached the maximum rental days.");
        }

        System.out.println("Renting " + equipment.getEquipmentID() + " to " + studentName);
        equipment.setRented();
        equipment.setStudentName(studentName);

        String description = days + " days";

        RentalRecord record = new RentalRecord(equipmentID, studentName, equipment.getName(), RentalAction.RENT, description);
        history.add(record);
    }

    @Override
    public void rentLaptop(String equipmentID, String studentName, String descriptions) throws InvalidRentalException {
        LaptopEquipment laptop = (LaptopEquipment) foundEquipmentByID(equipmentID);

        if (descriptions == null || descriptions.isBlank()) {
            throw new InvalidRentalException("Error: " + descriptions + " cannot be blank.");
        } else if (descriptions.length() < 5) {
            throw new InvalidRentalException("Error: " + descriptions + " must have at least 5 characters.");
        }

        if (studentName == null || studentName.isBlank()) {
            throw new InvalidRentalException("Error: " + studentName + " cannot be blank.");
        }

        if (laptop == null) {
            throw new InvalidRentalException("Error: " + equipmentID + " does not exist.");
        }

        if (laptop.isRented()) {
            throw new InvalidRentalException("Error: " + equipmentID + " is already rented.");
        }

        System.out.println("Renting " + laptop.getEquipmentID() + " to " + studentName);
        laptop.setRented();
        laptop.setStudentName(studentName);
        laptop.setDescription(descriptions);

        RentalRecord record = new RentalRecord(equipmentID, studentName, laptop.getName(), RentalAction.RENT, descriptions);
        history.add(record);
    }

    @Override
    public boolean validateRental(Equipment equipment) {
        return equipment instanceof CameraEquipment;
    }

    @Override
    public ArrayList<Equipment> getRentedEquipments() {
        ArrayList<Equipment> rentedEquipmentList = new ArrayList<>();
        for (Equipment equipment : equipmentList) {
            if (equipment.isRented()) {
                rentedEquipmentList.add(equipment);
            }
        }
        return rentedEquipmentList;
    }

    @Override
    public void returnEquipment(Equipment equipment) {
        equipment.setAvailable();

        RentalRecord record = new RentalRecord(equipment.getEquipmentID(), equipment.getStudentName(), equipment.getName(), RentalAction.RETURN, "Return");
        history.add(record);
    }

    @Override
    public void saveFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (RentalRecord record : history) {
                bw.write(recordToString(record));
                bw.newLine();
            }
            System.out.println("History Saved Successfully.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private String recordToString(RentalRecord record) {
        return record.getAction() + "," + record.getEquipmentID() + "," + record.getStudentName() + "," + record.getEquipmentName() + "," + record.getDescription();
    }

    @Override
    public void loadFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                RentalRecord record = lineToRecord(line);
                history.add(record);
            }
            System.out.println("History Loaded Successfully");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private RentalRecord lineToRecord(String line) {
        String[] fields = line.split(",");
        RentalAction action = RentalAction.valueOf(fields[0]);
        String equipmentID = fields[1];
        String studentName = fields[2];
        String equipmentName = fields[3];
        String description = fields[4];

        return new RentalRecord(equipmentID, studentName, equipmentName, action, description);
    }

    public ArrayList<Equipment> getEquipmentList() {
        return equipmentList;
    }

    public ArrayList<Equipment> getAvailableEquipmentList() {
        ArrayList<Equipment> availableEquipmentList = new ArrayList<>();
        for (Equipment equipment : equipmentList) {
            if (!equipment.isRented()) {
                availableEquipmentList.add(equipment);
            }
        }
        return availableEquipmentList;
    }

    public ArrayList<Equipment> getRentedEquipmentList() {
        ArrayList<Equipment> rentedEquipmentList = new ArrayList<>();
        for (Equipment equipment : equipmentList) {
            if (equipment.isRented()) {
                rentedEquipmentList.add(equipment);
            }
        }
        return rentedEquipmentList;
    }

    public ArrayList<RentalRecord> getHistory() {
        return history;
    }
}
