package src.June_2026.Week_4.JD_2026_06_W4_EquipmentRentalCenterApp;

public class Camera extends Equipment {
    public Camera (String equipmentID){
        super(equipmentID);
        super.equipmentType = "Camera";
    }

    @Override
    public boolean canSupport(RentalRequest request){
        boolean canSupport = false;

        if (super.canSupport(request)) {
            if (request.getRequestType() == RequestType.MEDIA_PROJECT) {
                canSupport = true;
            }
        }

        return canSupport;
    }
}
