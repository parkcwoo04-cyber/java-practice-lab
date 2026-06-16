package src.June_2026.Week_3.JD_2026_06_W3_LibraryBorrowingSystem;

public class LibraryMember {
    String memberID;
    String name;
    int borrowedCount;

    public LibraryMember(String memberID, String name, int borrowedCount) {
        this.memberID = memberID;
        this.name = name;
        this.borrowedCount = borrowedCount;
    }

    public void showBasicInfo() {
        System.out.println("Member ID: " + memberID + ", Name: " + name + ", Borrowed Count: " + borrowedCount);
    }

    public void increaseBorrowedCount(){
        if (borrowedCount >= 2) {
            throw new IllegalStateException("Error: " + getName() + " reached maximum number of borrowed books.");
        } else {
            borrowedCount++;
        }
    }

    public void decreaseBorrowedCount(){
        borrowedCount--;
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

    public int getBorrowedCount() {
        return borrowedCount;
    }

    public void setBorrowedCount(int borrowedCount) {
        this.borrowedCount = borrowedCount;
    }
}
