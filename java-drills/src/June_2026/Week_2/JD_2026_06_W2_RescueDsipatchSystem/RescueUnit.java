package src.June_2026.Week_2.JD_2026_06_W2_RescueDsipatchSystem;

public class RescueUnit {
    protected String unitName;
    protected int fuel;
    protected boolean available;
    protected int completedMissions;

    public RescueUnit(String unitName, int fuel, boolean available) {
        this.unitName = unitName;
        this.fuel = fuel;
        this.available = available;
        this.completedMissions = 0;
    }

    public void showStatus() {
        System.out.println(this.unitName + " | Fuel: " + this.fuel + " | Available: " + this.available +
                " | Missions: " + this.completedMissions);
    }

    public void refuel(int amount) {
        if (amount > 0) {
            this.fuel += amount;
        }
    }

    public boolean canDispatch(String emergencyType) {
        return false;
    }

    public void dispatch(String emergencyType) {

    }

    protected boolean hasEnoughFuel(int requiredFuel) {
        return this.fuel >= requiredFuel;
    }

    public void completeMission(int fuelUsed) {
        this.fuel -= fuelUsed;
        this.completedMissions++;
    }

    public String getUnitName() {
        return unitName;
    }
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getCompletedMissions() {
        return completedMissions;
    }

    public void setCompletedMissions(int completedMissions) {
        this.completedMissions = completedMissions;
    }


}

