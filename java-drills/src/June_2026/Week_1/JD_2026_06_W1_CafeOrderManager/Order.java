package src.June_2026.Week_1.JD_2026_06_W1_CafeOrderManager;

public class Order {
    public String drinkName;
    public int quantity;
    public int price;
    public boolean isMember;

    public Order(String drinkName, int quantity, int price, boolean isMember) {
        this.drinkName = drinkName;
        this.quantity = quantity;
        this.price = price;
        this.isMember = isMember;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public int getTotalPrice(){
        int totalPrice = quantity*price;

        return totalPrice;
    }

    public int getDiscountPrice(){
        int discountPrice = getTotalPrice();

        if(isMember){
            discountPrice = getTotalPrice()*9/10;
        }
        return discountPrice;
    }

    public void printOrderInfo(){
        System.out.println("Drink: " + this.drinkName);
        System.out.println("Quantity: " + this.quantity);
        System.out.println("Original Price: " + getTotalPrice());
        System.out.println("Discount Price: " + getDiscountPrice());
    }
}
