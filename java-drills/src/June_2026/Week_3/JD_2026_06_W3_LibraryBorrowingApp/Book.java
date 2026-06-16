package src.June_2026.Week_3.JD_2026_06_W3_LibraryBorrowingApp;

public class Book extends LibraryItem implements Borrowable {
    private String author;

    public Book(String itemID, String title, boolean available, String author) {
        super(itemID, title, available);
        this.author = author;
    }

    @Override
    public void showInfo() {
        System.out.println("[Book] ID: " + getItemID() + ", Title: " + getTitle() + ", Author: " + this.author + ", Available: " + isAvailable());
    }

    @Override
    public void borrowItem(LibraryMember member) {
        if (!isAvailable()) {
            throw new IllegalStateException("Error: " + getTitle() + " is already borrowed.");
        } else {
            System.out.println("Borrow success: " + getTitle() + " borrowed by " + member.getName() + ".");
            this.setAvailable(false);
        }
    }

    @Override
    public void returnItem() {
        if (isAvailable()) {
            throw new IllegalStateException("Error: " + this.getTitle() + " is already returned.");
        } else {
            System.out.println("Return success: " + getTitle() + " is now available.");
            this.setAvailable(true);
        }
    }
}
