package src.May_2026.Week_4;
/*
ReadMe

Drill ID: JD_2026_05_W4_CafeOrderCalculator

Class Name: CafeOrderCalculator

Difficulty: Level 2 — Simple Application

Estimated Time: 20 minutes

Actual Time Taken: 22 minutes

Written by: Chanwoo Park

Problem
Create a simple cafe order calculator that applies a membership discount.

Requirements
- Store the drink name, original price, and membership status.
- If membership is "yes", apply a 10% discount.
- If membership is "no", keep the original price.
- Print the drink name, original price, membership status, and final price.

Learning Objective
Practice Java variables, arithmetic operators, if-else statements, and String comparison.
*/
public class JD_2026_05_W4_CafeOrderCalculator {
    public static void main(String args[]) {
        String drinkName = "Latte";
        int price = 5000;
        String membership = "yes";

        int finalPrice = 0;

        if (membership.equalsIgnoreCase("yes")) {
            finalPrice = price * 9 / 10;
        } else {
            finalPrice = price;
        }

        System.out.println("Drink: " + drinkName);
        System.out.println("Original Price: " + price);
        System.out.println("Membership: " + membership);
        System.out.println("Final Price: " + finalPrice);
    }
}
