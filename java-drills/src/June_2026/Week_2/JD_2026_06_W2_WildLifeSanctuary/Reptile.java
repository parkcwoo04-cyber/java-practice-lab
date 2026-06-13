package src.June_2026.Week_2.JD_2026_06_W2_WildLifeSanctuary;

public class Reptile extends Animal {
    private boolean needsHeatLamp;

    public Reptile(String name, int age, int healthScore, boolean fedToday, boolean needsHeatLamp) {
        super(name, age, healthScore, fedToday);
        this.needsHeatLamp = needsHeatLamp;
    }

    @Override
    public void provideCare() {
        System.out.println("Checking " + getName() + "'s temperature.");
        if(needsHeatLamp){
            if (getHealthScore() >= 88) {
                setHealthScore(100);
            } else {
                setHealthScore(getHealthScore() + 12);
            }
        } else {
            if (getHealthScore() >= 94) {
                setHealthScore(100);
            } else {
                setHealthScore(getHealthScore() + 6);
            }
        }
    }

    @Override
    public String getCareType() {
        return "Temperature regulation";
    }
}
