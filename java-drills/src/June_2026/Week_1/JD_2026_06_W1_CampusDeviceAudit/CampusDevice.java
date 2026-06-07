package src.June_2026.Week_1.JD_2026_06_W1_CampusDeviceAudit;

public class CampusDevice {
    private String assetTag;
    private String roomName;
    private boolean isWorking;
    private int daySinceInspection;

    public CampusDevice(String assetTag, String roomName, boolean isWorking, int daySinceInspection) {
        this.assetTag = assetTag;
        this.roomName = roomName;
        this.isWorking = isWorking;
        this.daySinceInspection = daySinceInspection;
    }

    public String getAssetTag() {
        return assetTag;
    }

    public void setAssetTag(String assetTag) {
        this.assetTag = assetTag;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public boolean isWorking() {
        return isWorking;
    }

    public void setWorking(boolean working) {
        isWorking = working;
    }

    public int getDaySinceInspection() {
        return daySinceInspection;
    }

    public void setDaySinceInspection(int daySinceInspection) {
        this.daySinceInspection = daySinceInspection;
    }

    public void printBasicInfo() {
        System.out.println("Asset Tag: " + this.assetTag);
        System.out.println("Room: " + this.roomName);
        System.out.println("Working: " + this.isWorking);
        System.out.println("Day Since Inspection: " + this.daySinceInspection);
    }

    public boolean needsInspection() {
        if(this.daySinceInspection >= 30) {
            return true;
        } else if (!this.isWorking) {
            return true;
        }
        return false;
    }

    public void inspect() {
        if(!isWorking()) {
            System.out.println("Repair Completed.");
            setWorking(true);
            setDaySinceInspection(0);
        } else if(getDaySinceInspection() >= 30) {
            System.out.println("Regular inspection completed.");
            setDaySinceInspection(0);
        }
    }

    public void search(String searchTag) {
        if(searchTag.trim().equalsIgnoreCase(this.assetTag)) {
            printBasicInfo();
        }
    }
}
