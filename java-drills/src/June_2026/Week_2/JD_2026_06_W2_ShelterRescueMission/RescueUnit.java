package src.June_2026.Week_2.JD_2026_06_W2_ShelterRescueMission;

public class RescueUnit {
    public final String unitID;

    public RescueUnit(String unitID){
        this.unitID = unitID;
    }

    public void showUnitRole() {
        System.out.println("Unit " + unitID + ": General rescue unit");
    }

    public void handleAnimal(Animal animal) {
    }
}
