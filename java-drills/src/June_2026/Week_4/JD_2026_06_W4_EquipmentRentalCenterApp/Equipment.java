package src.June_2026.Week_4.JD_2026_06_W4_EquipmentRentalCenterApp;

public abstract class Equipment implements Rentable {
    String equipmentID;
    EquipmentStatus status;
    int completedRentalCount;
    String equipmentType;

    public Equipment(String equipmentID) {
        this.equipmentID = equipmentID;
        this.status = EquipmentStatus.AVAILABLE;
        this.completedRentalCount = 0;
        this.equipmentType = null;
    }

    @Override
    public void rentTo(RentalRequest request) {
        System.out.printf("%s has been rented for %s.\n", this.equipmentID, request.getRentalId());
        this.status = EquipmentStatus.RENTED;
        request.setRequestStatus(RequestStatus.RENTED);
    }

    public void showInfo() {
        System.out.printf("[%s] %s | Status: %s | Completed Rental Count: %d\n", this.equipmentType, this.equipmentID, this.status, this.completedRentalCount);
    }

    @Override
    public void markReturned() {
        this.status = EquipmentStatus.RETURNED;
        this.completedRentalCount++;
    }

    public boolean isAvailable() {
        return status == EquipmentStatus.AVAILABLE;
    }

    @Override
    public boolean canSupport(RentalRequest request) {
        return isAvailable();
    }

    @Override
    public void assignTo(RentalRequest request) {
        if (canSupport(request)) {
            System.out.printf("%s assigned to %s %s.\n", request.getRentalId(), equipmentType, equipmentID);
            this.status = EquipmentStatus.ASSIGNED;
            request.setRequestStatus(RequestStatus.ASSIGNED);
        }
    }

    public String getEquipmentID() {
        return equipmentID;
    }

    public EquipmentStatus getStatus() {
        return status;
    }

    public int getCompletedRentalCount() {
        return completedRentalCount;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }
}
