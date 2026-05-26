

## Metadata

Drill ID: JD_2026_05_W4_WeeklyScoreAnalyzer

Class Name: WeeklyScoreAnalyzer

Difficulty: Level 3 — Multi-Step Problem

Estimated Time: 25–30 minutes

Actual Time Taken: ______ 22 minutes

Written by: Chanwoo Park

---

## **Goal**

Use an array to store quiz scores, then calculate and print each score, the total score, average score, highest score, and final pass result.

---

## **What I Tried**

Created an `int[] scores` array to store five quiz scores.

Created `total` to accumulate all score values.

Initialized `highest` with `scores[0]` to compare scores safely.

Used a `for` loop with `scores.length` to process every array element.

Printed each day’s score inside the loop.

Added each score to `total`.

Compared each score with `highest` and updated `highest` when a larger score was found.

Calculated `average` after the loop.

Used an `if-else` statement to assign `"Pass"` or `"Review Needed"` to `result`.

---

## **Mistakes**

There were no major syntax or logic mistakes.

---

## Why the Mistake Happened

There were no major syntax or logic mistakes.

---

## Improved Solution

The logic can stay the same. A slightly cleaner structure would calculate `average` directly after the loop.

```java
int average = total / scores.length;
```

This makes the code clearer because each variable is assigned when its real value is available.

---

## **What I Learned**

Arrays can store multiple related values under one variable name.

`scores.length` makes the loop flexible and safer than writing a fixed number.

`total += scores[i]` is useful for accumulating values.

Initializing `highest` with `scores[0]` is a reliable way to start maximum-value comparison.

`if-else` can be used after calculation to classify the result.

Cleaner spacing and variable declaration timing improve readability.

---

## Related Java Concepts

- [[6. Array#Array list|Array]]
- [[5. Loop Statement#for Loop|for Loop]]
- [[6. Array#Array list|Array Index]]
- [[3. String#Measuring the Length of a String|.length()]]
- [[4. Conditional statement#if Statement|if-else Statement]]
