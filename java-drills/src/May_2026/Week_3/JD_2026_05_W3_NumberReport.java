package src.May_2026.Week_3;

import java.util.Scanner;

/*
ReadMe

Drill ID: JD_2026_05_W3_NumberReport

Class Name: NumberReport

Difficulty: Level 3 — Multi-Step Problem

Estimated Time: 25–30 minutes

Actual Time Taken: 33 minutes

Written by: Chanwoo Park

Problem
Create a program that checks numbers from 1 to 30 and prints whether each number is even, odd, and/or a multiple of 5.

Requirements
- Use a for loop to check numbers from 1 to 30.
- Use the modulo operator to check even, odd, and multiples of 5.
- Count even numbers, odd numbers, and multiples of 5.
- Calculate the sum of all even numbers.
- Print a final summary after the loop.

Learning Objective
This exercise is designed to practice loop control, condition checking, modulo operations, and accumulated counting in Java.

*/

public class JD_2026_05_W3_NumberReport {
    public static  void main(String[] args){

        int evenCount = 0;
        int oddCount = 0;
        int multipleFiveCount = 0;
        int evenSum = 0;

        for (int i = 1; i <= 30; i++) { //i is from 1 to 30

            if (i % 2 == 0) { // if i is even
                evenSum += i; // sum of even numbers
                if (i % 5 == 0) { // if i is even and muliple of 5
                    System.out.println(i + " is even and multiple of 5");
                    multipleFiveCount++;
                } else {
                    System.out.println(i + " is even");
                    evenCount++;
                }
            } else { // if i is odd
                if (i % 5 == 0) {
                    System.out.println(i + " is odd and multiple of 5");
                    multipleFiveCount++;
                } else {
                    System.out.println(i + " is odd");
                    oddCount++;
                }
            }
        }

        System.out.println();
        System.out.println("Summary");
        System.out.println("Even count: " + evenCount);
        System.out.println("Odd count: " + oddCount);
        System.out.println("Multiple Five count: " + multipleFiveCount);
        System.out.println("Sum of even numbers: " + evenSum);
    }
}
