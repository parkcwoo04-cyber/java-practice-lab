package src.June_2026.Week_4.JD_2026_06_W4_StudyLogManager;

import java.io.*;
import java.util.ArrayList;

public class StudyLogService {
    ArrayList<StudyLog> studyLogs = new ArrayList<StudyLog>();
    private final String fileName = "java-drills/src/June_2026/Week_4/JD_2026_06_W4_StudyLogManager/StudyLog.txt";

    public StudyLogService(ArrayList<StudyLog> studyLogs) {
        this.studyLogs = studyLogs;
    }

    public void addStudyLog(StudyLog studyLog){
        try {
            if (studyLog.getTopic() == null || studyLog.getTopic().isEmpty()) {
                throw new IllegalArgumentException("Topic is empty.");
            } else if (studyLog.getMinutes() <= 0) {
                throw new IllegalArgumentException("Minutes should be greater than 0.");
            } else {
                studyLogs.add(studyLog);
                System.out.println("Study Log added.");
                System.out.println();
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeStudyLog(StudyLog studyLog){
        System.out.println("Study Log removed.");
        studyLogs.remove(studyLog);
    }

    public void viewAllStudyLogs(){
        System.out.println("=== View All Study Logs ===");
        int index = 1;
        for (StudyLog studyLog : studyLogs) {
            System.out.printf("[%d] ", index);
            studyLog.printLog();
            System.out.println();
            index++;
        }
    }

    public int showTotalStudyMinutes() {
        int totalMinutes = 0;

        for (StudyLog studyLog : studyLogs) {
            totalMinutes += studyLog.getMinutes();
        }

        return totalMinutes;
    }

    public void saveToFile(ArrayList<StudyLog> studyLogs) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (StudyLog studyLog : studyLogs) {
                writer.write(logToLineCovert(studyLog));
                writer.newLine();
            }
            System.out.println("Study Log saved successfully.");
            System.out.println();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public String logToLineCovert(StudyLog studyLog){
        return studyLog.getTopic() + "," + studyLog.getMinutes() + ","
                + studyLog.getReflection();
    }

    public ArrayList<StudyLog> loadFromFile() {
        File file = new File(fileName);
        ArrayList<StudyLog> studyLogs = new ArrayList<>();

        System.out.println("Recovering Study Logs from file....");

        if (!file.exists()) {
            System.out.println("Study Log file does not exist.");
            return studyLogs;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                StudyLog log = convertFileToStudyLog(line);

                if (log != null) {
                    studyLogs.add(log);
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return studyLogs;
    }

    public StudyLog convertFileToStudyLog(String line) {
        String[] tokens = line.split(",");

        if (tokens.length != 3) {
            return null;
        }

        String topic = tokens[0];
        int minutes = Integer.parseInt(tokens[1]);
        String reflection = tokens[2];

        return new StudyLog(topic, minutes, reflection);
    }

    public void setStudyLogs(ArrayList<StudyLog> studyLogs) {
        this.studyLogs = studyLogs;
    }
}
