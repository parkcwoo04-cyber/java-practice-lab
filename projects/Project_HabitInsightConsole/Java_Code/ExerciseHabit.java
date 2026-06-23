public class ExerciseHabit extends Habit {
    private String exerciseType;
    private int  totalExerciseMinutes;

    public ExerciseHabit(String title, HabitCategory category, int targetCount, String exerciseType) {
        super(title, category, targetCount);
        this.exerciseType = exerciseType;
        this.totalExerciseMinutes = 0;
    }

    @Override
    public String getHabitTypes() {
        return "Exercise Habit";
    }

    public void addWorkOut(int minutes) {
        totalExerciseMinutes += minutes;
        markComplete();
    }

    public double getAverageWorkoutMinutes() {
        if (getCompletedCount() == 0) {
            return 0.0;
        }

        return (double) totalExerciseMinutes / getCompletedCount();
    }

    @Override
    public String getProgressSummary() {
        return super.getProgressSummary()
                + " | Exercise: " + exerciseType
                + " | Total Exercise Minutes: " + totalExerciseMinutes
                + " | Avg Minutes: " + String.format("%.1f", getAverageWorkoutMinutes());
    }

    public String getExerciseType() {
        return exerciseType;
    }
}
