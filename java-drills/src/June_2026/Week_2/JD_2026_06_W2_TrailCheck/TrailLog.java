package src.June_2026.Week_2.JD_2026_06_W2_TrailCheck;

public class TrailLog {
    private Trail[] trails;

    public TrailLog(Trail[] trails){
        this.trails = trails;
    }

    public void printAllTrails(){
        System.out.println("=== Trail Inspection List ===");
        for(int i = 0; i < trails.length; i++){
            trails[i].printInfo();
            System.out.println();
        }
    }

    public int countOpenTrails(){
        int count = 0;
        for(int i = 0; i < trails.length; i++){
            if(trails[i].isOpen()){
                count++;
            }
        }
        return count;
    }

    public Trail findTrailByName(String name){
        Trail searchResult = null;

        for (Trail trail : trails) {
            if (name.equalsIgnoreCase(trail.getTrailName())) {
                searchResult = trail;
            }
        }
        return searchResult;
    }

    public void searchTrail(String name){
        System.out.println("=== Search Result ===");
        System.out.println("Found trail: " + name);
        Trail searchResult = findTrailByName(name);
        if(searchResult != null){
           searchResult.printInfo();
        } else {
            System.out.println("No trail found");
        }
        System.out.println();
    }

    public void closeUnsafeTrails(){
        for(int i = 0; i < trails.length; i++){
            if(trails[i].getSafetyScore() < 65){
                trails[i].closeTrail();
            }
        }
    }

    public void searchTrailKeyword(String keyword) {
        System.out.println("=== Keyword Search: " + keyword + " ===");
        String searchKeyword = keyword.toLowerCase();
        for(int i = 0; i < trails.length; i++){
            if(trails[i].getTrailName().toLowerCase().contains(searchKeyword)){
                trails[i].printInfo();
            }
        }
        System.out.println();
    }

    public void printWarningSigns() {
        System.out.println("=== Warning Sign Needed ===");
        for(Trail trail : trails){
            if(trail.needsWarningSign()) {
                System.out.println(trail.getTrailName() + " needs a warning sign.");
            }
        }
    }
}
