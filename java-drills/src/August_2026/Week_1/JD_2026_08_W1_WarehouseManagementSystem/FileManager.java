package src.August_2026.Week_1.JD_2026_08_W1_WarehouseManagementSystem;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class FileManager {
    Warehouse warehouse;
    LinkedHashMap<String, Product> products;
    final private String fileName = "/Users/parkcwoo04/Desktop/GitHub/java-practice-lab/java-drills/src/August_2026/Week_1/JD_2026_08_W1_WarehouseManagementSystem/FileStorage.txt";

    public FileManager(Warehouse warehouse) {
        this.warehouse = warehouse;
        products = warehouse.getProducts();
    }

    private String convertProductToLine(Product product) {
        return product.getProductID() + "," + product.getProductName() + "," + product.getPrice()
                + "," + product.getQuantity() + "," + product.getCategory();
    }

    public void saveFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Product product : products.values()) {
                bw.write(convertProductToLine(product));
                bw.newLine();
            }
            System.out.println("Products saved successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Product convertLineToProduct(String line) {
        String[] parts = line.split(",");
        String productID = parts[0];
        String productName = parts[1];
        int price = Integer.parseInt(parts[2]);
        int quantity = Integer.parseInt(parts[3]);
        Category category = Category.valueOf(parts[4]);

        Product product = null;

        if (category == Category.FOOD) {
            product = new FoodProduct(productID, productName, price, quantity);
        } else {
            product = new ElectronicProduct(productID, productName, price, quantity);
        }

        return product;
    }

    public void loadFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while (!((line = br.readLine()) == null)) {
                Product product = convertLineToProduct(line);
                warehouse.addProduct(product);
            }
            System.out.println("Products loaded successfully");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidProductException e) {
            throw new RuntimeException(e);
        }
    }
}
