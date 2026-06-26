

## Metadata

Drill ID: JD_2026_06_W4_EquipmentRentalCenterApp

Linked code: 
- [View Java Code](../../java-drills/src/June_2026/Week_4/JD_2026_06_W4_EquipmentRentalCenterApp)

Difficulty: Level 3.5 — Object-Oriented State Management

Estimated Time: 60–90 minutes

Actual Time Taken: 76 minutes

Written by: Chanwoo Park

---

## Goal

The goal of this drill was to build an equipment rental system using object-oriented design. The program manages rental requests, equipment objects, assignment logic, rental status changes, return processing, and rental records. The main concepts practiced were abstraction, inheritance, overriding, polymorphism, enum-based status management, `ArrayList`, `LinkedHashMap`, validation, and custom exception handling.

---

## What I Tried

- Created an abstract `Equipment` parent class for shared equipment data and behavior.
- Created child equipment classes such as `Camera`, `Laptop`, `Projector`, and `Microphone`.
- Used `canSupport(RentalRequest request)` so each equipment type could decide whether it supports a request.
- Avoided using `instanceof` for rental assignment logic.
- Used `EquipmentStatus` and `RequestStatus` enums to represent state changes.
- Used `ArrayList` to store equipment and rental requests.
- Used `LinkedHashMap` to connect assigned rental requests with their assigned equipment.
- Added validation logic for invalid rental request data.
- Increased `completedRentalCount` after equipment was returned, not when rental was first assigned.

---

## Mistakes

The main mistake was a project-level exception class mismatch.

```java
import src.June_2026.Week_4.JD_2026_06_W4_CampusMaintenanceRequestApp.InvalidRequestException;
```

instead of using the exception class created for this project:

```java
public class InvalidRentalRequestException extends Exception {
    public InvalidRentalRequestException(String message) {
        super(message);
    }
}
```

This caused the rental app to depend on a previous project’s exception class instead of being independent.

A second mistake was that `RentalRequest.markReturned()` did not clearly move the request into a returned state.

```java
public void markReturned() {
    this.requestStatus = RequestStatus.RENTED;
}
```

instead of:

```java
public void markReturned() {
    this.requestStatus = RequestStatus.RETURNED;
}
```

This made the request status inaccurate after the equipment was returned.

A design issue was that `RETURNED` was used as an equipment status, but the next state after `RETURNED` was not fully defined. The `RETURNED` state itself was not wrong because the assignment required a return state. However, if the equipment should be rented again later, the program needs a clear policy for moving from `RETURNED` back to `AVAILABLE`.

---

## Why the Mistake Happened

The exception mistake happened because code from a previous drill was reused without fully updating the import and exception type. This is a compile-time dependency problem. Each mini project should compile independently unless it intentionally shares a package or utility class.

The request status mistake happened because the equipment state and request state were not kept fully synchronized. The equipment could become `RETURNED`, but the rental request still remained `RENTED`. This shows that state changes must be applied consistently across related objects.

The `RETURNED` state design issue happened because there are two possible meanings of “returned”:

1. `RETURNED` can mean “this rental cycle is completed.”
2. `RETURNED` can mean “the equipment is currently back and ready for future rental.”

These two meanings should not be mixed. If `RETURNED` is used as a historical state, then another method such as `prepareForNextRental()` may be needed before the equipment becomes available again.

---

## Improved Solution

Change the exception usage so the project uses its own custom exception:

```java
public boolean validateRequest() throws InvalidRentalRequestException {
    if (studentName == null || studentName.trim().isEmpty()) {
        throw new InvalidRentalRequestException("Student name cannot be empty.");
    }

    if (requestType == null) {
        throw new InvalidRentalRequestException("Request type cannot be null.");
    }

    return true;
}
```

Change request return handling so the request also records the completed return state:

```java
public enum RequestStatus {
    PENDING,
    ASSIGNED,
    RENTED,
    RETURNED,
    REJECTED
}
```

```java
public void markReturned() {
    this.requestStatus = RequestStatus.RETURNED;
}
```

Keep `completedRentalCount` increasing after return completion:

```java
public void markReturned() {
    this.status = EquipmentStatus.RETURNED;
    this.completedRentalCount++;
}
```

This is correct because the count represents completed rental cycles, not started rental cycles.

If the program should allow the same equipment to be rented again, add a separate state transition method:

```java
public void prepareForNextRental() {
    if (status == EquipmentStatus.RETURNED) {
        status = EquipmentStatus.AVAILABLE;
    }
}
```

This keeps `RETURNED` meaningful while still allowing future rentals.

A cleaner `canSupport()` design would separate type compatibility from availability:

```java
@Override
public boolean canSupport(RentalRequest request) {
    return request.getRequestType() == RequestType.MEDIA_PROJECT;
}
```

Then `RentalCenter` should combine availability and compatibility:

```java
if (equipment.isAvailable() && equipment.canSupport(request)) {
    equipment.assignTo(request);
    assignedRequests.put(request, equipment);
    break;
}
```

This makes the responsibility clearer: equipment type checks belong to the child class, and assignment flow belongs to the center.

---

## What I Learned

I learned that `completedRentalCount` should increase when a rental is fully completed, not when it is first assigned. The name of the field matters because it defines when the value should change.

I also learned that using enum status values is useful, but every status needs a clear transition rule. Creating `RETURNED` is valid, but the design must explain whether returned equipment stays in a completed state or becomes available for the next rental.

The strongest part of this solution was using polymorphism through `canSupport()` instead of using `instanceof` in the rental assignment logic. This kept the `RentalCenter` from knowing the details of every equipment subclass.

The main debugging habit to improve is checking whether related object states change together. When equipment is returned, both the `Equipment` and the `RentalRequest` should reflect that return.

---

## Related Java Concepts

- [[10. Abstraction and Interface#Abstraction|Abstraction]]
- [[10. Abstraction and Interface#Interface|Interface]]
- [[9. Class part 2#Method Overriding|Method Overriding]]
- [[9. Class part 2#Polymorphism|Polymorphism]]
- [[9. Class part 2#Enum|Enum]]
- [[11. Collection Framework|Collection Framework]]

