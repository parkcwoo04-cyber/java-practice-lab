package src.June_2026.Week_1.JD_2026_06_W1_CafeOrderManager;

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
