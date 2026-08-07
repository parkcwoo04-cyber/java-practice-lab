package src.August_2026.Week_1.JD_2026_08_W1_CourseRegistrationSystem;

import src.August_2026.Week_1.JD_2026_08_W1_WarehouseManagementSystem.Product;

import java.io.*;

public class FileManager {
    RegistrationManager registrationManager;
    private final String fileName = "/Users/parkcwoo04/Desktop/GitHub/java-practice-lab/java-drills/src/August_2026/Week_1/JD_2026_08_W1_CourseRegistrationSystem/FileStorage.txt";

    public FileManager(RegistrationManager registrationManager) {
        this.registrationManager = registrationManager;
    }

    private String registrationToLine(Registration registration) {
        return registration.getStudent().getStudentID() + "," + registration.getCourse().getCode();
    }

    public void saveFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Registration registration : registrationManager.getRegistrations()) {
                writer.write(registrationToLine(registration));
                writer.newLine();
            }
            System.out.println("Registrations saved successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Registration lineToRegistration(String line) throws InvalidRegistrationException {
        String[] parts = line.split(",");
        String studentID = parts[0];
        String courseCode = parts[1];

        Course course = registrationManager.findCourseByID(courseCode);
        Student student = registrationManager.findStudentByID(studentID);
        return new Registration(student, course);
    }

    private String courseID(String line) {
        String[] parts = line.split(",");
        return parts[1];
    }

    private String studentID(String line) {
        String[] parts = line.split(",");
        return parts[0];
    }

    public void loadFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String courseID = courseID(line);
                String studentID = studentID(line);

                registrationManager.registerClass(studentID, courseID);
            }
            System.out.println("Registrations loaded successfully");
        } catch (IOException e)  {
            System.out.println(e.getMessage());
        } catch (InvalidRegistrationException e) {
            System.out.println(e.getMessage());
        }
    }
}
