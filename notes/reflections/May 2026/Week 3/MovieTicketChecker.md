

Class Name: MovieTicketChecker.java

Difficulty: Level 3 — Multi-Step Problem

Estimated Time: 25–30 minutes

Actual Time Taken: 40 minutes  

Written by: Chanwoo Park

---

## Goal

Create a movie ticket checking program that determines whether the user can enter the theater and calculates the final ticket price based on age, ticket ownership, membership type, and weekend status.

---

## What I Tried

I created variables for the main conditions:

- `int age`
- `boolean hasTicket`
- `String membership`
- `boolean weekend`
- `boolean entryStatus`

I used a combined condition with `&&` and `||` to decide whether entry should be allowed.

I also used an `if-else` statement to set the base ticket price depending on whether it was a weekend or weekday.

After that, I tried to apply discounts using `if` and `else if`.

---

## Mistakes

The main logic mistake was using `else if` for the VIP discount after checking the child discount.

Because of this structure, if the user was under 13 and also a VIP member, only the child discount would be applied, and the VIP discount would be skipped.

Another issue was that the entry condition used:

```java
membership.equals("VIP")
```
instead of:
```java
membership.equalsIgnoreCase("VIP")
```

Also, the program did not include a final check to prevent the ticket price from becoming negative.

---

## Why the Mistake Happened

The mistake happened because `else if` means “check this only if the previous condition was false.”

In this problem, the child discount and VIP discount are not exclusive choices. They are separate discounts that can both apply.

So the program should use two independent `if` statements instead of `if` followed by `else if`.

The String comparison issue happened because `equals()` is case-sensitive, while the requirement expected case-insensitive comparison.

---

## Improved Solution

The discount logic should be separated into independent checks:

```java
if (age < 13) {
	ticketPrice -= 5;
}

if (membership.equalsIgnoreCase("VIP")) {
	ticketPrice -= 3;
	vIP = "VIP Benefits Applied";
}
```

The entry condition should also use `equalsIgnoreCase()`:

```java
if ((age >= 18 && hasTicket) || membership.equalsIgnoreCase("VIP")) {
	entryStatus = true;
}
```

Finally, the program should check that the final price is not negative:

```java
if (ticketPrice < 0) {
	ticketPrice = 0;
}
```

---

## What I Learned

I learned that `else if` should be used when only one condition should be selected.

When multiple conditions can all apply, I should use separate `if` statements.

I also learned that String comparison should use `.equals()` or `.equalsIgnoreCase()`, not `==`, and that `equalsIgnoreCase()` is better when user input may have different capitalization.

Next time, I should check whether conditions are exclusive or cumulative before choosing between `else if` and multiple `if` statements.

---

## Related Java Concepts

- [[1. Data Type and Variables#Types of Data|boolean]]
- [[3. String#Comparing Strings|String Comparison]]
- [[3. String#Comparing Strings#Ignoring Uppercase and Lowercase|equalsIgnoreCase()]]
- [[4. Conditional statement|if statement]]
- [[2. Operator#Logical Operator|Logical Operators]]
- [[2. Operator#Assignment Operator|Compound Assignment Operator]]