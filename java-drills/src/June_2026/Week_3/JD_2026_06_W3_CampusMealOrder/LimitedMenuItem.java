package src.June_2026.Week_3.JD_2026_06_W3_CampusMealOrder;

public class LimitedMenuItem extends MenuItem {
    int quantity;

    public LimitedMenuItem(String menuName, int price, int quantity) {
        super(menuName, price);
        this.quantity = quantity;
    }

    @Override
    public void showMenuInfo() {
        System.out.println(menuName + " | $" + price + " | Remaining Stock: " + quantity);
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean outOfStock(int orderQuantity) {
        return quantity < orderQuantity;
    }
}

