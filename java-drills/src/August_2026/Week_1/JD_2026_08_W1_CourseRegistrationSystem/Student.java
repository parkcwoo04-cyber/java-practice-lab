package src.August_2026.Week_1.JD_2026_08_W1_CourseRegistrationSystem;

import src.August_2026.Week_1.JD_2026_08_W1_WarehouseManagementSystem.InvalidProductException;
import src.August_2026.Week_1.JD_2026_08_W1_WarehouseManagementSystem.Product;

import java.util.ArrayList;

public class Student {
    private String studentID;
    private String studentName;
    private ArrayList<Course> registeredCourses;

    public Student(String studentID, String studentName) throws InvalidRegistrationException {
        if (studentID == null || studentID.isBlank()) {
            throw new InvalidRegistrationException("Student ID cannot be blank.");
        } else if (studentName == null || studentName.isBlank()) {
            throw new InvalidRegistrationException("Student Name cannot be blank.");
        }
        this.studentID = studentID;
        this.studentName = studentName;
        this.registeredCourses = new ArrayList<>();
    }

    public void registerCourse(Course course) throws InvalidRegistrationException {
        if (course.getOpenSeats() <= 0) {
            throw new InvalidRegistrationException("Course is unavailable.");
        }

        registeredCourses.add(course);
    }

    public String printStudentInformation() {
        StringBuilder studentInfo = new StringBuilder(studentID + " | " + studentName);
        if (!registeredCourses.isEmpty()) {
            studentInfo.append(" | ");
            for (Course course : registeredCourses) {
                studentInfo.append(course.getCode()).append(", ");
            }
            studentInfo.delete(studentInfo.length() - 2, studentInfo.length());
        }
        return studentInfo.toString();
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public ArrayList<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void setRegisteredCourses(ArrayList<Course> registeredCourses) {
        this.registeredCourses = registeredCourses;
    }

}
