package src.August_2026.Week_2.JD_2026_08_W2_CommunityGardenPlanner;

public class Plot {
    private String plotID;
    private PlotSize plotSize;
    private PlotStatus status;
    private String gardenerID;

    public Plot(String plotID, PlotSize plotSize) {
        if (plotID == null || plotID.isEmpty()) {
            throw new IllegalArgumentException("Plot ID cannot be empty");
        }

        this.plotID = plotID;
        this.plotSize = plotSize;
        this.status = PlotStatus.AVAILABLE;
        this.gardenerID = null;
    }

    public String printInfo() {
        StringBuilder sb = new StringBuilder(getPlotID() + " | " + getPlotSize() + " | " + getStatus());
        if (getGardenerID() != null) {
            sb.append(" | Gardener: ").append(getGardenerID());
        }

        return sb.toString();
    }

    public String getPlotID() {
        return plotID;
    }

    public PlotSize getPlotSize() {
        return plotSize;
    }

    public PlotStatus getStatus() {
        return status;
    }

    public String getGardenerID() {
        return gardenerID;
    }

    public void setGardenerID(String gardenerID) {
        if (gardenerID == null || gardenerID.isEmpty()) {
            throw new IllegalArgumentException("Gardener ID cannot be empty");
        }
        this.gardenerID = gardenerID;
    }

    public void isOccupied() {
        if (status == PlotStatus.AVAILABLE) {
            this.status = PlotStatus.OCCUPIED;
        }
    }
}
