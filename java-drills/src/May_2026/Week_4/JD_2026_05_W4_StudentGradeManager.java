package src.May_2026.Week_4;
/*
ReadMe

Drill ID: JD_2026_05_W4_StudentGradeManager

Class Name: StudentGradeManager

Difficulty: Level 4 — Mini Project

Estimated Time: 40-50 minutes

Actual Time Taken: 41 minutes

Written by: Chanwoo Park

Problem
Create a student grade manager that analyzes scores using multiple methods.

Requirements
- Store five student scores in an int array.
- Create a method to print all scores.
- Create a method to calculate and return the total score.
- Create a method to calculate and return the average score.
- Create a method to find and return the highest score.
- Create a method to return a grade based on the average score.
- Create a method to print the final grade report.
- Use scores.length instead of writing the array size directly.

Learning Objective
Practice organizing Java programs with methods, parameters, return values, arrays, loops, and conditional logic.
*/
public class JD_2026_05_W4_StudentGradeManager {
            public static void main(String[] args) {
                int[] scores = {85, 90, 76, 88, 92};

                printReport(scores);
    }

    public static void printScores(int[] scores){
        System.out.println("Student Scores");

        for(int i = 0; i < scores.length; i++){
            System.out.println("Student " + (i + 1) + ": " + scores[i]);
        }
    }

    public static int calculateTotal(int[] scores) {
        int total = 0;

        for(int i = 0; i < scores.length; i++){
            total += scores[i];
        }
        return total;
    }

    public static int calculateAverage(int[] scores) {
        int total = calculateTotal(scores);
        int count = 0;

        for (int i = 0; i < scores.length; i++){
            count++;
        }

        int average = total / count;
        return average;
    }

    public static int findHighestScore(int[] scores) {
        int highest = scores[0];
        for(int i = 0; i < scores.length; i++){
            if(scores[i] > highest){
                highest = scores[i];
            }
        }

        return highest;
    }

    public static String getGrade(int[] scores){
        int average = calculateAverage(scores);

        if(average >= 90) {
            return "A";
        } else if(average >= 80) {
            return "B";
        } else if(average >= 70) {
            return "C";
        } else {
            return "Needs review";
        }

    }

    public static void printReport(int[] scores){
        System.out.println("Student Scores");

        for(int i = 0; i < scores.length; i++){
            System.out.println("Student " + (i + 1) + ": " + scores[i]);
        }

        System.out.println("\nGrade Report");
        System.out.println("Total Scores: " + calculateTotal(scores));
        System.out.println("Average Scores: " + calculateAverage(scores));
        System.out.println("Highest Scores: " + findHighestScore(scores));
        System.out.println("Grade: " + getGrade(scores));
    }
}
