

## Metadata

Drill ID: JD_2026_06_W2_DisasterResponseSystem

Linked code:
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W2_DisasterResponseSystem/DisasterResponseMain.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W2_DisasterResponseSystem/EmergencyUnit.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W2_DisasterResponseSystem/FireUnit.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W2_DisasterResponseSystem/MedicalUnit.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W2_DisasterResponseSystem/RescueUnit.java)

Difficulty: Level 2 — Multi-Class Application

Estimated Time: 45–60 minutes

Actual Time Taken: 43 minutes

Written by: Chanwoo Park

---

## Goal

The goal of this exercise was to build a small disaster response system using an `abstract class`, inheritance, overriding, an object array, and a loop. `EmergencyUnit` works as the abstract parent class, while `FireUnit`, `MedicalUnit`, and `RescueUnit` provide different response behaviors through overriding.

---

## What I Tried

- Created an abstract parent class named `EmergencyUnit`.
- Stored shared fields such as `unitName`, `location`, and `readiness` in the parent class.
- Used a constructor in the parent class and called it from child classes using `super(...)`.
- Created child classes such as `FireUnit`, `MedicalUnit`, and `RescueUnit`.
- Overrode `respond()` differently in each child class.
- Created an `EmergencyUnit[]` array to store different child objects together.
- Used a loop to process all units.
- Counted units whose readiness was greater than or equal to `80`.

---

## Mistakes

The main mistake was that the overridden `respond()` method was created correctly, but it was not called inside the loop.

```java
for (EmergencyUnit unit : units) {
    unit.showBasicInfo();
    System.out.println();
}
```

instead of:

```java
for (EmergencyUnit unit : units) {
    unit.showBasicInfo();
    unit.respond();
    System.out.println();
}
```

Because `respond()` was not called, the program did not fully demonstrate the main purpose of the abstract method. Each child class had its own response behavior, but the `main` method only printed basic information.

A second design issue was that `countReadyUnits()` directly printed the result instead of returning the count.

```java
public static void countReadyUnits(EmergencyUnit[] units) {
    int count = 0;
    for (EmergencyUnit unit : units) {
        if(unit.getReadiness() >= 80 ) {
            count++;
        }
    }

    System.out.println("Ready units: " + count);
}
```

This works for output, but it makes the method less reusable because the result cannot be stored, compared, or used by another method.

---

## Why the Mistake Happened

The main concept that was not fully connected was **polymorphism through an abstract parent type**.

The child classes correctly override `respond()`, but overriding only becomes visible when the method is actually called. Since the array type is `EmergencyUnit[]`, each element is handled as the parent type. However, when `unit.respond()` is called, Java runs the child class version depending on the actual object type.

This is the key flow:

```java
EmergencyUnit unit = new FireUnit(...);
unit.respond(); // FireUnit's respond() runs
```

So the design was mostly correct, but the execution flow did not use the abstract method at the point where polymorphism should happen.

For `countReadyUnits()`, the issue was method responsibility. A method that counts values usually should return the count. Printing can be done separately in `main`. This separates calculation logic from output logic.

---

## Improved Solution

Change the main loop to call both the shared method and the overridden abstract method:

```java
for (EmergencyUnit unit : units) {
    unit.showBasicInfo();
    unit.respond();
    System.out.println();
}
```

This improvement allows the parent type array to execute child-specific behavior.

Change `countReadyUnits()` to return an `int`:

```java
public static int countReadyUnits(EmergencyUnit[] units) {
    int count = 0;

    for (EmergencyUnit unit : units) {
        if (unit.getReadiness() >= 80) {
            count++;
        }
    }

    return count;
}
```

Then print the result in `main`:

```java
int readyCount = countReadyUnits(units);
System.out.println("Ready units: " + readyCount);
```

This makes the method more reusable and keeps the calculation separate from console output.

A smaller cleanup is that `FireUnit` does not need to override `showBasicInfo()` if it only calls the parent method without adding new behavior.

```java
@Override
public void showBasicInfo() {
    super.showBasicInfo();
}
```

This can be removed unless the child class needs to add extra information.

---

## What I Learned

An `abstract class` can define common fields and normal methods while forcing child classes to complete abstract methods. In this exercise, `EmergencyUnit` provided shared data and `showBasicInfo()`, while each child class completed `respond()` differently.

I also learned that overriding a method is not enough by itself. To test polymorphism, I need to call the overridden method through the parent type reference, especially inside an array or loop.

For method design, I should distinguish between a method that **calculates and returns a value** and a method that **prints output**. Returning a value usually makes the method more flexible and easier to reuse.

Next time, I should check this debugging flow:

1. Did I create the required abstract method?
2. Did every concrete child class override it?
3. Did I call the overridden method through the parent type?
4. Does each method have one clear responsibility?

---

## Related Java Concepts

- [[10. Abstraction and Interface#Abstraction|Abstraction]]
- [[10. Abstraction and Interface#Abstract Method|Abstraction Method]]
- [[9. Class part 2#Inheritance|Inheritance]]
- [[9. Class part 2#Method Overriding|Method Overriding]]
- [[9. Class part 2#Polymorphism|Polymorphism]]
- [[6. Array#Array list|Array]]

