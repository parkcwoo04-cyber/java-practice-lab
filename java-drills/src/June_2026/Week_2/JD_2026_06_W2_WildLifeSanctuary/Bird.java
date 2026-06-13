package src.June_2026.Week_2.JD_2026_06_W2_WildLifeSanctuary;

public class Bird extends Animal {
    private double wingSpan;

    public Bird(String name, int age, int healthScore, boolean fedToday, double wingSpan) {
        super(name, age, healthScore, fedToday);
        this.wingSpan = wingSpan;
    }

    @Override
    public void provideCare() {
        System.out.println("Checking " + getName() + "'s wings.");
        if (getHealthScore() >= 92) {
            setHealthScore(100);
        } else {
            setHealthScore(getHealthScore() + 8);
        }
    }

    @Override
    public String getCareType() {
        return "Wing inspection";
    }

    public double getWingSpan() {
        return wingSpan;
    }
}
