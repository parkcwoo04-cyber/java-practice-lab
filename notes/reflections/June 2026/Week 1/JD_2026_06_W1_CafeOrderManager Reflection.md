
## Metadata

Drill ID: JD_2026_06_W1_CaferOrderManager

Linked code: [View Java Code](../../../../java-drills/src/June_2026/Week_1/JD_2026_06_W1_CafeOrderManager.java)

Difficulty: Level 4 — Mini Project

Estimated Time: 30–40 minutes

Actual Time Taken: 32 minutes

Written by: Chanwoo Park

---

## Goal

Create a small cafe order management program using two classes: `CafeOrderManager` and `Order`. The program stores several orders, prints each order, calculates total sales, and searches for a Latte order.

---

## What I Tried

- Created an `Order` class to store `drinkName`, `quantity`, `price`, and `isMember`.
- Used a constructor to initialize each `Order` object.
- Created methods such as `getTotalPrice()`, `getDiscountPrice()`, `getDrinkName()`, and `printOrderInfo()`.
- Created an `Order[]` array in `CafeOrderManager`.
- Used an enhanced for loop to print all orders and calculate total sales.
- Used `.equalsIgnoreCase("Latte")` to search for a specific drink.

---

## Mistakes

The main mistake was declaring the main method as:

```java
public void main(String[] args)
```

instead of:

```java
public static void main(String[] args)
```

Because `main` was not `static`, Java could compile the class but could not run the program as an application entry point.

A smaller design issue is that the fields in `Order` are declared as `public`. Since getter/setter methods are already being used, these fields should be `private` to follow encapsulation more clearly.

---

## Why the Mistake Happened

The logic of the program was mostly correct, but the role of `static` in the main method was not fully applied. Java starts execution from a class-level method, so `main` must be available without creating a `CafeOrderManager` object.

The `public` fields issue happened because the class was used mainly as a data container, but object-oriented design expects important object data to be protected from direct outside access.

---

## Improved Solution

Change the main method header to:

```java
public static void main(String[] args)
```

Then the program can be executed normally.

Improve the `Order` class fields like this:

```java
private String drinkName;
private int quantity;
private int price;
private boolean isMember;
```

The existing methods can still access these fields inside the class, while outside code should use methods such as `getDrinkName()`.

Optionally, rename `getDiscountPrice()` to `getDiscountedPrice()` to match the original requirement more exactly.

---

## What I Learned

The program logic can be correct but still fail to run if the `main` method signature is not exact. I should always check that the entry point is written as `public static void main(String[] args)`.

I also learned that class fields should usually be private when using object-oriented design. This keeps object data safer and makes the class easier to maintain.

Next time, I should test compile and run separately: compilation checks syntax, while execution checks whether Java can find the correct starting point.

---

## Related Java Concepts

- [[7. Method#Main Method|Main Method]]
- [[7. Method#`public static void|Static Method]]
- [[8. Class part 1#Object Oriented Programming|Class and Object]]
- [[8. Class part 1#Constructor|Constructor]]
- [[8. Class part 1#Instance Variable|Instance Variable]]
- [[8. Class part 1#Access Modifier|Encapsulation]]
- [[8. Class part 1#Getter and Setter|Getter and Setter]]
- [[6. Array|Array]]
- [[6. Array#Enhanced for loop|Enhanced for Loop]]
