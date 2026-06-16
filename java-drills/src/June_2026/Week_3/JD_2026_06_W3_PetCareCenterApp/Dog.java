package src.June_2026.Week_3.JD_2026_06_W3_PetCareCenterApp;

public class Dog extends Pet implements CareService {
    private String favoriteToy;

    public Dog(String name, int age, int healthScore, String favoriteToy) {
        super(name, age, healthScore);
        this.favoriteToy = favoriteToy;
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println("Favorite toy: " + favoriteToy);
    }

    @Override
    public void makeSound() {
        System.out.println("Sound: Woof!");
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

    public String getFavoriteToy() {
        return favoriteToy;
    }

    public void setFavoriteToy(String favoriteToy) {
        this.favoriteToy = favoriteToy;
    }
}
