package src.August_2026.Week_1.JD_2026_08_W1_CampusEquipmentRental;

public class RentalRecord {
    private final String equipmentID;
    private final String equipmentName;
    private final String studentName;
    private final RentalAction action;
    private final String description;

    public RentalRecord(String equipmentID, String studentName, String equipmentName, RentalAction action, String description) {
        this.equipmentID = equipmentID;
        this.studentName = studentName;
        this.equipmentName = equipmentName;
        this.action = action;
        this.description = description;
    }

    public String getRentalRecord() {
        return getAction() + " | " + getEquipmentID() + " | " + getEquipmentName() + " | " + getStudentName() + " | Description: " + getDescription();
    }

    public String getEquipmentID() {
        return equipmentID;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public String getStudentName() {
        return studentName;
    }

    public RentalAction getAction() {
        return action;
    }

    public String getDescription() {
        return description;
    }
}
