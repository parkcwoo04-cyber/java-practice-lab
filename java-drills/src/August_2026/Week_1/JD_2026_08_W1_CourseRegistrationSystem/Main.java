package src.August_2026.Week_1.JD_2026_08_W1_CourseRegistrationSystem;

public class Main {
    public static void main(String[] args) throws InvalidRegistrationException {
        RegistrationManager manager = new RegistrationManager();

        System.out.println("=== Student Registrations ===");
        addStudent(manager, "S001", "Daniel");
        addStudent(manager, "S002", "Alice");
        addStudent(manager, "S003", "Bob");
        addStudent(manager, "S003", "Kimmy");
        addStudent(manager, "S004", "John");
        addStudent(manager, "S005", "Michael");
        System.out.println();

        System.out.println("=== Course Registrations ===");
        addCourse(manager, "MATH211", "Mathematics", 10, "Calculus 1", CourseType.ONLINE, "Boiler/MATH211");
        addCourse(manager, "PHYS201", "Physics", 8, "Intro to Physics", CourseType.ONLINE, "Boiler/PHYS201");
        addCourse(manager, "PSYC101", "Psychology", 12, "Basic Psychology", CourseType.OFFLINE, "C103");
        addCourse(manager, "PSYC101", "Psychology", 12, "Basic Psychology", CourseType.ONLINE, "C103");
        addCourse(manager, "POSC209", "Political Science", 20, "American Governemnt", CourseType.OFFLINE, "B209");
        System.out.println();

        System.out.println("=== Registrations ===");
        registerCourse(manager, "S001", "MATH211");
        registerCourse(manager, "S002", "Physics");
        registerCourse(manager, "S999", "Mathematics");
        registerCourse(manager, "S001", "MATH211");
        registerCourse(manager, "S001", "PHYS201");
        registerCourse(manager, "S001", "POSC209");
        registerCourse(manager, "S002", "MATH211");
        registerCourse(manager, "S002", "PSYC101");
        registerCourse(manager, "S003", "PHYS201");
        registerCourse(manager, "S003", "POSC209");
        registerCourse(manager, "S004", "MATH211");
        registerCourse(manager, "S004", "PHYS201");
        registerCourse(manager, "S005", "POSC209");
        System.out.println();

        System.out.println("=== Student Summary ===");
        manager.printStudents();
        System.out.println();

        System.out.println("=== Course Summary ===");
        manager.printCourseSummary();

        findPopularCourse(manager);
        manager.printStatistics();

    }

    private static void addStudent(RegistrationManager manager, String studentID, String studentName) {
        try {
            Student student = new Student(studentID, studentName);
            manager.addStudent(student);
        } catch (InvalidRegistrationException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void addCourse(RegistrationManager manager, String code, String subject, int openSeats, String description, CourseType courseType, String meetingURLOrClassroom) {
        Course course = null;
        try {
            if (courseType == CourseType.ONLINE) {
                course = new OnlineCourse(code, subject, openSeats, description, meetingURLOrClassroom);
            } else if (courseType == CourseType.OFFLINE) {
                course = new OfflineCourse(code, subject, openSeats, description, meetingURLOrClassroom);
            }
            if (course != null) {
                manager.addCourse(course);
            }
        } catch (InvalidRegistrationException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void registerCourse(RegistrationManager manager, String studentID, String courseID) {
        try {
            manager.registerClass(studentID, courseID);
        } catch (InvalidRegistrationException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void findPopularCourse(RegistrationManager manager) {
        Course course = manager.findMostPopularCourse();
        System.out.println("=== Most Popular Course ===");
        System.out.println(course.getCode() + " (" + course.getStudents() + " students)");
        System.out.println();
    }
}
