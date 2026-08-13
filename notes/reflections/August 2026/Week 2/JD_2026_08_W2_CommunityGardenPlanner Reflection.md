

## Metadata

Drill ID: JD_2026_08_W2_CommunityGardenPlanner

Linked code: [View Java Code](../../../../java-drills/src/August_2026/Week_2/JD_2026_08_W2_CommunityGardenPlanner/CommunityGardenApp.java)

Difficulty: Level 2 — Multi-Class Application

Estimated Time: 45–60 minutes

Actual Time Taken: 48 minutes

Written by: Chanwoo Park

---

## Goal

Build a community garden planner using multiple collaborating classes. The program registers `Plot` and `Gardener` objects, prevents duplicate IDs, matches gardeners to plots by `PlotSize`, updates both objects when an assignment succeeds, and reports available and occupied plots.

The release feature was intentionally removed from the final version, so this implementation focuses on one-way assignment from `AVAILABLE` to `OCCUPIED`.

---

## What I Tried

- Created separate `Plot`, `Gardener`, `GardenManager`, `PlotSize`, and `PlotStatus` types.
- Used `enum` values instead of raw `String` values for plot size and status.
- Used `List<Gardener>` and `List<Plot>` as the collection field types in `GardenManager`.
- Used `ArrayList` as the concrete implementation.
- Added validation for empty IDs and duplicate gardener/plot IDs.
- Used `GardenManager` to coordinate assignments instead of putting the matching logic in `main`.
- Matched a gardener's preferred size with a plot's size.
- Updated both `Gardener.plotID` and `Plot.gardenerID` when an assignment succeeded.
- Counted available and occupied plots by iterating through the plot collection.
- Kept normal registration and summary output in `CommunityGardenApp` in several places.

---

## Mistakes

The most important logic issue is that `assignGardener(Plot plot)` does not verify that the plot is still available before assigning another gardener.

Current logic:

```java
public void assignGardener(Plot plot) {
    for (Gardener gardener : gardenerList) {
        if (gardener.getPreferredSize().equals(plot.getPlotSize())
                && gardener.getPlotID() == null) {

            gardener.setPlotID(plot.getPlotID());
            plot.setGardenerID(gardener.getGardenerID());
            plot.isOccupied();
            break;
        }
    }
}
```

If this method is called a second time for an already occupied plot, another unassigned gardener with the same preferred size can receive the same plot.

`plot.setGardenerID(...)` will overwrite the previous gardener ID, while the first gardener still stores the same plot ID.

That could create an inconsistent state such as:

```text
Gardener G-101 → P-01
Gardener G-104 → P-01

Plot P-01 → G-104
```

The plot and gardener objects would no longer agree about the assignment.

A second design issue is that assignment state can still be modified too freely:

```java
gardener.setPlotID(...);
plot.setGardenerID(...);
plot.isOccupied();
```

Although the fields are `private`, outside code can still change the pieces of an assignment separately. This means encapsulation is only partial.

The method name:

```java
isOccupied()
```

is also misleading because names beginning with `is...` usually suggest a boolean question, but this method actually changes the plot state.

Another issue is exposing the real mutable collections:

```java
public List<Gardener> getGardenerList() {
    return gardenerList;
}

public List<Plot> getPlotList() {
    return plotList;
}
```

Code outside `GardenManager` can directly add, remove, or replace elements without using `addGardener()` or `addPlot()`. That allows duplicate-ID validation to be bypassed.

The current assignment direction also changes the meaning of the original task.

The application loops through every plot:

```java
for (Plot plot : plotList) {
    manager.assignGardener(plot);
}
```

so the question becomes:

```text
"For this plot, can I find a gardener?"
```

rather than:

```text
"For this gardener, can I find the first suitable available plot?"
```

This approach can work as a different design, but it makes it harder to report the important failure case where a gardener cannot receive a plot because all matching plots are occupied.

The summary currently prints plots and plot counts, but does not print every gardener and the gardener's current assignment.

---

## Why the Mistake Happened

I correctly separated collection coordination into `GardenManager`, but I focused mainly on finding a matching `PlotSize`.

Because of that, I checked the gardener side:

```java
gardener.getPlotID() == null
```

but did not apply the same protection to the plot side:

```text
plot must also be AVAILABLE
```

This shows that when two objects represent one relationship, I need to verify the invariant from both sides before changing either object.

I also used setter-style methods because they made it easy for `GardenManager` to update both objects. However, this makes it possible to create partial or contradictory state.

The collection getters were convenient for loops in `CommunityGardenApp`, but returning the actual lists weakens the duplicate-registration rules that `GardenManager` is supposed to protect.

---

## Improved Solution

Before assignment, confirm all conditions first:

```text
1. Gardener is not already assigned.
2. Plot is AVAILABLE.
3. Gardener preferred size matches Plot size.
```

Only after all three conditions succeed should either object change.

The `Plot` object should own its own assignment behavior rather than exposing separate mutation methods.

Conceptually:

```java
plot.assignTo(gardenerID);
```

That method could protect rules such as:

```text
status must be AVAILABLE
gardenerID must not already be assigned
```

Likewise, `Gardener` could use a behavior-oriented method instead of a generic setter:

```java
gardener.assignPlot(plotID);
```

This would make the intention clearer than `setPlotID()`.

A clearer state-changing method name would also be:

```java
occupy()
```

or better, combine that state change into `assignTo()` instead of having a separate `isOccupied()` mutator.

The manager should also avoid returning its actual mutable collections. If the application only needs reporting, the manager can provide reporting behavior or return information without allowing the caller to mutate the original lists.

Finally, assignment would better match the original exercise goal if the manager receives a gardener and searches for the first available matching plot:

```text
Gardener
   ↓
GardenManager searches plots
   ↓
first matching AVAILABLE Plot
```

This naturally supports the failure case:

```text
"No matching available plot exists for this gardener."
```

---

## What I Learned

I successfully used `List` as the collection abstraction and separated `Plot`, `Gardener`, and `GardenManager` into distinct responsibilities.

I also kept the basic assignment relationship synchronized during the normal successful path by updating both objects.

However, I learned that a normal successful path is not enough to prove that two-object state is safe. I also need to ask what happens if the same operation is called again.

The most important debugging/design habit from this exercise is:

```text
When two objects represent one relationship,
validate both sides before changing either side.
```

I also learned that `private` fields do not automatically mean strong encapsulation. If several public setters can independently modify parts of one relationship, outside code can still create invalid state.

For future exercises, I should check:

- whether both sides of a relationship are validated
- whether an operation can be safely called twice
- whether method names describe behavior accurately
- whether mutable collections are exposed
- whether business rules can be bypassed through setters
- whether the output covers both sides of the object relationship

---

## Related Java Concepts

- [[8. Class part 1#Class|Class]]
- [[8. Class part 1#Access Modifier|Encapsulation]]
- [[11. Collection Framework#List Interface|List]]
- [[9. Class part 2#Enum|Enum]]
- [[4. Conditional statement#if Statement|if Statement]]
- [[5. Loop Statement#for Loop|for Loop]]
