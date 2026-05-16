package src.May_2026.Week_3;
/*
ReadMe

Drill ID: JD-2026-05-W3-DiscountCalculator

Class Name: DiscountCalculator

Difficulty: Level 2 — Simple Application

Estimated Time: 20 minutes

Actual Time Taken: 25 minutes

Written by: Chanwoo Park

Problem
Create a program that calculates a discount amount and final price
based on the product price and membership status.

Requirements
- Store the product price in an int variable.
- Store the membership status in a boolean variable.
- Use the following discount rules:
  - Member and price is 100000 or more: 15% discount
  - Member and price is less than 100000: 10% discount
  - Non-member: 5% discount
- Calculate the discount amount.
- Calculate the final price.
- Print the product price, membership status, discount rate,
  discount amount, and final price.
- Print money values with 2 decimal places.

Learning Objective
Practice using variables, arithmetic operators, comparison operators,
logical operators, and conditional logic in a simple calculation program.
*/

public class JD_2026_05_W3_DiscountCalculator {
    public static void main(String[] args) {
        int price = 120000;
        System.out.println("Product Price: " + price); // printing product price

        boolean isMember = true;
        System.out.println("Member: " + isMember); // printing out member:

        double discountRate;
        discountRate = (price >= 100000 ) ? 15.00 : 10.00;
        System.out.printf("%s%.2f%s%n",  "Discount Rate: ", discountRate, "%");

        double discountAmount = (double)price * discountRate / 100.00 ;
        System.out.printf("%s%.2f%n",  "Discount Amount: ", discountAmount);

        double finalPrice = (double)price - discountAmount;
        System.out.printf("%s%.2f%n",  "Final Price: ", finalPrice);

    }
}