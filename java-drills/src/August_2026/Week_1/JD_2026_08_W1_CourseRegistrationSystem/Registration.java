package src.August_2026.Week_1.JD_2026_08_W1_CourseRegistrationSystem;

import java.time.LocalDate;

public class Registration {
    private Student student;
    private Course course;
    private final LocalDate registeredDate;

    public Registration(Student student, Course course) throws InvalidRegistrationException {
        if (course.getOpenSeats() <= 0) {
            throw new InvalidRegistrationException("This course is unavailable");
        }
        this.student = student;
        this.course = course;
        this.registeredDate = LocalDate.now();
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public LocalDate getRegisteredDate() {
        return registeredDate;
    }
}
