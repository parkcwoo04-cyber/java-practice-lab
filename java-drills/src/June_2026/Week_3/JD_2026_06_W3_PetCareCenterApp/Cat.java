package src.June_2026.Week_3.JD_2026_06_W3_PetCareCenterApp;

public class Cat extends Pet implements CareService {
    private boolean indoorOnly;

    public Cat(String name, int age, int healthScore, boolean indoorOnly) {
        super(name, age, healthScore);
        this.indoorOnly = indoorOnly;
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println("Indoor Only: " + indoorOnly);
    }

    @Override
    public void makeSound(){
        System.out.println("Sound: Meow!");
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

    public boolean getIndoorOnly() {
        return indoorOnly;
    }

    public void setIndoorOnly(boolean indoorOnly) {
        this.indoorOnly = indoorOnly;
    }
}
