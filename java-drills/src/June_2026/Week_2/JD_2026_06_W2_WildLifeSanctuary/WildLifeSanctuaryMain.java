package src.June_2026.Week_2.JD_2026_06_W2_WildLifeSanctuary;

public class WildLifeSanctuaryMain {
    public static void main(String[] args) {
        Animal[] animals = {
                new Bird ("Sol", 5, 75, true, 1.5),
                new Bird ("John", 7, 37, false, 1.3),
                new Mammal ("Joeun", 12, 98, false, "Excellent"),
                new Mammal ("Sarah", 12, 29, true, "Great"),
                new Reptile("Leah", 11, 74, true, true),
                new Reptile ("Leonard", 11, 55, false, false),
        };

        processDailyCare(animals);
        countCriticalAnimals(animals);
        foundLowestHealthAnimal(animals);
        printAnimalsByCareType(animals, "Temperature Regulation");

    }

    public static void processDailyCare(Animal[] animals) {
        System.out.println("=== Daily Wildlife Care ===");
        System.out.println();

        for (Animal animal : animals) {
            animal.showBasicInfo();
            animal.feed();
            animal.provideCare();
            System.out.println();
        }
    }

    public static void countCriticalAnimals(Animal[] animals) {
        System.out.println("=== Critical Animal Report ===");
        int count = 0;
        for (Animal animal : animals) {
            if(animal.isCritical()) {
                count++;
            }
        }
        System.out.println("Critical animals: " + count);
        System.out.println();
    }

    public static void foundLowestHealthAnimal(Animal[] animals) {
        System.out.println("=== Lowest Health Animal ===");
        Animal lowestHealth = animals[0];

        for (Animal animal : animals) {
            if(animal.getHealthScore() < lowestHealth.getHealthScore()) {
                lowestHealth = animal;
            }
        }

        lowestHealth.showBasicInfo();
        System.out.println();
    }

    public static void printAnimalsByCareType(Animal[] animals, String careType) {
        System.out.println("=== Animals Needing " + careType + " ===");
        for (Animal animal : animals) {
            if(animal.getCareType().equalsIgnoreCase(careType)) {
                animal.showBasicInfo();
                System.out.println();
            }
        }
    }
}
