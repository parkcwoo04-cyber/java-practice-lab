package src.June_2026.Week_3.JD_2026_06_W3_LibraryBorrowingSystem;

public class Magazine extends  LibraryItem {
    private String issueMonth;
    public Magazine(String itemID, String title, boolean available, String issueMonth) {
        super(itemID, title, available);
        this.issueMonth = issueMonth;
    }

    @Override
    public void showInfo() {
        System.out.println("[Magazine] ID: " + getItemID() + ", Title: " + getTitle() + ", Issue: " + this.issueMonth + ", Available: " + isAvailable());
    }
}
