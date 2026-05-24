package src.May_2026.Week_3;
/*
ReadMe

Drill ID: JD_2026_05_W3_StudentScoreAnalyzer

Class Name: StudentScoreAnalyzer

Difficulty: Level 3 — Multi-Step Problem

Estimated Time: 25–30 minutes

Actual Time Taken: 32 minutes

Written by: Chanwoo Park

Problem
Analyze student scores using arrays, loops, and conditional statements.

Requirements
- Print each student's name, score, and grade.
- Calculate the total score and average score.
- Find and print the student with the highest score.

Learning Objective
Practice using arrays, for loops, if-else statements, accumulator variables, and maximum value tracking.
*/
public class JD_2026_05_W3_StudentScoreAnalyzer {
    public static void main(String args[]) {
        String[] names = {"Daniel", "Amy", "Chris", "Mina", "Jason"};
        int[] scores = {85, 92, 76, 68, 90};

        int total = 0;
        int highestScore = scores[0];
        String topStudent = names[0];
        String grade = null;

        for (int i = 0; i < names.length; i++) {
            total += scores[i];
            if (scores[i] >= 90) {
                grade = "A";
            } else if (scores[i] >= 80) {
                grade = "B";
            } else if (scores[i] >= 70) {
                grade = "C";
            } else {
                grade = "Needs Improvement";
            }
            System.out.println(names[i] + ": " + scores[i] + " - " + grade);

            if (highestScore > scores[i]) {
                highestScore = scores[i];
                topStudent = names[i];
            }
        }

        double averageScore = (double)total / (double)names.length;

        System.out.println("\nTotal Score: " + total);
        System.out.printf("Average Score: %.2f\n", averageScore);
        System.out.println("Top Student: " + topStudent);
        System.out.println("Highest Score: " + highestScore);
    }
}
