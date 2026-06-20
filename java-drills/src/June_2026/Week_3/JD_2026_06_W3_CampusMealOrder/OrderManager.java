package src.June_2026.Week_3.JD_2026_06_W3_CampusMealOrder;

import src.June_2026.Week_1.JD_2026_06_W1_CafeOrderManager.Order;
import src.June_2026.Week_3.JD_2026_06_W3_PetCareCenterApp.Pet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class OrderManager {
    ArrayList<MealOrder> mealOrders = new ArrayList<MealOrder>();
    ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();
    ArrayList<String> rejectedOrders = new ArrayList<>();

    public OrderManager(ArrayList<MenuItem> menuItems, ArrayList<MealOrder> mealOrders) {
        this.mealOrders = mealOrders;
        this.menuItems = menuItems;
    }

    public void registerMenuItem(MenuItem menuItem) {
        menuItems.add(menuItem);
    }

    public String[] getMenuItems() {
        String[] menuItemNames = new String[menuItems.size()];

        for (int i = 0; i < menuItems.size(); i++) {
            menuItemNames[i] = menuItems.get(i).getMenuName();
        }

        return menuItemNames;
    }

    public void registerOrder(MealOrder mealOrder) {
        mealOrders.add(mealOrder);
    }

    public void showMealOrders() {
        System.out.println("Order List");
        for (MealOrder order : mealOrders) {
            order.showMealOrder();
        }
        System.out.println();
    }

    public void validateOrder() {
        boolean wrongOrderName = true;

        Iterator<MealOrder> iterator = mealOrders.iterator();
        while (iterator.hasNext()) {
            MealOrder order = iterator.next();
            try {
                Iterator<MenuItem> menuItemIterator = menuItems.iterator();
                while (menuItemIterator.hasNext()) {
                    MenuItem menuItem = menuItemIterator.next();
                    if (order.getOrderQuantity() <= 0) {
                        wrongOrderName = false;
                        iterator.remove();
                        rejectedOrders.add(order.getMealName() + ": Quantity must be greater than 0.");
                        throw new InvalidOrderException("Order rejected: Quantity should be greater than 0");
                    }

                    if (order.getMealName().equalsIgnoreCase(menuItem.getMenuName())) {
                        if (menuItem instanceof LimitedMenuItem) {
                            LimitedMenuItem limitedMenuItem = (LimitedMenuItem) menuItem;
                            if (limitedMenuItem.getQuantity() > order.getOrderQuantity()) {
                                wrongOrderName = false;
                                throw new SuccessfulOrderException("Order accepted: " + order.getMealName() + " X " + order.getOrderQuantity() + " = $" + (order.getOrderQuantity() * menuItem.getPrice()));
                            } else {
                                wrongOrderName = false;
                                iterator.remove();
                                rejectedOrders.add(order.getMealName() + ": Not enough stocks. Requested: " + order.getOrderQuantity() + ", Remaining: " + ((LimitedMenuItem) menuItem).getQuantity());
                                throw new InvalidOrderException("Order rejected: Not enough stock for " + order.getMealName() + " . Requested: " + order.getOrderQuantity() + ", Remaining: " + limitedMenuItem.getQuantity());
                            }
                        } else {
                            wrongOrderName = false;
                            throw new SuccessfulOrderException("Order accepted: " + order.getMealName() + " X " + order.getOrderQuantity() + " = $" + (order.getOrderQuantity() * menuItem.getPrice()));
                        }
                    }
                    wrongOrderName = true;
                }
                if (wrongOrderName) {
                    iterator.remove();
                    rejectedOrders.add(order.getMealName() + ": Unknown menu item.");
                    throw new InvalidOrderException("Order rejected: Unknown menu item: " + order.getMealName());
                }
            } catch (SuccessfulOrderException e) {
                System.out.println(e.getMessage());
            } catch (InvalidOrderException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println();
    }

    public void processOrder() {
        Iterator<MealOrder> iterator = mealOrders.iterator();
        while (iterator.hasNext()) {
            MealOrder order = iterator.next();
            for (MenuItem menuItem : menuItems) {
                if (menuItem.getMenuName().equalsIgnoreCase(order.getMealName())) {
                    if (menuItem instanceof LimitedMenuItem) {
                        LimitedMenuItem limitedMenuItem = (LimitedMenuItem) menuItem;
                        limitedMenuItem.setQuantity(limitedMenuItem.getQuantity() - order.getOrderQuantity());
                    }
                }
            }
        }
    }

    public MenuItem getMenuItem(MealOrder mealOrder) {
        for (MenuItem menuItem : menuItems) {
            if (menuItem.getMenuName().equalsIgnoreCase(mealOrder.getMealName())) {
                return menuItem;
            }
        }
        return null;
    }

    public void showSuccessfulOrders() {
        System.out.println("=== Successful Order List ===");
        for (MealOrder order : mealOrders) {
            order.showMealOrder();
        }
        System.out.println();
    }

    public int totalRevenue() {
        int totalRevenue = 0;

        for (MealOrder order : mealOrders) {
            totalRevenue += getMenuItem(order).getPrice() * order.getOrderQuantity();
        }

        return totalRevenue;
    }

    public void showRejectedOrders() {
        System.out.println("=== Rejected Order ===");
        for (String rejectedOrder : rejectedOrders) {
            System.out.println(rejectedOrder);
        }
        System.out.println();
    }

    public void showRemainingStocks() {
        System.out.println("=== Remaining Limited Stock ===");
        for (MenuItem menuItem : menuItems) {
            if (menuItem instanceof LimitedMenuItem) {
                System.out.println(menuItem.getMenuName() + ": " + ((LimitedMenuItem) menuItem).getQuantity() + " left");
            }
        }
        System.out.println();
    }

    public void showMenuInfo() {
        System.out.println("=== Menu ===");
        System.out.println();

        System.out.println("Regular Menu");
        for (MenuItem menuItem : menuItems) {
            if (menuItem instanceof RegularMenuItem) {
                menuItem.showMenuInfo();
            }
        }
        System.out.println();

        System.out.println("Limited Menu");
        for (MenuItem menuItem : menuItems) {
            if (menuItem instanceof LimitedMenuItem) {
                menuItem.showMenuInfo();
            }
        }
        System.out.println();
    }
}