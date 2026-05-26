package src.May_2026.Week_4;
/*
ReadMe

Drill ID: JD_2026_05_W4_WeeklyScoreAnalyzer

Class Name: WeeklyScoreAnalyzer

Difficulty: Level 3 — Multi-Step Problem

Estimated Time: 25-30 minutes

Actual Time Taken: 22 minutes

Written by: Chanwoo Park

Problem
Analyze five daily quiz scores using an array and calculate the total, average, highest score, and pass result.

Requirements
- Create an int array named scores with five quiz scores.
- Use a for loop to print each score.
- Calculate and print the total score.
- Calculate and print the average score.
- Find and print the highest score.
- Print "Pass" if the average is 70 or higher.
- Print "Review Needed" if the average is lower than 70.
- Use scores.length instead of writing the array size directly.

Learning Objective
Practice using arrays with loops, accumulating values, finding a maximum value, and applying conditional logic.
*/
public class JD_2026_05_W4_WeeklyScoreAnalyzer {
    public static void main(String[] args) {
        int[] scores = {80, 65, 90, 75, 60};

        int total = 0;
        int highest = scores[0];
        int average = 0;
        String result = "";

        for(int i = 0; i < scores.length; i++){
            System.out.println("Day " + (i+1) + " Score: " + scores[i]);
            total += scores[i];
            if(scores[i] > highest){
                highest = scores[i];
            }
        }

        average = total / scores.length;
        if (average >= 70) {
            result = "Pass";
        } else {
            result = "Review Needed";
        }

        System.out.println("Total Score: " + total);
        System.out.println("Average Score: " + average);
        System.out.println("Highest Score: " + highest);
        System.out.println("Result: " + result);
    }
}
