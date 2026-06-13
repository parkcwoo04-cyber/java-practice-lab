package src.June_2026.Week_2.JD_2026_06_W2_DisasterResponseSystem;

public class RescueUnit extends EmergencyUnit {

    public RescueUnit(String unitName, String location, int readiness) {
        super(unitName, location, readiness);
    }

    @Override
    public void showBasicInfo() {
        super.showBasicInfo();
        System.out.println("Searching trapped civilians and clearing blocked paths.");
    }

    @Override
    public void respond() {
        System.out.println("Response: Searching trapped civilians and clearing blocked paths..");
    }
}
