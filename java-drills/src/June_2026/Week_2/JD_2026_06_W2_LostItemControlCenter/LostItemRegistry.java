package src.June_2026.Week_2.JD_2026_06_W2_LostItemControlCenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class LostItemRegistry {
    ArrayList<LostItem> lostItems;

    HashSet<String> itemIDs;

    HashSet<String> categories;

    HashMap<String, Integer> countClaimAttempts;

    public LostItemRegistry() {
        this.lostItems = new ArrayList<>();
        this.itemIDs = new HashSet<>();
        this.categories = new HashSet<>();
        this.countClaimAttempts = new HashMap<>();
    }

    public void addLostItem(LostItem lostItem){
        boolean alreadyExists = false;

        for(LostItem lostItem1 : lostItems){
            alreadyExists = lostItem1.getItemID().equalsIgnoreCase(lostItem.getItemID());
            if(alreadyExists){
                break;
            }
        }

        if(!alreadyExists){
            lostItems.add(lostItem);
            System.out.println("Registered: " + lostItem.getItemID() + " - " + lostItem.getName());
        } else {
            System.out.println("Rejected duplicate item ID: " + lostItem.getItemID());
        }
    }

    public void printAllCategories() {
        System.out.println("=== Existing Categories ===");
        for(LostItem lostItem : lostItems){
            categories.add(lostItem.getCategory());
        }

        for (String category : categories) {
            System.out.println(category);
        }
        System.out.println();
    }

    public void printLostItems() {
        System.out.println("=== Unclaimed Items ===");

        for(LostItem lostItem : lostItems){
            if(!lostItem.isClaimed()){
                lostItem.printBasicInfo();
            }
        }
        System.out.println();
    }

    public void searchByCategory(String category){
        System.out.println("=== Search: " + category + " ===");
        for(LostItem lostItem : lostItems){
            if(lostItem.getCategory().equalsIgnoreCase(category)){
                lostItem.printBasicInfo();
            }
        }
        System.out.println();
    }

    public void claimLostItem(ClaimRequest claimRequest){
        boolean alreadyExists = false;
        int i = 0;

        for(i = 0; i < lostItems.size(); i++){
            alreadyExists = lostItems.get(i).getItemID().equalsIgnoreCase(claimRequest.getItemID());
            if(alreadyExists){
                break;
            }
        }

        if(!alreadyExists){
            System.out.println("Claim failed: description does not match the item");
        } else {
            if(!lostItems.get(i).matchesDescription(claimRequest.description)){
                System.out.println("Claim failed: description does not match the item");
            } else {
                if (!lostItems.get(i).isClaimed()) {
                    System.out.println("Claim success: " + claimRequest.getStudentName() + " claimed " + lostItems.get(i).getName());
                    lostItems.get(i).matchClaimed();
                } else {
                    System.out.println("Claim failed: item is already claimed");
                }
            }
        }

        countClaimAttempt(claimRequest.getStudentName());
    }

    public void printClaimAttempts() {
        System.out.println("=== Claim Attempt Counts ===");
        for(String name : countClaimAttempts.keySet()){
            System.out.println(name + " : " + countClaimAttempts.get(name));
        }
    }

    private void countClaimAttempt(String studentName) {
        countClaimAttempts.merge(studentName, 1, Integer::sum);
    }
}
