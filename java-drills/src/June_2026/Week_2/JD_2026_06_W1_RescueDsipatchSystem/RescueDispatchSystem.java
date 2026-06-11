package src.June_2026.Week_2.JD_2026_06_W1_RescueDsipatchSystem;

public class RescueDispatchSystem {
    public static void main(String[] args) {
        RescueUnit[] units = {
                new AmbulanceUnit("Ambulance-A", 60, true),
                new AmbulanceUnit("Ambulance-B", 10, true),
                new FireTruckUnit("FireTruck-A", 45, true),
                new FireTruckUnit("FireTruck-B", 35, true),
                new RescueBoatUnit("Boat-A", 40, true),
                new DroneUnit("Drone-A", 30, true)
        };

        String[] emergencies = {"medical", "fire", "flood", "search", "accident", "river"};

        System.out.println("=== Unit Status Before Dispatch ===");
        for (RescueUnit unit : units) {
            unit.showStatus();
        }

        System.out.println("\n=== Emergency Dispatch ===");
        for (String emergencyType : emergencies) {
            System.out.println("Emergency: " +  emergencyType);
            for (RescueUnit unit : units) {
                if (unit.canDispatch(emergencyType)) {
                    unit.dispatch(emergencyType);
                    System.out.println();
                    break;
                }
            }
        }

        System.out.println("=== Special Equipment Check ===");
        for (RescueUnit unit : units) {
            if (unit instanceof FireTruckUnit ) {
                ((FireTruckUnit) unit).extendLadder();
            } else if (unit instanceof DroneUnit ) {
                ((DroneUnit) unit).scanArea();
            }
        }
        System.out.println();

        System.out.println("=== Unit Status After Dispatch ===");
        for (RescueUnit unit : units) {
            unit.showStatus();
        }
    }
}
