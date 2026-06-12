package src.June_2026.Week_2.JD_2026_06_W2_ShelterRescueMission;

public class ShelterRescueMission {
    public static void main(String[] args) {
        Animal[] animals = {
                new Animal("Luna ", "Cat", 3, AnimalStatus.CRITICAL, UrgencyLevel.HIGH),
                new Animal("Max", "Dog", 5, AnimalStatus.INJURED, UrgencyLevel.MEDIUM),
                new Animal("Coco", "Cat", 2, AnimalStatus.HEALTHY, UrgencyLevel.LOW),
                new Animal("Bolt", "Dog", 4, AnimalStatus.CRITICAL, UrgencyLevel.HIGH)
        };

        RescueUnit[] units = {
                new MedicalUnit("M-01"),
                new TransportUnit("T-01")
        };

        System.out.println("=== Rescue Units ===");
        for (RescueUnit unit : units) {
            unit.showUnitRole();
        }
        System.out.println();

        System.out.println("=== Initial Critical Count ===");
        int criticalCount = 0;
        for (Animal animal : animals) {
            if(animal.isCritical()){
                criticalCount++;
            }
        }
        System.out.println("Critical animals: " + criticalCount);
        System.out.println();

        System.out.println("=== Mission Processing ===");
        for (RescueUnit unit : units) {
            for (Animal animal : animals) {
                unit.handleAnimal(animal);
            }
        }
        System.out.println();

        System.out.println("=== Species Search: dog ===");
        for (Animal animal : animals) {
            animal.searchBySpecies("dog");
        }
        System.out.println();

        System.out.println("=== Name Keyword Search: o ===");
        for (Animal animal : animals) {
            animal.searchKeyword("o");
        }
        System.out.println();

        System.out.println("=== Final Mission Report ===");
        for (Animal animal : animals) {
            animal.printFinalReport();
        }
        System.out.println();

        int transported = 0;
        int needCare = 0;
        for (Animal animal : animals) {
            if(animal.isTransported()){
                transported++;
            }

            if(animal.needCare()){
                needCare++;
            }
        }

        System.out.println("Transported animals: " + transported);
        System.out.println("Animals still needing care: " + needCare);
    }
}
