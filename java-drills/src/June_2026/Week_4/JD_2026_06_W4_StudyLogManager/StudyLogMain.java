package src.June_2026.Week_4.JD_2026_06_W4_StudyLogManager;

import java.util.ArrayList;
import java.util.Scanner;

public class StudyLogMain {
    public static void main(String[] args) {
        ArrayList<StudyLog> studyLogs = new ArrayList<>();
        StudyLogService service = new StudyLogService(studyLogs);
        Scanner scanner = new Scanner(System.in);

        boolean run = true;

        System.out.println("Welcome to Study Log Manager");
        service.setStudyLogs(service.loadFromFile());
        while (run) {
            int mainMenuChoice = mainMenuChoice(scanner);
            switch (mainMenuChoice) {
                case 1: // add log
                    System.out.println("Enter Topic");
                    scanner.nextLine();
                    String topic = scanner.nextLine();

                    System.out.println("Enter Minutes");
                    int minutes = scanner.nextInt();

                    System.out.println("Enter Reflection");
                    scanner.nextLine();
                    String reflection = scanner.nextLine();

                    service.addStudyLog(new StudyLog(topic, minutes, reflection));
                    break;

                case 2: // delete log
                    StudyLog targetLog = searchStudyLogByTopic(scanner, studyLogs);
                    if (targetLog == null) {
                        break;
                    } else {
                        service.removeStudyLog(targetLog);
                        break;
                    }

                case 3: // view all log
                    service.viewAllStudyLogs();
                    break;

                case 4: //show total study time
                    if (studyLogs.isEmpty()) {
                        System.out.println("There are no Logs recorded.");
                        System.out.println();
                    } else {
                        System.out.println("Total study time is " + service.showTotalStudyMinutes() + " minutes.");
                        System.out.println();
                    }
                    break;

                case 5: // save to file
                    service.saveToFile(studyLogs);
                    break;

                case 0: // exit
                    run = false;
                    System.out.println("Saving Files...");
                    service.saveToFile(studyLogs);
                    System.out.println("Terminating Program...");
                    break;

                default:
                    System.out.println("Invalid Input.");
            }
        }


    }

    private static int mainMenuChoice(Scanner scanner) {
        System.out.println("=== Study Log Manager ===");
        System.out.println("1. Add Study Log");
        System.out.println("2. Delete Study Log");
        System.out.println("3. View All Study Logs");
        System.out.println("4. Show Total Study Time");
        System.out.println("5. Save Logs to File");
        System.out.println("0. Exit");
        return scanner.nextInt();
    }

    private static StudyLog searchStudyLogByTopic(Scanner scanner, ArrayList<StudyLog> studyLogs) {
        System.out.println("Enter Topic");
        scanner.nextLine();
        String topic = scanner.nextLine();

        ArrayList<StudyLog> logs = searchStudyLogByTopic(scanner, topic, studyLogs);

        System.out.println();
        System.out.println("Search Result");

        if (logs.isEmpty()) {
            System.out.println("No Study Log Found.");
            System.out.println();
            return null;
        } else {
            for (StudyLog studyLog : logs) {
                int count = 1;
                System.out.printf("%d. %s | minutes: %d | reflection: %s\n", count, topic, studyLog.getMinutes(), studyLog.getReflection());
            }
        }
        int choice = scanner.nextInt();
        return logs.get(choice - 1);
    }

    private static ArrayList<StudyLog> searchStudyLogByTopic(Scanner scanner, String topic, ArrayList<StudyLog> studyLogs) {
        ArrayList<StudyLog> logs = new ArrayList<>();

        for (StudyLog log : studyLogs) {
            if (log.getTopic().equals(topic)) {
                logs.add(log);
            }
        }

        return logs;
    }
}
