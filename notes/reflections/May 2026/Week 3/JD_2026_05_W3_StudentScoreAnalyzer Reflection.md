

## Metadata

Drill ID: JD_2026_05_W3_StudentScoreAnalyzer

Linked code: [View Java Code](../../../../java-drills/src/May_2026/Week_3/JD_2026_05_W3_StudentScoreAnalyzer.java)

Class Name: NumberReport

Difficulty: Level 3 — Multi-Step Problem

Estimated Time: 25 - 30 minutes

Actual Time Taken: 33 minutes

Written by: Chanwoo Park

---

## Goal


The goal of this exercise was to analyze student scores using `array`, `loop`, and `conditional statement`. The program needed to print each student's score and grade, calculate the total and average score, and find the student with the highest score.

---

## What I Tried

Created two arrays: one for student names and one for scores.

Used a `for` loop to access each student by index. 

Added each score to the total, used an `if-else if-else` statement to decide the grade, and printed each student's result.

Created `highestScore` and `topStudent` variables to track the highest score and the student who received it.

---

## Mistakes

The main logic mistake was in the condition used to update the highest score.

```java
if(highestScore > scores[i]) {
	highestScore = scores[i];
	topStudent = names[i];
}
```

The condition checks whether the current score is lower than the current highest score. As a result, the program updates `highestScore` when it finds a lower score, which makes the program find the lowest score instead of the highest score.

---

## Why the Mistake Happened

The mistake happened because the comparison direction was reversed. To find the highest score, the program should compare whether the current score is greater than the stored highest score.

The variable name `highestScore` means it should only be updated when a larger value is found.

---

## Improved Solution

The condition should be changed

```java
if(scores[i] > highestScore) {
	highestScore = scores[i];
	topStudent = names[i];
}
```

This correctly updates the highest score only when the current student's score is higher than the previous highest score.

---

## What I Learned

When finding a maximum value, the comparison must check whether the current value is greater than the stored maximum value.

Should test the logic step by step using the first few values in the array to confirm whether the variable is being updated correctly.

---

## Related Java Concepts

- [[6. Array#Array list|array]]
- [[5. Loop Statement#for Loop|for Loop]]
- [[4. Conditional statement#if-else Statement|if-else Statement]]
- [[2. Operator#Comparison Operator|Comparison Operator]]