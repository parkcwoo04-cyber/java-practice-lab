package src.June_2026.Week_2.JD_2026_06_W2_DisasterResponseSystem;

public abstract class EmergencyUnit {
    String unitName;
    String location;
    int readiness;

    public EmergencyUnit(String unitName, String location, int readiness) {
        this.unitName = unitName;
        this.location = location;
        this.readiness = readiness;
    }

    public void showBasicInfo()
    {
        System.out.println("Unit Name: " + unitName);
        System.out.println("Location: " + location);
        System.out.println("Readiness: " + readiness);
        if(readiness >= 80) {
            System.out.println("Status: Ready");
        } else {
            System.out.println("Status: Need Preparation");
        }
    }

    public abstract void respond();

    public String getUnitName() {
        return unitName;
    }

    public String getLocation() {
        return location;
    }

    public int getReadiness() {
        return readiness;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setReadiness(int readiness) {
        this.readiness = readiness;
    }
}
