---
github_repo: parkcwoo04-cyber|java-practice-lab
github_issue: 2
---


Class Name: DiscountCalculator

Difficulty: Level 2 — Simple Application

Estimated Time: 20 minutes

Actual Time Taken: 25 minutes

Written by: Chanwoo Park

---

## Goal

Practice using variables, arithmetic operators, comparison operators, logical operators, and conditional logic to calculate a discount.

---

## What I Tried

Created variables for product price, membership status, discount rate, discount amount, and final price.

Used a ternary operator to choose the discount rate based on the product price.

Used `System.out.printf()` to print decimal values with 2 decimal places.

---

## Mistakes

No major syntax mistakes.

The main logic mistake was that `isMember` was printed but not used when calculating the discount rate.

The discount rate only depended on `price`.

---

## Why the Mistake Happened

The condition only checked whether `price >= 100000`.

It did not include the membership condition.

Because of this, a non-member could incorrectly receive a 15% or 10% discount.

---

## Improved Solution

Use both `isMember` and `price` when deciding the discount rate.

Check whether the customer is a member first.

Then apply 15% for members with a price of 100000 or more, 10% for members below 100000, and 5% for non-members.

---

## What I Learned

A boolean variable should be used in the program logic when the requirement depends on it.

Printing a variable is not the same as using it to control the calculation.

I also learned that I should test different cases, such as member and non-member, to check whether the logic works correctly.

---

## Related Java Concepts

- [[1. Data Type and Variables#Types of Data|int]]
- [[1. Data Type and Variables#Types of Data|boolean]]
- [[1. Data Type and Variables#Types of Data|double]]
- [[2. Operator#Arithmetic Operator|arithmetic operators]]
- [[2. Operator#Comparison Operator|comparison operators]]
- [[2. Operator#Logical Operator|logical operators]]
- [[2. Operator#Ternary Operator|ternary operator]]
- [[1. Data Type and Variables#How to Print 2 Decimal Points of `double`|System.out.printf()]]
- [[3. String#Commonly Used Escape Characters|escape character]]
- 