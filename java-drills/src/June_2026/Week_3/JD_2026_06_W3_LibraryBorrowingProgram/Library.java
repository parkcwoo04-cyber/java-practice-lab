package src.June_2026.Week_3.JD_2026_06_W3_LibraryBorrowingProgram;

import java.util.ArrayList;

public class Library {
    ArrayList<Book> books = new ArrayList<>();
    ArrayList<Member> members = new ArrayList<>();

    public Library(ArrayList<Book> books, ArrayList<Member> members) {
        this.books = books;
        this.members = members;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void showBooks() {
        System.out.println("Book List");
        for (Book book : books) {
            book.showInfo();
        }
        System.out.println();
    }

    public void addMember(Member member) {
        members.add(member);
    }

    public void showMembers() {
        System.out.println("Member List");
        for (Member member : members) {
            member.showMemberInfo();
        }
        System.out.println();
    }

    public void borrowBook(String memberID, String bookID) {
        boolean memberMatched = false;

        try {
            for (Member member : members) {
                if (member.getMemberID().equals(memberID)) {
                    memberMatched = true;
                    boolean bookMatched = false;
                    if (member.getBookCount() < member.getMaximumBook()) {
                        for (Book book : books) {
                            if (book.getBookID().equals(bookID)) {
                                bookMatched = true;
                                if (book.isAvailable()) {
                                    book.borrowBook(member);
                                    member.borrowBook();
                                    throw new SuccesfulBorrowAttemptsException("Borrow Success: " + book.getTitle() + " borrowed by "+ member.getName() + ".");
                                } else {
                                    throw new InvalidBorrowAttemptsException("This book is already borrowed.");
                                }
                            }
                        }
                        if (!bookMatched) {
                            throw new InvalidBorrowAttemptsException("Book is not found.");
                        }
                    } else {
                        throw new InvalidBorrowAttemptsException("Rental limit reached.");
                    }
                }
            }
            if (!memberMatched) {
                throw new InvalidBorrowAttemptsException("Member not found.");
            }
        } catch (SuccesfulBorrowAttemptsException e) {
            System.out.println(e.getMessage());
        } catch (InvalidBorrowAttemptsException e) {
            System.out.println(e.getMessage());
        }
    }

    public void returnBook(String memberID, String bookID) {
        boolean memberMatched = false;
        try {
            for (Member member : members) {
                if (member.getMemberID().equals(memberID)) {
                    memberMatched = true;
                    boolean bookMatched = false;
                    for (Book book : books) {
                        if (book.getBookID().equals(bookID)) {
                            bookMatched = true;
                            if (!book.isAvailable()) {
                                member.returnBook();
                                book.returnBook(member);
                                throw new SuccessfulReturnAttemptsException("Return success: " + book.getTitle() + " returned by " + member.getName() + ".");
                            } else {
                                throw new InvalidReturnAttemptException("This book is already returned.");
                            }
                        }
                    }

                    if (!bookMatched) {
                        throw new InvalidReturnAttemptException("Book is not found.");
                    }
                }
            }
            if (!memberMatched) {
                throw new InvalidReturnAttemptException("Member not found.");
            }
        } catch (SuccessfulReturnAttemptsException e) {
            System.out.println(e.getMessage());
        } catch (InvalidReturnAttemptException e) {
            System.out.println(e.getMessage());
        }
    }

    public void showMemberBorrowingStatus(String memberID) {
        boolean memberMatched = false;
        System.out.println("===Member Borrowing Status===");
        for (Member member : members) {
            if (member.getMemberID().equals(memberID)) {
                memberMatched = true;
                member.showMemberInfo();
                System.out.println();
                System.out.println("Borrowed Books: ");
                boolean memberBorrowed = false;
                for (Book book : books) {
                    if (book.getMember() != null) {
                        if (book.getMember().getMemberID().equals(memberID)) {
                            memberBorrowed = true;
                            book.showInfo();
                        }
                    }
                }
                if (!memberBorrowed) {
                    System.out.println("No books borrowed.");
                }
            }
        }
        if(!memberMatched) {
            System.out.println("Member not found.");
        }
        System.out.println();
    }
}
