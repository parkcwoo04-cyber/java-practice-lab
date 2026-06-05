
## Metadata

Drill ID: JD_2026_06_W1_CinemaSeatPlan

Linked code:
- [View Java Code](../../../../java-drills/src/June_2026/Week_1/JD_2026_06_W1_CinemaSeatPlan/CinemaSeatPlanner.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_1/JD_2026_06_W1_CinemaSeatPlan/Theater.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_1/JD_2026_06_W1_CinemaSeatPlan/SeatRequest.java)

Difficulty: Level 2 — Multi-Class Application

Estimated Time: 45–60 minutes

Actual Time Taken: 51 minutes

Written by: Chanwoo Park

---

## Goal

The goal of this exercise was to build a small cinema seat reservation program using three classes: `CinemaSeatPlanner`, `Theater`, and `SeatRequest`. The main practice point was using a two-dimensional `String[][]` array to store seat labels, update reserved seats, validate row and column indexes, and count available seats with nested loops.

---

## What I Tried

- Created a `CinemaSeatPlanner` main class to create a `Theater` object and run the reservation scenario.
- Created a `SeatRequest` class to store customer name, row index, and column index.
- Created a `Theater` class to manage the `String[][] seats` array.
- Used a constructor in `Theater` to create the seat array and call `fillSeats()`.
- Used nested `for` loops to initialize, print, and count seats.
- Used conditionals to reject invalid seat positions and already reserved seats.
- Used `"XX"` as the state marker for reserved seats.

---

## Mistakes

The main mistake was:

```java
System.out.println(name + " failed: " + (char) (rowIndex + 1) + (colIndex + 1) + " is already reserved.");
```

instead of:

```java
String seatName = String.valueOf((char) ('A' + rowIndex)) + (colIndex + 1);
System.out.println(name + " failed: " + seatName + " is already reserved.");
```

This affected the execution output. The program compiled successfully, but when Daniel tried to reserve an already reserved seat, the output showed a broken character instead of the correct seat label:

```text
Daniel failed: 2 is already reserved.
```

The expected message should be closer to:

```text
Daniel failed: A2 is already reserved.
```

A smaller design issue is that `rows` and `cols` are declared in `Theater`, but they are never assigned or used:

```java
private int rows;
private int cols;
```

Since the actual row and column sizes are already available through `seats.length` and `seats[i].length`, these fields are unnecessary unless you plan to store and use them later.

---

## Why the Mistake Happened

The mistake happened because of confusion between numeric values and character codes.

```java
(char) (rowIndex + 1)
```

does not convert row index `0` into `'A'`. It converts the number `1` into the character with ASCII/Unicode code value `1`, which is a non-printable control character.

To create row letters, the calculation should start from the character `'A'`:

```java
(char) ('A' + rowIndex)
```

Then:

- row index `0` becomes `'A'`
- row index `1` becomes `'B'`
- row index `2` becomes `'C'`

This connects directly to the multidimensional array idea: the row index is still a number, but the displayed seat label needs a separate conversion from index to row letter.

---

## Improved Solution

Change the duplicated seat-label creation logic into a small helper method:

```java
private String getSeatName(int rowIndex, int colIndex) {
    return String.valueOf((char) ('A' + rowIndex)) + (colIndex + 1);
}
```

Then use it inside `reserveSeats()`:

```java
} else if (seats[rowIndex][colIndex].equals("XX")) {
    System.out.println(name + " failed: " + getSeatName(rowIndex, colIndex) + " is already reserved.");
}
```

This fixes the broken output and makes the logic easier to maintain. It also prevents seat-name formatting from being rewritten differently in multiple places.

---

## Evaluation

Overall, the solution is mostly correct.

The strongest parts are:

- The program uses three classes appropriately.
- `SeatRequest` has a clear responsibility.
- `Theater` correctly owns and manages the `String[][] seats` array.
- Nested loops are used correctly for initialization, printing, and counting.
- Invalid index checking is handled before accessing the array, which prevents `ArrayIndexOutOfBoundsException`.
- The final available seat count is correct.

The main issue is the incorrect character conversion in the already-reserved message. This is a runtime logic/output bug, not a compile error.

---

## What I Learned

A two-dimensional array should be accessed carefully using row and column indexes:

```java
seats[rowIndex][colIndex]
```

Before accessing that position, the indexes should be validated:

```java
rowIndex >= 0 && rowIndex < seats.length
colIndex >= 0 && colIndex < seats[rowIndex].length
```

When converting an index into a display label, the index itself is not the same as the character label. For row letters, start from `'A'` and add the row index.

Next time, after checking that the program compiles, I should also inspect suspicious output values. If a program prints a strange character, I should check whether I accidentally cast a small number into a `char`.

---

## Related Java Concepts

- [[6. Array#Multidimensional Array|Multidimensional Array]]
- [[5. Loop Statement#nested loop|Nested Loop]]
- [[6. Array|Array Index]]
- [[4. Conditional statement|Conditional Statement]]
- [[7. Method|Method]]
- [[8. Class part 1|Class]]
- [[8. Class part 1#Constructor|Constructor]]
- [[6. Array#ASCII Code|ASCII Code]]

