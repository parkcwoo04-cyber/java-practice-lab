public class StudyHabit extends Habit {
    private String subject;
    private int totalStudyMinutes;

    public StudyHabit(String title, HabitCategory category, int targetCount, String subject) {
        super(title, category, targetCount);
        this.subject = subject;
        this.totalStudyMinutes = 0;

    }

    public void addStudySession(int minutes) {
        totalStudyMinutes += minutes;
        markComplete();
    }

    public double getAverageStudyMinutes() {
        if (getCompletedCount() == 0) {
            return 0.0;
        }

        return (double) totalStudyMinutes / getCompletedCount();
    }

    @Override
    public String getHabitTypes() {
        return "Study Habit";
    }

    @Override
    public String getProgressSummary() {
        return super.getProgressSummary()
                + " | Subject: " + subject
                + " | Total Study Minutes: " + totalStudyMinutes
                + " | Avg Minutes: " + String.format("%.1f", getAverageStudyMinutes());
    }
}
