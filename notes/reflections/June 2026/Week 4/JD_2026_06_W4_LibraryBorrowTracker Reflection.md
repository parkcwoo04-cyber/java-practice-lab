

## Metadata

Drill ID: JD_2026_06_W4_LibraryBorrowTracker

Linked code:
- [View Java Code](../java-drills/src/LibraryBorrowTracker/LibraryMain.java)
- [View Java Code](../java-drills/src/LibraryBorrowTracker/Book.java)
- [View Java Code](../java-drills/src/LibraryBorrowTracker/LibraryService.java)
- [View Java Code](../java-drills/src/LibraryBorrowTracker/Available.java)

Difficulty: Level 2 — Multi-Class Application

Estimated Time: 45–60 minutes

Actual Time Taken: 48 minutes

Written by: Chanwoo Park

---

## Goal

The goal of this program was to build a small library borrowing system using multiple classes. `Book` represents one book, `Available` represents the book's availability state, `LibraryService` manages the book list and borrowing logic, and `LibraryMain` controls the menu flow. The main practice goal was to improve object responsibility by making the service class manage the collection instead of letting the main class control the data directly.

---

## What I Tried

- Created a `Book` class to store one book's title, author, and availability state.
- Used an `Available` enum instead of a simple boolean to make the book status more readable.
- Created a `LibraryService` class with methods for adding books, showing all books, searching books, borrowing books, returning books, and showing statistics.
- Used an `ArrayList<Book>` to store multiple book objects.
- Used loops to search through books and print numbered results.
- Used `Scanner`, `while`, and `switch` in `LibraryMain` to create a menu-based console program.
- Improved the previous `count` mistake by declaring the counter outside the loop and increasing it during each iteration.

---

## Mistakes

The main mistake was that `LibraryMain` still created and initialized the `ArrayList<Book>` directly.

```java
ArrayList<Book> books = new ArrayList<Book>();
LibraryService service = new LibraryService(books);

books.add(new Book("Java Basics", "Robert Martin"));
books.add(new Book("Spring MVC", "James Cameron"));
books.add(new Book("Spring Boot Basics", "Carl Anderson"));
```

instead of allowing `LibraryService` to fully own the list:

```java
LibraryService service = new LibraryService();
```

and creating the list inside `LibraryService`:

```java
public class LibraryService {
    private ArrayList<Book> books;

    public LibraryService() {
        books = new ArrayList<>();
        books.add(new Book("Java Basics", "Robert Martin"));
        books.add(new Book("Spring MVC", "James Cameron"));
        books.add(new Book("Spring Boot Basics", "Carl Anderson"));
    }
}
```

This did not completely break the program because the same list reference was passed into `LibraryService`. However, it still weakened the design. The assignment's main rule was that `LibraryMain` should not create or directly manage the book list. The book collection should belong to `LibraryService`.

A smaller issue was that search and selection logic could still be stronger. For example, if the user enters a number outside the valid search result range, the program may try to access an invalid index.

```java
return searchedBooks.get(choice - 1);
```

This can cause an `IndexOutOfBoundsException` if the input is too small or too large.

---

## Why the Mistake Happened

The mistake happened because I improved the previous data-flow problem, but I did not fully apply the responsibility rule.

Compared with the previous `StudyLogManager`, this version was better because `LibraryService` handled most of the list operations. However, I still let `LibraryMain` create the list and add the starter books. That means `Main` was still partly responsible for the program's data setup.

The design problem is not mainly about whether the code runs. The problem is about ownership. If `LibraryService` is responsible for managing books, then it should also be responsible for creating and storing the `ArrayList<Book>`. `LibraryMain` should only coordinate user choices and call service methods.

The search result counter was improved correctly. This time, the counter was declared before the loop:

```java
int i = 1;

for (Book book : searchedBooks) {
    System.out.print(i + ". ");
    book.printBookInfo();
    i++;
}
```

This fixed the earlier scope issue where the counter reset to `1` during every loop iteration.

---

## Improved Solution

Move all list creation and initial book setup into `LibraryService`.

```java
public class LibraryService {
    private ArrayList<Book> books;

    public LibraryService() {
        books = new ArrayList<>();
        addDefaultBooks();
    }

    private void addDefaultBooks() {
        books.add(new Book("Java Basics", "Robert Martin"));
        books.add(new Book("Spring MVC", "James Cameron"));
        books.add(new Book("Spring Boot Basics", "Carl Anderson"));
    }
}
```

Then simplify `LibraryMain`:

```java
public class LibraryMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LibraryService service = new LibraryService();

        // menu loop only
    }
}
```

This makes the responsibility clearer:

- `LibraryMain`: menu flow and user choice
- `LibraryService`: book collection and library actions
- `Book`: one book's data and state changes
- `Available`: possible availability states

The selection logic should also check the user's input before accessing the list.

```java
if (choice < 1 || choice > searchedBooks.size()) {
    System.out.println("Invalid choice.");
    return null;
}

return searchedBooks.get(choice - 1);
```

This prevents the program from crashing when the user enters an invalid number.

---

## What I Learned

I learned that code can be functionally correct but still have a design weakness.

In this project, most features worked: adding books, searching books, borrowing books, returning books, and showing statistics. I also fixed the previous `count` scope problem. However, the main design goal was stricter than just making the program run. The service class was supposed to own the collection completely.

The biggest lesson is:

`Main` should not manage application data directly when a service class exists for that purpose.

Next time, when I create a service class, I should ask:

1. Does the service own the collection?
2. Is `Main` directly creating or modifying the collection?
3. Is there only one clear place responsible for data management?
4. Are user input edge cases checked before using an index?

This version was close, but not perfectly clean. That is useful because the remaining mistake shows exactly what to fix in the next design.

---

## Related Java Concepts

- [[8. Class part 1#Class|Class]]
- [[11. Collection Framework#ArrayList|ArrayList]]
- [[9. Class part 2#Enum|Enum]]
- [[5. Loop Statement|Loop Statement]]
- [[8. Class part 1#Access Modifier|Encapsulation]]
