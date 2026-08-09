package src.August_2026.Week_1.JD_2026_08_W1_LabSpecimenTracker;

public class Specimen {
    private String specimenID;
    private String specimenName;
    private SpecimenStatus status;
    private String station;

    public Specimen(String specimenID, String specimenName) throws InvalidSpecimenOperationException {
        if (specimenID == null || specimenID.isBlank()) {
            throw new InvalidSpecimenOperationException("Error: Specimen ID cannot be blank.");
        }

        if (specimenName == null || specimenName.isBlank()) {
            throw new InvalidSpecimenOperationException("Error: Specimen Name cannot be blank.");
        }

        this.specimenID = specimenID;
        this.specimenName = specimenName;
        this.status = SpecimenStatus.REGISTERED;
        this.station = "None";
    }

    public String printSpecimenInfo() {
        return getSpecimenID() + " | " + getSpecimenName() + " | " + getStatus() + " | Station: " + getStation();
    }

    public String getSpecimenID() {
        return specimenID;
    }

    public String getSpecimenName() {
        return specimenName;
    }

    public SpecimenStatus getStatus() {
        return status;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public void setAssigned() {
        this.status = SpecimenStatus.ASSIGNED;
    }

    public void setInAnalysis() {
        this.status = SpecimenStatus.IN_ANALYSIS;
    }

    public void setAnalyzed() {
        this.status = SpecimenStatus.ANALYZED;
    }

    public void setArchived() {
        this.status = SpecimenStatus.ARCHIVED;
    }

    public void setRegistered() {
        this.status = SpecimenStatus.REGISTERED;
    }

    public boolean isAssigned() {
        return this.status == SpecimenStatus.ASSIGNED;
    }

    public boolean isInAnalysis() {
        return this.status == SpecimenStatus.IN_ANALYSIS;
    }

    public boolean isAnalyzed() {
        return this.status == SpecimenStatus.ANALYZED;
    }

    public boolean isArchived() {
        return this.status == SpecimenStatus.ARCHIVED;
    }

    public boolean isRegistered() {
        return this.status == SpecimenStatus.REGISTERED;
    }
}
