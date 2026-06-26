package src.June_2026.Week_4.JD_2026_06_W4_EquipmentRentalCenterApp;

public class Laptop extends Equipment{

    public Laptop (String EquipmentID) {
        super(EquipmentID);
        super.equipmentType = "Laptop";
    }

    @Override
    public boolean canSupport(RentalRequest request){
        boolean canSupport = false;

        if (super.canSupport(request)) {
            if (request.getRequestType() == RequestType.CODING_WORKSHOP) {
                canSupport = true;
            }
        }

        return canSupport;
    }
}
