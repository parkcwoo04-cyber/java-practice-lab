package src.August_2026.Week_2.JD_2026_08_W2_DeepSpaceTransmissionControl.JD_2026_08_W1_DeepSpaceTransmissionControl;

public class MissionControlApp {
    public static void main(String[] args) {
        MissionControl control = new MissionControl();
        TransmissionLogWriter writer = new TransmissionLogWriter(control);

        System.out.println("=== Deep Space Transmission Control ===");
        System.out.println();

        addGroundStation(control, "GS-A", "Houston", 10);
        addGroundStation(control, "GS-B", "New York", 16);
        System.out.println();

        addTransmission(control, "TX-101", "Europa-1", TransmissionType.IMAGE, 5);
        addTransmission(control, "TX-102", "Titan-1", TransmissionType.IMAGE, 4);
        addTransmission(control, "TX-103", "MarsRelay-2", TransmissionType.TELEMETRY, 6);
        addTransmission(control, "TX-104", "Europa-1", TransmissionType.TELEMETRY, 4);
        addTransmission(control, "TX-105", "Titan-1", TransmissionType.IMAGE, 3);
        System.out.println();

        System.out.println("=== Scheduling ===");
        scheduleTransmission(control, "TX-101", "GS-A");
        scheduleTransmission(control, "TX-102", "GS-B");
        scheduleTransmission(control, "TX-103", "GS-A");
        scheduleTransmission(control, "TX-103", "GS-B");
        scheduleTransmission(control, "TX-104", "GS-A");
        System.out.println();

        System.out.println("=== Transmission Summary ===");
        printTransmissionInfo(control);
        System.out.println();

        System.out.println("=== Transmission Operations ===");
        transmit(control, "TX-101");
        transmit(control, "TX-104");
        transmit(control, "TX-105");
        completeTransmission(control, "TX-101");
        completeTransmission(control, "TX-102");
        archiveTransmission(control, "TX-101");
        System.out.println();

        System.out.println("=== Transmission Summary === ");
        printTransmissionInfo(control);
        System.out.println();

        System.out.println("=== Ground Station Summary ===");
        printStationInfo(control);
        System.out.println();

        batchCleanUp(control);
        System.out.println();

        System.out.println("=== Active Transmissions ===");
        printTransmissionInfo(control);
        System.out.println();

        printArchivedTransmission(control);
        System.out.println();

        writer.saveFile();
    }

    private static void addTransmission(MissionControl control, String transmissionID, String probeName, TransmissionType type, int data) {
        try {
            if (type == TransmissionType.IMAGE) {
                control.addTransmission(new ImageTransmission(transmissionID, probeName, data));
            } else {
                control.addTransmission(new ImageTransmission(transmissionID, probeName, data));
            }
            System.out.println("Registered " + type + " transmission " + transmissionID + " from " + probeName);
        } catch (InvalidTransmissionOperationException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void addGroundStation(MissionControl control, String groundStationID, String groundStationName, int maxCapacity) {
        try {
            control.addGroundStation(new GroundStation(groundStationID, groundStationName, maxCapacity));
            System.out.println("Registered " + groundStationID + " | Capacity: " + maxCapacity);
        } catch (InvalidTransmissionOperationException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void scheduleTransmission(MissionControl control, String transmissionID, String groundStationID) {
        try {
            control.scheduleTransmission(transmissionID, groundStationID);
            int load = control.foundTransmissionByID(transmissionID).getCapacity();
            System.out.println(transmissionID + " scheduled to " + groundStationID + " | Load: " + load);
        } catch (InvalidTransmissionOperationException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void printTransmissionInfo(MissionControl control) {
        for (Transmission transmission : control.getTransmissionList()) {
            System.out.println(transmission.printTransmissionInfo());
        }
    }

    private static void transmit(MissionControl control, String transmissionID) {
        try {
            control.transmit(transmissionID);
            System.out.println(transmissionID + " transmitted started.");
        } catch (InvalidTransmissionOperationException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void completeTransmission(MissionControl control, String transmissionID) {
        try {
            GroundStation groundStation = control.foundGroundStationByTransmission(transmissionID);
            control.completeTransmission(transmissionID);
            System.out.println(transmissionID + " transmission completed.");
            System.out.println(groundStation.getStationID() + " released " + control.foundTransmissionByID(transmissionID).getCapacity() + " capacity.");
        } catch (InvalidTransmissionOperationException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void printStationInfo(MissionControl control) {
        for (GroundStation station : control.getStationList()) {
            System.out.println((station.printStationInfo()));
        }
    }

    private static void archiveTransmission(MissionControl control, String transmissionID) {
        try {
            control.archiveTransmission(transmissionID);
            System.out.println(transmissionID + " archived.");
        } catch (InvalidTransmissionOperationException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void batchCleanUp(MissionControl control) {
        try {
            System.out.println("=== Batch Cleanup ===");
            control.batchCleanUp();
        } catch (InvalidTransmissionOperationException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void printArchivedTransmission(MissionControl control) {
        System.out.println("=== Archived Transmission ===");
        for (Transmission transmission : control.getArchivedTransmissionList()) {
            System.out.println(transmission.printTransmissionInfo());
        }
        System.out.println();
    }
}
