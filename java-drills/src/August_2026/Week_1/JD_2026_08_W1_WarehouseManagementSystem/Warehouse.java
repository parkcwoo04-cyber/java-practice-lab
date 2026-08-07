package src.August_2026.Week_1.JD_2026_08_W1_WarehouseManagementSystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

public class Warehouse implements InventoryAction {
    private LinkedHashMap<String, Product> products;

    public Warehouse() {
        products = new LinkedHashMap<>();
    }

    public void printSummary() {
        for (Product product : products.values()) {
            System.out.println(product.printInfo());
        }
    }

    @Override
    public void addProduct(Product product) throws InvalidProductException {
        if (products.containsKey(product.getProductID())) {
            throw new InvalidProductException("Duplicate Product ID");
        }
        products.put(product.getProductID(), product);
    }

    @Override
    public void removeProduct(String productID) throws InvalidProductException{
        if (products.containsKey(productID)) {
            products.remove(productID);
        } else {
            throw new InvalidProductException("Product ID does not exist");
        }
    }

    @Override
    public Product findProductByID(String productID) throws InvalidProductException {
        if (products.containsKey(productID)) {
            return products.get(productID);
        } else {
            throw new InvalidProductException("Product does not exist");
        }
    }

    @Override
    public void increaseStock(String productID, int amount) throws InvalidProductException {
        if (!products.containsKey(productID)) {
            throw new InvalidProductException("Product does not exist");
        }
        Product product = findProductByID(productID);

        product.setQuantity(product.getQuantity() + amount);
        System.out.println(product.getProductID() + "'s quantity has been increased by " + amount);
    }

    @Override
    public void decreaseStock(String productID, int amount) throws InvalidProductException {
        if (!products.containsKey(productID)) {
            throw new InvalidProductException("Product does not exist");
        }
        Product product = findProductByID(productID);

        if (product.getQuantity() < amount) {
            throw new InvalidProductException("Decreasing quantity is too much");
        }

        product.setQuantity(product.getQuantity() - amount);
        System.out.println(product.getProductID() + "'s quantity has been decreased by " + amount);
    }

    @Override
    public List<Product> searchProductByName(String productName) throws InvalidProductException {
        List<Product> productList = new ArrayList<>();
        for (Product product : products.values()) {
            if (product.getProductName().toLowerCase().contains(productName.toLowerCase())) {
                productList.add(product);
            }
        }

        if (productList.isEmpty()) {
            throw new InvalidProductException("Product does not exist");
        }

        return productList;
    }

    @Override
    public List<Product> getProductSortedByName() {
        List<Product> productList = new ArrayList<>(products.values());
        Collections.sort(productList);

        return productList;
    }

    @Override
    public void printInsight() {
        int totalProducts = products.size();
        int foodProducts = 0;
        int ElectronicProducts = 0;
        int totalQuantity = 0;

        for (Product product : products.values()) {
            if (product.getCategory().equals(Category.ELECTRONIC)) {
                ElectronicProducts++;
            } else if (product.getCategory().equals(Category.FOOD)) {
                foodProducts++;
            }
            totalQuantity += product.getQuantity();
        }

        System.out.println("Total products: " + totalProducts);
        System.out.println("Food products: " + foodProducts);
        System.out.println("Electronic products: " + ElectronicProducts);
        System.out.println("Total quantity: " + totalQuantity);
    }

    @Override
    public void identifyLowStock() {
        System.out.println("Low Stock Products");
        for (Product product : products.values()) {
            if (product.getQuantity() < 5) {
                System.out.println(product.printInfo());
            }
        }
    }

    public LinkedHashMap<String, Product> getProducts() {
        return products;
    }
}
