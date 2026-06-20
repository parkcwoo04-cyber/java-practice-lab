package src.June_2026.Week_3.JD_2026_06_W3_CafeOrderApp;

public class MenuItem {
    int menuNumber;
    String menuName;
    int price;

    MenuItem(int menuNumber, String menuName, int price) {
        this.menuNumber = menuNumber;
        this.menuName = menuName;
        this.price = price;
    }

    public void showMenu() {
        System.out.println(getMenuNumber() + ". " + getMenuName() + " - $" + getPrice());
    }

    public int getMenuNumber() {
        return menuNumber;
    }

    public void setMenuNumber(int menuNumber) {
        this.menuNumber = menuNumber;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
