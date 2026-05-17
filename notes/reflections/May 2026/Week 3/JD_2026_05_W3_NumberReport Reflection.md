

## Metadata

Drill ID: JD_2026_05_W3_NumberReport

Linked code: [View Java Code](../../../../java-drills/src/May_2026/Week_3/JD_2026_05_W3_NumberReport.java)

Class Name: NumberReport

Difficulty: Level 3 — Multi-Step Problem

Estimated Time: 25 - 30 minutes

Actual Time Taken: 33 minutes

Written by: Chanwoo Park

---

## Goal

Create a Java program that checks numbers from 1 to 30 and reports whether each number is even, odd, and/or a multiple of 5. The program also counts each category and calculates the sum of even numbers.

---

## What I tried

Created variables to store `evenCount`, `oddCount`, `multipleFiveCount`, and `evenSum`.

Used a `for loop` to repeat from 1 to 30.

Used `i % 2 == 0` to check whether a number is even or odd.

Used `i % 5 == 0` to check whether a number is a multiple of 5.

Used `nested if statement` to print different messages depending on the number category.

---

## Mistakes

`evenCount++` only ran when the number was even but not a multiple of 5.

`oddCount++` only ran when the number was odd but not a multiple of 5.

Numbers like 10, 20, and 30 were counted as multiples of 5, but not counted as even numbers.

Numbers like 5, 15, and 25 were counted as multiples of 5, but not counted as odd numbers.

---

## Why the Mistake Happened

The logic treated "even or odd" and "multiple of 5" as if they were separate exclusive categories.

However, a number can belong to more that one category at the same time.

For example:

```
5 is odd and multiple of 5
10 is even and multiple of 5
```

Because the count statements were placed inside the `else` block, they were skipped when the number was also a multiple of 5.

---

## Improved Solution

The even or odd count should be updated immediately after checking whether the number is even or odd.

The multiple of 5 count should be handled separately.

```java
if (i % 2 == 0) {
    evenCount++;
    evenSum += i;

    if (i % 5 == 0) {
        multipleFiveCount++;
        System.out.println(i + " is even and multiple of 5");
    } else {
        System.out.println(i + " is even");
    }
} else {
    oddCount++;

    if (i % 5 == 0) {
        multipleFiveCount++;
        System.out.println(i + " is odd and multiple of 5");
    } else {
        System.out.println(i + " is odd");
    }
}
```

This keeps the category counting accurate while still pasting the correct message.

---

## What I Learned

A number can satisfy multiple conditions at the same time.

Counters should be placed where the condition is definitely true.

`nested if statement` can work, but the location of `++` operator is important.

When debugging counter, I should test special cases such as 5, 10, 15, 20, 25, and 30.

---

## Related Java Concepts

- [[5. Loop Statement#for Loop|for Loop]]
- [[2. Operator#Modulo Operator|Modulo Operator]]
- [[4. Conditional statement#if Statement|if Statement]]
- [[4. Conditional statement#if Statement|Nested if Statement]]
- [[2. Operator#Increment and Decrement Operator|Increment and Decrement Operator]]