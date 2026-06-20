package src.June_2026.Week_3.JD_2026_06_W3_LibraryBorrowingProgram;

public class Book {
    String bookID;
    String title;
    String author;
    boolean isAvailable;
    Member member;

    public Book(String bookID, String title, String author, boolean isAvailable) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.isAvailable = isAvailable;
        this.member = null;
    }

    public void showInfo() {
        String availability = isAvailable ? "Available" : "Not Available";
        System.out.printf("%s | %s | %s | %s%n", bookID, title, author, availability);
    }

    public void borrowBook(Member member) {
        this.setIsAvailable(false);
        this.setMember(member);
    }

    public void returnBook(Member member) {
        this.setIsAvailable(true);
        this.setMember(null);
    }

    public String  getBookID() {
        return bookID;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}
