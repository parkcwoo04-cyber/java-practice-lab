public class GeneralHabit extends Habit {
    private String memo;

    public GeneralHabit(String title, HabitCategory category, int targetCount, String memo) {
        super(title, category, targetCount);
        this.memo = memo;
    }

    public void updateMemo(String newMemo) {
        this.memo = newMemo;
    }

    @Override
    String getHabitTypes() {
        return "General Habit";
    }

    @Override
    public String getProgressSummary() {
        return super.getProgressSummary()
                + " | Memo: " + memo;
    }

    public String getMemo() {
        return memo;
    }
}
