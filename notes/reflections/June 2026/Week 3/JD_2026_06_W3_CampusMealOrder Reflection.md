

## Metadata

Drill ID: JD_2026_06_W3_CampusMealOrder

Linked code:
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W3_CampusMealOrder/CampusMealOrderApp.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W3_CampusMealOrder/MenuItem.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W3_CampusMealOrder/RegularMenuItem.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W3_CampusMealOrder/LimitedMenuItem.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W3_CampusMealOrder/MealOrder.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W3_CampusMealOrder/OrderManager.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W3_CampusMealOrder/InvalidOrderException.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W3_CampusMealOrder/SuccessfulOrderException.java)

Difficulty: Level 3 — Object-Oriented Problem Solving

Estimated Time: 70–90 minutes

Actual Time Taken: 73 minutes

Written by: Chanwoo Park

---

## Goal

The goal was to build a campus meal order system using multiple Java classes. The program manages regular menu items, limited-stock menu items, meal orders, rejected orders, total revenue, and remaining stock. The main structures used were inheritance, overriding, polymorphism through `ArrayList<MenuItem>`, collections, loops, `instanceof`, downcasting, and custom exceptions.

---

## What I Tried

- Created `MenuItem` as an abstract parent class.
- Created `RegularMenuItem` and `LimitedMenuItem` as child classes.
- Used method overriding for `showMenuInfo()`.
- Stored regular and limited menu items together in `ArrayList<MenuItem>`.
- Created `MealOrder` objects to represent order requests.
- Used `OrderManager` to register menu items, register orders, validate orders, process orders, calculate total revenue, and print final reports.
- Used `InvalidOrderException` for rejected orders.
- Added `SuccessfulOrderException` to print accepted order messages.
- Used `Iterator` to safely remove rejected orders while looping through the order list.

---

## Mistakes

The main design mistake was separating validation from stock update:

```java
if (limitedMenuItem.getQuantity() > order.getOrderQuantity()) {
    wrongOrderName = false;
    throw new SuccessfulOrderException(
        "Order accepted: " + order.getMealName()
    );
}
```

and later:

```java
limitedMenuItem.setQuantity(
    limitedMenuItem.getQuantity() - order.getOrderQuantity()
);
```

instead of accepting the order and updating limited stock as one connected operation:

```java
if (limitedMenuItem.getQuantity() >= order.getOrderQuantity()) {
    limitedMenuItem.setQuantity(
        limitedMenuItem.getQuantity() - order.getOrderQuantity()
    );
    // then store the successful order
}
```

This matters because multiple orders for the same limited item can each pass validation before stock is reduced. For example, if a limited item has stock `5`, two separate orders of quantity `3` may both pass validation if stock is not reduced immediately during acceptance. Later, `processOrder()` would reduce stock twice and produce an invalid negative stock.

A second logic issue was this condition:

```java
limitedMenuItem.getQuantity() > order.getOrderQuantity()
```

This rejects an order when the requested quantity is exactly equal to the remaining stock. If stock is `3` and the customer orders `3`, the order should be accepted. The condition should be:

```java
limitedMenuItem.getQuantity() >= order.getOrderQuantity()
```

A design issue was using `SuccessfulOrderException` for normal successful control flow. Exceptions should usually represent abnormal or invalid situations. A successful order is normal program behavior, so it should be handled with normal method return values, object creation, or printed messages instead of an exception.

Another design issue was that `OrderManager` performs most type-specific validation using `instanceof` and downcasting. This works, but it weakens polymorphism because the manager must know too much about the child classes.

---

## Why the Mistake Happened

The main issue came from treating validation and processing as two separate phases:

```java
manager.validateOrder();
manager.processOrder();
```

This made the program easier to organize at first, but it separated two actions that should happen together:

1. confirm the order can be accepted
2. immediately apply the state change caused by that accepted order

For limited-stock items, validation is not just a check. It is connected to object state. Once the system decides that an order is valid, the remaining stock must be updated at that point.

This is a state update problem. The stock inside `LimitedMenuItem` is shared object state, so delaying the update can make later validation decisions use outdated information.

The exception issue happened because exceptions were used as a way to jump out of nested loops. This solved the immediate flow-control problem, but it made successful behavior look like an error-handling case.

---

## Improved Solution

Change the order flow so that each order request is processed completely inside one method.

A stronger design would be:

```java
public void processOrder(MealOrder request) {
    try {
        MenuItem item = findMenuItem(request.getMealName());

        if (request.getOrderQuantity() <= 0) {
            throw new InvalidOrderException("Quantity must be greater than 0.");
        }

        if (item instanceof LimitedMenuItem limitedItem) {
            if (limitedItem.getQuantity() < request.getOrderQuantity()) {
                throw new InvalidOrderException("Not enough stock.");
            }

            limitedItem.setQuantity(
                limitedItem.getQuantity() - request.getOrderQuantity()
            );
        }

        mealOrders.add(request);
        System.out.println("Order accepted: " + request.getMealName());

    } catch (InvalidOrderException e) {
        rejectedOrders.add(request.getMealName() + ": " + e.getMessage());
        System.out.println("Order rejected: " + e.getMessage());
    }
}
```

This improves the program because each order is handled independently. The system searches, validates, updates stock, stores the successful order, or records rejection in one flow.

A more object-oriented improvement would be to move item-specific validation into `MenuItem` and override it in `LimitedMenuItem`. Then `OrderManager` would not need as much `instanceof` logic.

For example, the parent class could define a general rule:

```java
public boolean canOrder(int quantity) {
    return quantity > 0;
}
```

and `LimitedMenuItem` could override it:

```java
@Override
public boolean canOrder(int quantity) {
    return quantity > 0 && quantity <= this.quantity;
}
```

Then `OrderManager` could ask the menu item whether it can be ordered instead of checking every child class itself.

---

## What I Learned

I learned that successful validation and state updates should often happen together when an object has changing state. For this problem, `LimitedMenuItem` stock is not just information to print; it controls whether future orders should be accepted.

I also learned that exceptions should mainly represent invalid or unusual situations. `InvalidOrderException` is appropriate because rejected orders are exceptional cases in the order flow. `SuccessfulOrderException` is less appropriate because success is normal behavior.

For future debugging, I should test repeated orders against the same limited item, exact-stock orders, and edge cases like quantity `0`, negative quantity, and unknown menu names. These tests reveal state-update bugs that do not appear when each menu item is ordered only once.

---

## Related Java Concepts

- [[9. Class part 2#Inheritance|Inheritance]]
- [[9. Class part 2#Method Overriding|Method Overriding]]
- [[9. Class part 2#Polymorphism|Polymorphism]]
- [[9. Class part 2#Reference Type|Reference Type]]
- [[11. Collection Framework#ArrayList|ArrayList]]
- [[12. Exception Handling|Exception Handling]]

