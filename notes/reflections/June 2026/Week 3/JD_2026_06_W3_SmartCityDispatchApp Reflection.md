

## Metadata

Drill ID: JD_2026_06_W3_SmartCityDispatchApp

Linked code: 
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W3_SmartCityDispatchApp/SmartCityDispatchApp.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W3_SmartCityDispatchApp/DispatchCenter.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W3_SmartCityDispatchApp/Incident.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W3_SmartCityDispatchApp/IncidentType.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W3_SmartCityDispatchApp/IncidentStatus.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W3_SmartCityDispatchApp/ResponseUnit.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W3_SmartCityDispatchApp/FireUnit.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W3_SmartCityDispatchApp/MedicalUnit.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W3_SmartCityDispatchApp/PoliceUnit.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W3_SmartCityDispatchApp/Dispatchable.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W3_SmartCityDispatchApp/InvalidIncidentException.java)

Difficulty: Level 4 — Creative OOP Challenge

Estimated Time: 90+ minutes

Actual Time Taken: 113 minutes

Written by: Chanwoo Park

---

## Goal

The goal of this project was to build a small object-oriented emergency dispatch system. The program manages multiple `Incident` objects and multiple `ResponseUnit` objects, validates incident data, assigns suitable units, updates incident status, and prints a final dispatch report. The project was intended to check overall Java coding ability, especially class design, inheritance, overriding, polymorphism, collections, enum usage, exception handling, and object collaboration.

---

## What I Tried

- Created multiple classes such as `Incident`, `ResponseUnit`, `FireUnit`, `MedicalUnit`, `PoliceUnit`, `DispatchCenter`, and the main application class.
- Used `enum` types to represent incident type and incident status instead of relying on plain `String` values.
- Used collections such as `ArrayList`, `LinkedHashSet`, and `LinkedHashMap` to manage incidents, response units, and assignment records.
- Used inheritance by creating specialized response unit classes from a common `ResponseUnit` parent class.
- Used overriding so that each response unit type could define its own assignment or response behavior.
- Used a custom exception to reject invalid incident data.
- Built a full program flow from incident registration, validation, assignment, response completion, and final report generation.
- Adjusted the console output format slightly to improve readability.

---

## Mistakes

The main design issue was that `ResponseUnit` was created as a normal concrete class:

```java
public class ResponseUnit {
    public String unitID;
    public String unitName;
    public String baseLocation;
    public boolean availability;
    public int countCompletedResponse;
}
```

instead of using it as an abstract parent type:

```java
public abstract class ResponseUnit implements Dispatchable {
    private String unitID;
    private String unitName;
    private String baseLocation;
    private boolean availability;
    private int countCompletedResponse;

    public abstract boolean canHandle(Incident incident);
}
```

Because `ResponseUnit` was not abstract, the program allowed the idea of a generic response unit object even though the system actually needs specific unit types such as fire, medical, or police units. This weakened the object-oriented design.

Another important issue was that the assignment logic in `DispatchCenter` depended too much on `instanceof` and direct type checks:

```java
if (incident.getIncidentType().equals(IncidentType.FIRE)) {
    for (ResponseUnit responseUnit : units) {
        if (responseUnit instanceof FireUnit) {
            if (responseUnit.availability) {
                ((FireUnit) responseUnit).assign(incident);
            }
        }
    }
}
```

A better design would let each unit decide whether it can handle a given incident:

```java
for (ResponseUnit unit : units) {
    if (unit.isAvailable() && unit.canHandle(incident)) {
        unit.assign(incident);
        incidentAssign.put(incident, unit);
        return;
    }
}
```

This change would make `DispatchCenter` depend on polymorphism instead of knowing every child class directly.

---

## Why the Mistake Happened

The main issue happened because inheritance was used, but polymorphism was not fully applied.

The program had child classes such as `FireUnit`, `MedicalUnit`, and `PoliceUnit`, but `DispatchCenter` still controlled the exact matching logic by checking the incident type and then checking the unit type. This means the parent-child structure existed, but the responsibility for behavior was still centralized in the coordinator class.

This also created weaker separation of responsibilities:

- `DispatchCenter` knew too much about every unit subclass.
- `ResponseUnit` did not fully define the common contract for all response units.
- Child classes did not fully own the decision of whether they could handle an incident.
- Field access was too open because several fields were declared as `public`.

The validation logic also showed a design issue. Invalid severity was handled, but null or blank data was partly handled by catching `NullPointerException`. This is less intentional than directly validating each field and throwing a custom exception.

---

## Improved Solution

Change `ResponseUnit` into an abstract class and move the common dispatch contract into the parent type:

```java
public abstract class ResponseUnit implements Dispatchable {
    private final String unitID;
    private String unitName;
    private String baseLocation;
    private boolean availability;
    private int countCompletedResponse;

    public ResponseUnit(String unitID, String unitName, String baseLocation, boolean availability) {
        this.unitID = unitID;
        this.unitName = unitName;
        this.baseLocation = baseLocation;
        this.availability = availability;
    }

    public abstract boolean canHandle(Incident incident);

    public boolean isAvailable() {
        return availability;
    }

    protected void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
```

Then each child class should implement its own handling rule:

```java
@Override
public boolean canHandle(Incident incident) {
    return incident.getIncidentType() == IncidentType.FIRE;
}
```

`DispatchCenter` can then become simpler and more flexible:

```java
for (ResponseUnit unit : units) {
    if (unit.isAvailable() && unit.canHandle(incident)) {
        unit.assign(incident);
        incidentAssign.put(incident, unit);
        return;
    }
}
```

This improves the design because the coordinator does not need to know every specific unit type. If a new unit type is added later, the new class can define its own `canHandle()` logic without rewriting the whole assignment method.

Validation should also be separated into a dedicated method:

```java
private void validateIncident(Incident incident) throws InvalidIncidentException {
    if (incident == null) {
        throw new InvalidIncidentException("Incident cannot be null.");
    }

    if (incident.getLocation() == null || incident.getLocation().trim().isEmpty()) {
        throw new InvalidIncidentException("Location cannot be blank.");
    }

    if (incident.getDescription() == null || incident.getDescription().trim().isEmpty()) {
        throw new InvalidIncidentException("Description cannot be blank.");
    }

    if (incident.getSeverity() < 1 || incident.getSeverity() > 5) {
        throw new InvalidIncidentException("Severity must be between 1 and 5.");
    }

    if (incident.getIncidentType() == null || incident.getIncidentType() == IncidentType.UNKNOWN) {
        throw new InvalidIncidentException("Unsupported incident type.");
    }
}
```

This makes the exception handling more intentional and avoids relying on `NullPointerException` as part of normal validation flow.

---

## What I Learned

I learned that simply using inheritance does not automatically make the design strongly object-oriented. Inheritance creates a parent-child relationship, but polymorphism becomes meaningful only when the program uses the parent type to call behavior that each child class implements differently.

I also learned that a coordinator class should not know too much about every subclass. `DispatchCenter` should coordinate the flow, but each `ResponseUnit` should decide whether it can handle a specific `Incident`.

Another important lesson is that field access should be controlled. Public fields make the program easier to write at first, but they allow other classes to change object state without validation. Using `private`, `final`, getters, and controlled state-changing methods makes the design safer.

For exception handling, I learned that expected invalid input should be checked directly and handled with a custom exception. Catching `NullPointerException` is less clear because it means the program discovered invalid data only after something went wrong.

Next time, I should check these design questions before implementation:

- Is this parent class supposed to be directly instantiated, or should it be abstract?
- Is this coordinator class doing too much type-specific work?
- Can child classes own their own behavior through overriding?
- Are object fields protected from invalid external changes?
- Are validation failures handled intentionally rather than accidentally?

---

## Related Java Concepts

- [[8. Class part 1|Class]]
- [[9. Class part 2#Inheritance|Inheritance]]
- [[9. Class part 2#Method Overriding|Method Overriding]]
- [[9. Class part 2#Polymorphism|Polymorphism]]
- [[10. Abstraction and Interface#Abstraction|Abstract Class]]
- [[10. Abstraction and Interface#Interface|Interface]]
- [[9. Class part 2#Enum|Enum]]
- [[11. Collection Framework|Collection Framework]]
- [[12. Exception Handling|Exception Handling]]

