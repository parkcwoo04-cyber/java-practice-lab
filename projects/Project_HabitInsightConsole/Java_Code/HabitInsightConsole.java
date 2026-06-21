import java.util.ArrayList;
import java.util.Scanner;

public class HabitInsightConsole {
    public static void main(String[] args) {
        ArrayList<Habit> habits = new ArrayList<Habit>();

        HabitTracker tracker = new HabitTracker(habits);
        HabitStatistics stats = new HabitStatistics(habits);

        Scanner scanner = new Scanner(System.in);
        HabitCategory category = HabitCategory.STUDY;
        boolean run = true;
        while (run) {
            System.out.println("=== Menu ===");
            System.out.println("Enter the number only");
            System.out.println("1. Habit Management");
            System.out.println("2. Search Habit");
            System.out.println("3. Reports & Insights");
            System.out.println("0. Exit Program");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1: // Habit Management
                    System.out.println("1. Add Habit"); // adding new habit
                    System.out.println("2. Remove Habit"); // removing habit
                    System.out.println("3. Manage Habit"); // managing habit(editing information, change status)
                    System.out.println("4. Print All Habits");
                    System.out.println("0. Return");

                    boolean habitManagement = true;
                    while (habitManagement) {
                        int choice_1 = scanner.nextInt();
                        switch (choice_1) {
                            case 1: // add Habit
                                selectHabitType();
                                int choice_habitType = scanner.nextInt();
                                System.out.println("Enter Habit Title");
                                scanner.nextLine();
                                String title = scanner.nextLine();
                                selectHabitCategory();
                                int choice_habitCategory = scanner.nextInt();
                                switch (choice_habitCategory) {
                                    case 1: // Selected Study
                                        category = HabitCategory.STUDY;
                                        System.out.println("Study Selected");
                                        break;

                                    case 2: // Selected Exercise
                                        category = HabitCategory.EXERCISE;
                                        System.out.println("Exercise Selected");
                                        break;

                                    case 3: // Selected Health
                                        category = HabitCategory.HEALTH;
                                        System.out.println("Health Selected");
                                        break;

                                    case 4: // Selected Productivity
                                        category = HabitCategory.PRODUCTIVITY;
                                        System.out.println("Productivity Selected");
                                        break;

                                    case 5: // Selected Personal
                                        category = HabitCategory.PERSONAL;
                                        System.out.println("Personal Selected");
                                        break;

                                    case 6: // Selected General
                                        category = HabitCategory.GENERAL;
                                        System.out.println("General Selected");
                                        break;

                                    default:
                                        System.out.println("Invalid choice");
                                        break;
                                }
                                System.out.println("Enter Target Count");
                                int targetCount = scanner.nextInt();

                                switch (choice_habitType) {
                                    case 1: // Study Habit
                                        System.out.println("Enter Subject");
                                        scanner.nextLine();
                                        String subject = scanner.nextLine();
                                        System.out.println(title + category + targetCount + subject);
                                        tracker.addHabit(new StudyHabit(title, category, targetCount, subject));
                                        habitManagement = false;
                                        break;

                                    case 2: // Exercise Habit
                                        System.out.println("Enter Exercise Type");
                                        scanner.nextLine();
                                        String exerciseType = scanner.nextLine();
                                        tracker.addHabit(new ExerciseHabit(title, category, targetCount, exerciseType));
                                        habitManagement = false;
                                        break;

                                    case 3: // General Habit
                                        System.out.println("Enter Note");
                                        scanner.nextLine();
                                        String note = scanner.nextLine();
                                        tracker.addHabit(new GeneralHabit(title, category, targetCount, note));
                                        habitManagement = false;
                                        break;
                                }

                            case 2: // remove habit
                                System.out.println("Not Implemented Yet");
                                habitManagement = false;
                                break;

                            case 3: // manage habit
                                System.out.println("Not Implemented Yet");
                                habitManagement = false;
                                break;

                            case 4: // print all habits
                                tracker.printAllHabits();
                                habitManagement = false;
                                break;

                            case 0: // return
                                habitManagement = false;
                                break;

                            default:
                                System.out.println("Invalid choice");
                                habitManagement = false;
                                break;
                        }
                    }
                    break;
                case 2:
            }
        }
    }




    private static void selectHabitType() {
        System.out.println("Select Habit Type");
        System.out.println("1. Study Habit");
        System.out.println("2. Exercise Habit");
        System.out.println("3. General Habit");
    }

    private static void selectHabitCategory() {
        System.out.println("Select Habit Category");
        System.out.println("1. Study");
        System.out.println("2. Exercise");
        System.out.println("3. Health");
        System.out.println("4. Productivity");
        System.out.println("5. Personal");
        System.out.println("6. General");
    }
}
