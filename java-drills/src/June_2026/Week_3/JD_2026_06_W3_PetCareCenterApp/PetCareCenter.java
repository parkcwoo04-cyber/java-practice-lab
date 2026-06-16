package src.June_2026.Week_3.JD_2026_06_W3_PetCareCenterApp;

import java.util.ArrayList;

public class PetCareCenter {
    ArrayList<Pet> pets = new ArrayList<>();

    void addPet(Pet pet){
        pets.add(pet);
    }

    void showAllPets() {
        System.out.println("=== Registered Pets ===");
        for (Pet pet : pets) {
            pet.showInfo();
            System.out.println();
        }
    }

    void searchPet(String keyword) {
        System.out.println("Searching for " + keyword);

        boolean found = false;

        for (Pet pet : pets) {
            if (pet.getName().equalsIgnoreCase(keyword)) {
                System.out.println(keyword + " was found.");
                found = true;
            }
        }

        if (!found) {
            System.out.println(keyword + " was not found.");
        }
        System.out.println();
    }

    void provideCare(String keyword) {
        for (Pet pet : pets) {
            if (pet.getName().equalsIgnoreCase(keyword)) {
                pet.receiveCare();
            }
        }
        System.out.println();
    }

    void printUpdatedInfo(String keyword) {
        System.out.println("=== Updated Pet List ===");
        for (Pet pet : pets) {
            if (pet.getName().equalsIgnoreCase(keyword)) {
                pet.showInfo();
            }
        }
        System.out.println();
    }
}
