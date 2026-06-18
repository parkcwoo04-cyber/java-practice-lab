package src.June_2026.Week_3.JD_2026_06_W3_SmartCityDispatchApp;

public class ResponseUnit {
    public String unitID;
    public String unitName;
    public String baseLocation;
    public boolean availability;
    public int countCompletedResponse;

    public ResponseUnit(String unitID, String unitName, String baseLocation, boolean availability) {
        this.unitID = unitID;
        this.unitName = unitName;
        this.baseLocation = baseLocation;
        this.availability = availability;
        this.countCompletedResponse = 0;
    }

    public void showInfo() {
        System.out.println("Unit ID: " + unitID + " | Base Location: " + baseLocation + " | Availability: " + availability);
    }

    public String  getUnitID() {
        return unitID;
    }
    public String getUnitName() {
        return unitName;
    }

    public String getBaseLocation() {
        return baseLocation;
    }

    public boolean getAvailability() {
        return availability;
    }

    public int getCountCompletedResponse() {
        return countCompletedResponse;
    }

    public void setCountCompletedResponse(int countCompletedResponse) {
        this.countCompletedResponse = countCompletedResponse;
    }

    public void setUnitID(String unitID) {
        this.unitID = unitID;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public void setBaseLocation(String baseLocation) {
        this.baseLocation = baseLocation;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
