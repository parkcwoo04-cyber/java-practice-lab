package src.August_2026.Week_1.JD_2026_08_W1_CourseRegistrationSystem;

public abstract class Course {
    private String code;
    private String subject;
    private int openSeats;
    private String description;
    private int students;
    private CourseType courseType;

    public Course(String code, String subject, int openSeats, String description) throws InvalidRegistrationException {
        if (code == null || code.isBlank()) {
            throw new InvalidRegistrationException("Code cannot be blank.");
        } else if (subject == null || subject.isBlank()) {
            throw new InvalidRegistrationException("Subject cannot be blank.");
        } else if (openSeats <= 0) {
            throw new InvalidRegistrationException("Open Seats should be greater than zero.");
        } else if (description == null || description.isBlank()) {
            throw new InvalidRegistrationException("Description cannot be blank.");
        }

        this.code = code;
        this.subject = subject;
        this.openSeats = openSeats;
        this.description = description;
        this.students = 0;
    }

    public abstract String printCourseInformation();

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getOpenSeats() {
        return openSeats;
    }

    public void setOpenSeats(int openSeats) {
        this.openSeats = openSeats;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }

    public void increaseStudents() {
        this.students++;
    }

    public int getStudents() {
        return students;
    }
}
