package src.June_2026.Week_2.JD_2026_06_W2_DisasterResponseSystem;

public class MedicalUnit extends EmergencyUnit{

    public MedicalUnit(String unitName, String location, int readiness) {
        super(unitName, location, readiness);
    }

    @Override
    public void showBasicInfo() {
        super.showBasicInfo();
        System.out.println("Treating injured civilians and preparing medical supplies.");
    }

    @Override
    public void respond() {
        System.out.println("Response: Treating injured civilians and preparing medical supplies..");
    }
}
