

## Metadata

Drill ID: JD_2026_05_W3_LoginValidator

Linked code: [View Java Code](../../../../java-drills/src/May_2026/Week_3/JD_2026_05_W3_LoginValidator.java)

Difficulty: Level 3 — Multi-Step Problem

Estimated Time: 25–30 minutes

Actual Time Taken: ______ 15 minutes

Written by: Chanwoo Park

---

## Goal

The goal of this exercise was to create a login validation program that checks a username and password using String comparison and conditional statements.

---

## What I Tried

I created `username`, `password`, `correctUsername`, and `correctPassword` variables.

I first checked whether the username was empty. Then I checked whether the username matched the correct username. If the username was correct, I moved on to check the password.

For the password, I checked whether it was empty, whether it was incorrect, and whether it matched the correct password.

I used `.equals()` to compare String values.

---

## Mistakes

The main mistake was checking an empty String using `" "`.

```java
username.equals(" ")  
```

This checks for a `String` that contains one space, not an empty `String`.

Same issue appeared in the password check.

```java
password.equals(" ")
```

Another mistake is that some `else if statement` are used when it could be replace with `else statement`. 

Previous conditions already eliminated other possibilities.

---

## Why the Mistake Happened

The mistake happened because an empty `String ` and a space `String` looked similar but are different values in Java.

```Java
"" // empty String
" " // String with one space
```

Because of this, the program would not correctly detect a truly empty username or password.

The `condition statement` structure also had some repeated checks because I used `else if statement` even if after the previous conditions already eliminated other possibilities.

---

## Improved Solution

The empty `String` check should use `isEmpty()` or compare with "".

Better version:
```java
username.isEmpty()
password.isEmpty()

username.equals("")
password.equals("")
```

The username logic can also simplified by using `else statement` after checking for an empty or invalid username.

``` java
if (username.isEmpty()) {
    System.out.println("Username cannot be empty.");
} else if (!username.equals(correctUsername)) {
    System.out.println("Invalid username.");
} else {
    // check password
}
```

This makes the code easier to read because reaching the `else` block means the username is already valid.

---

## What I learned

I learned that "" and " " are different `String` values in Java.

I also learned that .isEmpty() is a clear way to check whether a String has no characters.

Next time, I should carefully check whether I am testing for an empty `String` or a `String` containing a spaces

I also learned that else can make condition logic cleaner when pervious conditions already handled the other cases.

---

## Related Java Concepts

- [[3. String#Comparing Strings|Comparing Strings]]
- [[4. Conditional statement|Conditional Statements]]
- [[4. Conditional statement|Nested if statements]]
- isEmpty()