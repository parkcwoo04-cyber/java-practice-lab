package src.June_2026.Week_4.JD_2026_06_W4_StudentCourseRegistrationApp;

public class RegistrationRequest {
    private String requestID;
    private String studentID;
    private String courseID;
    private RegistrationStatus status;
    private String rejectionReason;
    private Student student;
    private Course course;

    public RegistrationRequest(String requestID, String studentID, String courseID) {
        this.requestID = requestID;
        this.studentID = studentID;
        this.courseID = courseID;
        this.status = RegistrationStatus.PENDING;
        this.rejectionReason = "";
        this.student = null;
        this.course = null;
    }

    public void showRegistrationRequest() {
        System.out.printf("[%s] %s | %s | %s\n", status, requestID, studentID, courseID);
    }

    public void markApproved() {
        setStatus(RegistrationStatus.APPROVED);
    }

    public void markRejected() {
        setStatus(RegistrationStatus.REJECTED);
    }

    public void markWaitlisted() {
        setStatus(RegistrationStatus.WAITLISTED);
    }

    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public RegistrationStatus getStatus() {
        return status;
    }

    public void setStatus(RegistrationStatus status) {
        this.status = status;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getCourseID() {
        return courseID;
    }
}
