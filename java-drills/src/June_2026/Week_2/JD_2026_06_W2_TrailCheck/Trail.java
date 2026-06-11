package src.June_2026.Week_2.JD_2026_06_W2_TrailCheck;

public class Trail {
    private String trailName;
    private  String difficultyLevel;
    private double distance;
    private int safetyScore;
    private boolean isOpen;

    public Trail(String trailName, String difficultyLevel, double distance, int safetyScore, boolean isOpen) {
        this.trailName = trailName;
        this.difficultyLevel = difficultyLevel;
        this.distance = distance;
        this.safetyScore = safetyScore;
        this.isOpen = isOpen;
    }

    public boolean needsWarningSign() {
        if(isOpen){
            return safetyScore < 75;
        }
        return false;
    }

    public void closeTrail() {
        isOpen = false;
    }

    public String getStatusLabel() {
        if(isOpen){
            return "Open";
        }
        return "Closed";
    }

    public void printInfo(){
        System.out.println("Trail : " + trailName);
        System.out.println("Difficulty: " + difficultyLevel);
        System.out.println("Distance: " + distance + "km");
        System.out.println("Safety score: " + safetyScore);
        if(isOpen){
            System.out.println("Status: Open");
        } else {
            System.out.println("Status: Closed");
        }
    }

    public String getTrailName() {
        return trailName;
    }

    public void setTrailName(String trailName) {
        this.trailName = trailName;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getSafetyScore() {
        return safetyScore;
    }

    public void setSafetyScore(int safetyScore) {
        this.safetyScore = safetyScore;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
