package src.June_2026.Week_2.JD_2026_06_W2_WildLifeSanctuary;

public abstract class Animal {
    private String name;
    private int age;
    private int healthScore;
    private boolean fedToday;

    public Animal(String name, int age, int healthScore, boolean fedToday) {
        this.name = name;
        this.age = age;
        this.healthScore = healthScore;
        this.fedToday = fedToday;
    }

    public void showBasicInfo() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Health Score: " + healthScore);
        System.out.println("Fed Today: " + fedToday);
    }

    public void feed() {
        if(!fedToday) {
            setFedToday(true);
            System.out.println(this.name + " has been fed");
        }
    }

    public boolean isCritical() {
        return healthScore < 50;
    }

    public String getName() {
        return name;
    }

    public abstract void provideCare();

    public abstract String getCareType();

    public int getAge() {
        return age;
    }
    public int getHealthScore() {
        return healthScore;
    }

    public boolean isFedToday() {
        return fedToday;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public void setHealthScore(int healthScore) {
        if (healthScore <= 100 && healthScore >= 0) {
            this.healthScore = healthScore;
        } else {
            System.out.println("Invalid Health Score");
        }
    }

    public void setFedToday(boolean fedToday) {
        this.fedToday = fedToday;
    }
}
