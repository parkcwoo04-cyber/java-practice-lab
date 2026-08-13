package src.August_2026.Week_2.JD_2026_08_W2_DeepSpaceTransmissionControl.JD_2026_08_W1_DeepSpaceTransmissionControl;

import java.util.ArrayList;

public class MissionControl {
    private ArrayList<Transmission>  transmissionList;
    private ArrayList<GroundStation> stationList;
    private ArrayList<Transmission> archivedTransmissionList;

    public MissionControl() {
        transmissionList = new ArrayList();
        stationList = new ArrayList();
        archivedTransmissionList = new ArrayList();
    }

    public void addTransmission(Transmission transmission) throws InvalidTransmissionOperationException{
        for (Transmission t : transmissionList) {
            if (t.getTransmissionID().equalsIgnoreCase(transmission.getTransmissionID())) {
                throw new InvalidTransmissionOperationException("Error: Duplicate transmission ID: " + transmission.getTransmissionID());
            }
        }
        transmissionList.add(transmission);
    }

    public void addGroundStation(GroundStation groundStation) throws InvalidTransmissionOperationException {
        for (GroundStation s : stationList) {
            if (s.getStationID().equalsIgnoreCase(groundStation.getStationID())) {
                throw new InvalidTransmissionOperationException("Error: Duplicate Ground Station ID: " + groundStation.getStationID());
            }
        }

        stationList.add(groundStation);
    }

    public void scheduleTransmission(String transmissionID, String groundStationID) throws InvalidTransmissionOperationException {
        Transmission transmission = foundTransmissionByID(transmissionID);
        GroundStation groundStation = foundGroundStationByID(groundStationID);

        if (transmission == null) {
            throw new InvalidTransmissionOperationException("Error: Missing Transmission ID: " + transmissionID);
        }

        if (groundStation == null) {
            throw new InvalidTransmissionOperationException("Error: Missing Ground Station ID: " + groundStationID);
        }

        groundStation.updateCapacity(transmission.getCapacity());
        groundStation.updateAssignedTransmissions(transmissionID);
        transmission.setScheduled();
        transmission.assignStationID(groundStationID);
    }

    public void transmit(String transmissionID) throws InvalidTransmissionOperationException {
        Transmission transmission = foundTransmissionByID(transmissionID);

        if (transmission == null) {
            throw new InvalidTransmissionOperationException("Error: Missing Transmission ID: " + transmissionID);
        }

        if (!transmission.isScheduled()) {
            throw new InvalidTransmissionOperationException("Error: Unavailable to transmit. Current Status: " + transmission.getStatus());
        }

        transmission.setTransmitting();
    }

    public void completeTransmission(String transmissionID) throws InvalidTransmissionOperationException {
        Transmission transmission = foundTransmissionByID(transmissionID);
        GroundStation groundStation = foundGroundStationByTransmission(transmissionID);

        if (transmission == null) {
            throw new InvalidTransmissionOperationException("Error: Missing Transmission ID: " + transmissionID);
        } else if (groundStation == null) {
            throw new InvalidTransmissionOperationException("Error: Missing Ground Station ID: " + transmissionID);
        }

        if (!transmission.isTransmitting()) {
            throw new InvalidTransmissionOperationException("Error: Unavailable to complete transmission. Current Status: " + transmission.getStatus());
        }

        transmission.setCompleted();
        groundStation.updateCapacity((- transmission.getCapacity()));
        groundStation.completeTransmission(transmissionID);
    }

    public void archiveTransmission(String transmissionID) throws InvalidTransmissionOperationException {
        Transmission transmission = foundTransmissionByID(transmissionID);

        if (transmission == null) {
            throw new InvalidTransmissionOperationException("Error: Missing Transmission ID: " + transmissionID);
        }

        if (!transmission.isCompleted()) {
            throw new InvalidTransmissionOperationException("Error: Unavailable to archive. Current Status: " + transmission.getStatus());
        }

        transmission.setArchived();
    }

    public void batchCleanUp() throws InvalidTransmissionOperationException {
        for (Transmission t : transmissionList) {
            if (t.isArchived()) {
                archivedTransmissionList.add(t);
            }
        }

        if (archivedTransmissionList.isEmpty()) {
            throw new InvalidTransmissionOperationException("Error: No transmissions to archive.");
        }

        for (Transmission t : archivedTransmissionList) {
            transmissionList.remove(t);
        }
        System.out.println("Archived transmissions removed from active operations: " + archivedTransmissionList.size());
    }

    public ArrayList<Transmission> getTransmissionList() {
        return transmissionList;
    }

    public ArrayList<GroundStation> getStationList() {
        return stationList;
    }

    public Transmission foundTransmissionByID (String transmissionID) {
        for (Transmission t : transmissionList) {
            if (t.getTransmissionID().equalsIgnoreCase(transmissionID)) {
                return t;
            }
        }
        return null;
    }

    public GroundStation foundGroundStationByID (String groundStationID) {
        for (GroundStation s : stationList) {
            if (s.getStationID().equalsIgnoreCase(groundStationID)) {
                return s;
            }
        }
        return null;
    }

    public GroundStation foundGroundStationByTransmission(String transmissionID) {
        for (GroundStation s : stationList) {
            for (String tID : s.getAssignedTransmissions()) {
                if (tID.equalsIgnoreCase(transmissionID)) {
                    return s;
                }
            }
        }
        return null;
    }

    public ArrayList<Transmission> getArchivedTransmissionList() {
        return archivedTransmissionList;
    }
}
