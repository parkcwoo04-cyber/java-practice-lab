package src.June_2026.Week_4.JD_2026_06_W4_LibraryBorrowTracker;

import java.util.ArrayList;
import java.util.Scanner;

public class LibraryService {
    private ArrayList<Book> books;

    public LibraryService(ArrayList<Book> books) {
        this.books = books;
    }

    public void addBook(Book book) {
        try {
            if (book.getTitle() == null || book.getTitle().equals("")) {
                throw new IllegalArgumentException("Title cannot be empty.");
            } else if (book.getAuthor() == null || book.getAuthor().equals("")) {
                throw new IllegalArgumentException("Author cannot be empty.");
            } else {
                System.out.println("Book added.");
                System.out.println();
                books.add(book);
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void showAllBooks() {
        System.out.println("=== Book Lists ===");
        for (Book book : books) {
            book.printBookInfo();
        }
        System.out.println();
    }

    public void searchBook(Scanner scanner) {
        System.out.println("Enter Book Title");
        scanner.nextLine();
        String title = scanner.nextLine();

        ArrayList<Book> searchedBooks = searchBookByTitle(title);

        int i = 1;

        if (searchedBooks.isEmpty()) {
            System.out.println("No books found.");
            return;
        }

        for (Book book : searchedBooks) {
            System.out.print(i + ". ");
            book.printBookInfo();
            i ++;
        }
        System.out.println();
    }

    public Book searchByTitle(Scanner scanner) {
        System.out.println("Enter Book Title");
        scanner.nextLine();
        String title = scanner.nextLine();

        ArrayList<Book> searchedBooks = searchBookByTitle(title);

        int i = 1;

        if (searchedBooks.isEmpty()) {
            System.out.println("No books found.");
            return null;
        }

        for (Book book : searchedBooks) {
            System.out.print(i + ". ");
            book.printBookInfo();
            i ++;
        }
        int choice = scanner.nextInt();

        return searchedBooks.get(choice - 1);
    }

    public ArrayList<Book> searchBookByTitle(String title) {
        ArrayList<Book> books1 = new ArrayList<>();

        for (Book b : books) {
            if (b.getTitle().contains(title)) {
                books1.add(b);
            }
        }

        return books1;
    }

    public void borrowBook(Scanner scanner) {
        Book targetBook = searchByTitle(scanner);
        if (targetBook == null) {
            return;
        }

        if (targetBook.getAvailable() == Available.UNAVAILABLE) {
            System.out.println("Book is not available.");
            return;
        }

        System.out.printf("%s has been borrowed.\n", targetBook.getTitle());
        System.out.println();
        targetBook.borrowBook();
    }

    public void returnBook(Scanner scanner) {
        Book targetBook = searchByTitle(scanner);
        if (targetBook == null) {
            return;
        }

        if (targetBook.getAvailable() == Available.AVAILABLE) {
            System.out.println("Book is not borrowed.");
            return;
        }

        System.out.printf("%s has been returned.\n", targetBook.getTitle());
        System.out.println();
        targetBook.returnBook();
    }

    public void showStatistics() {
        int available = 0;
        int borrowed = 0;
        for (Book book : books) {
            if (book.getAvailable() == Available.AVAILABLE) {
                available++;
            } else if (book.getAvailable() == Available.UNAVAILABLE) {
                borrowed++;
            }
        }
        System.out.println("Total Books: " + books.size());
        System.out.println("Available Books: " + available);
        System.out.println("Borrowed Books: " + borrowed);
        System.out.println();
    }
}
