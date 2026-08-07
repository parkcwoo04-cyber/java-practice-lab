

## Metadata

Drill ID: JD_2026_08_W1_CourseRegistrationSystem

Linked code: [View Java Code](../../java-drills/src/August_2026/Week_1/JD_2026_08_W1_CourseRegistrationSystem/Main.java)

Difficulty: Level 4 — Object-Oriented Scheduling System

Estimated Time: 150–210 minutes

Actual Time Taken: 187 minutes

Written by: Chanwoo Park

---

## Goal

Build a university course registration system using `Student`, `Course`, `Registration`, and `RegistrationManager` objects. The program uses an abstract `Course` hierarchy with `OnlineCourse` and `OfflineCourse`, stores students, courses, and registrations in collections, validates registration rules with a custom exception, tracks course seats and enrollment counts, and saves registration relationships to a text file.

---

## What I Tried

- Created an abstract `Course` parent class and specialized it with `OnlineCourse` and `OfflineCourse`.
- Used `CourseType` as an `enum` and `RegistrationService` as an interface implemented by `RegistrationManager`.
- Created `Registration` as a separate object that represents the relationship between one `Student` and one `Course`.
- Stored students, courses, and registrations separately in `ArrayList` collections.
- Centralized duplicate student IDs, duplicate course IDs, duplicate registrations, and missing-object checks in `RegistrationManager`.
- Updated the student's registered-course list, remaining seats, and enrolled-student count when registration succeeds.
- Created `InvalidRegistrationException` to handle invalid application states.
- Implemented file saving with `BufferedWriter` and loading with `BufferedReader`.
- Saved each registration as a `studentID,courseCode` relationship instead of duplicating complete student and course objects in the file.
- Added successful and invalid scenarios in `Main`, including duplicate IDs, invalid student/course searches, and duplicate registration attempts.

---

## Mistakes

The main design mistake was the order of file restoration.

```java
RegistrationManager manager = new RegistrationManager();
FileManager fileManager = new FileManager(manager);

fileManager.loadFile();

addStudent(manager, "S001", "Daniel");
addCourse(manager, "MATH211", "Mathematics", ...);
```

`FileStorage.txt` stores only references such as:

```text
S001,MATH211
```

but `loadFile()` calls:

```java
registrationManager.registerClass(studentID, courseID);
```

which requires the referenced `Student` and `Course` objects to already exist inside `RegistrationManager`. Loading before creating those objects means the saved relationship cannot actually be restored.

The intended flow should therefore ensure that the base objects exist before registration relationships are loaded:

```java
RegistrationManager manager = new RegistrationManager();

addStudents(manager);
addCourses(manager);

FileManager fileManager = new FileManager(manager);
fileManager.loadFile();
```

A second important issue was that the assignment required a maximum of five courses per student, but `registerClass()` currently checks duplicate registration and available seats only. A student can therefore continue registering beyond the required limit.

There are also some design inconsistencies that should be cleaned up. `OfflineCourse` assigns its own `courseType` as `ONLINE`, while both subclasses declare fields such as `code`, `subject`, `openSeats`, and `description` that already belong to `Course`. These duplicate fields are not needed because the subclasses already inherit the parent state.

---

## Why the Mistake Happened

I separated `FileManager` from `RegistrationManager`, but I treated file loading as an isolated file operation instead of considering the object dependency required to reconstruct a `Registration`.

A saved registration does not contain complete `Student` and `Course` objects. It contains their IDs. Therefore, restoring the relationship requires the corresponding objects to already be registered in memory.

I also focused on the relationship-object design and duplicate validation, but did not verify every business rule from the requirements against the final `registerClass()` flow. The five-course limit was therefore omitted even though the central manager was the correct place to enforce it.

The subclass duplication happened because I repeated the parent fields while creating specialized course classes instead of relying fully on inheritance.

---

## Improved Solution

Before adding a registration, let `RegistrationManager` validate every rule in one place:

```java
public void registerClass(String studentID, String courseID)
        throws InvalidRegistrationException {

    Student student = findStudentByID(studentID);
    Course course = findCourseByID(courseID);

    if (student.getRegisteredCourses().size() >= 5) {
        throw new InvalidRegistrationException(
                "A student cannot register for more than five courses."
        );
    }

    for (Registration registration : registrations) {
        if (registration.getStudent().getStudentID().equalsIgnoreCase(studentID)
                && registration.getCourse().getCode().equalsIgnoreCase(courseID)) {
            throw new InvalidRegistrationException(
                    "This student has already registered for this course."
            );
        }
    }

    Registration registration = new Registration(student, course);

    registrations.add(registration);
    student.registerCourse(course);
    course.setOpenSeats(course.getOpenSeats() - 1);
    course.increaseStudents();
}
```

The persistence flow should also match the data format. Because the file stores only registration relationships, students and courses must exist before registrations are restored.

For the inheritance design, keep shared state only in `Course`. `OnlineCourse` should own only its meeting URL, and `OfflineCourse` should own only its classroom. The subclass type should also be assigned consistently as `ONLINE` or `OFFLINE`.

Finally, file loading should handle invalid lines individually so that one malformed or invalid registration does not stop the rest of the file from loading.

---

## What I Learned

A relationship can be modeled as its own object when the relationship itself has meaning. `Registration` is not another version of `Student` or `Course`; it represents one student's connection to one course and gives the system a place to attach registration-specific data later.

I also learned that separating classes is not enough by itself. Their execution order and dependencies still matter. A file containing only IDs cannot reconstruct relationships until the referenced objects exist.

`RegistrationManager` is the correct location for system-wide registration rules because it owns the registration collection and coordinates `Student`, `Course`, and `Registration`. When adding a new business rule, I should trace the entire operation and verify that every requirement is enforced before any state changes occur.

For inheritance, I should check whether a field already belongs to the parent before declaring it again in a child class. A subclass should normally contain only the state and behavior that make it different from its parent.

For future debugging, I should test persistence from a completely new `RegistrationManager`, not only check whether the save file was written. A complete persistence test should save data, create a fresh manager, rebuild prerequisite objects, load the file, and verify that registration counts, seat counts, and student course lists match the original state.

---

## Related Java Concepts

- [[9. Class part 2#Inheritance|Inheritance]]
- [[10. Abstraction and Interface#Abstraction|Abstraction]]
- [[10. Abstraction and Interface#Interface|Interface]]
- [[11. Collection Framework#ArrayList|ArrayList]]
- [[12. Exception Handling#Custom Exception|Custom Exception]]
- [[13. Input, Output and Files#BufferedReader|BufferedReader]]

