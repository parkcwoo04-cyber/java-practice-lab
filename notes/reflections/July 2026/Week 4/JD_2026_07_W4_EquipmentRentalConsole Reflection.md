# Equipment Rental Console — Code Review Reflection

## Metadata

Drill ID: JD_2026_07_W4_EquipmentRentalConsole

Linked code: [View Java Code](../../java-drills/src/July_2026/Week_4/JD_2026_07_W4_EquipmentRentalConsole/EquipmentRentalConsole.java)

Difficulty: Level 3 — Object-Oriented Problem Solving

Estimated Time: 75–90 minutes

Actual Time Taken: 86 minutes

Written by: Chanwoo Park

---

## Goal

Build a multi-class equipment rental system using `Equipment`, `RentalService`, `RentalStatus`, and a custom exception. The program should register equipment, reject invalid operations, handle rental and maintenance state changes, calculate return fees, and print a final status report.

The main design goal was to keep each `Equipment` object in a valid state while separating domain logic from console output.

---

## What I Tried

- Created an `Equipment` class with ID, name, price, status, and renter fields.
- Used `RentalStatus` enum values instead of arbitrary status strings.
- Initialized new equipment as `AVAILABLE`.
- Created a `RentalService` with an `ArrayList<Equipment>`.
- Checked duplicate IDs before registration.
- Implemented rental, return, and maintenance operations.
- Used a custom `InvalidRentalOperationException` for invalid operations.
- Calculated the return fee from rental days and daily price.
- Counted equipment by status for the final summary.
- Demonstrated duplicate registration, repeated rental, invalid return, invalid rental days, and maintenance restrictions in `main`.

---

## What Worked Well

### 1. The program was divided into meaningful classes

The solution did not place every operation inside `main`. `Equipment`, `RentalService`, `RentalStatus`, and the custom exception each represent a recognizable part of the system.

This shows progress from writing a single procedural class toward designing collaborating objects.

### 2. Enum values were used for rental state

```java
public enum RentalStatus {
    AVAILABLE,
    RENTED,
    MAINTENANCE
}
```

Using an enum prevents invalid status strings and makes conditions such as the following easier to read:

```java
return status == RentalStatus.AVAILABLE;
```

### 3. Duplicate IDs and several invalid operations were considered

The code checks duplicate IDs before adding equipment and attempts to reject invalid rental, return, and maintenance operations.

The main class also deliberately triggers edge cases instead of demonstrating only successful operations. This is a good debugging habit.

### 4. State counts were calculated from the collection

`printStatusSummary()` iterates through all equipment and classifies each object by its current state. This correctly treats the summary as collection-level work that belongs near the service.

---

## Mistakes

### Main mistake: the `Equipment` object does not control its own state

The current design changes state through public setters:

```java
equipment.setStatus(RentalStatus.RENTED);
equipment.setRenter(renter);
```

and exposes these methods:

```java
public void setRenter(String renter) {
    this.renter = renter;
}

public void setStatus(RentalStatus status) {
    this.status = status;
}
```

This allows any caller to create invalid combinations such as:

```java
equipment.setStatus(RentalStatus.AVAILABLE);
equipment.setRenter("Mina");
```

or:

```java
equipment.setStatus(null);
```

The assignment required `Equipment` to protect its own rental state. The service should ask an equipment object to perform an operation, not directly modify its fields.

A better responsibility boundary would be equivalent to:

```java
equipment.rentTo(renter);
int fee = equipment.returnAfter(rentalDays);
equipment.startMaintenance();
```

The exact method names can differ. The important point is that validation and related field updates happen together inside `Equipment`.

---

### Constructor validation is caught inside the constructor

The constructor currently does this:

```java
try {
    if (id == null || id.isBlank()) {
        throw new InvalidRentalOperationException("Error: ID cannot be empty.");
    }
    // ...
} catch (InvalidRentalOperationException e) {
    System.out.println(e.getMessage());
}
```

When invalid data is provided, the constructor catches the exception and finishes without assigning all valid field values. This can leave an `Equipment` reference pointing to a partially initialized object whose ID, name, status, or renter may be `null`.

The invalid object may then be passed to `registerEquipment()`, where other methods assume its fields are valid.

The constructor should validate first and allow the exception to leave the constructor. That prevents an invalid object from being created successfully.

---

### Domain classes print messages and catch their own business exceptions

`Equipment` prints details, while `RentalService` prints registration, rental, return, maintenance, filtering, and summary messages.

For example:

```java
try {
    // operation
} catch (InvalidRentalOperationException e) {
    System.out.println(e.getMessage());
}
```

This means the service performs four responsibilities:

1. searching the collection
2. applying business rules
3. deciding error handling
4. formatting console output

The assignment specifically required `Equipment` and `RentalService` not to print user-facing messages.

The service should return useful results or throw an exception. `EquipmentRentalConsole` should catch the exception and decide what to print.

---

### The collection is declared with the concrete implementation type

The field is currently:

```java
private ArrayList<Equipment> equipmentList;
```

The assignment required the field and public design to depend on the `List<Equipment>` interface.

A better declaration is:

```java
private List<Equipment> equipmentList;
```

The constructor may still create an `ArrayList`:

```java
this.equipmentList = new ArrayList<>();
```

This separates the type the class depends on from the implementation it chooses.

---

### Renter input is not validated

`rentEquipment()` accepts any renter value:

```java
rentalService.rentEquipment("EQ-101", "Mina");
```

but the method does not reject `null`, `""`, or blank text.

This permits a rented item to have no meaningful renter, which breaks the intended object state.

---

### Maintenance can be entered but not restored

The requirements included both:

- moving available equipment into maintenance
- restoring maintenance equipment to available

The current code implements entry into maintenance but not restoration. As a result, maintenance becomes a one-way state.

---

### Boolean tracking variables make lookup and state logic harder to follow

Methods use variables such as:

```java
boolean rent = false;
boolean matched = false;
```

The method then loops, performs the operation, and later checks the boolean.

This works for some flows, but it mixes “was an ID found?” with “did the operation succeed?”. In `maintenanceEquipment()`, for example, a matching rented item throws an exception before `matched` becomes true.

A clearer design is to create one lookup method that either returns the matching equipment or throws an unknown-ID exception:

```java
Equipment equipment = findById(equipmentId);
```

Then each operation can focus only on the requested state change.

---

### Method names can express boolean meaning more clearly

The current methods are:

```java
available()
rented()
maintenance()
```

Because they return booleans, Java naming conventions and readability are improved by names such as:

```java
isAvailable()
isRented()
isUnderMaintenance()
```

This is not a correctness issue, but it makes conditions read more naturally.

---

## Why the Mistake Happened

The main conceptual issue was treating `Equipment` mostly as a data container and placing the important behavior in `RentalService`.

Because getters and setters were available, the service could directly control the fields. This made the first implementation easier, but weakened encapsulation.

The stronger object-oriented rule is:

> The object that owns the state should also own the rules for changing that state.

`Equipment` owns `status` and `renter`, so it should validate and change them together.

A second issue was treating exceptions as messages rather than control flow. The exception was thrown and immediately caught inside the same class, so the caller could not decide how to respond. This prevented separation between business logic and presentation.

A third issue was focusing on producing the required console output before designing method return values and responsibility boundaries. The program demonstrates many required scenarios, but the output logic became tightly connected to the domain logic.

---

## Improved Solution

### 1. Move state transitions into `Equipment`

Change direct field modification from:

```java
equipment.setStatus(RentalStatus.RENTED);
equipment.setRenter(renter);
```

to an operation owned by the object:

```java
equipment.rentTo(renter);
```

Inside that method, validate all conditions before changing fields.

The same principle should be applied to return and maintenance operations.

---

### 2. Do not catch validation exceptions inside the constructor

Change the constructor flow so that invalid data throws immediately:

```java
if (id == null || id.isBlank()) {
    throw new InvalidRentalOperationException("ID cannot be empty.");
}
```

Only assign fields after all validation has succeeded.

This ensures every successfully created `Equipment` object starts in a valid state.

---

### 3. Let the console handle expected failures

Instead of printing inside `RentalService`, allow the service or equipment object to throw:

```java
rentalService.rentEquipment("EQ-101", "Mina");
```

Then handle it in `EquipmentRentalConsole`:

```java
try {
    rentalService.rentEquipment("EQ-101", "Mina");
    System.out.println("Rental completed.");
} catch (InvalidRentalOperationException e) {
    System.out.println("Rental failed: " + e.getMessage());
}
```

This keeps domain classes reusable and makes the console responsible for presentation.

---

### 4. Return operation data rather than printing it

The return operation should provide the calculated fee:

```java
int fee = rentalService.returnEquipment("EQ-101", 3);
```

The main class can then print the equipment ID, rental days, and fee.

This is an example of using a return value to separate calculation from output.

---

### 5. Depend on `List`, not `ArrayList`

Change:

```java
private ArrayList<Equipment> equipmentList;
```

to:

```java
private List<Equipment> equipmentList;
```

Keep `new ArrayList<>()` as the internal implementation.

When returning all or filtered equipment, return a newly created list rather than the original internal collection.

---

### 6. Add a single lookup method

Create one private helper with behavior equivalent to:

```java
private Equipment findById(String equipmentId)
```

It should return the matching object or throw an exception when no ID matches.

This removes repeated search loops from rental, return, and maintenance methods.

---

## What I Learned

I successfully combined classes, an enum, a collection, custom exceptions, loops, and conditions into one working object-oriented scenario.

The most important lesson is that encapsulation is not achieved only by making fields `private`. Encapsulation also requires restricting how state can change.

Public setters for `status` and `renter` allowed other classes to bypass the rental rules. A better design gives `Equipment` meaningful behavior methods and removes unrestricted setters.

I also learned that throwing and catching an exception inside the same domain method reduces its usefulness. Domain classes should report failure through exceptions or return values, while the console decides how to display the result.

For the next exercise, I should plan these questions before coding:

1. Which object owns each piece of state?
2. Which class should validate a state change?
3. What value should a method return?
4. Which class is responsible only for printing?
5. Can a caller bypass the business rules through a setter?
6. If an operation fails, are all fields unchanged?

---

## Related Java Concepts

- [[8. Class part 1#Access Modifier|Encapsulation]]
- [[9. Class part 2|Class and Objects]]
- [[9. Class part 2#Enum|Enum]]
- [[12. Exception Handling#Custom Exception|Custom Exception Handling]]
- [[11. Collection Framework#ArrayList|ArrayList]]

