package src.August_2026.Week_1.JD_2026_08_W1_WarehouseManagementSystem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();
        FileManager manager = new FileManager(warehouse);
        System.out.println("=== Warehouse Report ===");

        manager.loadFile();

        addProduct(warehouse, new FoodProduct("P001", "Apple", 5, 10));
        addProduct(warehouse, new FoodProduct("P002", "Banana", 7, 10));
        addProduct(warehouse, new FoodProduct("P002", "Banana", 5, 10));
        addProduct(warehouse, new FoodProduct("P003", "Chocolate", 3, 3));
        addProduct(warehouse, new ElectronicProduct("P004", "Headphone", 25, 10));
        addProduct(warehouse, new ElectronicProduct("P005", "Bluetooth Speaker", 30, 2));
        System.out.println();

        warehouse.printSummary();
        System.out.println();

        removeProduct(warehouse, "P002");
        System.out.println();

        increaseStock(warehouse, "P004", 5);
        decreaseStock(warehouse, "P004", 2);
        decreaseStock(warehouse, "P001", 12);
        System.out.println();

        System.out.println("=== Search Result ===");
        printResults(searchProductByName(warehouse, "a"));
        System.out.println();

        System.out.println("=== Sorted Result ===");
        printResults(sortProductsByName(warehouse));
        System.out.println();

        warehouse.printInsight();
        System.out.println();

        warehouse.identifyLowStock();

        manager.saveFile();
    }

    private static void addProduct(Warehouse warehouse, Product product) {
        try {
            warehouse.addProduct(product);
            System.out.println(product.getProductID() + " has been added");
        } catch (InvalidProductException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void removeProduct(Warehouse warehouse, String productID) {
        try {
            warehouse.removeProduct(productID);
            System.out.println(productID + " has been removed");
        } catch (InvalidProductException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void increaseStock(Warehouse warehouse, String productID, int amount) {
        try {
            warehouse.increaseStock(productID, amount);
        } catch (InvalidProductException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void decreaseStock(Warehouse warehouse, String productID, int amount) {
        try {
            warehouse.decreaseStock(productID, amount);
        } catch (InvalidProductException e) {
            System.out.println(e.getMessage());
        }
    }

    private static List<Product> searchProductByName(Warehouse warehouse, String productName) {
        List<Product> products = new ArrayList<>();

        try {
            products = warehouse.searchProductByName(productName);
        } catch (InvalidProductException e) {
            System.out.println(e.getMessage());
        }

        return products;
    }

    private static void printResults(List<Product> products) {
        int i = 1;
        for (Product product : products) {
            System.out.println(i + ". " + product.printInfo());
            i++;
        }
    }

    private static List<Product> sortProductsByName(Warehouse warehouse) {
        return warehouse.getProductSortedByName();
    }

}
