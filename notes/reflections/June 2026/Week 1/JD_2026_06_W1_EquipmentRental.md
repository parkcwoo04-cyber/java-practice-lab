
Metadata

Drill ID: Level2_EquipmentRental

Linked code:
- [View Java Code](../../../../java-drills/src/June_2026/Week_1/JD_2026_06_W1_EquipmentRental/EquipmentRentalApp.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_1/JD_2026_06_W1_EquipmentRental/Equipment.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_1/JD_2026_06_W1_EquipmentRental/RentalDesk.java)

Difficulty: Level 2 — Multi-Class Application

Estimated Time: 45–60 minutes

Actual Time Taken: 42 minutes

Written by: Chanwoo Park

---

## Goal

The goal was to build a campus equipment rental system using three classes: `Equipment`, `RentalDesk`, and `EquipmentRentalApp`. The program stores multiple `Equipment` objects in an array, prints available equipment, rents equipment by code, returns rented equipment, and counts how many items are currently rented.

---

## What I Tried

- Created an `Equipment` class with private fields: `code`, `name`, `category`, and `isRented`.
- Used a constructor to initialize each equipment object.
- Created getter and setter methods to access and update equipment data safely.
- Created a `RentalDesk` class that receives an `Equipment[]` array through its constructor.
- Used enhanced `for` loops to search through the equipment array.
- Used `trim()` and `equalsIgnoreCase()` to compare equipment codes more flexibly.
- Created methods for printing available equipment, renting equipment, returning equipment, and counting rented equipment.
- Tested repeated rental, return, and rented count logic in `EquipmentRentalApp`.

---

## Mistakes

The main design issue was that the rental or return state was updated before the validation result was fully handled.

```java
if (code.trim().equalsIgnoreCase(equipment.getCode().trim())) {
    item = equipment.getName();
    rented = equipment.getIsRented();
    equipment.setRented(true);
}
```

A safer structure is:

```java
if (code.trim().equalsIgnoreCase(equipment.getCode().trim())) {
    if (!equipment.getIsRented()) {
        equipment.setRented(true);
        System.out.println(equipment.getName() + " has been rented.");
    } else {
        System.out.println("Rental failed. " + equipment.getName() + " is already rented.");
    }
    return;
}
```

The original code still worked for the tested cases because setting an already rented item to `true` again does not change the final state. However, the order of logic was weaker because the object state changed before the method decided whether the action should succeed.

A smaller design issue was:

```java
Equipment[] equipments;
```

instead of:

```java
private Equipment[] equipments;
```

Since `equipments` belongs to the internal state of `RentalDesk`, it should be hidden from outside classes.

---

## Why the Mistake Happened

The main issue came from mixing two responsibilities in the same loop:

1. finding the matching equipment
2. updating the rental state

In object-oriented design, a method should usually check whether an action is valid before changing the object's state. This is especially important for state transition logic such as:

- available → rented
- rented → available

The code correctly used an object array and loop search, but the update happened too early. This is related to [[Class]], [[Object]], [[Array]], [[Loop Statement]], and [[Encapsulation]].

The `equipments` array also should be `private` because `RentalDesk` should control how its internal equipment list is accessed and modified. This connects to the concept of information hiding.

---

## Improved Solution

Change the rental logic so the method updates the state only after confirming that the equipment exists and is currently available:

```java
public void rentEquipment(String code) {
    System.out.println("Rental request: " + code);

    for (Equipment equipment : equipments) {
        if (equipment.getCode().trim().equalsIgnoreCase(code.trim())) {
            if (!equipment.getIsRented()) {
                equipment.setRented(true);
                System.out.println(equipment.getName() + " has been rented.");
            } else {
                System.out.println("Rental failed. " + equipment.getName() + " is already rented.");
            }
            System.out.println();
            return;
        }
    }

    System.out.println(code + " does not exist.");
    System.out.println();
}
```

This version has three advantages:

- It finds the target equipment and handles it immediately.
- It avoids changing state before validation.
- It removes the need for a separate `found`, `item`, and `rented` variable.

The same pattern can be applied to `returnItem()`.

Also improve the field declaration:

```java
private Equipment[] equipments;
```

This keeps the array controlled by the `RentalDesk` class.

---

## What I Learned

I practiced building a multi-class Java program where one class stores data and another class manages an array of those objects.

The most important lesson was that object state should be changed only after checking whether the action is valid. For rental logic, checking `getIsRented()` before calling `setRented(true)` makes the method safer and easier to understand.

I also reinforced the habit of making fields `private` and using methods to control access. This makes the class design more object-oriented and prevents outside code from changing internal data directly.

Next time, I should check these questions while debugging:

- Does this method change object state before validation?
- Can this field be made `private`?
- Is this method name accurate? For example, `returnRentedEquipmentsCount()` prints a count, so `printRentedEquipmentsCount()` would be clearer.
- Can I return early after finding the correct object to avoid unnecessary looping?

---

## Related Java Concepts

- [[8. Class part 1|Class]]
- [[8. Class part 1#Object Oriented Programming|Object]]
- [[6. Array#Array list|Array]]
- [[5. Loop Statement|Loop Statement]]
- [[7. Method#Method|Method]]
- [[8. Class part 1#Access Modifier|Encapsulation]]
- [[8. Class part 1#Getter and Setter|Getter and Setter]]
- [[3. String|String]]
- [[4. Conditional statement|Conditional Statement]]
