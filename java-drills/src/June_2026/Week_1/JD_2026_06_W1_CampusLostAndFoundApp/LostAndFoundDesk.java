package src.June_2026.Week_1.JD_2026_06_W1_CampusLostAndFoundApp;

public class LostAndFoundDesk {
    private LostItem[] lostItems;

    public LostAndFoundDesk(LostItem[] lostItems) {
        this.lostItems = lostItems;
    }

    public void printLostItems() {
        System.out.println("[All Items]");
        for (int i = 0; i < lostItems.length; i++) {
            System.out.print((i+1) + ". ");
            lostItems[i].printItemInfo();
        }
    }

    public void searchLostItem (String keyWord) {
        System.out.println("[Search Result: '" +  keyWord + "']");

        boolean found = false;

        for (LostItem lostItem : lostItems) {
            String item = lostItem.getItemName();
            String location = lostItem.getFoundLocation();
            String itemLocation = item.concat(location);

            if (itemLocation.toLowerCase().contains(keyWord.toLowerCase())) {
                System.out.println(item + " found at " + location);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No items found");
        }
    }

    public void claimLostItem (String ownerName) {
        boolean nameMatches = false;

        System.out.println("[Claim Attempt]");

        for (LostItem lostItem : lostItems) {
            if (lostItem.getOwnerName().equalsIgnoreCase(ownerName) && !lostItem.getIsClaimed()) {
                lostItem.setIsClaimed(true);
                System.out.println(lostItem.getOwnerName() + " successfully claimed " + lostItem.getItemName() + ".");
                nameMatches = true;
            } else if (lostItem.getOwnerName().equalsIgnoreCase(ownerName) && lostItem.getIsClaimed()) {
                System.out.println(lostItem.getItemName() + " is already claimed.");
                nameMatches = true;
            }
        }
        if (!nameMatches) {
            System.out.println("No matches found");
        }
    }

    public int getUnclaimedCount() {
        int count = 0;
        for (LostItem lostItem : lostItems) {
            if (!lostItem.getIsClaimed()) {
                count++;
            }
        }
        return count;
    }
}
