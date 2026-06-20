package src.June_2026.Week_3.JD_2026_06_W3_CampusMealOrder;

public class RegularMenuItem extends MenuItem {
    public RegularMenuItem(String menuName, int price) {
        super(menuName, price);
    }

    @Override
    public void showMenuInfo() {
        System.out.println(menuName + " | $" + price);
    }
}
