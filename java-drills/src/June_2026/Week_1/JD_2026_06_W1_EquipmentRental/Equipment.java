package src.June_2026.Week_1.JD_2026_06_W1_EquipmentRental;

public class Equipment {
    private String code;
    private String name;
    private String category;
    private boolean isRented;

    public Equipment(String code, String name, String category, boolean isRented) {
        this.code = code;
        this.name = name;
        this.category = category;
        this.isRented = isRented;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean getIsRented() {
        return isRented;
    }

    public void setRented(boolean rented) {
        isRented = rented;
    }

    public void printRentalInfo() {
        System.out.println(this.code + " | " + this.name + " | " + this.category);
    }
}
