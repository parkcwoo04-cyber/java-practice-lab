package src.May_2026.Week_3;
/*
ReadMe

Drill ID: JD_2026_05_W3_OrderDiscountCalculator

Class Name: OrderDiscountCalculator

Difficulty: Level 3 — Multi-Step Problem

Estimated Time: 25–30 minutes

Actual Time Taken: 35 minutes

Written by: Chanwoo Park

Problem
Calculate the final payment amount for an online order based on order amount,
membership status, coupon availability, discount rules, and delivery fee rules.

Requirements
- Apply a basic discount based on the order amount.
- Apply an additional coupon discount only when the user is a member and has a coupon.
- Calculate delivery fee and final payment amount.
- Print the order summary clearly.

Learning Objective
Practice Java variables, arithmetic operators, comparison operators, logical operators,
and if / else if / else conditional statements.
*/

public class JD_2026_05_W3_OrderDiscountCalculator {
    public static void main(String[] args) {

        int orderAmount = 80000;
        boolean isMember = true;
        boolean hasCoupon = true;

        int deliveryFee = 0;
        int discount = 0;
        int finalAmount = 0;
        boolean memberCoupon = false;

        if (orderAmount >= 100000) {
            discount += 10000;
        } else if (orderAmount >= 50000) {
            discount += 5000;
        } else {
        }

        if (isMember && hasCoupon) {
            discount += 3000;
            memberCoupon = true;
        }

        if (orderAmount < 30000) {
            deliveryFee += 3000;
        }

        finalAmount = orderAmount - discount + deliveryFee;

        System.out.println("Order Amount: " + orderAmount + "\nDiscount: " +  discount +
                "\nDelivery Fee: " + deliveryFee + "\nFinal Amount: " + finalAmount +
                "\nMember Coupon Applied: " + memberCoupon);
    }
}