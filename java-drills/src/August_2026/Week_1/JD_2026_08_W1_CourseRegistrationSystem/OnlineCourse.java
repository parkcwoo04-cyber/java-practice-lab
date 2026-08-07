package src.August_2026.Week_1.JD_2026_08_W1_CourseRegistrationSystem;

public class OnlineCourse extends Course {
    private String code;
    private String subject;
    private int openSeats;
    private String description;
    private String meetingURL;
    private CourseType courseType;

    public OnlineCourse(String code, String subject, int openSeats, String description, String meetingURL) throws InvalidRegistrationException {
        super(code, subject, openSeats, description);
        if (meetingURL == null || meetingURL.isBlank()) {
            throw new InvalidRegistrationException("Meeting URL cannot be blank.");
        }
        this.meetingURL = meetingURL;
        this.courseType = CourseType.ONLINE;
    }

    @Override
    public String printCourseInformation() {
        return getCode() + " | " + getSubject() + " | Open Seats: " + getOpenSeats() + " | Description: " + getDescription() + " | Current Enrolled Students: " + getStudents() + " | Meeting URL: " + getMeetingURL();
    }

    public String getMeetingURL() {
        return meetingURL;
    }
}
