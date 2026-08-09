package src.August_2026.Week_1.JD_2026_08_W1_LabSpecimenTracker;

import java.util.ArrayList;

public class SpecimenRegistry {
    private ArrayList<Specimen> specimenList;
    private ArrayList<LabStation> labStationList;

    public SpecimenRegistry() {
        this.specimenList = new ArrayList<>();
        this.labStationList = new ArrayList<>();
    }

    public void addSpecimen(Specimen specimen) throws InvalidSpecimenOperationException {
        for (Specimen s : specimenList) {
            if (s.getSpecimenID().equals(specimen.getSpecimenID())) {
                throw new InvalidSpecimenOperationException("Error: This specimen is already registered.");
            }
        }

        specimenList.add(specimen);
        System.out.println(specimen.getSpecimenID() + " registered.");
    }

    public void addLabStation(LabStation labStation) throws InvalidSpecimenOperationException {
        for (LabStation s : labStationList) {
            if (s.getStationID().equals(labStation.getStationID())) {
                throw new InvalidSpecimenOperationException("Error: This lab station is already registered.");
            }
        }

        labStationList.add(labStation);
        System.out.println(labStation.getStationID() + " registered.");
    }

    private Specimen foundSpecimenByID(String specimenID) {
        for (Specimen s : specimenList) {
            if (s.getSpecimenID().equals(specimenID)) {
                return s;
            }
        }

        return null;
    }

    private LabStation foundLabStationByID(String labStationID) {
        for (LabStation s : labStationList) {
            if (s.getStationID().equals(labStationID)) {
                return s;
            }
        }

        return null;
    }

    private LabStation foundLabStationBySpecimenID(String specimenID) {
        for (LabStation s : labStationList) {
            for (String ID : s.getSpecimens()) {
                if (ID.equals(specimenID)) {
                    return s;
                }
            }
        }

        return null;
    }

    public void assignSpecimen(String specimenID, String labStationID) throws InvalidSpecimenOperationException {
        Specimen specimen = foundSpecimenByID(specimenID);
        LabStation labStation = foundLabStationByID(labStationID);

        if (specimen == null) {
            throw new InvalidSpecimenOperationException("Error: This specimen ID does not exist.");
        } else if (labStation == null) {
            throw new InvalidSpecimenOperationException("Error: This lab station does not exist.");
        }

        if (!specimen.isRegistered()) {
            throw new InvalidSpecimenOperationException("Error: This specimen is not registered.");
        }

        labStation.assignSpecimen(specimenID);
        specimen.setAssigned();
        specimen.setStation(labStationID);
    }

    public void startAnalysis(String specimenID) throws InvalidSpecimenOperationException {
        Specimen specimen = foundSpecimenByID(specimenID);

        if (specimen == null) {
            throw new InvalidSpecimenOperationException("Error: This specimen ID does not exist.");
        }

        if (!specimen.isAssigned()) {
            throw new InvalidSpecimenOperationException("Error: This specimen is not assigned.");
        }

        specimen.setInAnalysis();
        System.out.println(specimenID + " analysis started.");
    }

    public void completeAnalysis(String specimenID) throws InvalidSpecimenOperationException {
        Specimen specimen = foundSpecimenByID(specimenID);

        if (specimen == null) {
            throw new InvalidSpecimenOperationException("Error: This specimen ID does not exist.");
        }

        if (!specimen.isInAnalysis()) {
            throw new InvalidSpecimenOperationException("Error: This specimen is not in analysis.");
        }

        specimen.setAnalyzed();
        System.out.println(specimenID + " analysis completed.");
    }

    public void archiveAnalysis(String specimenID) throws InvalidSpecimenOperationException {
        Specimen specimen = foundSpecimenByID(specimenID);
        LabStation labStation = foundLabStationBySpecimenID(specimenID);

        if (specimen == null) {
            throw new InvalidSpecimenOperationException("Error: This specimen ID does not exist.");
        } else if (labStation == null) {
            throw new InvalidSpecimenOperationException("Error: This lab station does not exist.");
        }

        if (!specimen.isAnalyzed()) {
            throw new InvalidSpecimenOperationException("Error: This specimen is not in analysis.");
        }

        specimen.setArchived();
        labStation.archiveSpecimen(specimenID);
        System.out.println(specimenID + " archived.");
    }

    public ArrayList<Specimen> getSpecimenList() {
        return specimenList;
    }

    public ArrayList<LabStation> getLabStationList() {
        return labStationList;
    }
}
