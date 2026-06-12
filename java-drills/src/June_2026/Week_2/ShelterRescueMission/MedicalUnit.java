package src.June_2026.Week_2.ShelterRescueMission;

public class MedicalUnit extends RescueUnit {

    public MedicalUnit(String unitID) {
        super(unitID);
    }

    @Override
    public void showUnitRole() {
        System.out.println("Unit " + unitID + ": Medical response unit");
    }

    @Override
    public void handleAnimal(Animal animal) {
        if (animal.getStatus().ordinal() == 1 || animal.getStatus().ordinal() == 2) {
            System.out.println(unitID + " treated " + animal.getName() + ". Status changed to recovering.");
            animal.setStatus(AnimalStatus.RECOVERING);
        }
    }
}
