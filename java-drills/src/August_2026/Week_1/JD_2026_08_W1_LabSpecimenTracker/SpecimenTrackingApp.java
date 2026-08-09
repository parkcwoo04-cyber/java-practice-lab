package src.August_2026.Week_1.JD_2026_08_W1_LabSpecimenTracker;

import src.August_2026.Week_1.JD_2026_08_W1_WarehouseManagementSystem.Product;

import java.util.ArrayList;

public class SpecimenTrackingApp {
    public static void main(String[] args) {
        SpecimenRegistry registry = new SpecimenRegistry();
        System.out.println("=== Laboratory Specimen Tracker ===");
        System.out.println();

        System.out.println("Registering specimen...");
        addSpecimen(registry, "SP-101", "Cell Sample A");
        addSpecimen(registry, "SP-102", "Cell Sample B");
        addSpecimen(registry, "SP-103", "Tissue Sample A");
        addSpecimen(registry, "SP-103", "Tissue Sample D");
        addSpecimen(registry, "SP-104", "Fluid Sample A");
        System.out.println();

        System.out.println("Registering lab station...");
        addLabStation(registry, "ST-A", "Molecular Analysis", 1);
        addLabStation(registry, "ST-B", "Imaging Lab", 2);
        System.out.println();

        printSpecimenSummary(registry);
        System.out.println();

        printLabStationSummary(registry);
        System.out.println();

        System.out.println("=== Workflow Test ===");

        assignSpecimen(registry, "SP-101", "ST-A");
        startAnalysis(registry, "SP-101");
        assignSpecimen(registry, "SP-102", "ST-A");
        completeAnalysis(registry, "SP-102");
        completeAnalysis(registry, "SP-101");
        archiveSpecimen(registry, "SP-101");
        assignSpecimen(registry, "SP-103", "ST-B");
        assignSpecimen(registry, "SP-104", "ST-B");
        assignSpecimen(registry, "SP-999", "ST-A");
        startAnalysis(registry, "SP-103");
        assignSpecimen(registry, "SP-102", "ST-A");
        System.out.println();

        printSpecimenSummary(registry);
        System.out.println();

        printLabStationSummary(registry);
        System.out.println();

        printStatusSummary(registry);
    }

    private static void addSpecimen(SpecimenRegistry registry, String specimenID, String specimenName){
        try {
            registry.addSpecimen(new Specimen(specimenID, specimenName));
        } catch (InvalidSpecimenOperationException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void addLabStation(SpecimenRegistry registry, String stationID, String stationName, int capacity) {
        try {
            registry.addLabStation(new LabStation(stationID, stationName, capacity));
        } catch (InvalidSpecimenOperationException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void printSpecimenSummary(SpecimenRegistry registry) {
        System.out.println("=== Laboratory Specimen Summary ===");
        ArrayList<Specimen> specimens = registry.getSpecimenList();
        for (Specimen specimen : specimens) {
            System.out.println(specimen.printSpecimenInfo());
        }
    }

    private static void printLabStationSummary(SpecimenRegistry registry) {
        System.out.println("=== Laboratory Lab Station Summary ===");
        ArrayList<LabStation> labStations = registry.getLabStationList();
        for (LabStation labStation : labStations) {
            System.out.println(labStation.printStationInfo());
        }
    }

    private static void assignSpecimen(SpecimenRegistry registry, String specimenID, String stationID) {
        try {
            registry.assignSpecimen(specimenID, stationID);
        } catch (InvalidSpecimenOperationException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void startAnalysis(SpecimenRegistry registry, String specimenID) {
        try {
            registry.startAnalysis(specimenID);
        } catch (InvalidSpecimenOperationException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void completeAnalysis(SpecimenRegistry registry, String specimenID) {
        try {
            registry.completeAnalysis(specimenID);
        } catch (InvalidSpecimenOperationException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void archiveSpecimen(SpecimenRegistry registry, String specimenID) {
        try {
            registry.archiveAnalysis(specimenID);
        } catch (InvalidSpecimenOperationException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void printStatusSummary(SpecimenRegistry registry) {
        ArrayList<Specimen> specimens = registry.getSpecimenList();

        int registered = 0;
        int assigned = 0;
        int inAnalysis = 0;
        int analyzed = 0;
        int archived = 0;

        for (Specimen specimen : specimens) {
            if (specimen.isRegistered()) {
                registered++;
            } else if (specimen.isAssigned()) {
                assigned++;
            } else if (specimen.isInAnalysis()) {
                inAnalysis++;
            } else if (specimen.isAnalyzed()) {
                analyzed++;
            } else if (specimen.isArchived()) {
                archived++;
            }
        }

        System.out.println("=== Status Summary ===");
        System.out.println("REGISTERED: " + registered);
        System.out.println("ASSIGNED: " + assigned);
        System.out.println("IN ANALYSIS: " + inAnalysis);
        System.out.println("ANALYZED: " + analyzed);
        System.out.println("ARCHIVED: " + archived);
    }
}
