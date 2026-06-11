package src.June_2026.Week_2.JD_2026_06_W1_RescueDsipatchSystem;

public class DroneUnit extends RescueUnit {
    public DroneUnit(String unitName, int fuel, boolean available) {
        super(unitName, fuel, available);
    }

    @Override
    public boolean canDispatch(String emergencyType) {
        if(emergencyType.equalsIgnoreCase("search") || emergencyType.equalsIgnoreCase("fire")
                || emergencyType.equalsIgnoreCase("flood")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void dispatch(String emergencyType) {
        if(!isAvailable()) {
            System.out.println(this.unitName + " is not available");
        } else if (!hasEnoughFuel(15)){
            System.out.println(this.unitName + " cannot dispatch. Fuel is too low.");
        } else {
            if(emergencyType.equalsIgnoreCase("fire")) {
                System.out.println(this.unitName + " is searching to the fire scene.");
                System.out.println("Fuel used: 15");
            } else if(emergencyType.equalsIgnoreCase("search")) {
                System.out.println(this.unitName + " is searching the area.");
                System.out.println("Fuel used: 15");
            } else if(emergencyType.equalsIgnoreCase("flood")) {
                System.out.println(this.unitName + " is searching the flood scene.");
            }
            this.fuel -= 15;
            this.completedMissions ++;
        }
    }

    public void scanArea() {
        System.out.println(this.unitName + " is scanning the area from above.");
    }
}
