package src.June_2026.Week_3.JD_2026_06_W3_CampusMealOrder;

import java.util.ArrayList;

public class CampusMealOrderApp {
    public static void main(String[] args) {
        ArrayList<MealOrder> mealOrders = new ArrayList<MealOrder>();
        ArrayList<MenuItem>  menuItems = new ArrayList<MenuItem>();

        OrderManager manager = new OrderManager(menuItems, mealOrders);

        manager.registerMenuItem(new RegularMenuItem("Chicken Rice", 10));
        manager.registerMenuItem(new RegularMenuItem("Ham Sandwich", 12));
        manager.registerMenuItem(new RegularMenuItem("Cheeseburger", 13));

        manager.registerMenuItem(new LimitedMenuItem("Salmon Bowl", 18, 10));
        manager.registerMenuItem(new LimitedMenuItem("Chicken Burrito", 8, 3));
        manager.registerMenuItem(new LimitedMenuItem("Today's Pasta", 19, 5));

        manager.showMenuInfo();

        manager.registerOrder(new MealOrder("Chicken Rice", 2, null));
        manager.registerOrder(new MealOrder("Ham Sandwich", 2, null));
        manager.registerOrder(new MealOrder("Salmon Bowl", -1, null));
        manager.registerOrder(new MealOrder("Today's pasta", 7, "Allergic to nuts"));
        manager.registerOrder(new MealOrder("Pizza", 1, null));
        manager.registerOrder(new MealOrder("Chicken Burrito", 1, null));

        manager.showMealOrders();

        manager.validateOrder();

        manager.processOrder();

        manager.showSuccessfulOrders();

        System.out.println("Total Revenue: " + manager.totalRevenue());
        System.out.println();

        manager.showRemainingStocks();

        manager.showRejectedOrders();
    }
}
