public interface Trackable {
    void markComplete();

    double calculateCompletionRate();

    boolean inComplete();

    String getProgressSummary();
}
