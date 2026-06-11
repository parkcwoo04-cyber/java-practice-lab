package src.June_2026.Week_2.JD_2026_06_W2_TrailCheck;

public class TrailCheckMain {
    public static void main(String[] args){
        Trail[] trails = {
                new Trail("Pine Ridge", "Medium", 4.8, 82, true),
                new Trail("Stone Valley", "Hard", 7.2, 54, true),
                new Trail("River Bend", "Easy", 2.5, 68, true),
                new Trail("Foggy Hill", "Hard", 6.1, 91, true)
        };

        TrailLog trailLog = new TrailLog(trails);

        trailLog.closeUnsafeTrails();

        trailLog.printAllTrails();

        System.out.println("=== Open Trail Count ===");
        System.out.println("Open trails: " + trailLog.countOpenTrails());
        System.out.println();

        trailLog.searchTrail("pine ridge");
        trailLog.searchTrailKeyword("valley");
        trailLog.printWarningSigns();
    }
}
