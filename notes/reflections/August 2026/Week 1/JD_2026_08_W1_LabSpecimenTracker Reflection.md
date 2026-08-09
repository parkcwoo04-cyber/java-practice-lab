

## Metadata

Drill ID: JD_2026_08_W1_LabSpecimenTracker

Linked code: [View Java Code](../../../../java-drills/src/August_2026/Week_1/JD_2026_08_W1_LabSpecimenTracker/SpecimenTrackingApp.java)

Difficulty: Level 3 — Object-Oriented Problem Solving

Estimated Time: 70–90 minutes

Actual Time Taken: 78 minutes

Written by: Chanwoo Park

---

## Goal

Build a laboratory specimen tracking system using multiple collaborating classes. The program registers specimens and lab stations, assigns specimens to stations, moves specimens through an `enum`-based workflow, validates invalid operations with a custom exception, and summarizes specimen and station state using collections.

---

## What I Tried

- Created separate `Specimen`, `LabStation`, `SpecimenRegistry`, `SpecimenStatus`, and `InvalidSpecimenOperationException` classes.
- Used `SpecimenStatus` to represent `REGISTERED`, `ASSIGNED`, `IN_ANALYSIS`, `ANALYZED`, and `ARCHIVED`.
- Used `ArrayList` collections to store specimens, lab stations, and station assignments.
- Added validation for duplicate specimen/station IDs, missing IDs, station capacity, and invalid workflow actions.
- Used helper methods in `SpecimenTrackingApp` to catch `InvalidSpecimenOperationException` so the program could continue testing multiple cases.
- Added search helper methods in `SpecimenRegistry` to locate specimens and stations by ID.
- Printed specimen, station, and status summaries after running the workflow.

---

## Mistakes

The main runtime mistake was modifying an `ArrayList` while iterating through it with an enhanced `for` loop:

```java
for (String id : specimens) {
    if (id.equalsIgnoreCase(specimenID)) {
        specimens.remove(id);
        found = true;
        break;
    }
}
```

This caused a `ConcurrentModificationException`.

A safer approach for this case is to remove by index:

```java
for (int i = 0; i < specimens.size(); i++) {
    if (specimens.get(i).equalsIgnoreCase(specimenID)) {
        specimens.remove(i);
        found = true;
        break;
    }
}
```

The enhanced `for` loop uses an iterator internally. Calling `specimens.remove(...)` directly changes the structure of the collection while that iterator is still active.

A more important design issue was that `Specimen` did not fully protect its own workflow state. Methods such as:

```java
setAssigned();
setInAnalysis();
setAnalyzed();
setArchived();
setRegistered();
```

allow outside classes to move a specimen into a state without the `Specimen` object checking whether the transition is valid.

The registry also returned its internal mutable collections directly:

```java
public ArrayList<Specimen> getSpecimenList()
public ArrayList<LabStation> getLabStationList()
```

and `LabStation` exposed its internal `specimens` list through `getSpecimens()`. This allows outside code to modify internal state without going through the intended validation logic.

Another logic mismatch was that a station's specimen was removed only during archive. The intended workflow required station capacity to be released when analysis was completed. Because removal happened later, an analyzed specimen could continue occupying station capacity longer than intended.

---

## Why the Mistake Happened

The `ConcurrentModificationException` happened because I treated an enhanced `for` loop as if it were safe to structurally modify the same collection during iteration. I need to distinguish between reading elements during iteration and changing the collection structure.

The larger design problems came from treating encapsulation mainly as `private` fields plus public state-changing methods. Although the fields themselves were private, outside classes still controlled the specimen's status through methods that performed no transition validation.

The workflow rules were mostly implemented inside `SpecimenRegistry`, so the registry knew whether a transition was valid, but the `Specimen` object itself did not enforce those rules. This weakens object ownership because another caller could use the public state-changing methods incorrectly.

I also used concrete `ArrayList` types for collection fields and return values instead of depending on the `List` interface. This made the implementation more exposed than necessary.

---

## Improved Solution

Change workflow state changes from unrestricted setter-style methods to behavior-oriented methods inside `Specimen`.

For example, instead of:

```java
specimen.setInAnalysis();
```

the specimen should own the transition rule:

```java
public void beginAnalysis() throws InvalidSpecimenOperationException {
    if (status != SpecimenStatus.ASSIGNED) {
        throw new InvalidSpecimenOperationException(
                "Error: Specimen must be ASSIGNED before analysis begins."
        );
    }

    status = SpecimenStatus.IN_ANALYSIS;
}
```

This keeps the rule next to the state that it protects.

Collection fields should depend on the interface type:

```java
private List<Specimen> specimenList = new ArrayList<>();
private List<LabStation> labStationList = new ArrayList<>();
```

and mutable internal collections should not be returned directly.

The station workload should also be updated when analysis is completed rather than waiting until archive. That keeps station capacity synchronized with the specimen workflow.

Finally, normal success output should stay in `SpecimenTrackingApp`. `SpecimenRegistry` and `LabStation` should perform business operations and return/throw results rather than printing directly.

---

## What I Learned

I learned that successfully separating code into multiple classes is not enough by itself. Each class also needs to protect the state that it owns.

`private` fields do not provide strong encapsulation if public methods still allow arbitrary state changes. Behavior-oriented methods are more useful when an object has a controlled workflow.

I also learned that an enhanced `for` loop should not directly modify the structure of the collection being iterated. When removal is required, I should choose an iteration method that supports that operation safely.

For future debugging, I should check three things separately:

1. whether the collection is being modified during iteration
2. whether the class that owns a state is also enforcing its transition rules
3. whether two related objects remain consistent after one workflow operation

---

## Related Java Concepts

- [[11. Collection Framework#ArrayList|ArrayList]]
- [[12. Exception Handling#Custom Exception|Custom Exception Handling]]
- [[9. Class part 2#Enum|Enum]]
- [[8. Class part 1#Access Modifier|Encapsulation]]

