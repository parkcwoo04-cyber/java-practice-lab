package src.June_2026.Week_1.JD_2026_06_W1_CafeOrderManager;
/*

ReadMe

Drill ID: JD_2026_June_W1_CafeOrderManager

Class Name: CafeOrderManager

Difficulty: Level 4 — Mini Project

Estimated Time: 30–40 minutes

Actual Time Taken: 32 minutes

Written by: Chanwoo Park

Problem
Create a cafe order management program that stores multiple orders as objects,
prints order information, calculates total sales, and searches for a specific drink.

Requirements
- Create an Order class with drinkName, quantity, price, and isMember fields.
- Use a constructor to initialize each Order object.
- Use methods to calculate original price, discounted price, and print order information.
- Store multiple Order objects in an array.
- Use a loop to print all orders and calculate total sales.
- Search for a Latte order using proper String comparison.

Learning Objective
Practice Java classes, objects, constructors, arrays, loops, methods, and String comparison
by building a small console-based order management program.
*/
public class CafeOrderManager {
    public void main(String[] args) {
        Order[] orders = {
                new Order("Americano", 2, 4000, true),
                new Order("Latte", 1, 5000, false),
                new Order("Mocha", 3, 6000, true),
                new Order("Tea", 2, 3500, false)
        };

        int totalSales = 0;

        System.out.println("===== Order List =====");

        for (Order order : orders) {
            order.printOrderInfo();
            System.out.println();
            totalSales += order.getDiscountPrice();
        }

        System.out.println("===== Sales Summary =====");
        System.out.println("Total Sales: " + totalSales);
        System.out.println();

        System.out.println("===== Search Result =====");

        boolean foundLatte = false;

        for (Order order : orders) {
            if (order.getDrinkName().equalsIgnoreCase("Latte")) {
                foundLatte = true;
                System.out.println("Latte order found.");
                order.printOrderInfo();
            }
        }

        if (!foundLatte) {
            System.out.println("Latte order not found");
        }
    }
}
