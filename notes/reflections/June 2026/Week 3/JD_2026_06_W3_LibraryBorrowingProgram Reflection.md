

## Metadata

Drill ID: JD_2026_06_W3_LibraryBorrowingProgram

Linked code:
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W3_LibraryBorrowingProgram/LibraryBorrowingApp.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W3_LibraryBorrowingProgram/Library.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W3_LibraryBorrowingProgram/Book.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W3_LibraryBorrowingProgram/Member.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W3_LibraryBorrowingProgram/InvalidBorrowAttemptsException.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W3_LibraryBorrowingProgram/InvalidReturnAttemptException.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W3_LibraryBorrowingProgram/SuccessfulBorrowAttemptsException.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W3_LibraryBorrowingProgram/SuccessfulReturnAttemptsException.java)

Difficulty: Level 3 — Object-Oriented Problem Solving

Estimated Time: 60–90 minutes

Actual Time Taken: 64 minutes

Written by: Chanwoo Park

---

## Goal

The goal was to create a console-based library borrowing system using `Scanner`, `ArrayList`, multiple classes, object collaboration, state updates, input validation, and exception handling. The program manages books and members, allows borrowing and returning books, and checks conditions such as whether a book exists, whether a member exists, whether the book is available, and whether the member has reached the borrow limit.

---

## What I Tried

- Created a `LibraryBorrowingApp` class to control the console menu and user input.
- Used `Scanner` to receive menu choices, member IDs, and book IDs.
- Created a `Book` class to store book information and borrowing state.
- Created a `Member` class to store member information, current borrowed book count, and maximum borrow limit.
- Created a `Library` class to manage `ArrayList<Book>` and `ArrayList<Member>`.
- Implemented book borrowing and returning through the `Library` class.
- Used conditions to check whether a member exists, whether a book exists, whether a book is available, and whether the member can borrow more books.
- Tried using custom exception classes for borrow and return results.
- Implemented member borrowing status by checking which books are connected to a specific member.

---

## Mistakes

The main design issue was that `Library.borrowBook()` and `Library.returnBook()` became too complex because too many responsibilities were handled inside one method.

The original structure was similar to:

```java
for (Member member : members) {
    if (member.getMemberID().equals(memberID)) {
        if (member.getBookCount() < member.getMaximumBook()) {
            for (Book book : books) {
                if (book.getBookID().equals(bookID)) {
                    if (book.isAvailable()) {
                        // borrow logic
                    } else {
                        // error logic
                    }
                }
            }
        } else {
            // limit reached logic
        }
    }
}
```

This caused deeply nested `if` statements. The problem was not simply that there were many `if` statements. The deeper issue was that searching, validation, state updates, success messages, error messages, and exception handling were all mixed together.

A second important logic mistake was in the return flow. The program checked whether the book was borrowed, but it did not fully verify whether the same member was the one who borrowed the book.

The problematic logic was similar to:

```java
if (!book.isAvailable()) {
    member.returnBook();
    book.returnBook(member);
    throw new SuccessfulReturnAttemptsException(
        "Return success: " + book.getTitle() + " returned by " + member.getName() + "."
    );
}
```

This can allow a different member to return a book they did not borrow.

A third issue was in the `switch-case` flow in `LibraryBorrowingApp`. `case 4` did not have a `break`, so the program could continue into `case 5` after returning a book.

```java
case 4:
    library.returnBook(memberId, bookId);

case 5:
    library.showMemberBorrowingStatus(memberid);
    break;
```

This is a control-flow mistake called fall-through.

A fourth issue was using exceptions for successful events.

```java
throw new SuccesfulBorrowAttemptsException("Borrow Success: ...");
```

Success is part of the normal program flow, not an exceptional situation. Throwing an exception for success makes the code harder to read because the program appears to be handling an error even when the operation worked correctly.

---

## Why the Mistake Happened

The nested `if` problem happened because the method tried to solve every step directly instead of separating the logic into smaller helper methods. In a Level 3 object-oriented program, the `Library` class should coordinate the process, but it should not become one large block of nested search and validation code.

The return logic mistake happened because the program checked the book's borrowed state but did not check the relationship between the `Book` and the `Member`. Returning a book is not valid just because the book is borrowed. It is valid only when the book is borrowed by the same member who is trying to return it.

The `switch-case` mistake happened because Java continues executing the next case if there is no `break`. This is not a compile error, but it changes the program flow at runtime.

The exception design issue happened because exception handling was used as a general message delivery mechanism. In Java, exceptions are intended for abnormal or exceptional situations. A successful borrow or return should be handled through normal statements, such as updating state, printing a success message, and returning from the method.

---

## Improved Solution

Create search helper methods in `Library`:

```java
private Member findMemberById(String memberID) {
    for (Member member : members) {
        if (member.getMemberID().equals(memberID)) {
            return member;
        }
    }
    return null;
}

private Book findBookById(String bookID) {
    for (Book book : books) {
        if (book.getBookID().equals(bookID)) {
            return book;
        }
    }
    return null;
}
```

Rewrite `borrowBook()` using early return:

```java
public void borrowBook(String memberID, String bookID) {
    Member member = findMemberById(memberID);
    Book book = findBookById(bookID);

    if (member == null) {
        System.out.println("Member not found.");
        return;
    }

    if (book == null) {
        System.out.println("Book not found.");
        return;
    }

    if (!book.isAvailable()) {
        System.out.println("This book is already borrowed.");
        return;
    }

    if (!member.canBorrowMore()) {
        System.out.println("Borrow limit reached.");
        return;
    }

    book.borrowBook(member);
    member.borrowBook();

    System.out.println("Borrow Success: " + book.getTitle() + " borrowed by " + member.getName() + ".");
}
```

Rewrite `returnBook()` with ownership validation:

```java
public void returnBook(String memberID, String bookID) {
    Member member = findMemberById(memberID);
    Book book = findBookById(bookID);

    if (member == null) {
        System.out.println("Member not found.");
        return;
    }

    if (book == null) {
        System.out.println("Book not found.");
        return;
    }

    if (book.isAvailable()) {
        System.out.println("This book is not currently borrowed.");
        return;
    }

    if (book.getMember() == null || !book.getMember().getMemberID().equals(memberID)) {
        System.out.println("This member did not borrow this book.");
        return;
    }

    book.returnBook();
    member.returnBook();

    System.out.println("Return success: " + book.getTitle() + " returned by " + member.getName() + ".");
}
```

Add `break` after `case 4`:

```java
case 4:
    input.nextLine();
    System.out.println("Enter member ID: ");
    String memberId = input.nextLine();
    System.out.println("Enter Book ID: ");
    String bookId = input.nextLine();
    library.returnBook(memberId, bookId);
    break;
```

Move `Scanner` creation outside the loop:

```java
Scanner input = new Scanner(System.in);

while (systemContinue) {
    // menu logic
}

input.close();
```

Improve `Book.returnBook()` by removing the unused parameter:

```java
public void returnBook() {
    this.isAvailable = true;
    this.member = null;
}
```

Improve `Member` by adding a method that clearly expresses borrow-limit logic:

```java
public boolean canBorrowMore() {
    return bookCount < maximumBook;
}
```

Protect `Member.returnBook()` from making the count negative:

```java
public void returnBook() {
    if (bookCount > 0) {
        bookCount--;
    }
}
```

---

## Why Success Should Not Be Handled as an Exception

A successful borrow or return is expected program behavior. It belongs to the normal path of execution.

For example, this is normal flow:

```java
book.borrowBook(member);
member.borrowBook();
System.out.println("Borrow Success: " + book.getTitle());
return;
```

This is not ideal:

```java
book.borrowBook(member);
member.borrowBook();
throw new SuccesfulBorrowAttemptsException("Borrow Success: " + book.getTitle());
```

The second version makes a successful operation look like something went wrong. Anyone reading the code expects `throw` to mean that the normal flow has been interrupted because of a problem.

Exceptions are better used for cases such as:

```text
- invalid input format
- missing file
- impossible operation
- unexpected runtime failure
- invalid borrow attempt, if the design intentionally uses exceptions
```

Success does not need recovery logic. It only needs a normal state update and a message.

Using exceptions for success also makes the structure harder to maintain:

```java
try {
    // normal borrow logic
    throw new SuccesfulBorrowAttemptsException("Success");
} catch (SuccesfulBorrowAttemptsException e) {
    System.out.println(e.getMessage());
}
```

This adds extra classes, `try-catch` blocks, and control-flow jumps without solving an actual error. A simple `System.out.println()` and `return` is clearer.

The better rule is:

```text
Success → normal method flow
Expected invalid case → if + return, especially at this learning stage
Unexpected or exceptional failure → exception
```

---

## What I Learned

I learned that a method can become hard to read when it handles searching, validation, state updates, and messages all in one nested structure. Separating search logic into helper methods makes the main operation easier to understand.

I learned that early return can reduce nested `if` statements. Instead of placing the success logic deep inside several conditions, I can handle each invalid case first and return immediately. Then the final part of the method represents the successful path clearly.

I learned that returning a book requires checking ownership, not only checking whether the book is borrowed. The program must confirm that the member trying to return the book is the same member who borrowed it.

I learned that `switch-case` requires `break` to prevent fall-through unless fall-through is intentional.

I also learned that exceptions should not be used for successful results. Success is normal program flow, while exceptions are for abnormal or exceptional situations.

Next time, I should test these cases:

1. Borrow an available book with a valid member.
2. Borrow a book that is already borrowed.
3. Borrow with an invalid member ID.
4. Borrow with an invalid book ID.
5. Borrow when the member has reached the limit.
6. Return a book borrowed by the same member.
7. Return a book borrowed by another member.
8. Return a book that is not currently borrowed.
9. Enter a non-number in the menu.
10. Check whether each switch case stops correctly.

---

## Related Java Concepts

- [[13. Input, Output and Files#Scanner|Scanner]]
- [[11. Collection Framework#ArrayList|ArrayList]]
- [[8. Class part 1#Access Modifier|Encapsulation]]
- [[7. Method#Return value|Return]]
- [[12. Exception Handling|Exception Handling]]
- [[12. Exception Handling#Custom Exception|Custom Exception]]
- [[4. Conditional statement#switch-case Statement|switch-case Statement]]

