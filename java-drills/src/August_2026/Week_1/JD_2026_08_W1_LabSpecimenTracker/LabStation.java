package src.August_2026.Week_1.JD_2026_08_W1_LabSpecimenTracker;

import src.August_2026.Week_1.JD_2026_08_W1_CampusEquipmentRental.InvalidRentalException;

import java.util.ArrayList;

public class LabStation {
    private String stationID;
    private String stationName;
    private int capacity;
    private ArrayList<String> specimens;

    public LabStation(String stationID, String stationName, int capacity) throws InvalidSpecimenOperationException {
        if (stationID == null || stationID.isBlank()) {
            throw new InvalidSpecimenOperationException("Error: Station ID cannot be blank.");
        }

        if (stationName == null || stationName.isBlank()) {
            throw new InvalidSpecimenOperationException("Error: Station Name cannot be blank.");
        }

        if (capacity <= 0) {
            throw new InvalidSpecimenOperationException("Error: Capacity should be greater than 0.");
        }

        this.stationID = stationID;
        this.stationName = stationName;
        this.capacity = capacity;
        specimens = new ArrayList<>();
    }
    
    public String printStationInfo() {
        StringBuilder sb = new StringBuilder(getStationID() + " | " +  getStationName() + " | Capacity: " + getCapacity() + " | Assigned Specimens: ");
        if (specimens.isEmpty()) {
            sb.append("None");
        } else {
            for (String specimen : specimens) {
                sb.append(specimen).append(", ");
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

    public int getCapacity() {
        return capacity;
    }

    public ArrayList<String> getSpecimens() {
        return specimens;
    }

    public void assignSpecimen(String specimenID) throws InvalidSpecimenOperationException {
        for (String id : specimens) {
            if (id.equals(specimenID)) {
                throw new InvalidSpecimenOperationException("Error: This specimen is already assigned for this station.");
            }
        }

        if (specimens.size() >= getCapacity()) {
            throw new InvalidSpecimenOperationException("Error: This station is already full.");
        }

        specimens.add(specimenID);
        System.out.println(specimenID + " assigned to " + getStationID() + ".");
    }

    public void archiveSpecimen(String specimenID) throws InvalidSpecimenOperationException {
        boolean found = false;

        for (String id : specimens) {
            if (id.equalsIgnoreCase(specimenID)) {
                specimens.remove(id);
                found = true;
                System.out.println(specimenID + " removed from " + getStationID() + ".");
                break;
            }
        }

        if (!found) {
            throw new InvalidSpecimenOperationException("Error: This specimen does not exist.");
        }
    }
}
