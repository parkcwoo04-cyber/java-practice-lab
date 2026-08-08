package src.August_2026.Week_1.JD_2026_08_W1_CampusEquipmentRental;

public class CameraEquipment extends Equipment {
    private final int maximumRentalDays;

    public CameraEquipment(String equipmentID, String name) throws InvalidRentalException {
        super(equipmentID, name);

        this.maximumRentalDays = 10;
    }

    @Override
    public String printInfo() {
        return "CAMERA | " + getEquipmentID() + " | " +  getName() + " | " + getStatus();
    }

    public int getMaximumRentalDays() {
        return maximumRentalDays;
    }
}
