package src.June_2026.Week_2.JD_2026_06_W2_LostItemControlCenter;

public class LostItem {
    private String itemID;
    private String name;
    private String category;
    private String locationFound;
    private boolean claimed;

    public LostItem(String itemID, String name, String category, String locationFound) {
        this.itemID = itemID;
        this.name = name;
        this.category = category;
        this.locationFound = locationFound;
        this.claimed = false;
    }

    public void printBasicInfo() {
        System.out.println(this.itemID + " | " + this.name + " | " + this.category + " | " + this.locationFound);
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLocationFound() {
        return locationFound;
    }

    public void setLocationFound(String locationFound) {
        this.locationFound = locationFound;
    }

    public boolean isClaimed() {
        return claimed;
    }

    public void matchClaimed() {
        this.claimed = true;
    }

    public boolean matchesDescription(String description) {
        return description.equals(this.name);
    }
}
