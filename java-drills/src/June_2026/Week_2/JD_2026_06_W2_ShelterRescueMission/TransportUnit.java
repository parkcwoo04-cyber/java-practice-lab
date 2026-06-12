package src.June_2026.Week_2.JD_2026_06_W2_ShelterRescueMission;

public class TransportUnit extends RescueUnit{
    public TransportUnit(String unitID) {
        super(unitID);
    }

    @Override
    public void showUnitRole() {
        System.out.println("Unit " + unitID + ": Animal transport unit");
    }

    @Override
    public void handleAnimal(Animal animal) {
        if (animal.getStatus().ordinal() == 0 || animal.getStatus().ordinal() == 3) {
            System.out.println(unitID + " transported " + animal.getName() + ".");
            animal.setIsTransported(true);
        } else if (animal.getStatus().ordinal() == 2 || animal.getStatus().ordinal() == 1) {
            System.out.println(unitID + " cannot transport " + animal.getName() + " because status is " +
                    animal.getStatus() + ".");
        }
    }
}
