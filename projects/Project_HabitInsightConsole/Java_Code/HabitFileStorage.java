import java.io.*;
import java.util.ArrayList;

public class HabitFileStorage {
    private final String fileName;

    public HabitFileStorage(String fileName) {
        this.fileName = fileName;
    }

    public void saveHabits(ArrayList<Habit> habits) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Habit habit : habits) {
                writer.write(convertHabitToLine(habit));
                writer.newLine();
            }
            System.out.println("Habits saved successfully");
        } catch (IOException e) {
            System.out.println("Failed to save habits: " + e.getMessage());
        }
    }

    public ArrayList<Habit> loadHabits() {
        ArrayList<Habit> habits = new ArrayList<>();

        File file = new File(fileName);

        if (!file.exists()) {
            System.out.println("Habits file does not exist");
            return habits;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = reader.readLine()) != null) {
                Habit habit = convertLineToHabit(line);

                if (habit != null) {
                    habits.add(habit);
                }
            }

            System.out.println("Habits loaded successfully");
        } catch (IOException e) {
            System.out.println("Failed to load habits: " + e.getMessage());
        }

        return habits;
    }

    private String convertHabitToLine(Habit habit) {
        if (habit instanceof StudyHabit) {
            StudyHabit studyHabit = (StudyHabit) habit;

            return "Study, "
                    + studyHabit.getHabitTitle() +","
                    + studyHabit.getHabitCategory() + ","
                    + studyHabit.getTargetCount() + ","
                    + studyHabit.getCompletedCount() + ","
                    + studyHabit.getSubject();
        }

        if (habit instanceof ExerciseHabit) {

            ExerciseHabit exerciseHabit = (ExerciseHabit) habit;

            return "EXERCISE,"

                    + exerciseHabit.getHabitTitle() + ","

                    + exerciseHabit.getHabitCategory() + ","

                    + exerciseHabit.getTargetCount() + ","

                    + exerciseHabit.getCompletedCount() + ","

                    + exerciseHabit.getExerciseType();

        }

        if (habit instanceof GeneralHabit) {

            GeneralHabit generalHabit = (GeneralHabit) habit;

            return "GENERAL,"

                    + generalHabit.getHabitTitle() + ","

                    + generalHabit.getHabitCategory() + ","

                    + generalHabit.getTargetCount() + ","

                    + generalHabit.getCompletedCount() + ","

                    + generalHabit.getMemo();

        }

        return "";
    }

    private Habit convertLineToHabit(String line) {
        String[] parts = line.split(",");

        if (parts.length < 6) {
            return null;
        }

        String habitType = parts[0];
        String title = parts[1];
        HabitCategory category = HabitCategory.valueOf(parts[2]);
        int targetCount = Integer.parseInt(parts[3]);
        int completedCount = Integer.parseInt(parts[4]);
        String specificField = parts[5];

        Habit habit;

        switch (habitType) {
            case "STUDY":
                habit = new StudyHabit(title, category, targetCount, specificField);
                break;

            case "EXERCISE":
                habit = new ExerciseHabit(title, category, targetCount, specificField);
                break;

            case "GENERAL":
                habit = new GeneralHabit(title, category, targetCount, specificField);
                break;

            default:
                return null;
        }

        for (int i = 0; i < completedCount; i++) {
            habit.markComplete();
        }

        return habit;
    }
}
