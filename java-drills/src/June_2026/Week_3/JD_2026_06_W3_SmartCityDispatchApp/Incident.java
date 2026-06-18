package src.June_2026.Week_3.JD_2026_06_W3_SmartCityDispatchApp;

public class Incident {
    String incidentID;
    String location;
    IncidentType incidentType;
    int severity;
    String description;
    IncidentStatus incidentStatus;
    boolean cancelled;

    public Incident(String incidentID, String location, IncidentType incidentType, int severity, String description) {
        this.incidentID = incidentID;
        this.location = location;
        this.incidentType = incidentType;
        this.severity = severity;
        this.incidentStatus = null;
        this.description = description;
        this.cancelled = false;
    }

    public void showInfo() {
        System.out.println("Incident ID: " + incidentID);
        System.out.println("Location: " + location + " | Incident type: " + incidentType + " | Severity: " + severity + " | Incident Status: " + incidentStatus);
        System.out.println("Description: " + description);
        System.out.println();
    }

    public void showReport() {
        System.out.println(getIncidentID() + " | " + getIncidentType() + " | " + getLocation() + " | " + getIncidentStatus());
    }

    public void cancel() {
        this.cancelled = true;
    }

    public boolean isCancelled() {
        return this.cancelled;
    }

    public String getIncidentID() {
        return incidentID;
    }

    public String getLocation() {
        return location;
    }

    public IncidentType getIncidentType() {
        return incidentType;
    }

    public int getSeverity() {
        return severity;
    }

    public String getDescription() {
        return description;
    }

    public IncidentStatus getIncidentStatus() {
        return incidentStatus;
    }

    public void setIncidentID(String incidentID) {
        this.incidentID = incidentID;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setIncidentType(IncidentType incidentType) {
        this.incidentType = incidentType;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIncidentStatus(IncidentStatus incidentStatus) {
        this.incidentStatus = incidentStatus;
    }
}
