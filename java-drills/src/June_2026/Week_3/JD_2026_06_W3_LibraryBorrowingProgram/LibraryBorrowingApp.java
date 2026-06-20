package src.June_2026.Week_3.JD_2026_06_W3_LibraryBorrowingProgram;

import java.util.ArrayList;
import java.util.Scanner;

public class LibraryBorrowingApp {
    public static void main(String[] args) {
        ArrayList<Book> books = new ArrayList<>();
        ArrayList<Member> members = new ArrayList<>();
        Library library = new Library(books, members);

        library.addBook(new Book("B01", "Java Basics", "Kim", true));
        library.addBook(new Book("B02", "Python Basics", "Lee", true));
        library.addBook(new Book("B03", "C++ Basics", "Hwang", true));
        library.addBook(new Book("B04", "SprintBoot", "Kim", true));
        library.addBook(new Book("B05", "CS180 Intro", "Park", true));

        library.addMember(new Member("M01", "Daniel", 0, 5));
        library.addMember(new Member("M02", "Joeun", 0, 8));
        library.addMember(new Member("M03", "Lucas", 0, 10));

        boolean systemContinue = true;
        while (systemContinue) {
            System.out.println("=== Library Menu ====");
            System.out.println("1. Show All Books");
            System.out.println("2. Show All Members");
            System.out.println("3. Borrow a book");
            System.out.println("4. Return a book");
            System.out.println("5. Show member borrowing status");
            System.out.println("0. Exit");
            Scanner input = new Scanner(System.in);
            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    library.showBooks();
                    break;

                case 2:
                    library.showMembers();
                    break;

                case 3:
                    input.nextLine();
                    System.out.println("Enter member ID: ");
                    String memberID = input.nextLine();
                    System.out.println("Enter Book ID: ");
                    String bookID = input.nextLine();
                    library.borrowBook(memberID, bookID);
                    break;

                case 4:
                    input.nextLine();
                    System.out.println("Enter member ID: ");
                    String memberId = input.nextLine();
                    System.out.println("Enter Book ID: ");
                    String bookId = input.nextLine();
                    library.returnBook(memberId, bookId);

                case 5:
                    input.nextLine();
                    System.out.println("Enter member ID: ");
                    String memberid = input.nextLine();
                    library.showMemberBorrowingStatus(memberid);
                    break;

                case 0:
                    systemContinue = false;
                    break;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
