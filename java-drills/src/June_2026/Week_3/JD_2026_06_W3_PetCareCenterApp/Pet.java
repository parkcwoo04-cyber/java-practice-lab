package src.June_2026.Week_3.JD_2026_06_W3_PetCareCenterApp;

public class Pet implements CareService {
    private String name;
    private int age;
    private int healthScore;

    public Pet(String name, int age, int healthScore) {
        this.name = name;
        this.age = age;
        this.healthScore = healthScore;
    }

    public void showInfo() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Health Score: " + healthScore);
        makeSound();
    }

    @Override
    public void receiveCare() {
        System.out.println("Providing care to " + getName());
        System.out.println(getName() + " received care.");
        setHealthScore(getHealthScore() + 15);
        if(getHealthScore() > 100) {
            setHealthScore(100);
            System.out.println(getName() + "'s health score is now 100.");
        } else {
            System.out.println(getName() + "'s health score is now " + getHealthScore() + ".");
        }
    }

    public void makeSound() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHealthScore() {
        return healthScore;
    }

    public void setHealthScore(int healthScore) {
        this.healthScore = healthScore;
    }
}
