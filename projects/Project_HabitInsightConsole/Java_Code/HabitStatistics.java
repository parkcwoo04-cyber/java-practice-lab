import jdk.jfr.Category;

import java.util.ArrayList;

public class HabitStatistics {
    ArrayList<Habit> habits = new ArrayList<>();

    public HabitStatistics(ArrayList<Habit> habits) {
        this.habits = habits;
    }

    public double calculateOverallCompletionRate() {
        double overallCompletionRate = 0.0;
        double totalCompletionRate = 0.0;

        if (habits.isEmpty()) {
            return 0.0;
        }

        for (Habit habit : habits) {
            totalCompletionRate += habit.calculateCompletionRate();
        }

        overallCompletionRate = totalCompletionRate / habits.size();

        return overallCompletionRate;
    }

    public void countByStatus() {
        int activeHabitCount = 0;
        int pausedHabitCount = 0;
        int archivedHabitCount = 0;

        for (Habit habit : habits) {
            if (habit.getStatus() == HabitStatus.ACTIVE) {
                activeHabitCount++;
            } else if (habit.getStatus() == HabitStatus.PAUSED) {
                pausedHabitCount++;
            } else if (habit.getStatus() == HabitStatus.ARCHIVED) {
                archivedHabitCount++;
            }
        }

        System.out.println("=== Count by Status ===");
        System.out.println("Total Habits: " + habits.size());
        System.out.println("Active Habits: " + activeHabitCount);
        System.out.println("Paused Habits: " + pausedHabitCount);
        System.out.println("Archived Habits: " + archivedHabitCount);
        System.out.println();
    }

    public int countByCategory(HabitCategory category) {
        int count = 0;
        for (Habit habit : habits) {
            if (habit.getCategory() == category) {
                count++;
            }
        }

        return count;
    }

    public int countByStatus(HabitStatus status) {
        int count = 0;
        for (Habit habit : habits) {
            if (habit.getStatus() == status) {
                count++;
            }
        }

        return count;
    }

    public void countByCategory() {
        int studyHabitCount = 0;
        int exerciseHabitCount = 0;
        int healthHabitCount = 0;
        int productivityHabitCount = 0;
        int personalHabitCount = 0;
        int generalHabitCount = 0;

        for (Habit habit : habits) {
            if (habit.getCategory().equals(HabitCategory.STUDY)) {
                studyHabitCount++;
            } else if (habit.getCategory().equals(HabitCategory.EXERCISE)) {
                exerciseHabitCount++;
            } else if (habit.getCategory().equals(HabitCategory.PRODUCTIVITY)) {
                productivityHabitCount++;
            } else if (habit.getCategory().equals(HabitCategory.HEALTH)) {
                healthHabitCount++;
            } else if (habit.getCategory().equals(HabitCategory.PERSONAL)) {
                personalHabitCount++;
            } else if (habit.getCategory().equals(HabitCategory.GENERAL)) {
                generalHabitCount++;
            }
        }

        System.out.println("=== Count by Category ===");
        System.out.println("Study Habits: " + studyHabitCount);
        System.out.println("Exercise Habits: " + exerciseHabitCount);
        System.out.println("Health Habits: " + healthHabitCount);
        System.out.println("Productivity Habits: " + productivityHabitCount);
        System.out.println("Personal Habits: " + personalHabitCount);
        System.out.println("General Habits: " + generalHabitCount);
        System.out.println();
    }

    public Habit findBestStreakHabit() {
        Habit bestStreakHabit = habits.get(0);

        for (Habit habit : habits) {
            if (bestStreakHabit.getBestStreak() > habit.getBestStreak()) {
                bestStreakHabit = habit;
            }
        }

        return bestStreakHabit;
    }

    public Habit findLowestCompletionRateHabit() {
        Habit lowestCompletionHabit = habits.get(0);

        for (Habit habit : habits) {
            if (lowestCompletionHabit.calculateCompletionRate() < habit.calculateCompletionRate()) {
                lowestCompletionHabit = habit;
            }
        }

        return lowestCompletionHabit;
    }

    public Habit findHighestCompletionRateHabit() {
        Habit highestCompletionHabit = habits.get(0);

        for (Habit habit : habits) {
            if (highestCompletionHabit.calculateCompletionRate() > habit.calculateCompletionRate()) {
                highestCompletionHabit = habit;
            }
        }
        return highestCompletionHabit;
    }

    public void printSummaryReport() {
        if (habits.isEmpty()) {
            System.out.println("No habits data available for summary report.");
            return;
        }

        System.out.println("===== Habit Summary Report ======");
        System.out.println("Total Habits: " +  habits.size());
        System.out.println("Active Habits: " + countByStatus(HabitStatus.ACTIVE));
        System.out.println("Paused Habits: " + countByStatus(HabitStatus.PAUSED));
        System.out.println("Archived Habits: " + countByStatus(HabitStatus.ARCHIVED));
        System.out.println();
        System.out.printf("Overall Completion Rate: %.1f%%\n", calculateOverallCompletionRate());

        Habit bestStreakHabit = findBestStreakHabit();

        if (bestStreakHabit != null) {
            System.out.println("Best Streak Habit: " + bestStreakHabit.getHabitTitle() + " | Best Streaks: " + bestStreakHabit.getBestStreak());
        }

        System.out.printf("High Performing Habit:\n %s | Rate: %.1f%%\n", findHighestCompletionRateHabit().getHabitTitle(), findHighestCompletionRateHabit().calculateCompletionRate());
        System.out.printf("Low Performing Habit:\n %s | Rate: %.1f%%\n", findLowestCompletionRateHabit().getHabitTitle(), findLowestCompletionRateHabit().calculateCompletionRate());
        System.out.println();
        countByCategory();
        System.out.println("===================================");
    }
}
