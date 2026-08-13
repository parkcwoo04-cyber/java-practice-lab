package src.August_2026.Week_2.JD_2026_08_W2_DeepSpaceTransmissionControl;

public abstract class Transmission {
    private String transmissionID;
    private String probeName;
    private TransmissionStatus status;
    private String assignedStationID;

    public Transmission(String transmissionID, String probeName) throws InvalidTransmissionOperationException {
        if (transmissionID == null || transmissionID.isBlank()) {
            throw new InvalidTransmissionOperationException("Error: Transmission ID cannot be blank.");
        }

        if (probeName == null || probeName.isBlank()) {
            throw new InvalidTransmissionOperationException("Error: Probe Name cannot be blank.");
        }

        this.transmissionID = transmissionID;
        this.probeName = probeName;
        this.status = TransmissionStatus.CREATED;
        this.assignedStationID = "None";
    }

    public String printTransmissionInfo() {
        return getTransmissionID() + " | " + getProbeName() + " | " + getStatus() + " | Assigned Station ID: " + getAssignedStationID();
    }

    public String getTransmissionID() {
        return transmissionID;
    }

    public String getProbeName() {
        return probeName;
    }

    public TransmissionStatus getStatus() {
        return status;
    }

    public String getAssignedStationID() {
        return assignedStationID;
    }

    public void setAssignedStationID(String assignedStationID) {
        this.assignedStationID = assignedStationID;
    }

    public boolean isCreated() {
        return status == TransmissionStatus.CREATED;
    }

    public boolean isScheduled() {
        return status == TransmissionStatus.SCHEDULED;
    }

    public boolean isTransmitting() {
        return status == TransmissionStatus.TRANSMITTING;
    }

    public boolean isCompleted() {
        return status == TransmissionStatus.COMPLETED;
    }

    public boolean isArchived() {
        return status == TransmissionStatus.ARCHIVED;
    }

    public void setScheduled() throws InvalidTransmissionOperationException {
        if (status != TransmissionStatus.CREATED) {
            throw new InvalidTransmissionOperationException("Error: " + getTransmissionID() + " is not created.");
        }

        this.status = TransmissionStatus.SCHEDULED;
    }

    public void setTransmitting() throws InvalidTransmissionOperationException {
        if (status != TransmissionStatus.SCHEDULED) {
            throw new InvalidTransmissionOperationException("Error: " + getTransmissionID() + " is not scheduled.");
        }

        this.status = TransmissionStatus.TRANSMITTING;
    }

    public void setCompleted() throws InvalidTransmissionOperationException {
        if (status != TransmissionStatus.TRANSMITTING) {
            throw new InvalidTransmissionOperationException("Error: " + getTransmissionID() + " is not transmitting.");
        }
        this.status = TransmissionStatus.COMPLETED;
    }

    public void setArchived() throws InvalidTransmissionOperationException {
        if (!(status == TransmissionStatus.COMPLETED)) {
            throw new InvalidTransmissionOperationException("Error: " + getTransmissionID() + " is not completed.");
        }

        this.status = TransmissionStatus.ARCHIVED;
    }

    public void assignStationID(String assignedStationID) throws InvalidTransmissionOperationException {
        if (assignedStationID == null || assignedStationID.isBlank()) {
            throw new InvalidTransmissionOperationException("Error: Station ID cannot be blank.");
        }

        this.assignedStationID = assignedStationID;
    }

    public abstract int getCapacity();
}
