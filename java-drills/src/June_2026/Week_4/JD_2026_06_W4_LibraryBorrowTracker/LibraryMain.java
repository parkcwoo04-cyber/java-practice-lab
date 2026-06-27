package src.June_2026.Week_4.JD_2026_06_W4_LibraryBorrowTracker;

import java.util.ArrayList;
import java.util.Scanner;

public class LibraryMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Book> books = new ArrayList<Book>();
        LibraryService service =  new LibraryService(books);

        boolean running = true;

        books.add(new Book("Java Basics", "Robert Martin"));
        books.add(new Book("Spring MVC", "James Cameron"));
        books.add(new Book("Spring Boot Basics", "Carl Anderson"));

        while (running) {
            int mainMenu = mainMenuChoice(scanner);
            switch (mainMenu){
                case 1: // add book
                    System.out.println("Enter Title");
                    scanner.nextLine();
                    String title = scanner.nextLine();

                    System.out.println("Enter Author");
                    String author = scanner.nextLine();

                    service.addBook(new Book(title, author));
                    break;

                case 2: // show all books
                    service.showAllBooks();
                    break;

                case 3: // search book by title
                    service.searchBook(scanner);
                    break;

                case 4: // borrow book
                    service.borrowBook(scanner);
                    break;

                case 5: // return book
                    service.returnBook(scanner);
                    break;

                case 6: // show statistics
                    service.showStatistics();
                    break;

                case 0: // exit
                    running = false;
                    System.out.println("Terminating Program...");
                    break;

                default:
                    System.out.println("Wrong choice. Try again.");
                    break;
            }
        }
    }

    private static int mainMenuChoice(Scanner scanner) {
        System.out.println("=== Library Borrow Tracker ===");
        System.out.println("1. Add Book");
        System.out.println("2. Show All Book");
        System.out.println("3. Search Book by Title");
        System.out.println("4. Borrow Book");
        System.out.println("5. Return Book");
        System.out.println("6. Show Statistics");
        System.out.println("0. Exit");

        return scanner.nextInt();
    }
}
