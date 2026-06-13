package src.June_2026.Week_2.JD_2026_06_W2_DisasterResponseSystem;

public class DisasterResponseMain {
    public static void main(String[] args) {

        EmergencyUnit[] units = {
                new FireUnit("Alpha Fire", "North District", 90),
                new MedicalUnit("Central Medic", "Central Station", 75),
                new RescueUnit("River Rescue", "East River", 85),
                new FireUnit("Metro Fire", "Metropolitan Station", 55)
        };

        System.out.println("=== Disaster Response Unit ===");
        System.out.println();

        for (EmergencyUnit unit : units) {
            unit.showBasicInfo();
            System.out.println();
        }

        countReadyUnits(units);

    }

    public static void countReadyUnits(EmergencyUnit[] units) {
        int count = 0;
        for (EmergencyUnit unit : units) {
            if(unit.getReadiness() >= 80 ) {
                count++;
            }
        }

        System.out.println("Ready units: " + count);
    }
}
