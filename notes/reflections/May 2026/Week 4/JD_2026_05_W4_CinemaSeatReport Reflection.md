

## Metadata

Drill ID: JD_2026_05_W4_CinemaSeatReport

Linked code: [View Java Code](../../../../java-drills/src/May_2026/Week_4/JD_2026_05_W4_CinemaSeatReport.java)

Class Name: CinemaSeatReport

Difficulty: Level 4 — Mini project

Estimated Time: 35 -35 minutes

Actual Time Taken: 42 minutes

Written by: Chanwoo Park

--- 

## **Goal**

Use a two-dimensional array and nested loops to print a cinema seat map, count available and reserved seats, create a row report, and find the row with the most reserved seats.

---

## **What I Tried**

Created a `String[][] seats` array to store seat status values.

Used `"O"` for available seats and `"X"` for reserved seats.

Used a nested `for` loop to process rows and columns.

Created seat labels such as `A1`, `A2`, and `B1`.

Counted total reserved seats with `totalReserved`.

Counted reserved seats in the current row with `reservedCount`.

Compared `reservedCount` with `mostReservedCount` to find the row with the most reserved seats.

Calculated available seats by subtracting reserved seats from 20.

---

## **Mistakes**

The main missing part was the row report.

The code printed `"Row Report"`, but it did not print each row’s available and reserved seat counts.

Another issue was this line.

```java
totalAvailable = 20 - totalReserved;
```

This works only because the current array has exactly 20 seats. If the seat array changes, the calculation may become incorrect.

Also, the most-reserved-row comparison was placed inside the inner loop. It works, but the logic is clearer when it is checked after one full row has been processed.

---

## **Why the Mistake Happened**

The code focused mainly on total reserved seats and the most reserved row.

However, the problem also required row-level reporting, which means each row needs its own counters.

```java
rowAvailable
rowReserved
```

The available seat count was calculated from a fixed number instead of being counted directly from the array. This reduces flexibility because the code depends on the current array size.

---

## **Improved Solution**

The improved structure should create row counters inside the outer loop.

```java
for (int i = 0; i < seats.length; i++) {
    int rowAvailable = 0;
    int rowReserved = 0;

    for (int j = 0; j < seats[i].length; j++) {
        if (seats[i][j].equals("O")) {
            rowAvailable++;
            totalAvailable++;
        } else if (seats[i][j].equals("X")) {
            rowReserved++;
            totalReserved++;
        }
    }

    System.out.println("Row " + row + " - Available: " + rowAvailable + ", Reserved: " + rowReserved);
}
```

This makes the logic more accurate and easier to read.

---

## **What I Learned**

Nested loops are useful for processing two-dimensional arrays.

The outer loop usually represents rows, and the inner loop represents columns.

Row-based calculations need counters that reset inside the outer loop.

Total counters should keep accumulating across all rows.

Avoid hardcoding values like `20` when the array size can be calculated through loops.

Logic that depends on a full row should usually be placed after the inner loop finishes.

---

## Related Java Concepts

- [[5. Loop Statement#nested loop|Nested Loop]]
- [[6. Array#Multidimensional Array|Two-Dimensional Array]]
- [[6. Array#Array list|Array]]
- [[3. String#Comparing Strings|.eqauls()]]