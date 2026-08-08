

## Metadata

Drill ID: JD_2026_08_W1_CampusEquipmentRental

Linked code: [View Java Code](../../java-drills/src/August_2026/Week_1/JD_2026_08_W1_CampusEquipmentRental/CampusEquipmentRentalApp.java)

Difficulty: Level 4 — Creative OOP Challenge

Estimated Time: 90–120+ minutes

Actual Time Taken: 125 minutes

Written by: Chanwoo Park

---

## Goal

Build a campus equipment rental system using multiple collaborating objects. The program manages `CameraEquipment` and `LaptopEquipment` through a common `Equipment` type, tracks equipment state with `RentalStatus`, records successful rental and return events with `RentalRecord`, handles invalid rental operations with `InvalidRentalException`, and saves/loads rental history using file I/O.

---

## What I Tried

- Created an abstract `Equipment` parent class with shared equipment information and rental state.
- Created `CameraEquipment` and `LaptopEquipment` subclasses with different rental information.
- Used `RentalStatus` and `RentalAction` enums instead of raw status/action strings.
- Stored different equipment subclasses together in an `ArrayList<Equipment>`.
- Created `RentalService` to manage equipment, rental operations, returns, history, and file persistence.
- Created a separate `RentalRecord` object for each successful rental or return.
- Used one `description` field in `RentalRecord` so different equipment types could store different event details.
- Used a custom `InvalidRentalException` to reject invalid equipment IDs, duplicate IDs, invalid student names, and invalid rental conditions.
- Used `Scanner` for the menu and rental input.
- Used `BufferedReader` and `BufferedWriter` to load and save rental history.

---

## Mistakes

The main design mistake was placing equipment-specific rental behavior inside `RentalService` instead of allowing the equipment subclasses to provide that behavior polymorphically.

For example:

```java
void rentCamera(String equipmentID, String studentName, int days);
void rentLaptop(String equipmentID, String studentName, String descriptions);

boolean validateRental(Equipment equipment);
```

and:

```java
public boolean validateRental(Equipment equipment) {
    return equipment instanceof CameraEquipment;
}
```

This makes `RentalService` determine the concrete equipment type and then call a different rental method for each type.

The result is that the `Rentable` interface behaves more like an interface for `RentalService` than a common rental contract for equipment objects.

A second design issue was that the actual subclass-specific validation was also performed inside `RentalService`.

For example, camera rental days were checked with:

```java
if (days > equipment.getMaximumRentalDays()) {
    throw new InvalidRentalException(
        "Error: " + equipmentID + " has reached the maximum rental days."
    );
}
```

while laptop description validation was also performed by the service:

```java
if (descriptions == null || descriptions.isBlank()) {
    throw new InvalidRentalException(
        "Error: " + descriptions + " cannot be blank."
    );
} else if (descriptions.length() < 5) {
    throw new InvalidRentalException(
        "Error: " + descriptions + " must have at least 5 characters."
    );
}
```

This means the subclasses store some type-specific data, but they do not fully own their type-specific rental rules.

There were also smaller runtime/design issues:

- `mainMenu()` uses `scanner.nextInt()` directly, so non-numeric menu input can terminate the program instead of returning to the menu.
- `returnEquipment()` changes the status directly without independently verifying that the equipment is currently rented.
- Returned equipment keeps its previous `studentName`, so an `AVAILABLE` object can still contain stale borrower information.
- `getEquipmentList()` returns the internal mutable `ArrayList`, allowing outside code to modify the service’s collection directly.
- The history file path is an absolute local path, so the program is tied to one computer and directory.
- `line.split(",")` can break a saved history record if a rental description itself contains a comma.

---

## Why the Mistake Happened

I separated the program into multiple classes, but I mainly focused on separating data rather than separating behavior.

`CameraEquipment` owns `maximumRentalDays`, and `LaptopEquipment` owns `description`, but `RentalService` still knows how both equipment types should be validated.

Because of this, the design uses inheritance structurally but does not take full advantage of polymorphism.

The following logic is effectively being used:

```text
RentalService
    |
    +-- Is this CameraEquipment?
    |       -> use camera rental logic
    |
    +-- Otherwise
            -> use laptop rental logic
```

A stronger polymorphic design would make the service depend on a common rental operation and allow the actual equipment object to determine which validation rule applies.

I also relied on the normal menu flow to prevent several invalid operations. For example, the return menu only displays rented equipment, so `returnEquipment()` normally receives a valid object. However, the service method itself does not protect its own state transition.

This showed the difference between:

- making an operation safe because the current UI happens to call it correctly, and
- making the object/service itself enforce its business rules.

---

## Improved Solution

Improve the responsibility boundary so that equipment-specific validation belongs closer to the equipment object.

Conceptually, the flow should become:

```text
RentalService
    ↓
find Equipment
    ↓
ask Equipment to validate/apply its rental rule
    ↓
change state
    ↓
create RentalRecord
```

Instead of having the service use type detection such as:

```java
if (service.validateRental(equipment)) {
    service.rentCamera(...);
} else {
    service.rentLaptop(...);
}
```

the design should move toward a common operation where polymorphism determines the actual behavior.

The exact method signature can vary, but the important design principle is:

```java
equipment.validateRental(...);
```

where `CameraEquipment` and `LaptopEquipment` provide their own implementation of the relevant rental rule.

The return operation should also protect its own state transition rather than relying only on the menu:

```java
if (!equipment.isRented()) {
    throw new InvalidRentalException("Equipment is not currently rented.");
}
```

After a successful return, temporary borrower information should be cleared so that the current state remains consistent.

The input loop should also validate menu input before calling `nextInt()` or handle invalid numeric input so that incorrect console input does not unexpectedly terminate the application.

---

## What I Learned

I learned that using inheritance and an interface does not automatically create a polymorphic design.

The important question is not only:

“Are Camera and Laptop subclasses of Equipment?”

but also:

“Can the caller use a common operation without knowing which subclass it is working with?”

I also learned that a service method should protect its own business rules. Filtering valid objects in the UI is useful, but the underlying operation should still reject an invalid state transition.

Separating `RentalRecord` from `Equipment` was useful because it clearly distinguished current state from historical events. Using a generic `description` field also allowed different rental types to store different historical details without adding unused Camera- or Laptop-specific fields to every record.

For future projects, I should check object-oriented designs by asking:

1. Which class owns the data?
2. Which class should know the rule for that data?
3. Does another class know too much about concrete subclasses?
4. Can the same operation work through a parent/interface reference?
5. Does each state-changing method protect itself against invalid transitions?

---

## **Related Java Concepts**

- [[9. Class part 2#Inheritance|Inheritance]]
- [[9. Class part 2#Method Overriding|Method Overriding]]
- [[9. Class part 2#Polymorphism|Polymorphism]]
- [[10. Abstraction and Interface#Abstraction|Abstraction]]
- [[10. Abstraction and Interface#Interface|Interface]]
- [[11. Collection Framework#ArrayList|ArrayList]]
- [[12. Exception Handling#Custom Exception|Custom Exception Handling]]
- [[9. Class part 2#Enum|Enum]]
- [[13. Input, Output and Files#BufferedReader|BufferedReader and BufferedWriter]]
