package src.August_2026.Week_1.JD_2026_08_W1_WarehouseManagementSystem;

public class ElectronicProduct extends Product implements Comparable<Product>{
    public ElectronicProduct(String productID, String productName, int price, int quantity) {
        super(productID, productName, price, quantity);
        setCategory(Category.ELECTRONIC);
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
