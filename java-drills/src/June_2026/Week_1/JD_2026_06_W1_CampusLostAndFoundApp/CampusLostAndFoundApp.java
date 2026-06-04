package src.June_2026.Week_1.JD_2026_06_W1_CampusLostAndFoundApp;

public class CampusLostAndFoundApp {
    public static void main(String[] args) {
        LostItem[] items = {
                new LostItem("Laptop Charger", "Library", "Mina", false),
                new LostItem("Blue Umbrella", "Cafeteria", "Daniel", false),
                new LostItem("Student ID Card", "Engineering Building", "Jisoo", true),
                new LostItem("Water Bottle", "Gym", "Alex", false)
        };

        LostAndFoundDesk desk = new LostAndFoundDesk(items);

        System.out.println("=== Campus Lost and Found ===");
        System.out.println();

        desk.printLostItems();
        System.out.println();

        desk.searchLostItem("library");
        System.out.println();

        desk.claimLostItem("Daniel");
        System.out.println();

        System.out.println("[Unclaimed Count]");
        System.out.println("Remaining unclaimed items: " + desk.getUnclaimedCount());
    }
}

