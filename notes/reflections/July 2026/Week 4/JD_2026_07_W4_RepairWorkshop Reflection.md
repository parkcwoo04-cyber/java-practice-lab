

## Metadata

Drill ID: JD_2026_07_W4_RepairWorkshop

Linked code: [View Java Code](../../java-drills/src/July_2026/Week_4/JD_2026_07_W4_RepairWorkshop/RepairWorkshopConsole.java)

Difficulty: Level 4 — Creative OOP Challenge

Estimated Time: 120–150 minutes

Actual Time Taken: 152 minutes

Written by: Chanwoo Park

---

## Goal

Build a repair workshop application using `RepairJob`, `Workshop`, concrete standard and urgent job types, enum-based workflow states, custom exceptions, collections, and file input/output. The main objective was to control repair-state transitions, calculate charges, continue after invalid operations, and save and restore repair-job data.

---

## What I Tried

- Created separate classes for the console, repair job, workshop service, storage, enum, custom exception, and concrete repair types.
- Used helper methods in `RepairWorkshopConsole` so each operation could handle its own exception without stopping later operations.
- Used `JobStatus` to represent repair workflow states.
- Added constructor validation for blank job ID, customer name, and device name.
- Used `Workshop` to register, search, diagnose, repair, complete, collect, cancel, filter, and summarize jobs.
- Used `StandardRepairJob` and `UrgentRepairJob` to apply different base repair costs.
- Used try-with-resources with `BufferedWriter` and `BufferedReader`.
- Converted repair jobs to comma-separated lines and restored jobs from saved text.

---

## What Worked Well

### Operation-level exception handling

The console used helper methods such as:

```java
private static void diagnoseJob(
        Workshop workshop,
        String jobID,
        String description,
        int cost) {

    try {
        workshop.diagnoseJob(jobID, description, cost);
    } catch (InvalidWorkshopOperationException e) {
        System.out.println(e.getMessage());
    }
}
```

This allowed one rejected operation to be reported without stopping the remaining scenario. This was an improvement over placing every demonstration inside one large `try-catch` block.

### Constructor exceptions were allowed to escape

The `RepairJob` constructor validated required values and threw an exception instead of catching it internally:

```java
if (customerName == null || customerName.isBlank()) {
    throw new InvalidWorkshopOperationException(
            "Rejected: Customer Name cannot be blank."
    );
}
```

This prevented invalid objects from completing construction.

### Shared lookup logic

`Workshop` reused one search method instead of duplicating the search loop inside every operation:

```java
private RepairJob foundJob(String jobID) {
    for (RepairJob repairJob : repairJobs) {
        if (repairJob.getJobId().equalsIgnoreCase(jobID)) {
            return repairJob;
        }
    }
    return null;
}
```

This reduced repeated collection-search code.

### File resources were closed safely

Both save and load operations used try-with-resources, which was the correct approach for closing file resources.

---

## Mistakes

### Main design mistake: `Workshop` changed `RepairJob` state directly

The main mistake was:

```java
repairJob.setDiagnosisDescription(diagnoseDescription);
repairJob.setReplacePartsCost(replacePartsCost);
repairJob.setStatus(JobStatus.DIAGNOSED);
```

instead of asking the object that owns the state to perform the operation:

```java
repairJob.diagnose(diagnoseDescription, replacePartsCost);
```

The same issue appeared in repair, completion, collection, and cancellation. `Workshop` knew the complete transition rules and directly modified the job through public setters.

This weakened encapsulation because any class could call:

```java
repairJob.setStatus(JobStatus.COLLECTED);
```

without diagnosis, repair, or completion.

---

### Workflow rules were incomplete

`cancelRepair()` cancelled a job regardless of its current status:

```java
repairJob.setStatus(JobStatus.CANCELLED);
```

This allowed an `IN_REPAIR`, `COMPLETED`, `COLLECTED`, or already `CANCELLED` job to be cancelled.

The intended rule was that cancellation should be allowed only from `RECEIVED` or `DIAGNOSED`.

Also, `repair()` printed an error when the state was invalid instead of throwing the custom exception:

```java
if (repairJob.isDiagnosed()) {
    repairJob.setStatus(JobStatus.IN_REPAIR);
} else {
    System.out.println(repairJob.getJobId() + " should be diagnosed.");
}
```

This made error handling inconsistent. Some invalid operations threw an exception, while others only printed a message.

---

### Domain and service classes printed user-facing output

`RepairJob`, `Workshop`, and `RepairJobStorage` all called `System.out.println()` or `printf()`.

Examples included:

```java
System.out.println("Registered: ...");
```

```java
System.out.println(repairJob.getJobId() + " repair started.");
```

```java
System.out.println("saved " + repairJobs.size() + " jobs.");
```

The console should have been the only class responsible for user-facing output. Domain and service methods should return useful results or throw exceptions.

---

### The inheritance and polymorphism objective was only partially implemented

`RepairJob` was declared as a concrete class, and both subclasses inherited the same `finalCharge()` behavior:

```java
public int finalCharge() {
    return repairCost + replacePartsCost;
}
```

`UrgentRepairJob` changed the base repair cost to `45`, but it did not override charge calculation or represent an urgent surcharge separately.

The intended design was closer to:

```java
public abstract int calculateFinalCharge();
```

with one implementation in `StandardRepairJob` and another in `UrgentRepairJob`.

This would allow different calculation behavior through a `RepairJob` reference without checking the subtype.

---

### Saved data did not preserve enough object state

The saved line contained:

```text
job ID, customer, device, urgency, status, final charge
```

but did not preserve:

- diagnosis description
- base repair cost
- replaced-parts cost
- a separate urgent surcharge value

The loaded object was created with default costs and then only `finalCharge` was assigned:

```java
repairJob.setFinalCharge(Integer.parseInt(finalCharge));
```

However, `printJobDetails()` called `finalCharge()`, which recalculated the value from `repairCost` and `replacePartsCost`. Therefore, the restored `finalCharge` field was not actually used by the report.

This means the loaded object could display a different charge from the saved value and did not fully restore its original state.

---

### Concrete subtype restoration was missing

`lineToJobConvert()` always created:

```java
new RepairJob(jobId, customerName, deviceName);
```

even when the saved urgency was `STANDARD` or `URGENT`.

The load process should recreate the correct concrete class:

```java
RepairJob job;

if (type.equals("STANDARD")) {
    job = new StandardRepairJob(...);
} else if (type.equals("URGENT")) {
    job = new UrgentRepairJob(...);
}
```

Otherwise, the polymorphic type information is lost during loading.

---

### The collection was exposed directly

`Workshop` returned its internal mutable `ArrayList`:

```java
public ArrayList<RepairJob> getRepairJobs() {
    return repairJobs;
}
```

It also allowed the entire list to be replaced through `setRepairJobs()`.

External code could therefore bypass registration rules:

```java
workshop.getRepairJobs().clear();
```

or insert duplicate and invalid entries directly.

A safer design would declare the field through the interface and return a copy:

```java
private final List<RepairJob> repairJobs = new ArrayList<>();

public List<RepairJob> getAllJobs() {
    return new ArrayList<>(repairJobs);
}
```

---

### Duplicate state for final charge

`RepairJob` stored a `finalCharge` field but also recalculated the charge every time through:

```java
return repairCost + replacePartsCost;
```

This created two possible sources of truth:

- stored `finalCharge`
- calculated `repairCost + replacePartsCost`

If the values disagree, it is unclear which one is correct.

The charge should normally be calculated from its component values, or stored once under a clearly controlled rule, but not both without synchronization.

---

## Why the Mistakes Happened

The implementation still treated `RepairJob` mostly as a data container and `Workshop` as the class that performs all business logic.

This led to:

- public setters for protected workflow data
- transition rules placed in the service
- direct status assignment
- printing mixed with domain operations
- storage restoration through setters
- weak separation between object state and collection management

The key concept not fully applied was **state ownership**.

A class that owns a field should usually own the rules that control changes to that field. Since `RepairJob` owns `status`, `diagnosisDescription`, and `replacePartsCost`, it should also own diagnosis, repair start, completion, collection, and cancellation behavior.

The second concept was **polymorphic behavior**. Changing only a field value in a subclass is inheritance, but overriding a common calculation contract is what demonstrates behavioral polymorphism.

---

## Improved Solution

### Move state transitions into `RepairJob`

Change direct setter-based state changes to meaningful operations:

```java
public void diagnose(String description, int partsCost)
        throws InvalidWorkshopOperationException {

    if (status != JobStatus.RECEIVED) {
        throw new InvalidWorkshopOperationException(
                "Job can only be diagnosed from RECEIVED."
        );
    }

    if (description == null || description.isBlank()) {
        throw new InvalidWorkshopOperationException(
                "Diagnosis cannot be blank."
        );
    }

    if (partsCost < 0) {
        throw new InvalidWorkshopOperationException(
                "Parts cost cannot be negative."
        );
    }

    this.diagnosisDescription = description;
    this.replacePartsCost = partsCost;
    this.status = JobStatus.DIAGNOSED;
}
```

Then `Workshop` should only locate and delegate:

```java
public void diagnoseJob(String jobID, String description, int partsCost)
        throws InvalidWorkshopOperationException {

    findJobOrThrow(jobID).diagnose(description, partsCost);
}
```

---

### Remove unrestricted workflow setters

Remove or restrict:

```java
setStatus(...)
setDiagnosisDescription(...)
setReplacePartsCost(...)
setUrgency(...)
setFinalCharge(...)
```

Replace them with domain actions and read-only getters.

---

### Make charge calculation polymorphic

Use an abstract calculation method:

```java
public abstract int calculateFinalCharge();
```

Standard implementation:

```java
@Override
public int calculateFinalCharge() {
    validateChargeAvailable();
    return getRepairCost() + getReplacePartsCost();
}
```

Urgent implementation:

```java
@Override
public int calculateFinalCharge() {
    validateChargeAvailable();
    return getRepairCost()
            + getReplacePartsCost()
            + urgentSurcharge;
}
```

---

### Keep output in the console

Instead of printing from `Workshop`, return the relevant object or result:

```java
RepairJob job = workshop.completeRepair("RJ-101");
System.out.println(job.getJobId() + " repair completed.");
```

For saving:

```java
int savedCount = storage.saveRepairJobs(workshop.getAllJobs());
System.out.println("Saved " + savedCount + " jobs.");
```

---

### Restore complete subtype state

The saved format should include enough fields to reconstruct the job accurately, for example:

```text
type,id,customer,device,baseCost,status,diagnosis,partsCost,surcharge
```

The loader should validate field count and numeric and enum values before creating a completed object.

It should recreate the correct subtype and use a controlled restoration design rather than unrestricted public setters.

---

## What I Learned

I learned that dividing a program into multiple classes is not enough by itself. Each class must also own the rules connected to its data.

`Workshop` should manage the collection and find jobs, but `RepairJob` should decide whether its own workflow state may change.

I also learned that exception handling and exception creation are different responsibilities:

- domain and service classes validate and throw
- the console catches and displays

Using helper methods in the console was a useful way to continue the demonstration after individual failures.

I learned that inheritance is not only about changing field values. For polymorphism, subclasses should override behavior such as final-charge calculation.

For file restoration, saving only the displayed total is not enough. The file must preserve the data required to recreate a valid object and its concrete subtype.

Next time I should check:

1. Which object owns the field being changed?
2. Can another class bypass the intended workflow?
3. Does a failed operation leave every field unchanged?
4. Is any non-console class printing user-facing messages?
5. Is the same information stored in two different forms?
6. Can saved data fully recreate the original object?
7. Does subtype-specific behavior survive save and load?

---

## Related Java Concepts

- [[8. Class part 1#Access Modifier|Encapsulation]]
- [[12. Exception Handling#Custom Exception|Custom Exception]]
- [[9. Class part 2#Inheritance|Inheritance]]
- [[8. Class part 1#Method Overloading|Overriding]]
- [[9. Class part 2#Polymorphism|Polymorphism]]
- [[9. Class part 2#Enum|Enum]]
- [[13. Input, Output and Files|File Input and Output]]
