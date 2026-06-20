package src.June_2026.Week_3.JD_2026_06_W3_CafeOrderApp;

import java.util.Scanner;

public class CafeOrderApp {
    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
        MenuItem[] menu = {
                new MenuItem(1, "Americano", 3),
                new MenuItem(2, "Latte", 5),
                new MenuItem(3, "Mocha", 5),
                new MenuItem(4, "Green Tea", 4)
        };

        Order order = new Order(menu);

       order.showMenu();

       boolean orderContinue = true;
       while (orderContinue) {
           System.out.println("Select menu number: ");
           int menuNumber = scanner.nextInt();
           if (menuNumber >= 1 && menuNumber <= 4) {
               System.out.println("Select quantity: ");
               int quantity = scanner.nextInt();
               order.addItem(menuNumber, quantity);
           } else if (menuNumber == 0) {
               orderContinue = false;
           } else {
               System.out.println("Invalid menu number");
           }
       }
       System.out.println();

       order.showSummary();
    }
}
