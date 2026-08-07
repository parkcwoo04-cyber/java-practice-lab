package src.August_2026.Week_1.JD_2026_08_W1_WarehouseManagementSystem;

public class FoodProduct extends Product implements Comparable<Product> {
    public FoodProduct(String productID, String productName, int price, int quantity) {
        super(productID, productName, price, quantity);
        setCategory(Category.FOOD);
    }

    @Override
    public String printInfo() {
        return getProductID() + " | " + getCategory() + " | " + getProductName() + " | Price: $" + getPrice() + " | " + getQuantity() + "ea";
    }


    @Override
    public int compareTo(Product o) {
        return 0;
    }
}
