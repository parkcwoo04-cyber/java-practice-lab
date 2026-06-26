package src.June_2026.Week_4.JD_2026_06_W4_EquipmentRentalCenterApp;

public class Projector extends Equipment{

    public Projector (String EquipmentID) {
        super(EquipmentID);
        super.equipmentType = "Projector";
    }

    @Override
    public boolean canSupport(RentalRequest request){
        boolean canSupport = false;

        if (super.canSupport(request)) {
            if (request.getRequestType() == RequestType.PRESENTATION) {
                canSupport = true;
            }
        }

        return canSupport;
    }
}
