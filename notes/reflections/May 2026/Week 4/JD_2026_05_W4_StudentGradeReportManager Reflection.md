

## Metadata

Drill ID: JD_2026_05_W4_StudentGradeReportManager

Linked code: [View Java Code](../../../../java-drills/src/May_2026/Week_4/JD_2026_05_W4_StudentGradeReportManager.java)

Difficulty: Level 4 — Mini Project

Estimated Time: 40-60 minutes

Actual Time Taken: ______ 47 minutes

Written by: Chanwoo Park

---

## Goal

Create a student grade report manager that stores student names and scores in arrays, then uses methods to print student data, search students, calculate totals and averages, assign grades, determine pass/fail status, and summarize the class.

---

## What I Tried

Created arrays for student names and three subject scores.

Used `printAllStudents()` to print each student's score information.

Used `findStudentIndex()` with `equalsIgnoreCase()` to search for a student.

Created separate methods for total, average, grade, and pass/fail calculation.

Used `printStudentReport()` to print one student's full report.

Used `printClassSummary()` to calculate class average, top student, and pass/fail counts.

Used `findTopStudentIndex()` to find the student with the highest score.

---

## Mistakes

The not-found message prints `Student not found` instead of the required `Student not found.`

`printStudentReport()` prints `targetName` instead of `names[index]`, which can preserve incorrect capitalization from user input.

`printClassSummary()` searches for each student's index again using `findStudentIndex(names, names[i])`, even though the loop already has the correct index `i`.

Pass/fail counting uses `getGrade(avg).equalsIgnoreCase("F")` instead of reusing `isPassed(avg)`.

`findTopStudentIndex()` compares total scores, not average scores. In this specific problem it works because every student has three scores, but comparing averages would match the method's purpose more clearly.

---

## Why the Mistake Happened

Some methods were correctly created, but not always reused in the most direct way.

For example, `isPassed()` already contains the pass/fail rule, but the class summary recalculates pass/fail indirectly through the grade.

The class summary loop also made the logic more complex than necessary by searching for an index that was already available from the loop variable.

---

## Improved Solution

Use the existing loop index directly in `printClassSummary()`.

```java
for (int i = 0; i < names.length; i++) {
    int total = calculateTotal(korean[i], english[i], math[i]);
    classTotal += total;
}
```

Use `isPassed()` when counting passed and failed students.

```java
if (isPassed(avg)) {
    passCount++;
} else {
    failCount++;
}
```

In `printStudentReport()`, print the stored name from the array.

```java
System.out.println("Name: " + names[index]);
```

This keeps the output consistent even if the search input uses different capitalization.

---

## What I Learned

Reusing helper methods makes code easier to understand and maintain.

A loop index can often be used directly without searching again.

Output format matters when matching a problem requirement.

Method names and method behavior should match clearly.

`isPassed()` is better than checking whether the grade is `"F"` when the goal is pass/fail judgment.

---

## Related Java Concepts

- [[7. Method|Method Reuse]]
- [[6. Array|Array Index]]
- [[7. Method#Return value|Return Value]]
- [[3. String#Comparing Strings|String Comparison]]
