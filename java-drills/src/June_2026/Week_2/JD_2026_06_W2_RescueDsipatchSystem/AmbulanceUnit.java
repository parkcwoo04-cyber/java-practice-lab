package src.June_2026.Week_2.JD_2026_06_W2_RescueDsipatchSystem;

public class AmbulanceUnit extends RescueUnit {
    public AmbulanceUnit(String unitName, int fuel, boolean available) {
        super(unitName, fuel, available);
    }

    @Override
    public boolean canDispatch(String emergencyType) {
        if(emergencyType.equalsIgnoreCase("medical") || emergencyType.equalsIgnoreCase("accident")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void dispatch(String emergencyType) {
        super.dispatch(emergencyType);
        if (!isAvailable()) {
            System.out.println(this.unitName + " is not available");
        } else {
            if (emergencyType.equalsIgnoreCase("medical")) {
                if(!hasEnoughFuel(20)) {
                    System.out.println(this.unitName + " cannot dispatch. Fuel is too low.");
                } else {
                    System.out.println(this.unitName + " is responding to a medical emergency.");
                    System.out.println("Fuel used: 20");
                    fuel -= 20;
                    completedMissions++;
                }
            } else if (emergencyType.equalsIgnoreCase("accident")) {
                if(!hasEnoughFuel(20)) {
                    System.out.println(this.unitName + " cannot dispatch. Fuel is too low.");
                } else {
                    System.out.println(this.unitName + " is moving to the accident scene.");
                    System.out.println("Fuel used: 20");
                    fuel -= 20;
                    completedMissions++;
                }
            }
        }
    }
}
