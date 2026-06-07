

## Metadata

Drill ID: JD_2026_06_W1_CampusDeviceAudit

Linked code:
- [View Java Code](../../../../java-drills/src/June_2026/Week_1/JD_2026_06_W1_CampusDeviceAudit/CampusDeviceAudit.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_1/JD_2026_06_W1_CampusDeviceAudit/CampusDevice.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_1/JD_2026_06_W1_CampusDeviceAudit/Projector.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_1/JD_2026_06_W1_CampusDeviceAudit/LabComputer.java)

Difficulty: Level 3 — Object-Oriented Problem Solving

Estimated Time: 60–90 minutes

Actual Time Taken: 64 minutes

Written by: Chanwoo Park

---

## Goal

The goal of this exercise was to build a campus device inspection system using inheritance. The program used `CampusDevice` as the parent class and `Projector` and `LabComputer` as child classes. A `CampusDevice[]` array stored different device types together, and each object decided whether it needed inspection through an overridden `needsInspection()` method. The program also practiced object state updates through `inspect()`, plus searching by asset tag with `trim()` and `equalsIgnoreCase()`.

---

## What I Tried

- Created a parent class, `CampusDevice`, with common fields such as `assetTag`, `roomName`, `isWorking`, and `daySinceInspection`.
- Created child classes, `Projector` and `LabComputer`, using `extends CampusDevice`.
- Used constructors in child classes and called the parent constructor with `super(...)`.
- Overrode `printBasicInfo()`, `needsInspection()`, and `inspect()` in the child classes.
- Used a `CampusDevice[]` array to store both `Projector` and `LabComputer` objects.
- Used a loop in `main` to print device information, run inspections, count inspected devices, and search for one device.
- Used `trim()` and `equalsIgnoreCase()` to make asset tag search more flexible.

---

## Mistakes

The main mistake was calling the parent `needsInspection()` method but not using its returned value:

```java
@Override
public boolean needsInspection() {
    super.needsInspection();

    if (this.hasInputIssue) {
        return true;
    } else if (this.lampHours >= 2000) {
        return true;
    } else {
        return false;
    }
}
```

instead of:

```java
@Override
public boolean needsInspection() {
    return super.needsInspection() || lampHours >= 2000 || hasInputIssue;
}
```

The same issue also happened in `LabComputer`:

```java
@Override
public boolean needsInspection() {
    super.needsInspection();

    if (this.storageUsedPercent >= 90) {
        return true;
    } else if (needsUpdate) {
        return true;
    } else {
        return false;
    }
}
```

instead of:

```java
@Override
public boolean needsInspection() {
    return super.needsInspection() || storageUsedPercent >= 90 || needsUpdate;
}
```

This caused the child classes to ignore the common inspection conditions from the parent class. For example, if a `Projector` was not working but had normal `lampHours` and no input issue, the child `needsInspection()` method could return `false` even though the parent condition said it needed inspection.

A second important mistake was using `else if` in `LabComputer.inspect()`:

```java
if (this.storageUsedPercent >= 90) {
    System.out.println("Storage maintenance completed.");
    setStorageUsedPercent(storageUsedPercent - 20);
} else if (needsUpdate) {
    System.out.println("Software updated.");
    setNeedsUpdate(false);
}
```

This means that when both problems exist, only the storage problem is fixed. The software update is skipped.

A third issue was calling `inspect()` even when a device did not need inspection:

```java
if (device.needsInspection()) {
    device.inspect();
    counter++;
} else {
    device.inspect();
    System.out.println(device.getAssetTag().trim() + " does not need inspection.");
}
```

This makes the output confusing because a device can be inspected and then immediately reported as not needing inspection.

---

## Why the Mistake Happened

The main issue came from misunderstanding how a `boolean` return value from a method should be used.

Calling this method:

```java
super.needsInspection();
```

does execute the parent method, but the returned `true` or `false` disappears if it is not stored, returned, or used inside a condition. In inheritance, a child method can extend the parent method's logic, but it must include the parent result explicitly.

The intended logic was:

```text
A child device needs inspection if:
parent condition is true
OR child-specific condition is true
```

So the child class should combine the parent result with its own conditions using `||`.

The `LabComputer.inspect()` problem happened because `else if` creates mutually exclusive branches. That structure is correct when only one action should happen, but inspection can involve multiple independent problems. Storage maintenance and software update are separate issues, so they need separate `if` statements.

The `inspect()` call in the `else` block happened because the roles of `needsInspection()` and `inspect()` were not fully separated. `needsInspection()` should decide whether inspection is necessary. `inspect()` should only run when inspection is actually needed.

---

## Improved Solution

Change `Projector.needsInspection()` to:

```java
@Override
public boolean needsInspection() {
    return super.needsInspection() || lampHours >= 2000 || hasInputIssue;
}
```

Change `LabComputer.needsInspection()` to:

```java
@Override
public boolean needsInspection() {
    return super.needsInspection() || storageUsedPercent >= 90 || needsUpdate;
}
```

This preserves the parent class inspection rules and adds child-specific rules.

Improve `LabComputer.inspect()` to handle multiple independent problems:

```java
@Override
public void inspect() {
    System.out.println("Inspecting lab computer " + getAssetTag().trim());

    if (!isWorking()) {
        System.out.println("Repair completed.");
        setWorking(true);
    }

    if (storageUsedPercent >= 90) {
        System.out.println("Storage maintenance completed.");
        setStorageUsedPercent(storageUsedPercent - 20);
    }

    if (needsUpdate) {
        System.out.println("Software updated.");
        setNeedsUpdate(false);
    }

    setDaySinceInspection(0);
}
```

This fixes both storage and update problems when both exist.

Improve the inspection loop in `main` to avoid inspecting devices that do not need inspection:

```java
for (CampusDevice device : devices) {
    if (device.needsInspection()) {
        device.inspect();
        counter++;
    } else {
        System.out.println(device.getAssetTag().trim() + " does not need inspection.");
    }

    System.out.println();
}
```

This makes `needsInspection()` act as the filter and `inspect()` act as the state-changing action.

---

## What I Learned

I learned that overriding a method does not automatically preserve the parent method's logic. If the child class still needs the parent logic, I must explicitly use the parent result with `super.methodName()`.

I also learned that `if` and `else if` express different logic. Separate `if` statements are better when several actions may all need to happen. `else if` is better when only one branch should run.

For debugging, I should check whether each method's return value is actually used. If a method returns `boolean`, I should ask:

```text
Am I returning this value?
Am I storing it?
Am I using it in an if condition?
```

I should also test edge cases where multiple inspection reasons are true at the same time, such as a lab computer with both high storage usage and a pending update.

---

## Related Java Concepts

- [[9. Class part 2#Inheritance|Inheritance]]
- [[9. Class part 2#Method Overriding|Method Overriding]]
- [[9. Class part 2#Super|Super]]
- [[9. Class part 2#Polymorphism|Polymorphism]]
- [[4. Conditional statement#if-else Statement|if-else Statement]]
- [[9. Class part 2#Reference Type|Reference Type]]

