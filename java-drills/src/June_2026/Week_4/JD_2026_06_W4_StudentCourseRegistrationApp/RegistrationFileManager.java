package src.June_2026.Week_4.JD_2026_06_W4_StudentCourseRegistrationApp;

import java.io.*;
import java.util.ArrayList;

public class RegistrationFileManager {
    final String fileName;

    public RegistrationFileManager() {
        this.fileName = "/Users/parkcwoo04/Desktop/GitHub/java-practice-lab/java-drills/src/June_2026/Week_4/JD_2026_06_W4_StudentCourseRegistrationApp/RegistrationRecords.txt";
    }

    public void saveRegistrationRecords(ArrayList<RegistrationRequest> registrationRequests){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, false))) {
            for (RegistrationRequest registrationRequest : registrationRequests) {
                bw.write(convertLogtoString(registrationRequest));
                bw.newLine();
                // status,studentID,courseCode,requestID
            }
            System.out.println("Registration Log successfully saved");
            System.out.println();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void loadRegistrationRecords() {
        File file = new File(fileName);
        System.out.println("=== Loaded Records From File ===");
        if (!file.exists()) {
            System.out.println("Registration Log not found");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                String data = convertLogToString(line);
                System.out.println(data);
            }
            System.out.println();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public String convertLogtoString(RegistrationRequest registrationRequest) {
        return registrationRequest.getStatus() + "," + registrationRequest.getStudent().getStudentID() + ","
                + registrationRequest.getCourse().getCourseCode() + "," + registrationRequest.getRequestID();
    }

    public String convertLogToString(String line) {
        String[] parts = line.split(",");
        String status = parts[0];
        String studentID = parts[1];
        String courseCode = parts[2];
        String requestID = parts[3];

        return requestID + " | " + studentID + " | " + courseCode + " | " + status;
    }
}
