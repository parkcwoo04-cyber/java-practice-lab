package src.August_2026.Week_2.JD_2026_08_W2_CommunityGardenPlanner;

import java.util.List;

public class CommunityGardenApp {
    public static void main(String[] args){
        GardenManager manager = new GardenManager();
        System.out.println("=== Community Garden Planner ===");
        System.out.println();

        registerPlot(manager, "P-01", PlotSize.SMALL);
        registerPlot(manager, "P-02", PlotSize.SMALL);
        registerPlot(manager, "P-03", PlotSize.MEDIUM);
        registerPlot(manager, "P-04", PlotSize.LARGE);
        registerPlot(manager, "P-05", PlotSize.LARGE);
        System.out.println();

        registerGardener(manager, "G-101", "Mina", PlotSize.SMALL);
        registerGardener(manager, "G-102", "Daniel", PlotSize.MEDIUM);
        registerGardener(manager, "G-103", "Sara", PlotSize.LARGE);
        registerGardener(manager, "G-104", "Leo", PlotSize.SMALL);
        registerGardener(manager, "G-103", "Sara", PlotSize.MEDIUM);
        System.out.println();

        System.out.println("=== Assignment ===");
        assignGarden(manager);
        System.out.println();

        System.out.println("=== Garden Summary ===");
        printGardenSummary(manager);
        System.out.println();

        printGardenStatistics(manager);
    }

    private static void registerPlot(GardenManager manager, String plotID, PlotSize size) {
        try {
            manager.addPlot(new Plot(plotID, size));
            System.out.println("Registered plot: " + plotID + " | " + size);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void registerGardener(GardenManager manager, String gardenerID, String name, PlotSize size) {
        try {
            manager.addGardener(new Gardener(gardenerID, name, size));
            System.out.println("Registered gardener: " + name + " | Preferred size: " + size);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void assignGarden(GardenManager manager) {
        List<Plot> plotList = manager.getPlotList();

        for (Plot plot : plotList) {
            manager.assignGardener(plot);
        }

        for (Plot plot : plotList) {
            if (plot.getGardenerID() == null) {
                System.out.println("Assignment failed: no " + plot.getPlotSize() + " gardener is currently available for " + plot.getPlotID() + ".");
            }
        }
    }

    private static void printGardenSummary(GardenManager manager) {
        for (Plot plot : manager.getPlotList()) {
            System.out.println(plot.printInfo());
        }
    }

    private static void printGardenStatistics(GardenManager manager) {
        System.out.println("Available Plots: " + manager.availablePlots());
        System.out.println("Occupied Plots: " + manager.occupiedPlots());
    }
}
