package src.June_2026.Week_3.JD_2026_06_W3_CampusMealOrder;

public class MealOrder {
    String mealName;
    int orderQuantity;
    String description;

    public MealOrder(String mealName, int orderQuantity, String description) {
        this.mealName = mealName;
        this.orderQuantity = orderQuantity;
        this.description = description;
    }

    public void showMealOrder(){
        System.out.print(mealName + " | Quantity: " + orderQuantity);
        if(description != null) {
            System.out.print(" | Special Request: " + description);
        }
        System.out.println();
    }

    public String getMealName() {
        return mealName;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public String getDescription() {
        return description;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
