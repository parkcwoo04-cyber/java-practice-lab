

## Metadata

Drill ID: JD_2026_06_W3_LibraryBorrowingSystem

Linked code:
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W3_LibraryBorrowingSystem/LibraryBorrowingApp.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W3_LibraryBorrowingSystem/LibraryManager.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W3_LibraryBorrowingSystem/LibraryItem.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W3_LibraryBorrowingSystem/Book.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W3_LibraryBorrowingSystem/Magazine.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W3_LibraryBorrowingSystem/Borrowable.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W3_LibraryBorrowingSystem/LibraryMember.java)

Difficulty: Level 3 — Object-Oriented Problem Solving

Estimated Time: 70–90 minutes

Actual Time Taken: 71 minutes

Written by: Chanwoo Park

---

## Goal

The goal of this exercise was to build a small library borrowing system using inheritance, interface implementation, `ArrayList`, object search logic, state updates, and exception handling. The main classes were `LibraryItem`, `Book`, `Magazine`, `Borrowable`, `LibraryMember`, `LibraryManager`, and `LibraryBorrowingApp`.

The program needed to store different library items in one collection, distinguish borrowable and non-borrowable items with `instanceof`, cast borrowable items safely, and handle invalid actions using exceptions.

---

## What I Tried

- Created `LibraryItem` as the parent class for shared item data.
- Created `Book` as a child class that implements `Borrowable`.
- Created `Magazine` as a child class that does not implement `Borrowable`.
- Used `ArrayList<LibraryItem>` to store both `Book` and `Magazine` objects.
- Used `ArrayList<LibraryMember>` to store members.
- Used `instanceof` in `LibraryManager` to check whether an item can be borrowed.
- Cast a `LibraryItem` reference to `Borrowable` before calling `borrowItem()`.
- Used exceptions to handle unavailable books, non-borrowable magazines, and missing objects.
- Tested successful and invalid borrow/return cases in the main application.

---

## Mistakes

The main mistake was that the responsibility for updating borrowing state was split incorrectly between `LibraryManager` and `Book`.

The risky structure was:

```java
member.increaseBorrowedCount();
borrowable.borrowItem(member);
```

and:

```java
member.decreaseBorrowedCount();
borrowable.returnItem();
```

This is dangerous because `borrowable.borrowItem(member)` or `borrowable.returnItem()` can throw an exception. If the member count is changed before the actual borrow or return succeeds, the program state can become inconsistent.

For example, if a book is already borrowed, this flow can increase the member's borrowed count even though the book was not actually borrowed.

Another important issue was that `Book` did not store the current borrower.

The missing field was:

```java
private LibraryMember borrower;
```

Without this reference, the book does not know who borrowed it. This makes return logic weak because a different member could be passed into the manager and affect the wrong member's borrowed count.

---

## Why the Mistake Happened

The mistake happened because the roles of `LibraryManager`, `Book`, and `LibraryMember` were not fully separated.

`LibraryManager` should manage the collection-level workflow:

- find the item
- find the member
- check whether the item is `Borrowable`
- cast the item to `Borrowable`
- request the borrow or return operation

`Book` should manage its own borrowing state:

- check whether it is available
- store the current borrower
- change availability
- update the borrower's count only after the borrow or return is valid

`LibraryMember` should manage the numeric borrowed count:

- increase count
- decrease count
- prevent invalid count changes

The issue was not a syntax problem. It was a design and state-management problem. The code could compile and run, but exceptions could leave the objects in an incorrect state.

---

## Improved Solution

### 1. Store the borrower inside `Book`

Change `Book` to remember the member who borrowed it:

```java
public class Book extends LibraryItem implements Borrowable {
    private String author;
    private LibraryMember borrower;

    public Book(String itemId, String title, boolean available, String author) {
        super(itemId, title, available);
        this.author = author;
        this.borrower = null;
    }
}
```

This allows the `Book` object to know who currently has it.

---

### 2. Move successful borrow state updates into `Book.borrowItem()`

Improved structure:

```java
@Override
public void borrowItem(LibraryMember member) {
    if (!isAvailable()) {
        throw new IllegalStateException("Error: " + getTitle() + " is already borrowed.");
    }

    member.increaseBorrowedCount();
    borrower = member;
    setAvailable(false);

    System.out.println("Borrow success: " + getTitle() + " borrowed by " + member.getName() + ".");
}
```

This fixes the state update problem because the member count only changes after the book confirms that borrowing is allowed.

---

### 3. Move return state updates into `Book.returnItem()`

Improved structure:

```java
@Override
public void returnItem() {
    if (isAvailable()) {
        throw new IllegalStateException("Error: " + getTitle() + " is already available.");
    }

    borrower.decreaseBorrowedCount();
    borrower = null;
    setAvailable(true);

    System.out.println("Return success: " + getTitle() + " is now available.");
}
```

This is better because the book uses its own stored `borrower` reference. The manager no longer needs to guess which member should have their count decreased.

---

### 4. Keep `LibraryManager` focused on search, checking, casting, and delegation

Improved `borrowItem()` structure:

```java
public void borrowItem(String itemId, String memberId) {
    try {
        LibraryItem item = findItemById(itemId);
        LibraryMember member = findMemberById(memberId);

        if (!(item instanceof Borrowable)) {
            throw new IllegalStateException("Error: " + item.getTitle() + " cannot be borrowed.");
        }

        Borrowable borrowable = (Borrowable) item;
        borrowable.borrowItem(member);

    } catch (IllegalArgumentException | IllegalStateException e) {
        System.out.println(e.getMessage());
    }
}
```

The manager does not directly change `available`, `borrower`, or `borrowedCount`. It finds the correct objects and delegates the actual action to the object that owns the state.

---

### 5. Make search methods return objects

Instead of only printing search results, search methods should return the found object.

```java
public LibraryItem findItemById(String itemId) {
    for (LibraryItem item : items) {
        if (item.getItemId().equalsIgnoreCase(itemId)) {
            return item;
        }
    }

    throw new IllegalArgumentException("Error: Item ID " + itemId + " was not found.");
}
```

```java
public LibraryMember findMemberById(String memberId) {
    for (LibraryMember member : members) {
        if (member.getMemberId().equalsIgnoreCase(memberId)) {
            return member;
        }
    }

    throw new IllegalArgumentException("Error: Member ID " + memberId + " was not found.");
}
```

Returning objects makes these methods reusable inside `borrowItem()` and `returnItem()`.

---

### 6. Prevent negative borrowed count

The original `decreaseBorrowedCount()` could reduce the count below zero.

Improved version:

```java
public void decreaseBorrowedCount() {
    if (borrowedCount <= 0) {
        throw new IllegalStateException("Error: borrowed count cannot be negative.");
    }

    borrowedCount--;
}
```

This prevents invalid member state.

---

### 7. Use `private` fields for encapsulation

Fields should be protected from direct outside modification.

```java
private String memberId;
private String name;
private int borrowedCount;
```

```java
private ArrayList<LibraryItem> items;
private ArrayList<LibraryMember> members;
```

This makes the design safer because object state must be changed through methods, not direct field access.

---

## What I Learned

The main lesson is that successful compilation does not mean the object design is correct. In this exercise, the code could run, but state updates could happen in the wrong order when exceptions occurred.

I learned that `LibraryManager` should not directly control every detail of borrowing. Its job is to find objects, check types with `instanceof`, cast to `Borrowable`, and call the correct method. The `Book` object should control its own borrowing state because it owns the `available` state and the `borrower` reference.

I also learned that exception handling is not only about preventing the program from stopping. It also protects object state from becoming inconsistent. State changes should usually happen only after validation succeeds.

Next time, I should check:

1. Which class owns the data being changed?
2. Could an exception happen after part of the state has already changed?
3. Does a method return the object needed by another method, or does it only print?
4. Can a count, index, or state become invalid after repeated operations?

---

## Related Java Concepts

- [[8. Class part 1|Class]]
- [[9. Class part 2#Inheritance|Inheritance]]
- [[10. Abstraction and Interface#Interface|Interface]]
- [[9. Class part 2#Polymorphism|Polymorphism]]
- [[9. Class part 2#Reference Type|Reference Type]]
- [[11. Collection Framework#ArrayList|ArrayList]]
- [[12. Exception Handling|Exception Handling]]
