package src.June_2026.Week_3.JD_2026_06_W3_LibraryBorrowingProgram;

public class Member {
    String memberID;
    String name;
    int bookCount;
    int maximumBook;

    public Member(String memberID, String name, int bookCount, int maximumBook) {
        this.memberID = memberID;
        this.name = name;
        this.bookCount = bookCount;
        this.maximumBook = maximumBook;
    }

    public void borrowBook() {
        bookCount++;
    }

    public void returnBook() {
        bookCount--;
    }

    public void showMemberInfo() {
        System.out.printf("%s | %s | borrowed: %d / %d%n", memberID, name, bookCount, maximumBook);
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBookCount() {
        return bookCount;
    }

    public void setBookCount(int bookCount) {
        this.bookCount = bookCount;
    }

    public int getMaximumBook() {
        return maximumBook;
    }

    public void setMaximumBook(int maximumBook) {
        this.maximumBook = maximumBook;
    }
}
