package src.June_2026.Week_2.JD_2026_06_W2_LostItemControlCenter;

import java.util.ArrayList;

public class LostItemControlCenter {
    public static void main(String[] args) {
        LostItemRegistry registry = new LostItemRegistry();

        System.out.println("=== Register Lost Items ===");
        registry.addLostItem(new LostItem("L1001", "Blue Umbrella", "Accessories", "Library"));
        registry.addLostItem(new LostItem("L1002", "Math Notebook", "School Supplies", "Room 204"));
        registry.addLostItem(new LostItem("L1001", "Blue Umbrella", "Accessories", "Library"));
        registry.addLostItem(new LostItem("L1003", "AirPods Case", "Electronics", "Gym"));
        registry.addLostItem(new LostItem("L1004", "Laptop", "Electronics", "Lab 236"));
        System.out.println();

        registry.printAllCategories();
        registry.printLostItems();
        registry.searchByCategory("Electronics");

        ClaimRequest claimRequest1 = new ClaimRequest("Mina", "L1004", "Laptop");
        ClaimRequest claimRequest2 = new ClaimRequest("Sol", "L1003", "Laptop");

        System.out.println("=== Claim Requests ===");
        registry.claimLostItem(claimRequest1);
        registry.claimLostItem(claimRequest1);
        registry.claimLostItem(claimRequest2);
        System.out.println();

        registry.printClaimAttempts();
    }
}
