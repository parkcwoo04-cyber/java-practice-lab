package src.June_2026.Week_4.JD_2026_06_W4_StudyLogManager;

public class StudyLog {
    private String topic;
    private int minutes;
    private String reflection;

    public StudyLog(String topic, int minutes, String reflection) {
        this.topic = topic;
        this.minutes = minutes;
        this.reflection = reflection;
    }

    public void printLog() {
        System.out.println("Topic: " + this.topic);
        System.out.println("Minutes: " + this.minutes);
        System.out.println("Reflection: " + this.reflection);
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public String getReflection() {
        return reflection;
    }

    public void setReflection(String reflection) {
        this.reflection = reflection;
    }
}
