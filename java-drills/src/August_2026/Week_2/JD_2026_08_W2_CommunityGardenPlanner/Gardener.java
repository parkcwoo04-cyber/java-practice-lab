package src.August_2026.Week_2.JD_2026_08_W2_CommunityGardenPlanner;

public class Gardener {
    private String gardenerID;
    private String name;
    private PlotSize preferredSize;
    private String plotID;

    public Gardener(String gardenerID, String name, PlotSize preferredSize) {
        if (gardenerID == null || gardenerID.isEmpty()) {
            throw new IllegalArgumentException("Gardener ID cannot be empty");
        }

        if ( name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Gardener name cannot be empty");
        }

        this.gardenerID = gardenerID;
        this.name = name;
        this.preferredSize = preferredSize;
        this.plotID = null;
    }

    public String printInfo() {
        StringBuilder sb = new StringBuilder(getGardenerID() + " | " + getName() + " | " + getPreferredSize());
        if (plotID != null) {
            sb.append(" | ").append(getPlotID());
        }

        return sb.toString();
    }

    public String getGardenerID() {
        return gardenerID;
    }

    public String getName() {
        return name;
    }

    public PlotSize getPreferredSize() {
        return preferredSize;
    }

    public String getPlotID() {
        return plotID;
    }

    public void setPlotID(String plotID) {
        if (plotID == null || plotID.isEmpty()) {
            throw new IllegalArgumentException("Plot ID cannot be empty");
        }

        this.plotID = plotID;
    }
}
