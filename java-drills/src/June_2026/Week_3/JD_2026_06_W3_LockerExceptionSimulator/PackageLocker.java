package src.June_2026.Week_3.JD_2026_06_W3_LockerExceptionSimulator;

public class PackageLocker {
    private int lockerNumber;
    private String ownerName;
    private String pickupCode;
    private boolean pickedUp;

    public PackageLocker(int lockerNumber, String ownerName, String pickupCode) {
        this.lockerNumber = lockerNumber;
        this.ownerName = ownerName;
        this.pickupCode = pickupCode;
        this.pickedUp = false;
    }

    public boolean matchesCode(String code){
        if(code.equalsIgnoreCase(pickupCode))
            return true;
        return false;
    }

    public void markPickedUp(){
        pickedUp = true;
    }


    public int getLockerNumber() {
        return lockerNumber;
    }

    public void setLockerNumber(int lockerNumber) {
        this.lockerNumber = lockerNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getPickupCode() {
        return pickupCode;
    }

    public void setPickupCode(String pickupCode) {
        this.pickupCode = pickupCode;
    }

    public boolean isPickedUp() {
        return pickedUp;
    }

    public void setPickedUp(boolean pickedUp) {
        this.pickedUp = pickedUp;
    }
}
