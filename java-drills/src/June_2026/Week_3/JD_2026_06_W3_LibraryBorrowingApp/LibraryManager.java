package src.June_2026.Week_3.JD_2026_06_W3_LibraryBorrowingApp;

import java.util.ArrayList;

public class LibraryManager {
    ArrayList<LibraryItem> items = new ArrayList<>();
    ArrayList<LibraryMember> members = new ArrayList<>();

    public void addItem(LibraryItem item) {
        items.add(item);
    }

    public void addMember(LibraryMember member) {
        members.add(member);
    }

    public void showAllItems() {
        System.out.println("=== Library Items ===");

        for (LibraryItem item : items) {
            item.showInfo();
        }
        System.out.println();
    }

    public void findItemByID(String itemID) {
        boolean found = false;
        System.out.println("=== Searching item: " + itemID + " ===");

        for (LibraryItem item : items) {
            if (item.getItemID().equalsIgnoreCase(itemID)) {
                item.showInfo();
                found = true;
            }
        }

        if (!found) {
            System.out.println("Item not found");
        }
        System.out.println();
    }

    public void findMemberByID(String memberID) {
        boolean found = false;
        System.out.println("=== Searching member: " + memberID + " ===");

        for (LibraryMember member : members) {
            if (member.getMemberID().equalsIgnoreCase(memberID)) {
                member.showBasicInfo();
                found = true;
            }
        }
        if (!found) {
            System.out.println("Member not found");
        }
        System.out.println();
    }

    public void borrowItem(String itemID, String memberID) {
        boolean itemFound = false;
        boolean memberFound = false;
        LibraryItem item = null;
        LibraryMember member = null;

        System.out.println("=== Borrowing item: " + itemID + " ===");

        for (LibraryMember member1 : members) {

            if (member1.getMemberID().equalsIgnoreCase(memberID)) {
                member = member1;
                memberFound = true;
            }
        }

        for (LibraryItem item1 : items) {
            if (item1.getItemID().equalsIgnoreCase(itemID)) {
                item = item1;
                itemFound = true;
            }
        }

        if (item != null && member != null) {

            try {
                if (item instanceof Borrowable) {
                    Borrowable borrowable = (Borrowable) item;
                    member.increaseBorrowedCount();
                    borrowable.borrowItem(member);
                } else {
                    System.out.println("Error: " + item.getTitle() + " cannot be borrowed.");
                }
            } catch (IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        }

        if (!itemFound && !memberFound) {
            System.out.println("Error: Wrong item and member ID.");
        } else if (!itemFound) {
            System.out.println("Error: Wrong item ID.");
        } else if (!memberFound) {
            System.out.println("Error: Wrong member ID.");
        }
        System.out.println();
    }

    public void returnItem(String itemID, String memberID) {
        boolean itemFound = false;
        boolean memberFound = false;
        LibraryItem item = null;
        LibraryMember member = null;

        System.out.println("=== Returning item: " + itemID + " ===");
        for (LibraryItem item1 : items) {
            if (item1.getItemID().equalsIgnoreCase(itemID)) {
                item = item1;
                itemFound = true;
            }
        }

        for (LibraryMember member1 : members) {
            if (member1.getMemberID().equalsIgnoreCase(memberID)) {
                member = member1;
                memberFound = true;
            }
        }

        if (item != null && member != null) {
            try {
                if (item instanceof Borrowable) {
                    Borrowable borrowable = (Borrowable) item;
                    member.decreaseBorrowedCount();
                    borrowable.returnItem();
                } else {
                    System.out.println("Error: " + item.getTitle() + " cannot be returned.");
                }
            } catch (IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        }

        if (!itemFound && !memberFound) {
            System.out.println("Error: Wrong item and member ID.");
        } else if (!itemFound) {
            System.out.println("Error: Wrong item ID.");
        }  else if (!memberFound) {
            System.out.println("Error: Wrong member ID.");
        }
        System.out.println();
    }

    public void finalItemStatus() {
        System.out.println("=== Final Item Status ===");
        for (LibraryItem item : items) {
            item.showInfo();
        }
        System.out.println();
    }

    public void manageMember() {
        System.out.println("=== Management of Members ===");
        for (LibraryMember member : members) {
            member.showBasicInfo();
        }
    }
}
