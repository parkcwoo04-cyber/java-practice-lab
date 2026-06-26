

## Metadata

Drill ID: JD_2026_06_W4_CampusMaintenanceRequestApp

Linked code:
- [View Java Code](../../../java-drills/src/June_2026/Week_4/JD_2026_06_W4_CampusMaintenanceRequestApp/CampusMaintenanceRequestApp.java)
- [View Java Code](../../../java-drills/src/June_2026/Week_4/JD_2026_06_W4_CampusMaintenanceRequestApp/MaintenanceCenter.java)
- [View Java Code](../../../java-drills/src/June_2026/Week_4/JD_2026_06_W4_CampusMaintenanceRequestApp/MaintenanceRequest.java)
- [View Java Code](../../../java-drills/src/June_2026/Week_4/JD_2026_06_W4_CampusMaintenanceRequestApp/MaintenanceTeam.java)
- [View Java Code](../../../java-drills/src/June_2026/Week_4/JD_2026_06_W4_CampusMaintenanceRequestApp/ElectricalTeam.java)
- [View Java Code](../../../java-drills/src/June_2026/Week_4/JD_2026_06_W4_CampusMaintenanceRequestApp/PlumbingTeam.java)
- [View Java Code](../../../java-drills/src/June_2026/Week_4/JD_2026_06_W4_CampusMaintenanceRequestApp/CleaningTeam.java)
- [View Java Code](../../../java-drills/src/June_2026/Week_4/JD_2026_06_W4_CampusMaintenanceRequestApp/Assignable.java)
- [View Java Code](../../../java-drills/src/June_2026/Week_4/JD_2026_06_W4_CampusMaintenanceRequestApp/RequestType.java)
- [View Java Code](../../../java-drills/src/June_2026/Week_4/JD_2026_06_W4_CampusMaintenanceRequestApp/InvalidRequestException.java)

Difficulty: Level 3 — Object-Oriented Problem Solving

Estimated Time: 60–90 minutes

Actual Time Taken: 73 ______ minutes

Written by: Chanwoo Park

---

## Goal

The goal of this practice was to build a campus maintenance request system using multiple collaborating classes. The program registers maintenance teams, accepts valid maintenance requests, rejects invalid requests with a custom exception, prioritizes urgent requests, and assigns each request to a team that can handle its request type. The main concepts practiced were `abstract class`, `interface`, `enum`, overriding, polymorphism, `ArrayList`, `LinkedHashMap`, validation, and custom exception handling.

---

## What I Tried

- Created `MaintenanceRequest` to store request data such as request ID, requestor name, location, request type, severity level, and assignment state.
- Created `RequestType` as an `enum` to represent fixed request categories: `ELECTRICAL`, `PLUMBING`, and `CLEANING`.
- Created `Assignable` as an interface with `canHandle()` and `assignRequest()` methods.
- Created `MaintenanceTeam` as an abstract parent class for shared team state and behavior.
- Created `ElectricalTeam`, `PlumbingTeam`, and `CleaningTeam` as child classes that override `canHandle()` based on request type.
- Created `MaintenanceCenter` to register teams, validate requests, store accepted requests, assign requests, and print reports.
- Used `ArrayList` to store teams and requests.
- Used `LinkedHashMap<MaintenanceRequest, MaintenanceTeam>` to connect each assigned request with the team assigned to it.
- Used `InvalidRequestException` to reject invalid request data such as invalid severity or missing required fields.
- Used `isUrgent()` in `MaintenanceRequest` to classify requests with severity level 4 or higher as urgent.

---

## Mistakes

The main mistake was that `completedRequestCount` was created but not updated when a team handled a request.

```java
@Override
public void assignRequest(MaintenanceRequest request) {
    this.setAvailable(false);
    request.setAssigned(true);
}
```

instead of:

```java
@Override
public void assignRequest(MaintenanceRequest request) {
    this.available = false;
    this.completedRequestCount++;
    request.markAssigned();
}
```

Because `completedRequestCount` was never increased, the final team summary showed `Completed: 0` even after teams had handled requests. This was a logic error, not a syntax error. The program ran, but the state of each team did not accurately reflect the work it completed.

A second important issue was that the assignment loop did not stop after a request was assigned.

```java
for (MaintenanceTeam maintenanceTeam : maintenanceTeams) {
    if (maintenanceTeam.canHandle(maintenanceRequest)) {
        maintenanceTeam.assignRequest(maintenanceRequest);
        maintenanceRequestsMap.put(maintenanceRequest, maintenanceTeam);
    }
}
```

If there are multiple teams that can handle the same request type, the same request could be assigned more than once. The loop should stop after the first successful assignment.

```java
for (MaintenanceTeam maintenanceTeam : maintenanceTeams) {
    if (maintenanceTeam.isAvailable() && maintenanceTeam.canHandle(maintenanceRequest)) {
        maintenanceTeam.assignRequest(maintenanceRequest);
        maintenanceRequestsMap.put(maintenanceRequest, maintenanceTeam);
        break;
    }
}
```

A smaller design issue was that `registerMaintenanceTeam()` still used `instanceof` to print the team type.

```java
if (maintenanceTeam instanceof ElectricalTeam) {
    System.out.print("[ELECTRICAL TEAM] ");
} else if (maintenanceTeam instanceof PlumbingTeam) {
    System.out.print("[PLUMBING TEAM] ");
} else if (maintenanceTeam instanceof CleaningTeam) {
    System.out.print("[CLEANING TEAM] ");
}
```

This worked, but it made `MaintenanceCenter` depend on specific child classes. Since each team already has `getTeamType()`, the center does not need to check the concrete subclass.

---

## Why the Mistake Happened

The `completedRequestCount` mistake happened because assignment was treated mostly as changing the request state, not as changing both the request state and the team state. In object-oriented design, assigning a request affects two objects:

- the `MaintenanceRequest` becomes assigned
- the `MaintenanceTeam` becomes unavailable and its completed request count should change

The missing `break` happened because the loop was written to find matching teams, but it did not clearly define the rule that one request should be assigned to only one team. The code checked capability correctly, but it did not stop after the assignment goal had already been achieved.

The remaining `instanceof` issue happened because polymorphism was applied well in `assignRequests()`, but not fully applied in `registerMaintenanceTeam()`. The design already had a polymorphic method, `getTeamType()`, but the coordinator class still used concrete class checks in one place.

The encapsulation issue happened because several fields in `MaintenanceTeam` were package-private.

```java
String teamID;
String baseBuilding;
boolean available;
int completedRequestCount;
```

This made it easier for child classes to access the data directly, but it weakened state protection. Fields such as `teamID` and `baseBuilding` should usually be initialized through the constructor and then protected from arbitrary modification.

---

## Improved Solution

First, update `MaintenanceTeam.assignRequest()` so the team state changes correctly.

```java
@Override
public void assignRequest(MaintenanceRequest request) {
    this.available = false;
    this.completedRequestCount++;
    request.markAssigned();
}
```

Then replace the generic setter in `MaintenanceRequest` with a more meaningful state-change method.

```java
public void markAssigned() {
    this.assigned = true;
}
```

This makes the code more readable because the method name describes the meaning of the state change.

Next, separate availability checking from request-type capability checking. Each child class should only decide whether it can handle the request type.

```java
@Override
public boolean canHandle(MaintenanceRequest request) {
    return request.getRequestType() == RequestType.ELECTRICAL;
}
```

Then `MaintenanceCenter` should combine availability and capability:

```java
if (maintenanceTeam.isAvailable() && maintenanceTeam.canHandle(maintenanceRequest)) {
    maintenanceTeam.assignRequest(maintenanceRequest);
    maintenanceRequestsMap.put(maintenanceRequest, maintenanceTeam);
    break;
}
```

This version makes the responsibility clearer:

- `MaintenanceTeam.isAvailable()` checks whether the team is currently free.
- `MaintenanceTeam.canHandle()` checks whether the team can handle the request type.
- `MaintenanceCenter` coordinates the assignment process.

Next, remove `instanceof` from `registerMaintenanceTeam()` and use polymorphism instead.

```java
public void registerMaintenanceTeam(MaintenanceTeam maintenanceTeam) {
    maintenanceTeams.add(maintenanceTeam);
    System.out.println("[" + maintenanceTeam.getTeamType() + "] "
            + maintenanceTeam.getTeamID() + " registered.");
}
```

Finally, improve encapsulation in `MaintenanceTeam`.

```java
public abstract class MaintenanceTeam implements Assignable {
    private final String teamID;
    private final String baseBuilding;
    private boolean available;
    private int completedRequestCount;

    public MaintenanceTeam(String teamID, String baseBuilding) {
        this.teamID = teamID;
        this.baseBuilding = baseBuilding;
        this.available = true;
        this.completedRequestCount = 0;
    }

    public String getTeamID() {
        return teamID;
    }

    public String getBaseBuilding() {
        return baseBuilding;
    }

    public boolean isAvailable() {
        return available;
    }

    protected void markUnavailable() {
        this.available = false;
    }

    protected void increaseCompletedRequestCount() {
        this.completedRequestCount++;
    }
}
```

This protects object state better and reduces the chance of accidental changes from outside the class.

---

## What I Learned

I learned that applying polymorphism in only one part of the program is not enough. `assignRequests()` used `canHandle()` well, but `registerMaintenanceTeam()` still used `instanceof`, which means the coordinator class still knew too much about the concrete child classes.

I also learned that assignment is a state-changing action involving more than one object. When a request is assigned, the request state and the team state must both be updated. If only one object changes, the program may still run, but the final report can become logically incorrect.

Another important lesson is that loop control matters in object collaboration. When the goal is to assign one request to one team, the loop should stop after a successful assignment. Otherwise, the code may accidentally assign the same request multiple times when more teams are added later.

For debugging, I should check not only whether the program prints the expected messages, but also whether each object's internal state changes correctly after each action.

---

## Related Java Concepts

- [[10. Abstraction and Interface#Abstraction|Abstraction]]
- [[10. Abstraction and Interface#Interface|Interface]]
- [[9. Class part 2#Inheritance|Inheritance]]
- [[9. Class part 2#Method Overriding|Method Overriding]]
- [[9. Class part 2#Polymorphism|Polymorphism]]
- [[8. Class part 1#Access Modifier|Encapsulation]]
- [[9. Class part 2#Enum|Enum]]
- [[11. Collection Framework|Collection Framework]]
- [[12. Exception Handling|Exception Handling]]


