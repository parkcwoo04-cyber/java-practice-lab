

## Metadata

Drill ID: JD_2026_08_W2_DeepSpaceTransmissionControl

Linked code: [View Java Code](../../../../java-drills/src/August_2026/Week_2/JD_2026_08_W2_DeepSpaceTransmissionControl/MissionControlApp.java)

Difficulty: Level 4 — Creative OOP Challenge

Estimated Time: 100–140 minutes

Actual Time Taken: 154 minutes

Written by: Chanwoo Park

---

## Goal

Build a deep-space transmission control system using inheritance, polymorphism, collections, custom exceptions, controlled workflow states, station-capacity management, batch cleanup, and file output. `MissionControl` coordinates `Transmission` objects with `GroundStation` objects while concrete transmission subclasses provide their own capacity calculation.

---

## What I Tried

- Created an abstract `Transmission` parent class and concrete `ImageTransmission` and `TelemetryTransmission` subclasses.
- Used overriding so each transmission subtype provides its own `getCapacity()` behavior.
- Used `TransmissionStatus` to model the lifecycle from `CREATED` to `ARCHIVED`.
- Moved state-transition validation into `Transmission` methods such as `setScheduled()`, `setTransmitting()`, `setCompleted()`, and `setArchived()`.
- Used `MissionControl` to coordinate transmission registration, station registration, scheduling, completion, archival, and searching.
- Used `GroundStation` to track current capacity and assigned transmission IDs.
- Used a custom checked exception and allowed business exceptions to reach `MissionControlApp`.
- Used two separate loops during batch cleanup so archived transmissions were not removed from the same collection being traversed by the enhanced `for` loop.
- Separated file writing into `TransmissionLogWriter` and used try-with-resources.

---

## Mistakes

The most important design mistake was updating one object before confirming that the entire multi-object operation could succeed.

For example, scheduling currently performs:

```java
groundStation.updateCapacity(transmission.getCapacity());
groundStation.updateAssignedTransmissions(transmissionID);
transmission.setScheduled();
transmission.assignStationID(groundStationID);
```

If one of the later operations throws an exception, the earlier station changes have already happened.

For example, attempting to schedule an already scheduled transmission could increase the station capacity first and then fail afterward. This can leave the `GroundStation` and `Transmission` representing different versions of the same operation.

The operation should validate every condition that can fail before committing related state changes, or each participating object should expose behavior that prevents partial updates.

Another important logic error appeared when creating transmission subtypes:

```java
if (type == TransmissionType.IMAGE) {
    control.addTransmission(new ImageTransmission(transmissionID, probeName, data));
} else {
    control.addTransmission(new ImageTransmission(transmissionID, probeName, data));
}
```

The `else` branch also creates an `ImageTransmission`, so values labeled as `TELEMETRY` in the console are actually stored as image objects. This prevents the intended polymorphic behavior from being demonstrated correctly.

The collection design also still exposed mutable implementation details:

```java
public ArrayList<Transmission> getTransmissionList()
public ArrayList<GroundStation> getStationList()
public ArrayList<Transmission> getArchivedTransmissionList()
public ArrayList<String> getAssignedTransmissions()
```

External code receives the real mutable lists and can modify them without using the validation rules in `MissionControl` or `GroundStation`.

There were also several smaller design issues:

- collection fields use `ArrayList` instead of the `List` interface
- raw constructors such as `new ArrayList()` are used
- `setAssignedStationID()` provides an unrestricted public mutation path
- `GroundStation.updateCapacity(int)` can receive a negative value without checking whether the final capacity becomes negative
- `assignedStationID` remains assigned even after the station releases the transmission
- `MissionControl.batchCleanUp()` prints output directly instead of returning information to the application layer
- the file path is hard-coded to one computer
- the archive file does not contain all required information such as concrete transmission type, final status, and communication load
- the final report does not yet count transmissions by status and concrete type

---

## Why the Mistake Happened

The main difficulty was no longer individual Java syntax. It was coordinating several objects that all represent part of one business operation.

I improved state encapsulation compared with the previous specimen project because `Transmission` now checks its own current status before changing state. However, I still treated scheduling as a sequence of separate mutations rather than one coordinated operation.

This means I need to distinguish between:

1. validating whether an operation is allowed
2. committing changes after validation succeeds

I also focused on creating the inheritance structure but did not verify that the application actually instantiated both concrete subclasses. As a result, the class hierarchy existed, but the runtime object creation did not fully use it.

For collections, I solved the previous `ConcurrentModificationException` problem by separating traversal and removal, but I still exposed the internal lists directly. Safe iteration and collection encapsulation are separate concerns.

---

## Improved Solution

Before mutating either object during scheduling, validate all required conditions first.

Conceptually:

```java
Transmission transmission = findTransmission(...);
GroundStation station = findStation(...);

// Validate transmission state.
// Validate station capacity.
// Validate duplicate assignment.

// Only after all checks succeed:
station.reserve(...);
transmission.scheduleTo(...);
```

The exact method design can vary, but the important idea is that validation should happen before partial state changes.

The transmission should also use behavior-oriented methods instead of setter-style names. For example:

```java
transmission.scheduleTo(stationID);
transmission.beginTransmission();
transmission.complete();
transmission.archive();
```

These names make it clearer that the methods represent controlled domain actions rather than unrestricted property changes.

The subtype creation logic should construct the correct runtime type:

```java
if (type == TransmissionType.IMAGE) {
    // create ImageTransmission
} else {
    // create TelemetryTransmission
}
```

Collection fields should use interface reference types:

```java
private List<Transmission> transmissionList = new ArrayList<>();
private List<GroundStation> stationList = new ArrayList<>();
```

and internal mutable lists should not be returned directly. Reporting can instead return formatted information, counts, or a separate safe copy when necessary.

`GroundStation` should own capacity invariants more explicitly. Rather than accepting a generic positive or negative delta through one method, behavior such as reserving and releasing capacity can validate that:

```text
0 <= currentCapacity <= maxCapacity
```

always remains true.

The file writer should use a relative path such as:

```text
ArchivedTransmissionLog.txt
```

and each archived record should include the required transmission type, status, and capacity as well as the ID and probe name.

---

## What I Learned

I learned that my state-management design improved from the previous Level 3 exercise. The `Transmission` object now validates its own lifecycle instead of allowing another class to directly assign any status.

I also learned how to avoid the previous collection-removal runtime problem. Instead of removing from the same list inside an enhanced `for` loop, I first collected archived objects and then removed them in a separate traversal.

The next design issue is more advanced: when one operation changes multiple objects, each individual method can be correct while the overall operation is still unsafe. I need to validate all failure conditions before committing related mutations.

I also learned that creating a parent class and overridden methods is not enough to prove polymorphism. I must verify that the actual runtime objects are really different subclasses and that the parent reference invokes each subclass implementation.

For future debugging and review, I should check:

- the actual runtime type of objects created by each branch
- whether all validation occurs before multi-object mutation begins
- whether any getter exposes a mutable internal collection
- whether an object's public methods can bypass its invariants
- whether related objects still agree after both successful and failed operations
- whether persistence output contains every piece of required data

---

## Related Java Concepts

- [[9. Class part 2#Inheritance|Inheritance]]
- [[9. Class part 2#Method Overriding|Method Overriding]]
- [[9. Class part 2#Polymorphism|Polymorphism]]
- [[8. Class part 1#Access Modifier|Encapsulation]]
- [[11. Collection Framework#ArrayList|ArrayList]]
- [[12. Exception Handling#Custom Exception|Custom Exception]]
- [[9. Class part 2#Enum|Enum]]
