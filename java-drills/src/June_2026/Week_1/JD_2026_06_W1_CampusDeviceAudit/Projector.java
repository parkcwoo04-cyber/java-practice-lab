package src.June_2026.Week_1.JD_2026_06_W1_CampusDeviceAudit;

public class Projector extends CampusDevice {
    private int lampHours;
    private boolean hasInputIssue;

    public Projector(String assetTag, String roomName, boolean isWorking, int daySinceInspection,
                     int lampHours, boolean hasInputIssue) {
        super(assetTag, roomName, isWorking, daySinceInspection);
        this.lampHours = lampHours;
        this.hasInputIssue = hasInputIssue;
    }

    public int getLampHours() {
        return lampHours;
    }

    public void setLampHours(int lampHours) {
        this.lampHours = lampHours;
    }

    public boolean isHasInputIssue() {
        return hasInputIssue;
    }

    public void setHasInputIssue(boolean hasInputIssue) {
        this.hasInputIssue = hasInputIssue;
    }

    @Override
    public void printBasicInfo() {
        super.printBasicInfo();
        System.out.println("Type: Projector");
        System.out.println("Lamp Hours: " + this.lampHours);
        System.out.println("Has Input Issue: " + this.hasInputIssue);
        System.out.println("Need Inspection: " + this.needsInspection());
        System.out.println();
    }

    @Override
    public boolean needsInspection() {
        super.needsInspection();
        if(this.hasInputIssue) {
            return true;
        }
        else if(this.lampHours >= 2000) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void inspect() {
        System.out.println("Inspecting projector " + getAssetTag().trim());
        super.inspect();
        if(lampHours >= 2000) {
            System.out.println("Lamp maintenance completed.");
            setLampHours(lampHours - 500);
        }

        if (hasInputIssue) {
            System.out.println("Input maintenance completed.");
            setHasInputIssue(false);
        }
    }
}
