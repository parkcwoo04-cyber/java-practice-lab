

## Metadata

Drill ID: JD_2026_05_W4_StudentGradeManager

Linked code: [View Java Code](../../../../java-drills/src/May_2026/Week_4/JD_2026_05_W4_StudentGradeManager.java)

Class Name: StudentGradeManager

Difficulty: Level 4 — Mini Project

Estimated Time: 40–50 minutes

Actual Time Taken: ______ 41 minutes

Written by: Chanwoo Park

---

## **Goal**

Create a student grade manager that uses multiple methods to print scores, calculate the total score, calculate the average score, find the highest score, determine the grade, and print the final report.

---

## **What I Tried**

Created an `int[] scores` array to store five student scores.

Created `printScores()` to print each student’s score.

Created `calculateTotal()` to calculate the total score.

Created `calculateAverage()` to calculate the average score.

Created `findHighestScore()` to find the highest score.

Created `getGrade()` to return a grade based on the average score.

Created `printReport()` to print the final grade report.

Used `scores.length` in loops to avoid hardcoding the array size.

---

## **Mistakes**

The main issue was method responsibility.

`printScores()` was created but not used. Instead, the same score-printing logic was repeated inside `printReport()`.

Another issue was that `calculateAverage()` recalculated the total score even though `calculateTotal()` already existed.

`getGrade()` also called `calculateAverage(scores)` multiple times, which caused repeated calculation.

The output labels also differed slightly from the required format.

---

## **Why the Mistake Happened**

The program focused on producing the correct final output, but the method design became less organized.

Some methods were written correctly, but the data flow between methods was not fully separated.

For example, `getGrade()` received the whole array even though it only needed the average. This made the method depend on more information than necessary.

---

## **Improved Solution**

The improved approach is to let each method do one clear job.

```java
int total = calculateTotal(scores);
int average = calculateAverage(total, scores.length);
int highest = findHighestScore(scores);
String grade = getGrade(average);
```

Then `printRepeort()` should receive already calculated values.

```java
printReport(total, average, highest, grade)
```

This makes the program easier to read, test, and modify.

---

## What I Learned

A method should usually have one clear responsibility.

Creating a method is not enough, it should be used in the correct place.

If a value has already been calculated, avoid recalculating it in another method.

A method should receive only the data it actually needs.

`getGrade()` should receive `average`, not the full `scores` array.

`printReport()` should focus on printing, not recalculating.

---

## Related Java Concepts

- [[7. Method#Method|Method Definition]]
- [[7. Method#Definition and call|Method Call]]
- [[7. Method#Argument and parameter|Parameter]]
- [[7. Method#Return value|Return Value]]
- [[6. Array#Array list|Array]]
- [[5. Loop Statement#for Loop|for Loop]]