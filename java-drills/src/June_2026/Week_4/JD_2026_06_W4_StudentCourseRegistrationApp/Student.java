package src.June_2026.Week_4.JD_2026_06_W4_StudentCourseRegistrationApp;

import java.util.ArrayList;

public class Student {
    private String studentID;
    private String studentName;
    private ArrayList<Course> enrolledCourses;

    public Student(String studentID, String studentName) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.enrolledCourses = new ArrayList<>();
    }

    public void showStudentInfo() {
        System.out.printf("%s | %s", studentID, studentName);
        for (Course course : enrolledCourses) {
            System.out.print(" | " + course.getCourseCode());
        }
        System.out.print("\n");
    }

    public void addCourse(Course course) {
        enrolledCourses.add(course);
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
}
