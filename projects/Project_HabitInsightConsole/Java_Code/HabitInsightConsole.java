import java.util.ArrayList;
import java.util.Scanner;

public class HabitInsightConsole {
    private static final String HABIT_FILE_PATH =
            "/Users/parkcwoo04/Desktop/GitHub/java-practice-lab/projects/Project_HabitInsightConsole/Java_Code/Habit.csv";

    public static void main(String[] args) {

        HabitFileStorage storage = new HabitFileStorage(HABIT_FILE_PATH);

        ArrayList<Habit> habits = storage.loadHabits();

        HabitTracker tracker = new HabitTracker(habits);
        HabitStatistics stats = new HabitStatistics(habits);

        tracker.addHabit(new StudyHabit("Java", HabitCategory.STUDY, 10, "CS"));
        tracker.addHabit(new StudyHabit("Python", HabitCategory.STUDY, 10, "CS"));
        tracker.addHabit(new ExerciseHabit("Running", HabitCategory.EXERCISE, 10, "Cardio"));

        Scanner scanner = new Scanner(System.in);
        boolean run = true;
        while (run) {

            int choice = mainMenuChoice();

            switch (choice) {
                case 1: // Habit Management

                    int managementChoice = managementMenuChoice(); // 1. Add Habit 2. Remove Habit 3. Manage Habit 4. Print all Habits 0. Return
                    boolean habitManagement = true;

                    while (habitManagement) {
                        switch (managementChoice) {
                            case 1: // add Habit

                                handleAddHabit(scanner, tracker);
                                habitManagement = false;
                                break;

                            case 2: // remove habit

                                Habit removeHabit = searchHabitByTitle(scanner, tracker);

                                if (removeHabit == null) {
                                    habitManagement = false;
                                    System.out.println();
                                    break;
                                }

                                tracker.removeHabit(removeHabit);
                                habitManagement = false;
                                break;

                            case 3: // manage habit

                                Habit modifyHabit = searchHabitByTitle(scanner, tracker);

                                if (modifyHabit == null) {
                                    System.out.println();
                                    habitManagement = false;
                                    break;
                                }

                                int editHabitStatus = habitModifyMenuChoice();

                                switch (editHabitStatus) {
                                    case 1: // edit title
                                        System.out.println("Enter New Title");
                                        scanner.nextLine();
                                        String newTitle = scanner.nextLine();
                                        modifyHabit.setHabitTitle(newTitle);
                                        System.out.println("Habit Title Edited to " + newTitle);
                                        habitManagement = false;
                                        break;

                                    case 2: // edit category

                                        HabitCategory modifiedCategory = categoryChoice();
                                        modifyHabit.setHabitCategory(modifiedCategory);

                                        System.out.println("Habit Category Edited to " + modifiedCategory);
                                        habitManagement = false;
                                        break;

                                    case 3: // edit target count
                                        System.out.println("Enter New Target Count");
                                        int newTargetCount = scanner.nextInt();

                                        modifyHabit.setTargetCount(newTargetCount);
                                        System.out.println("Habit Target Count Edited to " + newTargetCount);
                                        habitManagement = false;
                                        break;

                                    case 4: // Edit Status

                                        int habitStatus = habitStatusChoice();

                                        if (habitStatus < 1 || habitStatus > 4) {
                                            System.out.println("Invalid Input");
                                            habitManagement = false;
                                            break;
                                        } else if (habitStatus == 1) {
                                            modifyHabit.setStatus(HabitStatus.ACTIVE);
                                        } else if (habitStatus == 2) {
                                            modifyHabit.setStatus(HabitStatus.PAUSED);
                                        } else if (habitStatus == 3) {
                                            modifyHabit.setStatus(HabitStatus.ARCHIVED);
                                        }

                                        System.out.println("Habit Status Edited to " + habitStatus);
                                        habitManagement = false;
                                        break;

                                    default:
                                        System.out.println("Invalid Input");
                                        habitManagement = false;
                                        break;
                                }
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
                case 2: // Search Habit (Title, Category, Status)

                    int choice_Search = searchMenuChoice();

                    boolean searchHabit = true;
                    while (searchHabit) {
                        switch (choice_Search) {
                            case 1: // search Habit By Title
                                System.out.println("Enter Title");
                                String title = scanner.nextLine();

                                tracker.searchHabitByTitle(title);
                                searchHabit = false;
                                break;

                            case 2: // Search Habit by Category

                                HabitCategory targetCategory = categoryChoice();
                                tracker.searchHabitsByCategory(targetCategory);
                                System.out.println();
                                searchHabit = false;
                                break;

                            case 3: // Search Habit by Status

                                HabitStatus habitStatus = searchHabitStatus(scanner, tracker);

                                if (habitStatus == null) {
                                    searchHabit = false;
                                    break;
                                }

                                tracker.searchHabitByStatus(habitStatus);

                                System.out.println();
                                searchHabit = false;
                                break;

                            case 0:
                                searchHabit = false;
                                break;

                            default:
                                System.out.println("Invalid choice");
                                searchHabit = false;
                                break;
                        }
                        break;
                    }
                    break;

                case 3: // Reports and Insights
                    stats.printSummaryReport();
                    break;

                case 4: // Progress Tracking
                    Habit markHabit = searchHabitByTitle(scanner, tracker);

                    if (markHabit == null) {
                        System.out.println();
                        searchHabit = false;
                        break;
                    }

                    tracker.completeHabit(markHabit);
                    System.out.println(markHabit.getHabitTitle() + " has been marked completed.");
                    break;

                case 0:
                    System.out.println("Terminating Program...");
                    System.out.println("Saving Data...");
                    storage.saveHabits(habits);
                    run = false;
                    break;

                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }

    private static int mainMenuChoice() {
        System.out.println("=== Menu ===");
        System.out.println("Enter the number only");
        System.out.println("1. Habit Management");
        System.out.println("2. Search Habit");
        System.out.println("3. Reports & Insights");
        System.out.println("4. Progress Tracking");
        System.out.println("0. Exit Program");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        return choice;
    }

    private static int managementMenuChoice() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Habit Management Menu ===");
        System.out.println("1. Add Habit"); // adding new habit
        System.out.println("2. Remove Habit"); // removing habit
        System.out.println("3. Manage Habit"); // managing habit(editing information, change status)
        System.out.println("4. Print All Habits"); // Print all habits
        System.out.println("0. Return");
        int choice = scanner.nextInt();

        return choice;
    }

    private static int searchMenuChoice() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Search Menu ===");
        System.out.println("1. Search By Title");
        System.out.println("2. Search By Category");
        System.out.println("3. Search By Status");
        System.out.println("0. Return");
        int choice = scanner.nextInt();

        return choice;
    }

    private static int habitTypeChoice() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select Habit Type");
        System.out.println("1. Study Habit");
        System.out.println("2. Exercise Habit");
        System.out.println("3. General Habit");
        int choice = scanner.nextInt();

        return choice;
    }

    private static int habitCategoryChoice() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select Habit Category");
        System.out.println("1. Study");
        System.out.println("2. Exercise");
        System.out.println("3. Health");
        System.out.println("4. Productivity");
        System.out.println("5. Personal");
        System.out.println("6. General");
        int choice = scanner.nextInt();

        return choice;
    }

    private static int habitModifyMenuChoice() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Modify Habit ===");
        System.out.println("1. Edit Title");
        System.out.println("2. Edit Category");
        System.out.println("3. Edit Target Count");
        System.out.println("4. Edit Habit Status");
        int choice = scanner.nextInt();

        return choice;
    }

    private static int habitStatusChoice() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select Habit Status");
        System.out.println("1. Active");
        System.out.println("2. Paused");
        System.out.println("3. Archived");
        int choice = scanner.nextInt();

        return choice;
    }

    private static HabitCategory categoryChoice() {
        int choice = habitCategoryChoice();
        HabitCategory category = null;

        switch (choice) {
            case 1:
                System.out.println("Study Selected");
                System.out.println();
                category = HabitCategory.STUDY;
                break;

            case 2:
                System.out.println("Exercise Selected");
                System.out.println();
                category = HabitCategory.EXERCISE;
                break;

            case 3:
                System.out.println("Health Selected");
                System.out.println();
                category = HabitCategory.HEALTH;
                break;

            case 4:
                System.out.println("Productivity Selected");
                System.out.println();
                category = HabitCategory.PRODUCTIVITY;
                break;

            case 5:
                System.out.println("Personal Selected");
                System.out.println();
                category = HabitCategory.PERSONAL;
                break;

            case 6:
                System.out.println("General");
                System.out.println();
                category = HabitCategory.GENERAL;
                break;

            default:
                System.out.println("Invalid choice");
                break;
        }

        return category;
    }

    private static void handleAddHabit(Scanner scanner, HabitTracker tracker) {
        int choice_habitType = habitTypeChoice();
        scanner.nextLine();

        System.out.println("Enter Habit Title");
        String title = scanner.nextLine();

        HabitCategory habitCategory = categoryChoice();

        System.out.println("Enter Target Count");
        int targetCount = scanner.nextInt();

        switch (choice_habitType) {
            case 1: // Study Habit
                System.out.println("Enter Subject");
                scanner.nextLine();
                String subject = scanner.nextLine();
                tracker.addHabit(new StudyHabit(title, habitCategory, targetCount, subject));
                break;

            case 2: // Exercise Habit
                System.out.println("Enter Exercise Type");
                scanner.nextLine();
                String exerciseType = scanner.nextLine();
                tracker.addHabit(new ExerciseHabit(title, habitCategory, targetCount, exerciseType));
                break;

            case 3: // General Habit
                System.out.println("Enter Note");
                scanner.nextLine();
                String note = scanner.nextLine();
                tracker.addHabit(new GeneralHabit(title, habitCategory, targetCount, note));
                break;

            default:
                System.out.println("Invalid choice");
                break;
        }
    }

    private static Habit searchHabitByTitle(Scanner scanner, HabitTracker tracker) {
        System.out.println("Enter Habit Title Keyword.");
        String habitTitleKeyword = scanner.nextLine();

        ArrayList<Habit> results = tracker.findHabitsByTitle(habitTitleKeyword);

        if (results.isEmpty()) {
            System.out.println("No Habit Found for: " + habitTitleKeyword);
            return null;
        }

        System.out.println("=== Search Result ===");
        for (int i = 0; i < results.size(); i++) {
            Habit habit = results.get(i);

            System.out.println((i + 1) + ". " + habit.getProgressSummary());
        }
        System.out.println();
        System.out.println("Select Habit Number");
        int removeHabitNumber = scanner.nextInt();

        if (removeHabitNumber < 1 || removeHabitNumber > results.size()) {
            System.out.println("Invalid Input");
            return null;
        }

        Habit habit = results.get(removeHabitNumber - 1);
        return habit;
    }

    private static HabitStatus searchHabitStatus(Scanner scanner, HabitTracker tracker) {
        HabitStatus status = null;
        int habitStatus = habitStatusChoice();

        if (habitStatus < 1 || habitStatus > 3) {
            System.out.println("Invalid Input");
            return null;
        } else if (habitStatus == 1) {
            status = HabitStatus.ACTIVE;
        } else if (habitStatus == 2) {
            status = HabitStatus.PAUSED;
        } else if (habitStatus == 3) {
            status = HabitStatus.ARCHIVED;
        }

        return status;
    }
}
