

## Metadata

Drill ID: JD_2026_06_W2_WildLifeSanctuary

Linked code: 
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W2_WildLifeSanctuary/WildLifeSanctuaryMain.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W2_WildLifeSanctuary/Animal.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W2_WildLifeSanctuary/Bird.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W2_WildLifeSanctuary/Mammal.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W2_WildLifeSanctuary/Reptile.java)

Difficulty: Level 3 — Object-Oriented Problem Solving

Estimated Time: 60–90 minutes

Actual Time Taken: 65 minutes

Written by: Chanwoo Park

---

## Goal

The goal of this exercise was to build a wildlife sanctuary care system using an `abstract class`, inheritance, overriding, arrays, loops, and state update logic. The main structure used an abstract `Animal` parent class, concrete child classes such as `Bird`, `Mammal`, and `Reptile`, and an `Animal[]` array to process different animal types through one common parent type.

---

## What I Tried

- Created an abstract parent class named `Animal`.
- Stored common fields such as `name`, `age`, `healthScore`, and `fedToday` in the parent class.
- Used a constructor in `Animal` and called it from child classes using `super(...)`.
- Declared abstract methods `provideCare()` and `getCareType()`.
- Overrode `provideCare()` differently in `Bird`, `Mammal`, and `Reptile`.
- Used an `Animal[]` array to store different child objects together.
- Used an enhanced `for` loop to call `showBasicInfo()`, `feed()`, and `provideCare()` on each animal.
- Added methods to count critical animals, find the lowest health animal, and filter animals by care type.

---

## Mistakes

The main design issue was that some helper methods performed both calculation/search and printing directly.

```java
public static void countCriticalAnimals(Animal[] animals) {
    int count = 0;
    for (Animal animal : animals) {
        if(animal.isCritical()) {
            count++;
        }
    }
    System.out.println("Critical animals: " + count);
}
```

instead of:

```java
public static int countCriticalAnimals(Animal[] animals) {
    int count = 0;

    for (Animal animal : animals) {
        if (animal.isCritical()) {
            count++;
        }
    }

    return count;
}
```

A similar issue appeared in the lowest-health search method.

```java
public static void foundLowestHealthAnimal(Animal[] animals) {
    Animal lowestHealth = animals[0];

    for (Animal animal : animals) {
        if(animal.getHealthScore() < lowestHealth.getHealthScore()) {
            lowestHealth = animal;
        }
    }

    lowestHealth.showBasicInfo();
}
```

instead of:

```java
public static Animal findLowestHealthAnimal(Animal[] animals) {
    Animal lowestHealth = animals[0];

    for (Animal animal : animals) {
        if (animal.getHealthScore() < lowestHealth.getHealthScore()) {
            lowestHealth = animal;
        }
    }

    return lowestHealth;
}
```

The original code works, but the methods are less reusable because the result is printed immediately instead of being returned.

---

## Why the Mistake Happened

This happened because the method design focused on producing console output rather than separating responsibilities.

In Java, a method that calculates or searches for a value should often return that value. This makes the method reusable in other situations. For example, if `countCriticalAnimals()` returns an `int`, the result can be printed, compared, stored, or used in another condition.

This connects to the concept of [[Method Return Value]] and [[Method Responsibility]]. A method should usually have one clear role:

- calculate and return a result
- search and return an object
- print information to the console

Mixing these roles is not always wrong, but it makes the design weaker as the program grows.

---

## Improved Solution

Change the critical animal counting method to return an `int`.

```java
public static int countCriticalAnimals(Animal[] animals) {
    int count = 0;

    for (Animal animal : animals) {
        if (animal.isCritical()) {
            count++;
        }
    }

    return count;
}
```

Then print the result in `main`.

```java
int criticalCount = countCriticalAnimals(animals);
System.out.println("Critical animals: " + criticalCount);
```

Change the lowest-health method to return an `Animal`.

```java
public static Animal findLowestHealthAnimal(Animal[] animals) {
    Animal lowestHealth = animals[0];

    for (Animal animal : animals) {
        if (animal.getHealthScore() < lowestHealth.getHealthScore()) {
            lowestHealth = animal;
        }
    }

    return lowestHealth;
}
```

Then display the returned object in `main`.

```java
Animal lowest = findLowestHealthAnimal(animals);
lowest.showBasicInfo();
```

This improves the program because the search method now returns the object it found instead of deciding how the result must be displayed.

---

## What I Learned

The main concept I practiced was using an `abstract class` as a common parent type. `Animal` defined shared fields and required methods, while `Bird`, `Mammal`, and `Reptile` completed the abstract behavior through overriding.

I also practiced polymorphism. Even though the array type was `Animal[]`, calling `animal.provideCare()` executed the correct child class version depending on the actual object.

The main design lesson is that helper methods should not always print directly. When a method counts something, it is usually better to return an `int`. When a method searches for an object, it is usually better to return that object. This makes the code easier to reuse, test, and modify.

Next time, I should check each method and ask:

Does this method calculate, search, update, or print?

If the method calculates or searches, I should consider using a return value instead of printing inside the method.

---

## Related Java Concepts

- [[10. Abstraction and Interface#Abstraction|Abstraction]]
- [[10. Abstraction and Interface#Abstraction|Abstraction Class]]
- [[10. Abstraction and Interface#Abstract Method|Abstraction Method]]
- [[9. Class part 2#Inheritance|Inheritance]]
- [[9. Class part 2#Method Overriding|Method Overriding]]
- [[9. Class part 2#Polymorphism|Polymorphism]]
- [[5. Loop Statement#for Loop|for Loop Statement]]
- [[7. Method#Return value|Return Value]]

