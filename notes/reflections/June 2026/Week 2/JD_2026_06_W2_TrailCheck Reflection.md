

## Metadata

Drill ID: JD_2026_06_W2_TrailCheck

Linked code:
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W2_TrailCheck/Trail.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W2_TrailCheck/TrailLog.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W2_TrailCheck/TrailCheckMain.java)

Difficulty: Level 2 — Multi-Class Application

Estimated Time: 50 minutes

Actual Time Taken: 43 minutes

Written by: Chanwoo Park

---

## Goal

Build a small hiking trail inspection program using `Trail`, `TrailLog`, and `TrailCheckMain`. The program creates multiple trail objects, stores them in an array, updates open/closed state based on safety score, searches by name or keyword, and prints inspection results.

---

## What I Tried

- Created a `Trail` class with private instance variables.
- Used a constructor to initialize each trail object.
- Added getters, setters, and instance methods such as `printInfo()`, `needsWarningSign()`, and `closeTrail()`.
- Created a `TrailLog` class to manage a `Trail[]` array.
- Used normal `for` loops and enhanced `for` loops to process objects.
- Used `equalsIgnoreCase()`, `toLowerCase()`, and `contains()` for String-based searching.
- Used conditionals to count open trails, close unsafe trails, and print warning signs.

---

## Mistakes

The main mismatch was:

```java
if(trails[i].getSafetyScore() < 65){
    trails[i].closeTrail();
}
```

instead of the assignment rule:

```java
if (trails[i].getSafetyScore() < 60) {
    trails[i].closeTrail("Low safety score");
}
```

This changed the program behavior. A trail with a safety score between 60 and 64 would be closed in this solution, even though the requirement said only scores below 60 should be closed.

Another requirement mismatch was:

```java
public void closeTrail() {
    isOpen = false;
}
```

The assignment expected a reason to be stored when a trail is closed:

```java
public void closeTrail(String reason) {
    this.isOpen = false;
    this.closedReason = reason;
}
```

Without a reason field, `getStatusLabel()` cannot explain why a trail is closed.

---

## Why the Mistake Happened

The issue was not a syntax problem. The program compiled and ran correctly.

The mistake came from requirement tracking and object state design. The `Trail` object should not only know whether it is open or closed; it should also remember the reason when its state changes. This connects to `Class`, `Instance Variable`, `Constructor`, and `Instance Method` concepts.

The threshold issue is a logic condition problem. In Java, changing `< 60` to `< 65` still compiles, but it changes the business rule.

---

## Improved Solution

Change the closing logic to:

```java
public void closeUnsafeTrails() {
    for (int i = 0; i < trails.length; i++) {
        if (trails[i].getSafetyScore() < 60) {
            trails[i].closeTrail("Low safety score");
        }
    }
}
```

And change `Trail` to store the reason:

```java
private String closedReason;

public void closeTrail(String reason) {
    this.isOpen = false;
    this.closedReason = reason;
}

public String getStatusLabel() {
    if (isOpen) {
        return "Open";
    }
    return "Closed: " + closedReason;
}
```

This keeps the state update inside the `Trail` object and lets `TrailLog` use the object's method instead of managing the details itself.

---

## What I Learned

A Java program can compile and still fail part of the requirement if the condition or method signature does not match the problem statement.

Next time, check these before finishing:

1. Does every required method exist with the exact expected parameter list?
2. Are numeric thresholds copied exactly from the requirement?
3. Does the object store all state needed for later output?
4. Are search inputs cleaned with `trim()` before comparison?

---

## Related Java Concepts

- [[8. Class part 1|Class]]
- [[8. Class part 1#Instance Variable|Instance Variable]]
- [[8. Class part 1#Constructor|Constructor]]
- [[8. Class part 1#Getter and Setter|Getter and Setter]]
- [[6. Array|Array]]
- [[5. Loop Statement|Loop Statement]]
- [[4. Conditional statement|Conditional Statement]]
