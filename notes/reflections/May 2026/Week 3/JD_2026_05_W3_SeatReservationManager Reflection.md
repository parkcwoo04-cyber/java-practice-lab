

## Metadata

Drill ID: JD_2026_05_W3_SeatReservationManager

Linked code: [View Java Code](../../../../java-drills/src/May_2026/Week_3/JD_2026_05_W3_SeatReservationManager.java)

Class Name: SeatReservationManager

Difficulty: Level 4 — Mini Project

Estimated Time: 35 - 45 minutes

Actual Time Take: 47 minutes

Written by: Chanwoo Park

---

## Goal

The goal of this exercise was to create a simple seat reservation system using a two-dimensional array. The program needed to create seat labels, check whether a target seat exists, check whether it was already reserved, and print an updated seat layout.

---

## What I Tried

Created a two-dimensional `String` array named `seats` to store seat labels from `A1` to `D5`.

Used nested `for` loops to fill the array and print the initial seat layout. I used a `char` variable to create row letters and `j + 1` to create seat numbers.

Used a `reservedSeats` array to store already reserved seats and checked whether `targetSeat` matched one of those values. 

Used nested loops again to print `[X]` for reserved seats for the updated seat layout.

---

## Mistakes

The main logic mistake was that `alreadyReserved` was set to `true`, but it was not used correctly to control the reservation result.

Because of this, the program could print both
```
Seat already reserved
Reservation completed
```
when the target seat was already reserved

Another issue was that the target seat was changed to `[X]` in the updated layout without checking whether the reservation was actually completed.

---

## Why the Mistake Happened

The mistake happened because the program checked whether the seat was already reserved, but the final reservation message was not placed inside a separate `if-else` structure.

The boolean variable `alreadyReserved` stored useful information, but the program did not use that value to prevent `"Reservation completed"` from the being printed.

This is a common control-flow issue when using boolean flags.

---

## Improved Solution

The program should first check whether the target seat exists and whether it is already reserved.

Then it should decide the result using a clear condition order:

```java
if (!seatExists) {
    System.out.println("Invalid seat");
} else if (alreadyReserved) {
    System.out.println("Seat already reserved");
} else {
    System.out.println("Reservation completed");
}
```

When printing the updated seats, the target seat should be marked as `[X]` only if the reservation was completed successfully.

A condition like this is safer:

```java
if (seatExists && !alreadyReserved && seats[i][j].equals(targetSeat)) {
    seats[i][j] = "[X]";
}
```

--- 

## What I Learned

Setting a boolean variable is not enough. The boolean value must be used to control the program's logic.

Conditions should be separated clearly when a program has multiple possible states, such as invalid seat, already reserved seat, and successful reservation.

Should test in different environments to confirm that each condition works correctly.

---

## Related Java Concepts

- [[6. Array#Multidimensional Array|Two-Dimensional Array]]
- [[5. Loop Statement#for Loop|Nested For Loops]]
- [[3. String#Comparing Strings#Verifying Whether Two Strings Are Identical|String.equals()]]
- [[1. Data Type and Variables#Types of Data|boolean]]
- [[4. Conditional statement#if-else Statement|if-else Statement]]
