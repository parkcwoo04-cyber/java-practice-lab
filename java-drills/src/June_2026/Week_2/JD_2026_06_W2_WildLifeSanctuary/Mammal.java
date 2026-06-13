package src.June_2026.Week_2.JD_2026_06_W2_WildLifeSanctuary;

public class Mammal extends Animal {
    private String furCondition;

    public Mammal(String name, int age, int healthScore, boolean fedToday, String furCondition) {
        super(name, age, healthScore, fedToday);
        this.furCondition = furCondition;
    }

    public String getFurCondition() {
        return furCondition;
    }

    @Override
    public void provideCare() {
        System.out.println("Grooming " + getName() + " and checking body condition.");
        if (getHealthScore() >= 90) {
            setHealthScore(100);
        } else {
            setHealthScore(getHealthScore() + 10);
        }
    }

    @Override
    public String getCareType() {
        return "Grooming and body check";
    }
}
