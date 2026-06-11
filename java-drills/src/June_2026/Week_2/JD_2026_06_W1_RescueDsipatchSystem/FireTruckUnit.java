package src.June_2026.Week_2.JD_2026_06_W1_RescueDsipatchSystem;

public class FireTruckUnit extends RescueUnit{
    public FireTruckUnit(String unitName, int fuel, boolean available) {
        super(unitName, fuel, available);
    }

    @Override
    public boolean canDispatch(String emergencyType) {
        if(emergencyType.equalsIgnoreCase("fire") || emergencyType.equalsIgnoreCase("accident")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void dispatch(String emergencyType) {
        if(!isAvailable()) {
            System.out.println(this.unitName + " is not available");
        } else if (!hasEnoughFuel(30)){
            System.out.println(this.unitName + " cannot dispatch. Fuel is too low.");
        } else {
            if(emergencyType.equalsIgnoreCase("fire")) {
                System.out.println(this.unitName + " is moving to the fire scene.");
                System.out.println("Fuel used: 30");
            } else if(emergencyType.equalsIgnoreCase("accident")) {
                System.out.println(this.unitName + " is moving to the accident scene.");
                System.out.println("Fuel used: 30");
            }
            this.fuel -= 30;
            this.completedMissions ++;
        }
    }

    public void extendLadder() {
        System.out.println(this.unitName + " is extending ladder.");
    }
}
