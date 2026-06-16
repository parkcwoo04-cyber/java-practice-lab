

## Metadata

Drill ID: JD_2026_06_W3_PetCareCenterApp

Linked code:
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W3_PetCareCenterApp/CareService.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W3_PetCareCenterApp/Pet.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W3_PetCareCenterApp/Dog.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W3_PetCareCenterApp/Cat.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W3_PetCareCenterApp/PetCareCenter.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W3_PetCareCenterApp/PetCareCenterApp.java)

Difficulty: Level 2 — Multi-Class Application

Estimated Time: 45–60 minutes

Actual Time Taken: 43 minutes

Written by: Chanwoo Park

---

## Goal

The goal of this program was to build a small object-oriented pet care center application. The program uses a `Pet` parent class, `Dog` and `Cat` child classes, a `CareService` interface, and an `ArrayList<Pet>` to manage multiple pet objects. The main practice points were inheritance, method overriding, interface implementation, polymorphism, collection traversal, searching by name, and updating object state.

---

## What I Tried

- Created a `Pet` parent class with common fields: `name`, `age`, and `healthScore`.
- Created `Dog` and `Cat` classes that extend `Pet`.
- Added child-specific fields:
  - `Dog`: `favoriteToy`
  - `Cat`: `indoorOnly`
- Used method overriding for `showInfo()` and `makeSound()`.
- Created a `CareService` interface with a `receiveCare()` method.
- Used `ArrayList<Pet>` in `PetCareCenter` to store both `Dog` and `Cat` objects together.
- Used enhanced `for` loops to display, search, and update pets.
- Used `equalsIgnoreCase()` to make name search more flexible.
- Updated `healthScore` after care and prevented it from going above `100`.

---

## Mistakes

The main design mistake was:

```java
public class Pet implements CareService {
    ...
}
```

instead of:

```java
public class Pet {
    ...
}
```

and:

```java
public class Dog extends Pet implements CareService {
    ...
}

public class Cat extends Pet implements CareService {
    ...
}
```

The code compiled and ran, but the design weakened the purpose of the interface. Since `Pet` implemented `CareService`, every `Pet` object automatically became a care-service object. This made the interface less meaningful because the program no longer needed to check whether a specific pet actually supported `CareService`.

A related issue appeared in `provideCare()`:

```java
void provideCare(String keyword) {
    for (Pet pet : pets) {
        if (pet.getName().equalsIgnoreCase(keyword)) {
            pet.receiveCare();
        }
    }
    System.out.println();
}
```

This worked only because `Pet` implemented `CareService`. However, the intended practice was to use polymorphism with an interface type by checking and casting:

```java
if (pet instanceof CareService) {
    CareService carePet = (CareService) pet;
    carePet.receiveCare();
}
```

Another smaller issue was that `provideCare()` did not handle the case where the pet name was not found. Unlike `searchPet()`, it did not use a `found` flag.

---

## Why the Mistake Happened

The mistake happened because inheritance and interface roles were mixed together.

The `Pet` class should represent the common identity and shared state of all pets. It should contain common fields and shared behavior such as `name`, `age`, `healthScore`, `showInfo()`, and possibly `makeSound()`.

The `CareService` interface should represent an extra ability or rule: objects that implement it must provide `receiveCare()`.

By writing `Pet implements CareService`, the parent class was given responsibility for care behavior. This reduced the need for the child classes to express their own care behavior through the interface.

The key concept is that a parent class and an interface have different roles. A parent class defines what child classes are. An interface defines what an object can do.

---

## Improved Solution

Change `Pet` so it does not implement `CareService`:

```java
public class Pet {
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

    public void makeSound() {
    }

    // getters and setters
}
```

Keep the interface implementation in the child classes:

```java
public class Dog extends Pet implements CareService {
    @Override
    public void receiveCare() {
        System.out.println("Providing care to " + getName());
        setHealthScore(getHealthScore() + 15);

        if (getHealthScore() > 100) {
            setHealthScore(100);
        }

        System.out.println(getName() + "'s health score is now " + getHealthScore() + ".");
    }
}
```

Then update `provideCare()` to use `instanceof`, casting, and a failed-search message:

```java
public void provideCare(String keyword) {
    boolean found = false;

    for (Pet pet : pets) {
        if (pet.getName().equalsIgnoreCase(keyword)) {
            found = true;

            if (pet instanceof CareService) {
                CareService carePet = (CareService) pet;
                carePet.receiveCare();
            }
        }
    }

    if (!found) {
        System.out.println(keyword + " was not found.");
    }

    System.out.println();
}
```

This version makes the interface role clearer. The `ArrayList` still stores objects as `Pet`, but care behavior is accessed through the `CareService` interface only when the object supports that interface.

---

## What I Learned

I learned that code can compile and still have a design problem. The original program worked, but `Pet implements CareService` made the interface less useful because the parent class took responsibility that should belong to the child classes.

I also learned that `ArrayList<Pet>` can store different child objects together, but when I want to use an interface-specific method, I should check the actual object type first:

```java
if (pet instanceof CareService) {
    CareService carePet = (CareService) pet;
    carePet.receiveCare();
}
```

This is an important debugging habit: when using inheritance and interfaces together, I should ask:

1. Is this behavior common to all objects in the parent class?
2. Or is this an ability that should be expressed through an interface?
3. Am I using the parent type for shared structure and the interface type for specific behavior?

Next time, I should separate class responsibility before writing methods. The parent class should contain common data and behavior, while interfaces should represent additional abilities.

---

## Related Java Concepts

- [[8. Class part 1#Class|Class]]
- [[9. Class part 2#Inheritance|Inheritance]]
- [[10. Abstraction and Interface#Interface|Interface]]
- [[9. Class part 2#Method Overriding|Method Overriding]]
- [[9. Class part 2#Polymorphism|Polymorphism]]
- [[9. Class part 2#Reference Type|Reference Type]]
- [[11. Collection Famework#ArrayList|ArrayList]]

