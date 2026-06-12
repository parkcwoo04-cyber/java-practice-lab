package src.June_2026.Week_2.ShelterRescueMission;

public class Animal {
    private String name;
    private String species;
    private int age;
    private AnimalStatus status;
    private UrgencyLevel level;
    private boolean isTransported;

    public Animal(String name, String species, int age, AnimalStatus status, UrgencyLevel level) {
        this.name = name;
        this.species = species;
        this.age = age;
        this.status = status;
        this.level = level;
        this.isTransported = false;
    }

    public boolean isCritical() {
        return status == AnimalStatus.CRITICAL;
    }

    public boolean needCare() {
        if(status == AnimalStatus.CRITICAL) {
            return true;
        } else if(status == AnimalStatus.INJURED) {
            return true;
        }
        return false;
    }

    public void searchBySpecies(String species) {
        if (species.equalsIgnoreCase(this.species)) {
            System.out.println(this.name + " / " + this.species + " / " + this.status);
        }
    }

    public void searchKeyword(String keyword) {
        if (this.name.contains(keyword)) {
            System.out.println(this.name + " / " + this.species + " / " + this.status);
        }
    }

    public void printFinalReport() {
        System.out.println(this.name + " | " + this.species + " | " + this.status + " | transported: " + this.isTransported);
    }

    public String getName() {
        return name.trim();
    }

    public String getSpecies() {
        return species.trim();
    }

    public int getAge() {
        return age;
    }

    public AnimalStatus getStatus() {
        return status;
    }

    public UrgencyLevel getLevel() {
        return level;
    }

    public boolean isTransported() {
        return isTransported;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setStatus(AnimalStatus status) {
        this.status = status;
    }

    public void setLevel(UrgencyLevel level) {
        this.level = level;
    }

    public void setIsTransported(boolean isTransported) {
        this.isTransported = isTransported;
    }
}
