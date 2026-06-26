

## Metadata

Drill ID: JD_2026_06_W4_StudentCourseRegistrationApp

Linked code: 
- [View Java Code](../../java-drills/src/June_2026/Week_4/JD_2026_06_W4_StudentCourseRegistrationApp)

Difficulty: Level 3.5 — OOP, Collections, Exception Handling, and File I/O

Estimated Time: 60–90 minutes

Actual Time Taken: 88 minutes

Written by: Chanwoo Park

---

## Goal

The goal of this drill was to build a student course registration system using object-oriented design. The program manages `Student`, `Course`, and `RegistrationRequest` objects, processes course registration requests, updates each request status, and saves registration logs to a file. The main concepts practiced were `ArrayList`, `enum`, custom exceptions, object collaboration, and file input/output.

---

## What I Tried

- Created separate classes for `Student`, `Course`, `RegistrationRequest`, `RegistrationCenter`, and `RegistrationFileManager`.
- Used `ArrayList` to store students, courses, registration requests, and enrolled students.
- Used `RegistrationStatus` to represent `PENDING`, `APPROVED`, `WAITLISTED`, and `REJECTED`.
- Used `InvalidRegistrationException` to reject invalid registration requests.
- Processed course capacity rules so that full courses move valid requests to `WAITLISTED`.
- Rejected duplicate registration requests when a student already enrolled in the same course.
- Saved key registration data to a file instead of trying to store full Java objects.
- Improved the console output format so request status, request ID, student ID, and course code were easier to read.

---

## Mistakes

The main mistake was using a local absolute file path inside `RegistrationFileManager`:

```java
this.fileName = "/Users/parkcwoo04/Desktop/GitHub/java-practice-lab/java-drills/src/June_2026/Week_4/JD_2026_06_W4_StudentCourseRegistrationApp/RegistrationRecords.txt";
```

instead of a portable relative path or constructor-provided file name:

```java
public RegistrationFileManager(String fileName) {
    this.fileName = fileName;
}
```

or:

```java
this.fileName = "RegistrationRecords.txt";
```

This made the program depend on one specific computer and folder structure. The code may work on the original machine but fail in another environment.

A second issue was that approved registrations updated the `Course` object, but did not update the `Student` object's enrolled course list:

```java
course.addStudent(student);
registrationRequest.markApproved();
```

The better version is:

```java
course.addStudent(student);
student.addCourse(course);
registrationRequest.markApproved();
```

Without this, `Course` knows which students are enrolled, but `Student` does not know which courses it has enrolled in.

A smaller issue was leaving an old import from another project:

```java
import src.June_2026.Week_4.JD_2026_06_W4_CampusMaintenanceRequestApp.InvalidRequestException;
```

This import should be removed because the current project already has its own `InvalidRegistrationException`.

---

## Why the Mistake Happened

The file path issue happened because the program was tested in one local project folder. This made it easy to hard-code the full path without noticing that the code would not be portable.

The `Student.addCourse(course)` issue happened because the registration logic focused mainly on the `Course` side of the relationship. The `Course` object correctly tracked enrolled students, but the connected state inside the `Student` object was not updated at the same time.

This is an object collaboration issue. When two objects represent two sides of the same relationship, the coordinator class must decide whether both sides should be updated.

The old import issue happened because code from a previous drill was reused or copied. This is a compile-time dependency problem, not a logic problem.

---

## Improved Solution

Change the file manager so that it does not depend on a hard-coded local path:

```java
public class RegistrationFileManager {
    private final String fileName;

    public RegistrationFileManager(String fileName) {
        this.fileName = fileName;
    }
}
```

Then create it from `main`:

```java
RegistrationFileManager fileManager =
        new RegistrationFileManager("RegistrationRecords.txt");
```

Update both the course and the student when a registration is approved:

```java
if (course.isAvailable()) {
    course.addStudent(student);
    student.addCourse(course);
    registrationRequest.markApproved();
} else {
    registrationRequest.markWaitlisted();
}
```

Move duplicate checking into `Course` so `RegistrationCenter` does not need to inspect the internal list directly:

```java
public boolean hasStudent(String studentID) {
    for (Student student : enrolledStudents) {
        if (student.getStudentID().equals(studentID)) {
            return true;
        }
    }
    return false;
}
```

Then use it like this:

```java
if (course.hasStudent(student.getStudentID())) {
    registrationRequest.markRejected();
    throw new InvalidRegistrationException("Student already registered for this course.");
}
```

Remove unused state from `Course`:

```java
private boolean available;
```

The course availability can be calculated from the current number of enrolled students:

```java
public boolean isAvailable() {
    return enrolledStudents.size() < maxCapacity;
}
```

Make stable fields `final` and remove unnecessary setters:

```java
private final String courseCode;
private final String courseTitle;
private final int maxCapacity;
private final ArrayList<Student> enrolledStudents;
```

---

## What I Learned

I learned that saving logs does not require saving full Java objects. For this drill, it is enough to save key data such as request status, student ID, course code, and request ID.

I also learned that file paths should not be tied to one computer. A relative path or constructor-provided file name makes the program easier to run in different environments.

The biggest design lesson was that object relationships must stay consistent. If a course records that a student enrolled, the student should also record that course when the program needs both views of the relationship.

I also practiced making console output more readable. Formatting output with status labels made it much easier to verify whether each request was approved, waitlisted, or rejected.

For the next drill, I should check:
- Does the code compile without imports from older drills?
- Does file I/O use a portable path?
- Are both sides of an object relationship updated when needed?
- Are stable fields marked `final`?
- Are unnecessary setters and unused fields removed?
- Does the class that owns a list provide methods like `hasStudent()` instead of exposing the list directly?

---

## Related Java Concepts

- [[9. Class part 2|Class]]
- [[7. Method|Method]]
- [[11. Collection Framework#ArrayList|ArrayList]]
- [[9. Class part 2#Enum|Enum]]
- [[12. Exception Handling#Custom Exception|Exception Handling]]
- [[13. Input, Output and Files|File I/O]]

