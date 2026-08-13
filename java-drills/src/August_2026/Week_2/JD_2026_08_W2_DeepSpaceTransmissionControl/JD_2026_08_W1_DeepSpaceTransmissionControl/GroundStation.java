package src.August_2026.Week_2.JD_2026_08_W2_DeepSpaceTransmissionControl.JD_2026_08_W1_DeepSpaceTransmissionControl;

import java.util.ArrayList;

public class GroundStation {
    private String stationID;
    private String stationName;
    private int maxCapacity;
    private int currentCapacity;
    private ArrayList<String> assignedTransmissions;

    public GroundStation(String stationID, String stationName, int maxCapacity) throws InvalidTransmissionOperationException {
        if (stationID == null || stationID.isBlank()) {
            throw new InvalidTransmissionOperationException("Error: Ground Station ID cannot be blank.");
        }

        if (stationName == null || stationName.isBlank()) {
            throw new InvalidTransmissionOperationException("Error: Ground Station Name cannot be blank.");
        }

        if (maxCapacity < 0) {
            throw new InvalidTransmissionOperationException("Error: Ground Station Capacity cannot be negative.");
        }

        this.stationID = stationID;
        this.stationName = stationName;
        this.maxCapacity = maxCapacity;
        this.currentCapacity = 0;
        this.assignedTransmissions = new ArrayList<>();
    }

    public String printStationInfo() {
        StringBuilder sb = new StringBuilder(stationID + " | " + stationName + " | Maximum Capacity: " + maxCapacity + " | Remaining Capacity: " + (maxCapacity - currentCapacity) + " | Assigned Transmissions: ");
        if (assignedTransmissions.isEmpty()) {
            sb.append(" None");
        } else {
            for (String transmission : assignedTransmissions) {
                sb.append(transmission).append(", ");
            }
            sb.delete(sb.length() - 2, sb.length());
        }
        return sb.toString();
    }

    public String getStationID() {
        return stationID;
    }

    public String getStationName() {
        return stationName;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public void updateCapacity(int newCapacity) throws InvalidTransmissionOperationException {
        if (newCapacity > (maxCapacity - currentCapacity)) {
            throw new InvalidTransmissionOperationException("Error: Ground Station Capacity out of bounds.");
        }

        this.currentCapacity += newCapacity;
    }

    public void updateAssignedTransmissions(String transmissionID) throws InvalidTransmissionOperationException {
        if (transmissionID == null || transmissionID.isBlank()) {
            throw new InvalidTransmissionOperationException("Error: Ground Station Transmission ID cannot be blank.");
        }

        for (String transmission : assignedTransmissions) {
            if (transmissionID.equalsIgnoreCase(transmission)) {
                throw new InvalidTransmissionOperationException("Error: Ground Station Transmission ID already exists.");
            }
        }

        assignedTransmissions.add(transmissionID);
    }

    public void completeTransmission(String transmissionID) {
        assignedTransmissions.remove(transmissionID);
    }

    public ArrayList<String> getAssignedTransmissions() {
        return assignedTransmissions;
    }
}
