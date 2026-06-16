package src.June_2026.Week_3.JD_2026_06_W3_PetCareCenterApp;

public class PetCareCenterApp {
    public static void main(String[] args) {
        PetCareCenter app = new PetCareCenter();

        app.addPet(new Dog("Buddy", 3, 75, "Ball"));
        app.addPet(new Dog("Hunter", 5, 90, "Doll"));
        app.addPet(new Cat("Luna", 10, 82, true));
        app.addPet(new Cat("Mike", 2, 60, true));

        app.showAllPets();

        app.searchPet("Buddy");
        app.searchPet("Maddie");

        app.provideCare("Luna");
        app.printUpdatedInfo("Luna");

        app.provideCare("Hunter");
        app.printUpdatedInfo("Hunter");
    }
}
