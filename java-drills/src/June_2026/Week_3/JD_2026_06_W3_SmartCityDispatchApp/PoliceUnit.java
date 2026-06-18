package src.June_2026.Week_3.JD_2026_06_W3_SmartCityDispatchApp;

public class PoliceUnit extends ResponseUnit implements Dispatchable {
    public PoliceUnit(String unitID, String unitName, String baseLocation, boolean availability) {
        super(unitID, unitName, baseLocation, availability);
    }

    @Override
    public void showInfo() {
        System.out.print("Type: Police Unit | ");
        super.showInfo();
    }

    @ Override
    public void assign(Incident incident) {
        System.out.println(incident.getIncidentID() + " assigned to " + this.unitID);
        this.availability = false;
        incident.setIncidentStatus(IncidentStatus.ASSIGNED);
    }

    @ Override
    public void response(Incident incident) {
        System.out.println("PoliceUnit response: securing area at " + incident.getLocation());
        incident.setIncidentStatus(IncidentStatus.IN_PROGRESS);
    }

    @ Override
    public void completeMission(Incident incident) {
        incident.setIncidentStatus(IncidentStatus.RESOLVED);
        this.countCompletedResponse ++;
    }
}
