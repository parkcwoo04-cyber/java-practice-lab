package src.May_2026.Week_3;
/*
ReadMe

Drill ID: JD_2026_05_W3_WeeklySalesTracker

Class Name: WeeklySalesTracker

Difficulty: Level 4 — Mini Project

Estimated Time: 35–45 minutes

Actual Time Taken: 55 minutes

Written by: Chanwoo Park

Problem
Create a weekly sales tracking program that processes sales values for 7 days and prints a summary report.

Requirements
- Use a for loop to process sales for 7 days.
- Use conditional statements to classify each day as Low, Normal, or High.
- Calculate total sales and average sales.
- Track the highest and lowest sales amounts.
- Count high-sales days and low-sales days.
- Print the average with 2 decimal points.

Learning Objective
This exercise is designed to practice loop-based processing, condition checking, accumulated totals,
counter variables, and maximum/minimum value tracking.
*/
public class JD_2026_05_W3_WeeklySalesTracker {
    public static void main (String[] args) {

        int day = 0;
        int salesAmount = 0;
        String salesResult = "";

        int high = 0;
        int normal = 0;
        int low = 0;

        int totalSales = 0;
        int highestSales = 0;
        int lowestSales = 10000000;
        int numberOfDays = 0;

        for (day = 1; day <= 7; day++) {

            numberOfDays++;

            if (day == 1) {
                salesAmount = 120;
            } else if (day == 2) {
                salesAmount = 85;
            } else if (day == 3) {
                salesAmount = 230;
            } else if (day == 4) {
                salesAmount = 175;
            } else if (day == 5) {
                salesAmount = 95;
            } else if (day == 6) {
                salesAmount = 260;
            } else if (day == 7) {
                salesAmount = 150;
            } else {
                System.out.println("Invalid day");
            }

            if (salesAmount >= 200) {
                high ++;
                salesResult = "High";
            } else if (salesAmount >= 100) {
                normal++;
                salesResult = "Normal";
            } else {
                low++;
                salesResult = "Low";
            }

            System.out.println("Day " + day + ": " + salesAmount + " - " + salesResult);
            salesResult = "";
            totalSales += salesAmount;
            if (salesAmount > highestSales) {
                highestSales = salesAmount;
            }
            if (salesAmount < lowestSales) {
                lowestSales = salesAmount;
            }
        }

        double averageSales = (double)totalSales / (double)numberOfDays;

        System.out.println("Weekly Sales Summary");
        System.out.println("Total sales: " + totalSales);
        System.out.printf("%s%.2f", "Average sales: ", averageSales);
        System.out.println("\nHighest sales: " + highestSales);
        System.out.println("Lowest sales: " + lowestSales);
        System.out.println("High-sales days: " + high);
        System.out.println("Low-sales days: " + low);
    }
}
