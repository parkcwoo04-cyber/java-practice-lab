

## Metadata

Drill ID: JD_2026_06_W2_ShelterRescueMission Reflection

Linked code: 
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W2_ShelterRescueMission/ShelterRescueMission.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W2_ShelterRescueMission/Animal.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W2_ShelterRescueMission/RescueUnit.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W2_ShelterRescueMission/MedicalUnit.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W2_ShelterRescueMission/TransportUnit.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W2_ShelterRescueMission/AnimalStatus.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W2_ShelterRescueMission/UrgencyLevel.java)

Difficulty: Level 4 — Creative OOP Challenge

Estimated Time: 90–120 minutes

Actual Time Taken: 97 minutes

Written by: Chanwoo Park

---

## Goal

The goal of this exercise was to build an object-oriented rescue coordination system for an animal shelter. The program used `Animal` objects, `AnimalStatus` and `UrgencyLevel` enums, a parent `RescueUnit` class, and child classes such as `MedicalUnit` and `TransportUnit`. The main program stored different rescue units in a `RescueUnit[]` array and used polymorphism to process the same `Animal[]` array through different overridden `handleAnimal()` methods.

---

## What I Tried

- Created multiple classes to separate responsibilities:
  - `Animal` stores animal data and state.
  - `RescueUnit` represents the common parent type.
  - `MedicalUnit` handles treatment logic.
  - `TransportUnit` handles transport logic.
- Used inheritance with `MedicalUnit extends RescueUnit` and `TransportUnit extends RescueUnit`.
- Used method overriding so each unit could define its own `handleAnimal()` behavior.
- Used a `RescueUnit[]` array to practice polymorphism.
- Used `enum` values for animal status and urgency level.
- Used helper methods such as `isCritical()` and `needCare()` to make condition checks easier.
- Used loops to process multiple animals and produce summary counts.
- Used String methods for searching by species or keyword.

---

## Mistakes

The main mistake was using `ordinal()` to compare enum values:

```java
if (animal.getStatus().ordinal() == 1 || animal.getStatus().ordinal() == 2) {
    animal.setStatus(AnimalStatus.RECOVERING);
}
```

instead of comparing the enum constants directly:

```java
if (animal.getStatus() == AnimalStatus.INJURED ||
    animal.getStatus() == AnimalStatus.CRITICAL) {
    animal.setStatus(AnimalStatus.RECOVERING);
}
```

This code worked only because the current enum order was:

```java
HEALTHY, INJURED, CRITICAL, RECOVERING
```

However, the logic depends on the position of each enum constant. If the enum order changes later, the meaning of `1` and `2` also changes. This makes the code harder to read and less safe.

A second important issue was missing `null` handling. The assignment required the animal array to include one `null` element, but the loops and `handleAnimal()` methods did not consistently check for `null` before accessing animal methods. If a `null` value is added to the array, code such as this can cause a `NullPointerException`:

```java
unit.handleAnimal(animal);
```

and inside the method:

```java
animal.getStatus();
```

A third issue was that the `Animal` constructor directly assigned values to fields instead of using setter methods:

```java
this.name = name;
this.species = species;
this.age = age;
```

Because of this, validation rules such as trimming names, replacing empty names with `"Unknown"`, and preventing negative ages were not fully enforced.

---

## Why the Mistake Happened

The enum mistake happened because `ordinal()` looks like a convenient way to compare enum values numerically. However, an enum is meant to express a fixed set of meaningful names, not numeric positions. The logic should depend on `AnimalStatus.INJURED` or `AnimalStatus.CRITICAL`, not on whether those values happen to be stored at index `1` or `2`.

The null-handling issue happened because the program was tested mainly with valid `Animal` objects. Since every element in the initial array was a real object, the code appeared to work. However, reference-type variables can also store `null`, which means they do not point to any object. Calling a method through a `null` reference causes a runtime error.

The validation issue happened because the constructor bypassed the setter methods. The class had fields and methods, but the object was not fully protecting its own data during initialization. This weakens encapsulation because invalid or unprocessed data can enter the object when it is created.

---

## Improved Solution

Improve enum comparison in `MedicalUnit`:

```java
@Override
public void handleAnimal(Animal animal) {
    if (animal == null) {
        return;
    }

    if (animal.getStatus() == AnimalStatus.INJURED ||
        animal.getStatus() == AnimalStatus.CRITICAL) {
        System.out.println(unitID + " treated " + animal.getName()
                + ". Status changed to RECOVERING.");
        animal.setStatus(AnimalStatus.RECOVERING);
    }
}
```

Improve enum comparison in `TransportUnit`:

```java
@Override
public void handleAnimal(Animal animal) {
    if (animal == null) {
        return;
    }

    if (animal.getStatus() == AnimalStatus.HEALTHY ||
        animal.getStatus() == AnimalStatus.RECOVERING) {
        System.out.println(unitID + " transported " + animal.getName() + ".");
        animal.setIsTransported(true);
    } else {
        System.out.println(unitID + " cannot transport " + animal.getName()
                + " because status is " + animal.getStatus() + ".");
    }
}
```

Improve constructor validation in `Animal`:

```java
public Animal(String name, String species, int age,
              AnimalStatus status, UrgencyLevel level) {
    setName(name);
    setSpecies(species);
    setAge(age);
    setStatus(status);
    setLevel(level);
    this.isTransported = false;
}
```

Improve `setName()`:

```java
public void setName(String name) {
    if (name == null || name.trim().isEmpty()) {
        this.name = "Unknown";
    } else {
        this.name = name.trim();
    }
}
```

Improve `setAge()`:

```java
public void setAge(int age) {
    if (age < 0) {
        this.age = 0;
    } else {
        this.age = age;
    }
}
```

These changes make the code safer because the program no longer depends on enum order, avoids `NullPointerException`, and protects object data at the moment of construction.

---

## What I Learned

I learned that enum values should usually be compared directly by name instead of by `ordinal()`. Direct enum comparison makes the code clearer and safer because the logic matches the real meaning of the value.

I also learned that reference-type arrays can contain `null`, so loops that process objects should check for `null` before calling methods. This is especially important in object-oriented programs where many methods receive objects as parameters.

Finally, I learned that constructors should use setter methods when the setters contain validation logic. This keeps object initialization consistent with later updates and improves encapsulation.

Next time, I should debug with edge cases, not only normal data. I should test:
- an array containing `null`
- empty or spaced names
- negative ages
- each possible enum status
- whether status changes affect later objects through reference-type behavior

---

## Related Java Concepts

- [[9. Class part 2#Enum|Enum]]
- [[9. Class part 2#Reference Type|Reference Type]]
- [[9. Class part 2#Null and NullPointerException|Null Pointer Exception]]
- [[8. Class part 1#Getter and Setter|Getter and Setter]]
- [[9. Class part 2#Inheritance|Inheritance]]
- [[9. Class part 2#Method Overriding|Method Overriding]]
- [[9. Class part 2#Polymorphism|Polymorphism]]
- [[6. Array|Array]]
