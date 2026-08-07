package src.August_2026.Week_1.JD_2026_08_W1_WarehouseManagementSystem;

import java.util.ArrayList;
import java.util.List;

public interface InventoryAction {
    void addProduct(Product product) throws InvalidProductException;
    void removeProduct(String productID) throws InvalidProductException;
    Product findProductByID(String productID) throws InvalidProductException;
    void increaseStock(String id, int amount) throws InvalidProductException;
    void decreaseStock(String id, int amount) throws InvalidProductException;
    List<Product> searchProductByName(String productName) throws InvalidProductException;
    List<Product> getProductSortedByName();
    void printInsight();
    void identifyLowStock();
}
