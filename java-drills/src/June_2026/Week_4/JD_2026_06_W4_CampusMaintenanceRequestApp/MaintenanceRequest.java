package src.June_2026.Week_4.JD_2026_06_W4_CampusMaintenanceRequestApp;

public class MaintenanceRequest {
    private String requestID;
    private String requestorName;
    private String location;
    private RequestType requestType;
    private int severityLevel;
    private boolean assigned;

    public MaintenanceRequest(String requestID, String requestorName, String location, RequestType requestType, int severityLevel) {
        this.requestID = requestID;
        this.requestorName = requestorName;
        this.location = location;
        this.requestType = requestType;
        this.severityLevel = severityLevel;
        this.assigned = false;
    }

    public void printRequest(){
        if (this.isUrgent()) {
            System.out.print("[URGENT] ");
        }
        System.out.printf("Request ID: %s | Requestor Name: %s | Location: %s | Request Type: %s | Severity Level: %d\n", requestID, requestorName, location, requestType, severityLevel);
    }

    public boolean isUrgent() {
        return severityLevel >= 4;
    }

    public boolean isAssigned() {
        return assigned;
    }

    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }

    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    public String getRequestorName() {
        return requestorName;
    }

    public void setRequestorName(String requestorName) {
        this.requestorName = requestorName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public int getSeverityLevel() {
        return severityLevel;
    }

    public void setSeverityLevel(int severityLevel) {
        this.severityLevel = severityLevel;
    }
}
