package src.June_2026.Week_4.JD_2026_06_W4_EquipmentRentalCenterApp;

public interface Rentable {
    boolean canSupport(RentalRequest request);
    void markReturned();
    void assignTo(RentalRequest request);
    void rentTo(RentalRequest request);
}
