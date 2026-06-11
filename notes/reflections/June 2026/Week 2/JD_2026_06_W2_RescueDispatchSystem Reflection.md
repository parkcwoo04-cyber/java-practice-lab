

## Metadata

Drill ID: JD_2026_06_W2_RescueDispatchSystem

Linked code:
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W2_RescueDispatchSystem/RescueDispatchSystem.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W2_RescueDispatchSystem/RescueUnit.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W2_RescueDispatchSystem/AmbulanceUnit.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W2_RescueDispatchSystem/FireTruckUnit.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W2_RescueDispatchSystem/RescueBoatUnit.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W2_RescueDispatchSystem/DroneUnit.java)

Difficulty: Level 3 — Object-Oriented Problem Solving

Estimated Time: 60–90 minutes

Actual Time Taken: 82 minutes

Written by: Chanwoo Park

---

## Goal

The goal of this exercise was to build a rescue dispatch system using inheritance, method overriding, and polymorphism. A parent class, `RescueUnit`, defines common data and behavior for rescue units, while child classes such as `AmbulanceUnit`, `FireTruckUnit`, `RescueBoatUnit`, and `DroneUnit` override dispatch-related behavior depending on the emergency type. The main program stores different child objects in a `RescueUnit[]` array and scans the array to find a unit that can respond to each emergency.

---

## What I Tried

- Created a parent class, `RescueUnit`, to store common fields such as unit name, fuel, availability, and completed mission count.
- Created several child classes that inherit from `RescueUnit`.
- Used method overriding so that each child class could decide which emergency types it can handle.
- Used a `RescueUnit[]` array to manage different child objects through the parent type.
- Used a loop in `RescueDispatchSystem` to scan rescue units for each emergency.
- Tried to print a fuel shortage message when a unit could handle the emergency type but did not have enough fuel.
- Tried to stop scanning with `break` once a unit was dispatched.

---

## Mistakes

The main mistake was that `canDispatch()` and `dispatch()` did not clearly separate their responsibilities.

The logic was effectively this:

```java
for (RescueUnit unit : units) {
    if (unit.canDispatch(emergencyType)) {
        unit.dispatch(emergencyType);
        System.out.println();
        break;
    }
}
```

The problem was that `canDispatch(emergencyType)` mainly checked whether the unit could handle the emergency type, not whether the unit could actually dispatch with enough fuel.

For example, a child class had logic similar to this:

```java
@Override
public boolean canDispatch(String emergencyType) {
    if (emergencyType.equalsIgnoreCase("flood") || emergencyType.equalsIgnoreCase("river")) {
        return true;
    } else {
        return false;
    }
}
```

This means the method returns `true` even when the unit does not have enough fuel. As a result, the main loop calls `dispatch()` and then immediately executes `break`.

The incorrect flow was:

```text
Emergency type matches
→ canDispatch() returns true
→ dispatch() runs
→ dispatch() prints "Fuel is too low"
→ break runs
→ next unit is not checked
```

The intended flow should be:

```text
Emergency type matches
→ if fuel is too low, print fuel shortage message
→ continue checking the next unit
→ if dispatch succeeds, stop scanning with break
```

A second issue was that `dispatch()` had a `void` return type. Because of this, the main method could not know whether the unit actually dispatched or only printed a failure message.

```java
public void dispatch(String emergencyType) {
    // prints message, but main cannot know success or failure
}
```

This made the main loop unable to decide whether to continue scanning or stop.

A smaller design issue was repeated fuel update logic inside child classes.

```java
this.fuel -= 25;
this.completedMissions++;
```

Since the parent class already has shared behavior such as completing a mission, repeated logic should be reduced by using a parent method such as:

```java
completeMission(25);
```

Another smaller issue was inconsistent output behavior. For example, one branch in `DroneUnit` did not print the fuel used message even though other branches did. This makes the output harder to read and debug.

---

## Why the Mistake Happened

The mistake happened because the meaning of `canDispatch()` was not defined precisely enough.

There are two different questions:

```text
Can this unit handle this emergency type?
Can this unit actually dispatch right now?
```

These are not the same.

A unit may be able to handle `"fire"` logically, but it may still fail to dispatch because:

- fuel is too low
- the unit is not available
- another state condition prevents dispatch

The original code mixed these ideas together. In object-oriented design, method names should match their actual responsibility. If `canDispatch()` only checks emergency type, then the name is misleading. If `canDispatch()` means actual dispatch possibility, then it should check emergency type, fuel, and availability together.

The second reason was that `dispatch()` performed work but did not return a result. Since `void` methods do not send a value back to the caller, the main method had no way to know whether dispatch succeeded.

This is related to the Java concept of return values. A method should return a value when another part of the program needs the result of its operation.

---

## Improved Solution

A better design is to make `dispatch()` return `boolean`.

```java
public boolean dispatch(String emergencyType) {
    return false;
}
```

Then each child class should return:

```java
return false; // dispatch failed
return true;  // dispatch succeeded
```

Example structure:

```java
@Override
public boolean dispatch(String emergencyType) {
    if (!isAvailable()) {
        System.out.println(this.unitName + " cannot dispatch. Unit is not available.");
        return false;
    }

    if (!hasEnoughFuel(25)) {
        System.out.println(this.unitName + " cannot dispatch. Fuel is too low.");
        return false;
    }

    System.out.println(this.unitName + " dispatched to " + emergencyType + " emergency.");
    completeMission(25);
    return true;
}
```

Then the main loop can decide whether to continue scanning or stop.

```java
for (String emergencyType : emergencies) {
    System.out.println("Emergency: " + emergencyType);

    boolean dispatched = false;

    for (RescueUnit unit : units) {
        if (unit.canDispatch(emergencyType)) {
            boolean success = unit.dispatch(emergencyType);

            if (success) {
                dispatched = true;
                System.out.println();
                break;
            }
        }
    }

    if (!dispatched) {
        System.out.println("No unit could dispatch for " + emergencyType + ".");
        System.out.println();
    }
}
```

This fixes the main logic:

```text
If the unit cannot handle the emergency type:
→ skip it silently

If the unit can handle the emergency type but fuel is too low:
→ print fuel shortage message
→ return false
→ main continues scanning

If the unit can handle the emergency type and has enough fuel:
→ dispatch
→ return true
→ main breaks out of the loop
```

Another possible improvement is to rename the current `canDispatch()` method if it only checks emergency type.

```java
public boolean canHandleEmergency(String emergencyType)
```

Then the design becomes clearer:

```java
if (unit.canHandleEmergency(emergencyType)) {
    boolean success = unit.dispatch(emergencyType);

    if (success) {
        break;
    }
}
```

This naming makes the responsibilities easier to understand.

---

## What I Learned

The main lesson is that a method should communicate the information that the caller needs.

In this exercise, `dispatch()` did more than print a message. It decided whether the unit actually departed or failed because of fuel or availability. Since the main method needed that result, `dispatch()` should return a `boolean`.

I also learned that `break` should only be used after the correct stopping condition is reached. In this program, the stopping condition is not “a matching emergency type was found.” The correct stopping condition is “a unit successfully dispatched.”

A better debugging habit is to trace the program flow with questions:

```text
What does this method actually check?
What does the caller need to know after this method runs?
Should the loop continue or stop after this result?
```

This helps distinguish between:

```text
type match
```

and

```text
actual dispatch success
```

Those two states must be handled separately.

---

## Related Java Concepts

- [[9. Class part 2#Inheritance|Inheritance]]
- [[9. Class part 2#Method Overriding|Method Overriding]]
- [[9. Class part 2#Polymorphism|Polymorphism]]
- [[7. Method#Return value|Return Value]]
- [[5. Loop Statement|Loop Statement]]
- [[5. Loop Statement#break|break]]
- [[9. Class part 2#Reference Type|Reference Type]]

