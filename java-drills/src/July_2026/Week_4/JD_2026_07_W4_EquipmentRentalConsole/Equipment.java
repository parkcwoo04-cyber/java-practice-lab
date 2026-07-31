package src.July_2026.Week_4.JD_2026_07_W4_EquipmentRentalConsole;

public class Equipment {
    private String id;
    private String name;
    private int price;
    private RentalStatus status;
    private String renter;

    public Equipment(String id, String name, int price) {
        try {
            if (id == null || id.isBlank()) {
                throw new InvalidRentalOperationException("Error: ID cannot be empty.");
            } else if (name == null || name.isBlank()) {
                throw new InvalidRentalOperationException("Error: Name cannot be empty.");
            } else if (price <= 0) {
                throw new InvalidRentalOperationException("Error: Price should be greater than zero.");
            } else {
                this.id = id;
                this.name = name;
                this.price = price;
                this.status = RentalStatus.AVAILABLE;
                this.renter = "-";
            }
        } catch (InvalidRentalOperationException e) {
            System.out.println(e.getMessage());
        }
    }

    public void printEquipmentDetails() {
        System.out.printf("%s | %s | $%d/day | %s | Renter: %s\n", id, name, price, status, renter);
    }

    public boolean available() {
        return status == RentalStatus.AVAILABLE;
    }

    public boolean rented() {
        return status == RentalStatus.RENTED;
    }

    public boolean maintenance() {
        return status == RentalStatus.MAINTENANCE;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public RentalStatus getStatus() {
        return status;
    }

    public String getRenter() {
        return renter;
    }

    public void setRenter(String renter) {
        this.renter = renter;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setStatus(RentalStatus status) {
        this.status = status;
    }
}
