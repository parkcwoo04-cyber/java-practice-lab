package src.June_2026.Week_3.JD_2026_06_W3_LibraryBorrowingSystem;

public abstract class LibraryItem {
    private String itemID;
    private String title;
    private boolean available;

    public LibraryItem(String itemID, String title, boolean available) {
        this.itemID = itemID;
        this.title = title;
        this.available = available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getItemID() {
        return itemID;
    }

    public String getTitle() {
        return title;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public abstract void showInfo();


}
