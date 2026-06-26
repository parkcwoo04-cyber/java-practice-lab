package src.June_2026.Week_4.JD_2026_06_W4_StudentCourseRegistrationApp;

import src.June_2026.Week_4.JD_2026_06_W4_CampusMaintenanceRequestApp.InvalidRequestException;

import java.util.ArrayList;

public class Course {
    private String courseCode;
    private String courseTitle;
    private int maxCapacity;
    private ArrayList<Student> enrolledStudents;
    private boolean available;

    public Course(String courseCode, String courseTitle, int maxCapacity) {
        this.courseCode = courseCode;
        this.courseTitle = courseTitle;
        this.maxCapacity = maxCapacity;
        this.enrolledStudents = new ArrayList<>();
        this.available = false;
    }

    public boolean isAvailable() {
        return enrolledStudents.size() < maxCapacity;
    }

    public void addStudent(Student student) throws InvalidRegistrationException {
        if (isAvailable()) {
            enrolledStudents.add(student);
            System.out.printf("%s has been enrolled for %s.\n", student.getStudentID(), this.courseCode);
        } else {
            throw new InvalidRegistrationException(courseCode + " is already full.");
        }
    }

    public int currentEnrolledStudentCount() {
        return enrolledStudents.size();
    }

    public void printCourseInfo() {
        System.out.printf("%s | %s | %d/%d\n", courseCode, courseTitle, enrolledStudents.size(), maxCapacity);
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean checkStudent(Student student) {
        return enrolledStudents.contains(student);
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public ArrayList<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void setEnrolledStudents(ArrayList<Student> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }

}
