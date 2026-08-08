package src.August_2026.Week_1.JD_2026_08_W1_CampusEquipmentRental;

public class LaptopEquipment extends Equipment {
    private String description;

    public LaptopEquipment(String equipmentID, String name) throws InvalidRentalException {
        super(equipmentID, name);

        this.description = null;
    }

    @Override
    public String printInfo() {
        return "LAPTOP | " + getEquipmentID() + " | " +  getName() + " | " + getStatus();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
