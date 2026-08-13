package src.August_2026.Week_2.JD_2026_08_W2_CommunityGardenPlanner;

import java.util.ArrayList;
import java.util.List;

public class GardenManager {
    private List<Gardener> gardenerList;
    private List<Plot> plotList;

    public GardenManager() {
        gardenerList = new ArrayList<Gardener>();
        plotList = new ArrayList<Plot>();
    }

    public void addGardener(Gardener gardener) {
        for (Gardener g : gardenerList) {
            if (g.getGardenerID().equals(gardener.getGardenerID())) {
                throw new IllegalArgumentException("Error: Duplicate Gardener ID");
            }
        }

        gardenerList.add(gardener);
    }

    public void addPlot(Plot plot){
        for (Plot p : plotList) {
            if (p.getPlotID().equals(plot.getPlotID())) {
                throw new IllegalArgumentException("Error: Duplicate Plot ID");
            }
        }
        plotList.add(plot);
    }

    public void assignGardener(Plot plot) {
        for (Gardener gardener : gardenerList) {
            if (gardener.getPreferredSize().equals(plot.getPlotSize()) && gardener.getPlotID() == null) {
                gardener.setPlotID(plot.getPlotID());
                plot.setGardenerID(gardener.getGardenerID());
                plot.isOccupied();
                System.out.println(plot.getPlotID() + " assigned to " + gardener.getName() + ".");
                break;
            }
        }
    }

    public int availablePlots() {
        int available = 0;
        for (Plot plot : plotList) {
            if (plot.getStatus() == PlotStatus.AVAILABLE) {
                available++;
            }
        }
        return available;
    }

    public int occupiedPlots() {
        int occupied = 0;
        for (Plot plot : plotList) {
            if (plot.getStatus() == PlotStatus.OCCUPIED) {
                occupied++;
            }
        }
        return occupied;
    }

    public List<Gardener> getGardenerList() {
        return gardenerList;
    }

    public List<Plot> getPlotList() {
        return plotList;
    }
}
