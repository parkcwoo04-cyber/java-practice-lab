import java.util.ArrayList;
import java.util.HashSet;

public class HabitTracker {
    private ArrayList<Habit> habits;

    public HabitTracker(ArrayList<Habit> habits) {
        this.habits = habits;
    }

    public void addHabit(Habit habit) {
        if (!validateHabit(habit)) {
            return;
        }

        habits.add(habit);

        System.out.println("Habit added: " + habit.getHabitTitle());
    }

    public void removeHabit(Habit habit) {
        if (!validateHabit(habit)) {
            return;
        }

        Habit targetHabit = null;

        for (Habit h : habits) {
            if (h.getHabitID().equals(habit.getHabitID())) {
                targetHabit = h;
                break;
            }
        }

        if (targetHabit == null) {
            System.out.println("Cannot find habit: " + habit.getHabitTitle());
            return;
        }

        habits.remove(targetHabit);
        System.out.println("Habit removed: " + habit.getHabitTitle());
    }

    public ArrayList<Habit> findHabitsByTitle(String title) {
        ArrayList<Habit> results = new ArrayList<>();

        for (Habit habit : habits) {
            if (habit.getHabitTitle().toLowerCase().contains(title.toLowerCase())) {
                results.add(habit);
            }
        }

        return results;
    }

    public Habit findHabitByID(String habitID) {
        for (Habit habit : habits) {
            if (habit.getHabitID().equals(habitID)) {
                return habit;
            }
        }
        return null;
    }

    public void searchHabitByTitle(String title) {
        ArrayList<Habit> habits = findHabitsByTitle(title);

        if (habits.isEmpty()) {
            System.out.println("No habits found for " + title);
            return;
        }

        System.out.println("Search result for " + title);
        for (int i = 0; i <= habits.size(); i++) {
            System.out.println((i+1) + ". " + habits.get(i).getProgressSummary());
        }
    }

    public void completeHabit(Habit habit) {
        if (habit == null) {
            System.out.println("Habit cannot be null");
            return;
        }

        Habit targetHabit = findHabitByID(habit.getHabitID());

        if (targetHabit == null) {
            System.out.println("Habit not found for " + habit.getHabitTitle());
            return;
        }

        targetHabit.markComplete();
    }

    public void printAllHabits() {
        System.out.println("=== All Habit Lists ===");
        for (Habit habit : habits) {
            System.out.println(habit.getProgressSummary());
        }
        System.out.println();
    }

    public void printActiveHabits() {
        System.out.println("=== Active Habit Lists ===");
        for (Habit habit : habits) {
            if (habit.getStatus().equals(HabitStatus.ACTIVE)) {
                System.out.println(habit.getProgressSummary());
            }
        }
        System.out.println();
    }

    private boolean validateHabit(Habit habit) {
        if (habit == null) {
            System.out.println("Habit cannot be null.");
            return false;
        }

        if (habit.getHabitTitle() == null || habit.getHabitTitle().trim().isEmpty()) {
            System.out.println("Habit title cannot be empty.");
            return false;
        }

        if (habit.getCategory() == null) {
            System.out.println("Habit category cannot be null.");
            return false;
        }

        if (habit.getTargetCount() <= 0) {
            System.out.println("Habit target must be greater than 0.");
            return false;
        }

        return true;
    }

    public ArrayList<Habit> getActiveHabits() {
        ArrayList<Habit> activeHabits = new ArrayList<>();
        for (Habit habit : habits) {
            if (habit.getStatus().equals(HabitStatus.ACTIVE)) {
                activeHabits.add(habit);
            }
        }
        return activeHabits;
    }
}
