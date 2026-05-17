

## Metadata

Drill ID: JD_2026_05_W3_WeeklySalesTracker

Linked code: [View Java Code](../../../../java-drills/src/May_2026/Week_3/JD_2026_05_W3_WeeklySalesTracker.java)

Difficulty: Level 4 — Mini project

Estimated Time: 35 -35 minutes

Actual Time Taken: 55 minutes

Written by: Chanwoo Park

---

## Goal

Create a weekly sales tracking program that process sales values for 7 days, classifies each day's sales result, and prints a weekly summary.

---

## What I tried

Created variables for `day`, `salesAmount`, and `salesResult`.

Used `high`, `normal`, and `low` counters to count each sales category.

Used `totalSales` to accumulate the total sales amount.

Used `highestSales` and `lowestSales` to track the maximum and minimum sales values.

Used `for loop` to process days from 1 to 7.

Used type casting to calculate the average as a `double`.

---

## Mistakes

`lowestSales` was initialized with a very large fixed number:

```java
int lowestSales = 10000000;
```

This works for the current data, but it is not the safest general approach.

The `else` block for `"Invalid day"` is unnecessary because the loop only runs from day 1 to day 7.

The average output used `\n` inside the next `println()`, which works but is less clean than using '%n' in `printf()`

---

## Why the Mistake Happened

The program needed an initial value for `lowestSales`, so a very large number was used as a temporary starting point.

This works only because all sales values are smaller than 10000000. However, this approach depends on guessing a safe number.

A more reliable approach is to initialize `highestsales` and `lowestSales` using the first actual sales value.

---

## Improved Solution

The highest and lowest values should be initialized using the first day's sales amount.

A cleaner structure would be: 

```java
if (day == 1) {
    highestSales = salesAmount;
    lowestSales = salesAmount;
} else {
    if (salesAmount > highestSales) {
        highestSales = salesAmount;
    }

    if (salesAmount < lowestSales) {
        lowestSales = salesAmount;
    }
}
```

The average output can also be simplified:

```java
System.out.printf("Average sales: %.2f%n", averageSales);
```

This prints the average with two decimal places and moves to the next line clearly.

---

## What I learned

For a variable like `lowestSales`, it is safer to initialize it with the first actual data value instead of using an arbitrarily large number.

An arbitrary initial value may work with the current data, but it can cause unexpected if the data range changes.

If the `for loop` is already limited from 1 to 7, an `else` block for `Invalid days` may not be necessary inside the loop.

Output formatting should not only produce the correct result, but also manage line breaks clearly by using `printf()` and `%n`

---

## Related Java Concepts

- [[5. Loop Statement#for Loop|for Loop]]
- [[4. Conditional statement#if Statement|if Statement]]
- [[4. Conditional statement#else if Statement|else if Statement]]
- [[2. Operator#Increment and Decrement Operator|Increment and Decrement Operator]]
- [[1. Data Type and Variables#Type Casting|Type Casting]]
- [[1. Data Type and Variables#How to Print 2 Decimal Points of `double`|printf Formatting]]
