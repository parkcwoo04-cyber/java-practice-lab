package src.August_2026.Week_1.JD_2026_08_W1_CourseRegistrationSystem;

public interface RegistrationService {
    public void addStudent(Student student) throws InvalidRegistrationException;
    public void addCourse(Course course) throws InvalidRegistrationException;
    public void registerClass(String studentID, String courseID) throws InvalidRegistrationException;
    public void printStudents();
    public Student findStudentByID(String studentID) throws InvalidRegistrationException;
    public Course findCourseByID(String courseID) throws InvalidRegistrationException;
    public void printStudentSummary();
    public void printCourseSummary();
    public Course findMostPopularCourse();
    public void printStatistics();

}
