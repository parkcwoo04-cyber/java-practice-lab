package src.June_2026.Week_1.JD_2026_06_W1_CampusDeviceAudit;

public class CampusDeviceAudit {
    public static void main(String[] args) {
        CampusDevice[] devices = {
                new Projector("PRJ-101", "B201", true, 45, 2300,
                        false),
                new Projector("PRJ-103", "C302", true, 12, 1800,
                        false),
                new LabComputer("PC-204", "Lab A", true, 10,
                        94, true),
                new LabComputer("PC-382", "Lab C", true, 23,
                        75, false)
        };

        System.out.println("=== Campus Device Report: Before Inspection===");

        for (CampusDevice device : devices) {
            device.printBasicInfo();
        }

        int counter = 0;

        System.out.println("=== Inspection Started ===");
        for (CampusDevice device : devices) {
            if(device.needsInspection()) {
                device.inspect();
                counter++;
            } else {
                device.inspect();
                System.out.println(device.getAssetTag().trim() + " does not need inspection.");
            }
            System.out.println();
        }
        System.out.println("Total Inspected: " + counter);
        System.out.println();


        String searchTag = "pc-204";
        System.out.println("=== Search Result ===");
        for (CampusDevice device : devices) {
            device.search(searchTag);
        }
    }
}
