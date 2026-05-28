package src.May_2026.Week_4;
/*
ReadMe

Drill ID: JD_2026_05_W4_StudentGradeReportManager

Class Name: StudentGradeReportManager

Difficulty: Level 4 — Mini Project

Estimated Time: 40-60 minutes

Actual Time Taken: 47 minutes

Written by: Chanwoo Park

Problem
Create a student grade report manager using arrays and methods.

Requirements
- Store student names and scores in arrays.
- Print all student score data.
- Search for a student by name using equalsIgnoreCase().
- Calculate total score, average score, grade, and pass/fail result.
- Print one student's report.
- Print the class summary including class average, top student, passed count, and failed count.

Learning Objective
Practice organizing a larger Java program using methods, parameters, return values, arrays, loops, conditional statements, and String comparison.
*/
public class JD_2026_05_W4_StudentGradeReportManager {
    public static void main(String[] args) {
        String[] names = {"Daniel", "Mina", "Alex", "Jisoo", "Brian", "Clara"};
        int[] korean = {88, 95, 72, 59, 83, 91};
        int[] english = {92, 89, 68, 61, 77, 94};
        int[] math = {79, 93, 74, 58, 81, 90};

        System.out.println("===== Student List =====");
        printAllStudents(names, korean, english, math);
        System.out.println();

        printStudentReport(names, korean, english, math, "Mina");
        System.out.println();

        printStudentReport(names, korean, english, math, "Kevin");
        System.out.println();

        printClassSummary(names, korean, english, math);
    }

    public static void printAllStudents(String[] name, int[] korean, int[] english, int[] math) {
        for (int i = 0; i < name.length; i++) {
            System.out.println((i+1) + ". " + name[i] + " | Korean: " + korean[i] + " | English: " + english[i] +
                    " | Math: " + math[i]);
        }
    }

    public static int findStudentIndex(String[] names, String targetName) {
        for (int i = 0; i < names.length; i++) {
            if (names[i].equalsIgnoreCase(targetName)) {
                int index = i;
                return index;
            }
        }
        return -1;
    }

    public static int calculateTotal(int korean, int english, int math) {
        int total = korean + english + math;
        return total;
    }

    public static double calculateAverage(int total) {
        double average = (double)total / 3.0;
        return average;
    }

    public static String getGrade(double average) {
        if (average >= 90) {
            return "A";
        } else if (average >= 80) {
            return "B";
        } else if (average >= 70) {
            return "C";
        } else if (average >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    public static boolean isPassed(double average) {
        if (average >= 60) {
            return true;
        } else {
            return false;
        }
    }

    public static void printStudentReport(String[] names, int[] korean, int[] english, int[] math, String targetName) {
        int index = findStudentIndex(names, targetName);

        if (index == (-1)) {
            System.out.println("Student not found");
        } else {
            int total = calculateTotal(korean[index], english[index], math[index]);
            double average = calculateAverage(total);

            System.out.println("===== Student Report =====");
            System.out.println("Name: " + targetName);
            System.out.println("Korean: " + korean[index]);
            System.out.println("English: " + english[index]);
            System.out.println("Math: " + math[index]);
            System.out.println("Total: " + total);
            System.out.printf("%s%.2f\n", "Average: ", average);
            System.out.println("Grade: " + getGrade(average));
            if (isPassed(average)) {
                System.out.println("Result: Passed");
            } else {
                System.out.println("Result: Failed");
            }


        }
    }

    public static void printClassSummary(String[] names, int[] korean, int[] english, int[] math) {
        int classTotal = 0;
        int count = 0;

        for (int i = 0; i < names.length; i++) {
            int index = findStudentIndex(names, names[i]);
            int total = calculateTotal(korean[index], english[index], math[index]);
            classTotal += total;
            count++;
        }

        double average = (double)classTotal/((double)count*3.0);

        System.out.println("===== Class Summary =====");
        System.out.printf("%s%.2f\n", "Class Average: ", average);

        int topStudentIndex = findTopStudentIndex(names, korean, english, math);
        String topStudent = names[topStudentIndex];
        int topTotal = calculateTotal(korean[topStudentIndex], english[topStudentIndex], math[topStudentIndex]);
        double topAverage = calculateAverage(topTotal);

        System.out.println("Top Student: " + topStudent);
        System.out.printf("%s%.2f\n", "Top Average: ", topAverage);

        int passCount = 0;
        int failCount = 0;

        for (int i = 0; i < names.length; i++) {
            int total = calculateTotal(korean[i], english[i], math[i]);
            double avg = calculateAverage(total);

            if (getGrade(avg).equalsIgnoreCase("F")) {
                failCount++;
            } else {
                passCount++;
            }
        }

        System.out.println("Passed Students: " + passCount);
        System.out.println("Failed Students: " + failCount);

        passCount = 0;
        failCount = 0;
    }

    public static int findTopStudentIndex(String[] names, int[] korean, int[] english, int[] math) {
        int topStudentIndex = 0;
        for (int i = 0; i < names.length; i++) {
            int total = calculateTotal(korean[i], english[i], math[i]);
            int topStudentTotal = calculateTotal(korean[topStudentIndex], english[topStudentIndex], math[topStudentIndex]);
            if (total > topStudentTotal) {
                topStudentIndex = i;
            }
        }
        return topStudentIndex;
    }
}
