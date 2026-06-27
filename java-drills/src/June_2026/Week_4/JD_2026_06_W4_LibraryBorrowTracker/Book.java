package src.June_2026.Week_4.JD_2026_06_W4_LibraryBorrowTracker;

public class Book {
    private String title;
    private String author;
    private Available available;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.available = Available.AVAILABLE;
    }

    public void printBookInfo() {
        System.out.printf("%s | %s | %s\n", title, author, available);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void borrowBook() {
        this.available = Available.UNAVAILABLE;
    }

    public void returnBook(){
        this.available = Available.AVAILABLE;
    }

    public Available getAvailable() {
        return available;
    }
}
