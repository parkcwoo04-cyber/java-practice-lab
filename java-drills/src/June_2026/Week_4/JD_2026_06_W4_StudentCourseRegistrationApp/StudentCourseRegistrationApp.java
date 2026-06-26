package src.June_2026.Week_4.JD_2026_06_W4_StudentCourseRegistrationApp;

import java.util.ArrayList;

public class StudentCourseRegistrationApp {
    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Course> courses = new ArrayList<>();
        ArrayList<RegistrationRequest> requests = new ArrayList<>();

        RegistrationCenter center = new RegistrationCenter(students, courses, requests);
        RegistrationFileManager manager = new RegistrationFileManager();

        System.out.println("=== Registering Students ===");
        center.addStudent(new Student("S-001", "Daniel"));
        center.addStudent(new Student("S-002", "Dylan"));
        center.addStudent(new Student("S-003", "Andrew"));
        center.addStudent(new Student("S-004", "Sarah"));
        center.addStudent(new Student("S-005", "Michelle"));
        System.out.println();

        center.showStudentInfo();

        System.out.println("=== Registering Courses ===");
        center.registerCourse(new Course("CS180", "Into CS", 10));
        center.registerCourse(new Course("CS181", "Advanced CS", 10));
        center.registerCourse(new Course("CS182", "CS evaluation", 1));
        center.registerCourse(new Course("CS183", "Advanced CS", -5));
        System.out.println();

        center.showCourseInfo();

        System.out.println("=== Registering Requests ===");
        center.reportRegistrationRequest(new RegistrationRequest("R-001", "S-001", "CS180"));
        center.reportRegistrationRequest(new RegistrationRequest("R-002", "S-002", "CS181"));
        center.reportRegistrationRequest(new RegistrationRequest("R-003", "S-003", "CS182"));
        center.reportRegistrationRequest(new RegistrationRequest("R-004", "S-004", "CS182"));
        center.reportRegistrationRequest(new RegistrationRequest("R-005", "S-001", "CS180"));
        System.out.println();

        center.showRegistrationRequest();

        center.processAllRequests();

        center.showRegistrationRequest();

        center.showCourseInfo();

        manager.saveRegistrationRecords(requests);

        manager.loadRegistrationRecords();
    }
}
