package src.June_2026.Week_3.JD_2026_06_W3_CafeOrderApp;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Order {
    LinkedHashMap<MenuItem, Integer> orderList = new LinkedHashMap<>();
    MenuItem[] menuItems;
    int totalPrice;
    int totalQuantity;

    public Order(MenuItem[] menuItems) {
        this.menuItems = menuItems;
    }

    public void addItem(int number, int quantity) {
        MenuItem itemOrdered = null;
        for (MenuItem item : menuItems) {
            if (item.getMenuNumber() == number) {
                itemOrdered = item;
            }
        }
        orderList.put(itemOrdered, quantity);
        totalQuantity += quantity;
        totalPrice += itemOrdered.getPrice() * quantity;
    }

    public void showSummary() {
        System.out.println("=== Order Summary ===");
        for (MenuItem item : orderList.keySet()) {
            System.out.println(item.getMenuName() + " X " + orderList.get(item) + " = $" + orderList.get(item)*item.getPrice());
        }
        System.out.println("Total Quantity: " + totalQuantity);
        System.out.println("Total Price: " + totalPrice);
    }

    public void showMenu() {
        System.out.println("=== Cafe Menu ===");
        for (MenuItem item : menuItems) {
            item.showMenu();
        }
        System.out.println("0. Finish order");
        System.out.println();
    }
}
