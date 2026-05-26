

## Metadata

Drill ID: JD_2026_05_W4_CafeOrderCalculator

Linked code: [View Java Code](../../../../java-drills/src/May_2026/Week_4/JD_2026_05_W4_CafeOrderCalculator.java)

Class Name: CafeOrderCalculator

Difficulty: Level 2 - Simple Application

Estimated Time: 20 minutes

Actual Time Taken: 22 minutes

Written by: Chanwoo Park

---

## Goal

Create a simple cafe order calculator that applies a 10% membership discount when the customer has membership.

---

## What I tried

Created `drinkName` to store the selected drink.

Created `price` to store the original price

Created `membership` to store whether the customer has membership.

Created `finalPrice` to store whether the customer has membership.

Used an `if-else statement` to check whether `membership` is yes.

Used `equalsIgnoreCase()` to compare the value of `membership`.

Calculated the discounted price using `price * 9 / 10`.

---

## Mistakes

There were no major syntax or logic mistakes

One minor style point is that the main method is usually written as

```java
public static void main(String[] args)
```

instead of 

```java
public static void main(String args[])
```

Both can work, but `String[] args` is the more common style.

---

## Why the Mistake Happened

This was not functional mistake. It is mainly a Java style convention issue.

Java allows both array declaration styles, but placing `[]` next to the type makes it clearer that `args` is a `String array`.

---

## Improved Solution

The current logic is correct. A slightly cleaner version would be use the more common main method format:

```java
public static void main(String[] args)
```

The discount calculation can remain 

```java
finalPrice = price * 9 / 10;
```

This avoids unnecessary decimal conversion and works well for this integer-based price problem.

---

## What I Learned

`equalsIgnoreCase()` is appropriate for comparing `String` values when letter case should not matter.

`if-else` can be used to choose between discounted and original prices.

Integer arithmetic can be used for percentage discounts when the calculation is simple.

Java code can work correctly even if there are small style differences, but standard formatting improves readability.

---

## Related Java Concepts

- [[3. String#Comparing Strings|String Comparison]]
- [[4. Conditional statement#if-else Statement|if-else Statement]]
- [[2. Operator#Arithmetic Operator|Arithmetic Operator]]
- [[1. Data Type and Variables#Types of Data|Variable]]