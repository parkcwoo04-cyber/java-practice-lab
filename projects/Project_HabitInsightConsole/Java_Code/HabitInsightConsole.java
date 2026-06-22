import java.util.ArrayList;
import java.util.Scanner;

public class HabitInsightConsole {
    public static void main(String[] args) {
        ArrayList<Habit> habits = new ArrayList<Habit>();

        HabitTracker tracker = new HabitTracker(habits);
        HabitStatistics stats = new HabitStatistics(habits);

        tracker.addHabit(new StudyHabit("Java", HabitCategory.STUDY, 10, "CS"));
        tracker.addHabit(new StudyHabit("Python", HabitCategory.STUDY, 10, "CS"));
        tracker.addHabit(new ExerciseHabit("Running", HabitCategory.EXERCISE, 10, "Cardio"));

        Scanner scanner = new Scanner(System.in);
        HabitCategory category = HabitCategory.STUDY;
        boolean run = true;
        while (run) {
            System.out.println("=== Menu ===");
            System.out.println("Enter the number only");
            System.out.println("1. Habit Management");
            System.out.println("2. Search Habit");
            System.out.println("3. Reports & Insights");
            System.out.println("4. Progress Tracking");
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

                                    default:
                                        System.out.println("Invalid choice");
                                        break;
                                }

                                break;

                            case 2: // remove habit
                                System.out.println("Enter Habit Title Keyword to Remove.");
                                scanner.nextLine();
                                String habitTitleKeyword = scanner.nextLine();

                                ArrayList<Habit> results = tracker.findHabitsByTitle(habitTitleKeyword);

                                if (results.isEmpty()) {
                                    System.out.println("No Habit Found for: " + habitTitleKeyword);
                                    habitManagement = false;
                                    break;
                                }

                                System.out.println("=== Search Result ===");
                                for (int i = 0; i < results.size(); i++) {
                                    Habit habit = results.get(i);

                                    System.out.println((i+1) + ". " + habit.getProgressSummary());
                                }
                                System.out.println();
                                System.out.println("Select Habit Number to Remove");
                                int removeHabitNumber = scanner.nextInt();

                                if (removeHabitNumber < 1 || removeHabitNumber > results.size()) {
                                    System.out.println("Invalid Input");
                                    habitManagement = false;
                                    break;
                                }

                                Habit removeHabit = results.get(removeHabitNumber - 1);
                                tracker.removeHabit(removeHabit);
                                habitManagement = false;
                                break;

                            case 3: // manage habit
                                System.out.println("Enter Habit Title to Modify");
                                scanner.nextLine();
                                String habitManageTitle = scanner.nextLine();

                                ArrayList<Habit> searchResults = tracker.findHabitsByTitle(habitManageTitle);

                                if (searchResults.isEmpty()) {
                                    System.out.println("No Habit Found for: " + habitManageTitle);
                                    habitManagement = false;
                                    break;
                                }

                                System.out.println("=== Search Result ===");
                                for (int i = 0; i < searchResults.size(); i++) {
                                    System.out.println((i+1) + ". " + searchResults.get(i).getProgressSummary());
                                }
                                System.out.println();

                                System.out.println("Select Habit Number to Modify");
                                int modifyHabitNumber = scanner.nextInt();

                                if (modifyHabitNumber < 1 || modifyHabitNumber > searchResults.size()) {
                                    System.out.println("Invalid Input");
                                    habitManagement = false;
                                    break;
                                }

                                Habit modifyHabit = searchResults.get(modifyHabitNumber - 1);

                                System.out.println("=== Modify Habit ===");
                                System.out.println("1. Edit Title");
                                System.out.println("2. Edit Category");
                                System.out.println("3. Edit Target Count");
                                System.out.println("4. Edit Habit Status");
                                int editHabitStatus = scanner.nextInt();

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
                                        selectHabitCategory();
                                        int newCategory = scanner.nextInt();

                                        if (newCategory < 1 || newCategory > 6) {
                                            System.out.println("Invalid Input");
                                            habitManagement = false;
                                            break;
                                        } else if (newCategory ==  1) {
                                            modifyHabit.setHabitCategory(HabitCategory.STUDY);
                                        } else if (newCategory ==  2) {
                                            modifyHabit.setHabitCategory(HabitCategory.EXERCISE);
                                        } else if (newCategory ==  3) {
                                            modifyHabit.setHabitCategory(HabitCategory.HEALTH);
                                        } else if (newCategory ==  4) {
                                            modifyHabit.setHabitCategory(HabitCategory.PRODUCTIVITY);
                                        } else if (newCategory ==  5) {
                                            modifyHabit.setHabitCategory(HabitCategory.PERSONAL);
                                        } else if (newCategory ==  6) {
                                            modifyHabit.setHabitCategory(HabitCategory.GENERAL);
                                        }
                                        System.out.println("Habit Category Edited to " + newCategory);
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
                                        System.out.println("Select Habit Status");
                                        System.out.println("1. Active");
                                        System.out.println("2. Paused");
                                        System.out.println("3. Archived");
                                        int habitStatus = scanner.nextInt();

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
                    System.out.println("=== Search Menu ===");
                    System.out.println("1. Search By Title");
                    System.out.println("2. Search By Category");
                    System.out.println("3. Search By Status");
                    System.out.println("0. Return");
                    int choice_Search = scanner.nextInt();

                    boolean searchHabit = true;
                    while (searchHabit) {
                        switch (choice_Search) {
                            case 1: // search Habit By Title
                                System.out.println("Enter Habit Title");
                                scanner.nextLine();
                                String habitTitle = scanner.nextLine();

                                ArrayList<Habit> searchResults = tracker.findHabitsByTitle(habitTitle);
                                if (searchResults.isEmpty()) {
                                    System.out.println("No Habit Found for: " + habitTitle);
                                    searchHabit = false;
                                    break;
                                }

                                System.out.println("Search Result for " + habitTitle);
                                for (int i = 0; i < searchResults.size(); i++) {
                                    System.out.println((i+1) + ". " + searchResults.get(i).getProgressSummary());
                                }
                                System.out.println();
                                searchHabit = false;
                                break;

                            case 2: // Search Habit by Category
                                selectHabitCategory();
                                int searchCategory = scanner.nextInt();

                                if (searchCategory < 1 || searchCategory > 6) {
                                    System.out.println("Invalid Input");
                                    searchHabit = false;
                                    break;
                                } else if (searchCategory ==  1) {
                                    tracker.searchHabitsByCategory(HabitCategory.STUDY);
                                } else if (searchCategory ==  2) {
                                    tracker.searchHabitsByCategory(HabitCategory.EXERCISE);
                                } else if (searchCategory ==  3) {
                                    tracker.searchHabitsByCategory(HabitCategory.HEALTH);
                                } else if (searchCategory ==  4) {
                                    tracker.searchHabitsByCategory(HabitCategory.PRODUCTIVITY);
                                } else if (searchCategory ==  5) {
                                    tracker.searchHabitsByCategory(HabitCategory.PERSONAL);
                                } else if (searchCategory ==  6) {
                                    tracker.searchHabitsByCategory(HabitCategory.GENERAL);
                                }
                                System.out.println();
                                searchHabit = false;
                                break;

                            case 3: // Search Habit by Status
                                System.out.println("Select Habit Status");
                                System.out.println("1. Active");
                                System.out.println("2. Paused");
                                System.out.println("3. Archived");
                                int habitStatus = scanner.nextInt();

                                if (habitStatus < 1 || habitStatus > 3) {
                                    System.out.println("Invalid Input");
                                    searchHabit = false;
                                    break;
                                } else if (habitStatus == 1) {
                                    tracker.searchHabitByStatus(HabitStatus.ACTIVE);
                                } else if (habitStatus == 2) {
                                    tracker.searchHabitByStatus(HabitStatus.PAUSED);
                                } else if (habitStatus == 3) {
                                    tracker.searchHabitByStatus(HabitStatus.ARCHIVED);
                                }
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
                    System.out.println("Enter Habit Title");
                    scanner.nextLine();
                    String habitTitle = scanner.nextLine();

                    ArrayList<Habit> searchResults = tracker.findHabitsByTitle(habitTitle);

                    tracker.searchHabitByTitle(habitTitle);
                    System.out.println("Select Habit");
                    int title = scanner.nextInt();

                    tracker.completeHabit(searchResults.get(title - 1));
                    System.out.println(habitTitle + " has been marked completed.");
                    break;

                case 0:
                    System.out.println("Terminating Program...");
                    run = false;
                    break;

                default:
                    System.out.println("Invalid choice");
                    break;
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
