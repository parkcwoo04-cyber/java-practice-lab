package src.June_2026.Week_2.JD_2026_06_W2_DisasterResponseSystem;

public class FireUnit extends EmergencyUnit {

    public FireUnit(String unitName, String location, int readiness) {
        super(unitName, location, readiness);
    }

    @Override
    public void showBasicInfo() {
        super.showBasicInfo();
    }

    @Override
    public void respond() {
        System.out.println("Response: Extinguishing fire and checking smoke damage.");
    }
}
