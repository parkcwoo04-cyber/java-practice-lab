package src.August_2026.Week_1.JD_2026_08_W1_CampusEquipmentRental;

import java.util.ArrayList;

public interface Rentable {
    void rentCamera(String equipmentID, String studentName, int days) throws InvalidRentalException;
    void rentLaptop (String equipmentID, String studentName, String descriptions) throws InvalidRentalException;
    void addEquipment(Equipment equipment) throws InvalidRentalException;
    void removeEquipment(Equipment equipment);
    Equipment foundEquipmentByID(String equipmentID);
    boolean validateRental(Equipment equipment);
    void returnEquipment(Equipment equipment);
    ArrayList<Equipment> getRentedEquipments();
    void saveFile();
    void loadFile();
}
