package src.June_2026.Week_2.JD_2026_06_W2_RescueDsipatchSystem;

public class RescueBoatUnit extends  RescueUnit {
    public RescueBoatUnit(String unitName, int fuel, boolean available) {
        super(unitName, fuel, available);
    }

    @Override
    public boolean canDispatch(String emergencyType) {
        if(emergencyType.equalsIgnoreCase("flood") || emergencyType.equalsIgnoreCase("river")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void dispatch(String emergencyType) {
        if(!isAvailable()) {
            System.out.println(this.unitName + " is not available");
        } else if (!hasEnoughFuel(25)){
            System.out.println(this.unitName + " cannot dispatch. Fuel is too low.");
        } else {
            if(emergencyType.equalsIgnoreCase("flood")) {
                System.out.println(this.unitName + " is navigating to the flooded area.");
                System.out.println("Fuel used: 25");
            } else if(emergencyType.equalsIgnoreCase("river")) {
                System.out.println(this.unitName + " is navigating to the river area.");
                System.out.println("Fuel used: 25");
            }
            this.fuel -= 25;
            this.completedMissions ++;
        }
    }
}
