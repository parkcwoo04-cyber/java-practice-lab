package src.August_2026.Week_1.JD_2026_08_W1_CourseRegistrationSystem;

public class OfflineCourse extends Course {
    private String code;
    private String subject;
    private int openSeats;
    private String description;
    private String classroom;
    private CourseType courseType;

    public OfflineCourse(String code, String subject, int openSeats, String description, String classroom) throws InvalidRegistrationException {
        super(code, subject, openSeats, description);
        if (classroom == null || classroom.isBlank()) {
            throw new InvalidRegistrationException("Classroom cannot be blank.");
        }

        this.classroom = classroom;
        this.courseType = CourseType.ONLINE;
    }

    @Override
    public String printCourseInformation() {
        return getCode() + " | " + getSubject() + " | Open Seats: " + getOpenSeats() + " | Description: " + getDescription() + " | Current Enrolled Students: " + getStudents() + " | Classroom: " + getClassroom();
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }
}
