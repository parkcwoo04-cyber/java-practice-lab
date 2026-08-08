package src.August_2026.Week_1.JD_2026_08_W1_CampusEquipmentRental;

public abstract class Equipment {
    private String equipmentID;
    private String name;
    private RentalStatus status;
    private String studentName;

    public Equipment(String equipmentID, String name) throws InvalidRentalException {
        if (equipmentID == null || equipmentID.isBlank()) {
            throw new InvalidRentalException("Error: Equipment ID cannot be blank.");
        } else if (name == null || name.isBlank()) {
            throw new InvalidRentalException("Error: Name cannot be blank.");
        }

        this.equipmentID = equipmentID;
        this.name = name;
        this.status = RentalStatus.AVAILABLE;
        this.studentName = "";
    }

    public abstract String printInfo();

    public void setRented() {
        this.status = RentalStatus.RENTED;
    }

    public void setAvailable() {
        this.status = RentalStatus.AVAILABLE;
    }

    public String getEquipmentID() {
        return equipmentID;
    }

    public String getName() {
        return name;
    }

    public RentalStatus getStatus() {
        return status;
    }

    public boolean isRented() {
        return status == RentalStatus.RENTED;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentName() {
        return studentName;
    }
}
