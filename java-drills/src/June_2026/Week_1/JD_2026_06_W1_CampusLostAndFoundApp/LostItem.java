package src.June_2026.Week_1.JD_2026_06_W1_CampusLostAndFoundApp;

public class LostItem {
    public String itemName;
    public String foundLocation;
    public String ownerName;
    public boolean isClaimed;

    public LostItem(String itemName, String foundLocation, String ownerName, boolean isClaimed) {
        this.itemName = itemName;
        this.foundLocation = foundLocation;
        this.ownerName = ownerName;
        this.isClaimed = isClaimed;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return this.itemName;
    }

    public void setFoundLocation(String foundLocation) {
        this.foundLocation = foundLocation;
    }

    public String getFoundLocation() {
        return this.foundLocation;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerName() {
        return this.ownerName;
    }

    public void setIsClaimed(boolean isClaimed) {
        this.isClaimed = isClaimed;
    }

    public boolean getIsClaimed() {
        return this.isClaimed;
    }

    public void printItemInfo() {
        String claimed = null;

        if (getIsClaimed()) {
            claimed = "Claimed";
        } else {
            claimed = "Not Claimed";
        }

        System.out.println(getItemName() + " / " + getFoundLocation() + " / Owner: " + getOwnerName() + " / Status: "
                + claimed);
    }
}
