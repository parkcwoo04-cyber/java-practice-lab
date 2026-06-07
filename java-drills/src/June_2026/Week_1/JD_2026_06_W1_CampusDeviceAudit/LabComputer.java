package src.June_2026.Week_1.JD_2026_06_W1_CampusDeviceAudit;

public class LabComputer extends CampusDevice {
    private int storageUsedPercent;
    private boolean needsUpdate;

    public LabComputer(String assetTag, String roomName, boolean isWorking, int daySinceUpdate, int storageUsedPercent,
                       boolean needsUpdate) {
        super(assetTag, roomName, isWorking, daySinceUpdate);
        this.storageUsedPercent = storageUsedPercent;
        this.needsUpdate = needsUpdate;
    }

    public void setStorageUsedPercent(int storageUsedPercent) {
        this.storageUsedPercent = storageUsedPercent;
    }

    public void setNeedsUpdate(boolean needsUpdate) {
        this.needsUpdate = needsUpdate;
    }

    public int getStorageUsedPercent() {
        return storageUsedPercent;
    }

    public boolean getNeedsUpdate() {
        return needsUpdate;
    }

    @Override
    public void printBasicInfo() {
        super.printBasicInfo();
        System.out.println("Type: Lab Computer");
        System.out.println("Storage Used: " + this.storageUsedPercent + "%");
        System.out.println("Needs Update: " + this.needsUpdate);
        System.out.println("Needs Inspection: " + this.needsInspection());
        System.out.println();
    }

    @Override
    public boolean needsInspection() {
        super.needsInspection();
        if(this.storageUsedPercent >= 90) {
            return true;
        } else if (needsUpdate) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void inspect() {
        System.out.println("Inspecting lab computer " + getAssetTag().trim());
        super.inspect();
        if(this.storageUsedPercent >= 90) {
            System.out.println("Storage maintenance completed.");
            setStorageUsedPercent(storageUsedPercent - 20);
        } else if(needsUpdate) {
            System.out.println("Software updated.");
        setNeedsUpdate(false);
        }
    }
}
