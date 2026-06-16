package src.June_2026.Week_3.JD_2026_06_W3_LibraryBorrowingSystem;

public class LibraryBorrowingApp {
    public static void main(String[] args) {
        LibraryManager libraryManager = new LibraryManager();

        libraryManager.addItem(new Book("B001", "Java Basics", true, "Kim"));
        libraryManager.addItem(new Book("B002", "OOP Practice", true, "Lee"));
        libraryManager.addItem(new Book("B003", "Python Basics", true, "Park"));
        libraryManager.addItem(new Magazine("M001", "Tech Monthly", true, "June"));
        libraryManager.addItem(new Magazine("M002", "Dazed", true, "August"));

        libraryManager.addMember(new LibraryMember("S001", "Daniel", 0));
        libraryManager.addMember(new LibraryMember("S002", "Joeun", 0));
        libraryManager.addMember(new LibraryMember("S003", "Jimmy", 0));

        libraryManager.showAllItems();

        libraryManager.findItemByID("B001");
        libraryManager.findItemByID("B003");

        libraryManager.findMemberByID("S001");

        libraryManager.borrowItem("B001", "S001");
        libraryManager.borrowItem("B001", "S003");
        libraryManager.borrowItem("B002", "S001");
        libraryManager.borrowItem("B003", "S001");
        libraryManager.borrowItem("M001", "S003");

        libraryManager.returnItem("B001", "S001");
        libraryManager.returnItem("B001", "S003");

        libraryManager.finalItemStatus();
        libraryManager.manageMember();
    }
}
