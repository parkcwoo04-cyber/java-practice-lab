package src.August_2026.Week_1.JD_2026_08_W1_CourseRegistrationSystem;

import java.util.ArrayList;

public class RegistrationManager implements RegistrationService {
    private ArrayList<Student> students;
    private ArrayList<Course> courses;
    private ArrayList<Registration> registrations;

    public RegistrationManager() {
        this.students = new ArrayList<>();
        this.courses = new ArrayList<>();
        this.registrations = new ArrayList<>();
    }

    @Override
    public void printStudents() {
        for (Student student : students) {
            System.out.println(student.printStudentInformation());
        }
    }

    @Override
    public void addStudent(Student student) throws InvalidRegistrationException {
        for (Student stud : students) {
            if (stud.getStudentID().equalsIgnoreCase(student.getStudentID())) {
                throw new InvalidRegistrationException("Student ID already exists");
            }
        }
        this.students.add(student);
        System.out.println(student.getStudentID() + " has been successfully registered.");
    }

    @Override
    public void addCourse(Course course) throws InvalidRegistrationException {
        for (Course c : courses) {
            if (c.getCode().equalsIgnoreCase(course.getCode())) {
                throw new InvalidRegistrationException("Course code already exists");
            }
        }
        this.courses.add(course);
        System.out.println(course.getCode() + " has been added");
    }

    @Override
    public void registerClass(String studentID, String courseID) throws InvalidRegistrationException {
        Student student = findStudentByID(studentID);
        Course course = findCourseByID(courseID);

        Registration registration = new Registration(student, course);

        for (Registration r : registrations) {
            if (r.getCourse().getCode().equalsIgnoreCase(course.getCode())) {
                if (r.getStudent().getStudentID().equalsIgnoreCase(student.getStudentID())) {
                    throw new InvalidRegistrationException("This student has already registered for this course");
                }
            }
        }
        this.registrations.add(registration);
        student.registerCourse(course);
        course.setOpenSeats(course.getOpenSeats() - 1);
        course.increaseStudents();
        System.out.println(student.getStudentName() + " has been successfully registered for " + course.getCode());
    }

    @Override
    public Student findStudentByID(String studentID) throws InvalidRegistrationException {
        Student student = null;
        for (Student s : students) {
            if (s.getStudentID().equalsIgnoreCase(studentID)) {
                student = s;
            }
        }
        if (student == null) {
            throw new InvalidRegistrationException("Student ID does not exist");
        }
        return student;
    }

    @Override
    public Course findCourseByID(String courseID) throws InvalidRegistrationException {
        Course course = null;
        for (Course c : courses) {
            if (c.getCode().equalsIgnoreCase(courseID)) {
                course = c;
            }
        }
        if (course == null) {
            throw new InvalidRegistrationException("Course ID does not exist");
        }
        return course;
    }

    @Override
    public void printStudentSummary() {
        for (Student student : students) {
            System.out.println(student.printStudentInformation());
        }
        System.out.println();
    }

    @Override
    public void printCourseSummary() {
        for (Course course : courses) {
            System.out.println(course.printCourseInformation());
        }
        System.out.println();
    }

    @Override
    public Course findMostPopularCourse() {
        Course course = courses.get(0);

        for (Course c : courses) {
            if (c.getStudents() > course.getStudents()) {
                course = c;
            }
        }

        return course;
    }

    @Override
    public void printStatistics() {
        int totalStudents = students.size();
        int totalCourses = courses.size();
        int totalRegistrations = registrations.size();

        System.out.println("=== Statistics ===");
        System.out.println("Total students: " + totalStudents);
        System.out.println("Total courses: " + totalCourses);
        System.out.println("Total registrations: " + totalRegistrations);
        System.out.println();
    }

    public ArrayList<Registration> getRegistrations() {
        return registrations;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }
}
