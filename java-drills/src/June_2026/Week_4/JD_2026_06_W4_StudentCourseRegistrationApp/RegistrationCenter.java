package src.June_2026.Week_4.JD_2026_06_W4_StudentCourseRegistrationApp;

import java.util.ArrayList;

public class RegistrationCenter {
    private ArrayList<Student> students;
    private ArrayList<Course> courses;
    private ArrayList<RegistrationRequest> requests;

    public RegistrationCenter(ArrayList<Student> students, ArrayList<Course> courses, ArrayList<RegistrationRequest> requests) {
        this.students = students;
        this.courses = courses;
        this.requests = requests;
    }

    public void addStudent(Student student) {
        try {
            if (student.getStudentID() == null || student.getStudentID().isEmpty()) {
                throw new InvalidRegistrationException("Student ID cannot be empty.");
            } else if (student.getStudentName() == null || student.getStudentName().isEmpty()) {
                throw new InvalidRegistrationException("Student name cannot be null.");
            } else {
                System.out.println(student.getStudentID() + " has been successfully registered.");
                students.add(student);
            }
        } catch (InvalidRegistrationException e) {
            System.out.println(e.getMessage());
        }
    }

    public void registerCourse(Course course) {
        try {
            if (course.getCourseTitle() == null || course.getCourseTitle().isEmpty()) {
                throw new InvalidRegistrationException("Course title cannot be empty.");
            } else if (course.getCourseCode() == null || course.getCourseCode().isEmpty()) {
                throw new InvalidRegistrationException("Course code cannot be empty.");
            } else if (course.getMaxCapacity() <= 0) {
                throw new InvalidRegistrationException("Number of maximum capacity should be greater than 0.");
            } else {
                System.out.println(course.getCourseTitle() + " has been successfully registered.");
                courses.add(course);
            }
        } catch (InvalidRegistrationException e) {
            System.out.println(e.getMessage());
        }
    }

    public void showStudentInfo() {
        System.out.println("=== Student Lists ===");
        for (Student student : students) {
            student.showStudentInfo();
        }
        System.out.println();
    }

    public void showCourseInfo() {
        System.out.println("=== Course Lists ===");
        for (Course course : courses) {
            course.printCourseInfo();
        }
        System.out.println();
    }

    public void showRegistrationRequest() {
        System.out.println("=== Registration Requests ===");
        for (RegistrationRequest request : requests) {
            request.showRegistrationRequest();
        }
        System.out.println();
    }

    public void reportRegistrationRequest(RegistrationRequest registrationRequest) {
        try {
            if (registrationRequest.getRequestID() == null || registrationRequest.getRequestID().isEmpty()) {
                throw new InvalidRegistrationException("Request ID cannot be empty.");
            } else if (registrationRequest.getStudentID() == null || registrationRequest.getStudentID().isEmpty()) {
                throw new InvalidRegistrationException("Student ID cannot be empty.");
            } else if (registrationRequest.getCourseID() == null || registrationRequest.getCourseID().isEmpty()) {
                throw new InvalidRegistrationException("Course ID cannot be empty.");
            } else {
                if (validateRequest(registrationRequest)) {
                    System.out.println(registrationRequest.getRequestID() + " has been successfully registered.");
                    requests.add(registrationRequest);
                }
            }
        } catch (InvalidRegistrationException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean validateRequest(RegistrationRequest registrationRequest) {
        boolean studentValid = false;
        boolean courseValid = false;
        boolean isValid = false;
        try {
            for (Student student : students) {
                if (student.getStudentID().equals(registrationRequest.getStudentID())) {
                    studentValid = true;
                    registrationRequest.setStudent(student);
                    break;
                }
            }

            for (Course course : courses) {
                if (course.getCourseCode().equals(registrationRequest.getCourseID())) {
                    courseValid = true;
                    registrationRequest.setCourse(course);
                    break;
                }
            }


            if (!studentValid) {
                throw new InvalidRegistrationException("Student not found.");
            } else if (!courseValid) {
                throw new InvalidRegistrationException("Course not found.");
            }
            isValid = studentValid && courseValid;
        } catch (InvalidRegistrationException e) {
            System.out.println(e.getMessage());
        }

        return isValid;
    }

    public void processRegistrationRequest(RegistrationRequest registrationRequest) {
        Course course = null;
        try {
            for (Course c : courses) {
                if (c.getCourseCode().equals(registrationRequest.getCourseID())) {
                    course = c;
                }
            }
            Student student = null;
            for (Student s : students) {
                if (s.getStudentID().equals(registrationRequest.getStudentID())) {
                    student = s;
                }
            }
            if (!scanEnrolledCourses(registrationRequest)) {
                course.addStudent(student);
                registrationRequest.markApproved();
            } else {
                System.out.println(student.getStudentID() + " is already enrolled in " + course.getCourseCode() + ".");
                registrationRequest.markRejected();
            }
        } catch (InvalidRegistrationException e) {
            System.out.println(e.getMessage());
            registrationRequest.markWaitlisted();
            registrationRequest.setRejectionReason(e.getMessage());
        }
    }

    public void processAllRequests() {
        System.out.println("=== Processing All Requests ===");
        for (RegistrationRequest registrationRequest : requests) {
            processRegistrationRequest(registrationRequest);
        }
        System.out.println();
    }

    public boolean scanEnrolledCourses(RegistrationRequest registrationRequest) {
        boolean scanEnrolledCourses = false;

        Student student = registrationRequest.getStudent();
        Course course = registrationRequest.getCourse();
        ArrayList<Student> students = course.getEnrolledStudents();

        if (students.contains(student)) {
            scanEnrolledCourses = true;
        }

        return scanEnrolledCourses;
    }
}
