import java.time.LocalDate;

public abstract class Habit implements Trackable {
    private final String habitID;
    private String title;
    private HabitCategory category;
    private HabitStatus status;
    private int targetCount;
    private int completedCount;
    private int currentStreak;
    private int bestStreak;
    private LocalDate createdDate;
    private LocalDate lastCompletedDate;

    public Habit(String title, HabitCategory category, int targetCount) {
        this.habitID = generateHabitID();
        this.title = title;
        this.category = category;
        this.targetCount = targetCount;
        this.createdDate = LocalDate.now();

        this.lastCompletedDate = null;
        this.status = HabitStatus.ACTIVE;
        this.completedCount = 0;
        this.currentStreak = 0;
        this.bestStreak = 0;
    }

    private static int idCounter = 1;

    private static String generateHabitID() {
        return "H" + idCounter++;
    }

    @Override
    public void markComplete() {
        if (this.lastCompletedDate == null) {
            this.currentStreak = 1;
        } else if (this.lastCompletedDate.isEqual(LocalDate.now())) {
            System.out.println("This habit was already completed today");
            return;
        } else if (this.lastCompletedDate.isEqual(LocalDate.now().minusDays(1))) {
            currentStreak++;
        } else {
            currentStreak = 1;
        }

        lastCompletedDate = LocalDate.now();
        this.completedCount++;

        if (currentStreak > bestStreak) {
            this.bestStreak = currentStreak;
        }
    }

    @Override
    public double calculateCompletionRate() {
        double totalCompletionRate = 0;
        if (targetCount == 0) {
            return 0;
        } else {
            totalCompletionRate = (double) completedCount / (double) targetCount * 100;
        }
        return totalCompletionRate;
    }

    @Override
    public boolean inComplete() {
        return this.completedCount >= targetCount;
    }

    @Override
    public String getProgressSummary() {

        return String.format(
                "[%s] %s | Category: %s | Progress: %d/%d | Rate: %.1f%% | Current Streak: %d | Best Streak: %d",
                status,
                title,
                category,
                completedCount,
                targetCount,
                calculateCompletionRate(),
                currentStreak,
                bestStreak
        );
    }

    public int getCompletedCount() {
        return completedCount;
    }

    abstract String getHabitTypes();

    public String getHabitTitle() {
        return title;
    }

    public String getHabitID() {
        return habitID;
    }

    public HabitStatus getStatus() {
        return status;
    }

    public HabitCategory getCategory() {
        return category;
    }

    public int getTargetCount() {
        return targetCount;
    }

    public int getBestStreak() {
        return bestStreak;
    }

    public void setHabitTitle(String habitTitle) {
        this.title = habitTitle;
    }

    public void setHabitCategory(HabitCategory habitCategory) {
        this.category = habitCategory;
    }

    public void setTargetCount(int targetCount) {
        this.targetCount = targetCount;
    }

    public void setStatus(HabitStatus status) {
        this.status = status;
    }
}
