

## Metadata

Drill ID: JD_2026_05_W4_LibraryBookManager

Linked code: [View Java Code](../../../../java-drills/src/May_2026/Week_4/JD_2026_05_W4_LibraryBookManager.java)

Difficulty: Level 4 — Mini Project

Estimated Time: 30-40 minutes

Actual Time Taken: 28 minutes  

Written by: Chanwoo Park

---

## Goal

Create a simple library book management program that stores book titles and borrowing status in arrays, then uses methods to print, search, borrow, and return books.

---  

## What I Tried

Created a `String[] books` array to store book titles.

Created a `boolean[] borrowed` array to store each book's borrowing status.

Used `printBooks()` to display all books and their availability.

Used `findBookIndex()` to search for a book title and return its index.

Used `borrowBook()` and `returnBook()` to update the borrowing status.

Used conditional statements to handle found books, missing books, already borrowed books, and books that were not borrowed.

---

## Mistakes

`findBookIndex()` used `books[i].equals(title)` instead of `equalsIgnoreCase()`.

The `index` variable inside `findBookIndex()` was updated to `-1` inside the loop whenever the current book did not match.

The current logic still works for many cases, but it is more complicated than necessary and can make the search logic harder to understand.

---  

## Why the Mistake Happened

The search method mixed two responsibilities: checking each book and storing the final failure result.

A cleaner approach is to return immediately when a match is found, and only return `-1` after the entire loop finishes.

The use of `equals()` also means the search is case-sensitive. For example, `"clean code"` would not match `"Clean Code"`, even though they should be treated as the same title in this exercise.

---

## Improved Solution

The search method should compare titles using `equalsIgnoreCase()` and return `-1` only after checking all books.


```java
public static int findBookIndex(String[] books, String title) {
	for (int i = 0; i < books.length; i++) {
		if (books[i].equalsIgnoreCase(title)) {
		return i;
		}
	}
return -1;
}
```

This version is shorter, clearer, and directly matches the requirement.

---

## What I Learned

A method should usually return as soon as the correct result is found.

`return -1` is useful for representing “not found” after a full search.

`equalsIgnoreCase()` should be used when String comparison should ignore uppercase and lowercase differences.

Separating logic into methods makes the program easier to read and maintain.

---

  

## Related Java Concepts

  - [[7. Method#Return value|Return Value]]
  - [[6. Array|Array]]
  - [[3. String#Comparing Strings|String Comparison]]
  - [[4. Conditional statement#if Statement|if Statement]]