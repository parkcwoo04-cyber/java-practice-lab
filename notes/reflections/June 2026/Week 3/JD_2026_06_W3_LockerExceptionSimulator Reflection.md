

## Metadata

Drill ID: JD_2026_06_W3_LockerExceptionSimulator

Linked code:
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W3_LockerExceptionSimulator/LockerExceptionSimulator.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W3_LockerExceptionSimulator/LockerSystem.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W3_LockerExceptionSimulator/PackageLocker.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W3_LockerExceptionSimulator/InvalidLockerAccessException.java)

Difficulty: Level 2 — Multi-Class Application

Estimated Time: 45–60 minutes

Actual Time Taken: 43 minutes

Written by: Chanwoo Park

---

## Goal

Create a package locker pickup simulation using multiple classes. The program processes request strings, validates locker numbers and pickup codes, updates locker pickup state, and handles invalid input through `try-catch`, multiple `catch` blocks, `finally`, `throw`, and a custom exception.

---

## What I Tried

- Created `PackageLocker` to store locker number, owner name, pickup code, and pickup status.
- Created `LockerSystem` to process pickup requests and search lockers.
- Created `InvalidLockerAccessException` as a custom checked exception.
- Used a request array and loop in `LockerExceptionSimulator`.
- Used multiple `catch` blocks for `NumberFormatException`, `ArrayIndexOutOfBoundsException`, custom exception, and general `Exception`.
- Used `finally` to print a completion message for every request.

---

## Mistakes

The main issue was using `ArrayIndexOutOfBoundsException` manually for a business validation problem:

```java
if (!matched) {
    throw new ArrayIndexOutOfBoundsException("Error: Locker number is out of range.");
}
```

A better design is to use the custom exception because the program is not actually accessing an invalid array index at that point:

```java
if (!matched) {
    throw new InvalidLockerAccessException("Error: Locker number is out of range.");
}
```

The current code works, but the exception type does not clearly describe the program's logic. `ArrayIndexOutOfBoundsException` usually means the program attempted to access an array with an invalid index. In this code, the program is searching locker objects and failing to find a matching locker number.

A second issue is that malformed request strings such as `"0"` or `"0:1234:extra"` are not explicitly validated before accessing `requestLines[1]`.

```java
String[] requestLines = request.split(":");
int lockerNumber = Integer.parseInt(requestLines[0]);
String code = requestLines[1];
```

This can cause an accidental array exception before the program gives a clear validation message.

---

## Why the Mistake Happened

The code correctly recognized that invalid input should interrupt normal execution, but the exception type choice mixed two different meanings:

- technical array failure
- business rule validation failure

The learned concept here is that exception handling should communicate the cause of the problem clearly. A custom exception is useful when the problem belongs to the application's rule, such as invalid locker number, wrong pickup code, or already picked-up package.

---

## Improved Solution

Change request format validation and locker search logic to:

```java
String[] requestLines = request.split(":");

if (requestLines.length != 2) {
    throw new InvalidLockerAccessException("Error: Request format must be lockerNumber:pickupCode.");
}

int lockerNumber = Integer.parseInt(requestLines[0]);
String code = requestLines[1];

PackageLocker targetLocker = null;

for (PackageLocker locker : lockers) {
    if (lockerNumber == locker.getLockerNumber()) {
        targetLocker = locker;
        break;
    }
}

if (targetLocker == null) {
    throw new InvalidLockerAccessException("Error: Locker number is out of range.");
}
```

This separates parsing, validation, searching, and business rule handling more clearly.

A small style improvement:

```java
public boolean matchesCode(String code) {
    return pickupCode.equals(code);
}
```

Since the pickup code is numeric text, case-insensitive comparison is not necessary. Also, returning the boolean expression directly is cleaner than writing an `if` statement that returns `true` or `false`.

---

## What I Learned

The solution compiled and executed correctly. The strongest part was the overall exception flow: each request was processed independently, failed requests did not stop the whole program, and `finally` executed every time.

The main improvement is to choose exception types based on meaning. Built-in exceptions should be used when the actual technical error matches the exception type. Custom exceptions should be used when the problem is a business rule or validation rule in the application.

Next debugging habit: before throwing an exception, ask, “Is this a Java technical failure, or is this my program's validation rule?”

---

## Related Java Concepts

- [[12. Exception Handling|Exception Handling]]
- [[12. Exception Handling#Custom Exception|Custom Exception]]
- [[12. Exception Handling#finally|finally]]
- [[12. Exception Handling#Controlling Program Flow by Throwing Exceptions|throw and throws]]

