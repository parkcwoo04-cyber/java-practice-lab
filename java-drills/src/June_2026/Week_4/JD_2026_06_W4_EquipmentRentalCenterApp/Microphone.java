package src.June_2026.Week_4.JD_2026_06_W4_EquipmentRentalCenterApp;

public class Microphone extends Equipment{
    public Microphone (String EquipmentID) {
        super(EquipmentID);
        super.equipmentType = "Microphone";
    }

    @Override
    public boolean canSupport(RentalRequest request){
        boolean canSupport = false;

        if (super.canSupport(request)) {
            if (request.getRequestType() == RequestType.EVENT) {
                canSupport = true;
            }
        }

        return canSupport;
    }
}
