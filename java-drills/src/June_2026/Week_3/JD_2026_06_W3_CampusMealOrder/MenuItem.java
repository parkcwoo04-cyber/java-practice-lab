package src.June_2026.Week_3.JD_2026_06_W3_CampusMealOrder;

import java.util.ArrayList;

public abstract class MenuItem {
    String menuName;
    int price;
    ArrayList<MenuItem> menuItems;

    public MenuItem(String menuName, int price) {
        this.menuName = menuName;
        this.price = price;
    }

    abstract void showMenuInfo();

    public String getMenuName() {
        return menuName;
    }

    public int getPrice() {
        return price;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
