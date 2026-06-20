

## Metadata

Drill ID: JD_2026_06_W3_CafeOrderApp

Linked code:  
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W3_CafeOrderApp/CafeOrderApp.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W3_CafeOrderApp/MenuItem.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W3_CafeOrderApp/Order.java)

Difficulty: Level 2 — Multi-Class Application

Estimated Time: 45–60 minutes

Actual Time Taken: 38 minutes

Written by: Chanwoo Park

---

## Goal

The goal was to create a console-based cafe ordering program using `Scanner`, multiple classes, objects, arrays, loops, conditions, and order calculation logic. `CafeOrderApp` controls the input flow, `MenuItem` stores menu data, and `Order` manages selected items, quantities, total quantity, and total price.

---

## What I Tried

- Created a `CafeOrderApp` main class to run the program.
- Used `Scanner` in the `main` method to receive menu number and quantity input from the user.
- Created a `MenuItem` class to represent each cafe menu item.
- Stored multiple `MenuItem` objects inside an array.
- Created an `Order` class to manage order data.
- Used a `LinkedHashMap<MenuItem, Integer>` to connect each ordered menu item with its quantity.
- Used a loop to keep receiving orders until the user enters `0`.
- Used conditions to check whether the selected menu number is valid.
- Separated input control, menu data, and order calculation into different classes.

---

## Mistakes

The main mistake was:

```java
orderList.put(itemOrdered, quantity);
totalQuantity += quantity;
totalPrice += itemOrdered.getPrice() * quantity;
```

instead of:

```java
int currentQuantity = orderList.getOrDefault(itemOrdered, 0);
orderList.put(itemOrdered, currentQuantity + quantity);

totalQuantity += quantity;
totalPrice += itemOrdered.getPrice() * quantity;
```

The original code works when each menu item is ordered only once. However, if the same menu item is ordered multiple times, `put()` replaces the previous quantity stored in the `LinkedHashMap`.

For example:

```text
Latte x 2
Latte x 3
```

The total quantity and total price are updated as if 5 lattes were ordered, but the map only stores the latest quantity, `3`. This causes the printed order summary and the total values to become inconsistent.

A second mistake was that quantity validation was missing.

```java
int quantity = scanner.nextInt();
order.addItem(menuNumber, quantity);
```

This allows `0` or negative quantities to be added to the order.

The improved version should check:

```java
if (quantity < 1) {
    System.out.println("Quantity must be at least 1.");
} else {
    order.addItem(menuNumber, quantity);
}
```

A third issue was that the program did not clearly handle the case where the user finishes without ordering anything.

```java
System.out.println("=== Order Summary ===");
for (MenuItem item : orderList.keySet()) {
    System.out.println(item.getMenuName() + " X " + orderList.get(item));
}
```

The improved version should check whether the order is empty:

```java
if (orderList.isEmpty()) {
    System.out.println("No items ordered.");
    return;
}
```

---

## Why the Mistake Happened

The main issue came from how `Map.put()` works. A `Map` stores one value for each key. If the same key is inserted again, the old value is replaced instead of automatically added.

This was not a syntax error. It was a logic error related to collection behavior.

The quantity validation issue happened because the program trusted the user input too early. Since `Scanner` only reads the value, it does not decide whether the value is logically valid. The program must check the value before passing it to `Order`.

The empty-order issue happened because the summary logic assumed that at least one item had been ordered. This is an edge case that should be checked before printing normal order details.

The `Scanner` design question was also important. Keeping `Scanner` in `main` is appropriate for this problem because `CafeOrderApp` controls the console input flow. `MenuItem` should only store menu data, and `Order` should only manage order state and calculation. Putting `Scanner` inside `Order` would mix input responsibility with business logic, making the class harder to test and maintain.

---

## Improved Solution

Improve duplicate menu handling in `Order.addItem()`:

```java
public void addItem(int number, int quantity) {
    MenuItem itemOrdered = null;

    for (MenuItem item : menuItems) {
        if (item.getMenuNumber() == number) {
            itemOrdered = item;
            break;
        }
    }

    if (itemOrdered == null) {
        return;
    }

    int currentQuantity = orderList.getOrDefault(itemOrdered, 0);
    orderList.put(itemOrdered, currentQuantity + quantity);

    totalQuantity += quantity;
    totalPrice += itemOrdered.getPrice() * quantity;
}
```

Improve quantity validation in `CafeOrderApp`:

```java
System.out.print("Enter quantity: ");
int quantity = scanner.nextInt();

if (quantity < 1) {
    System.out.println("Quantity must be at least 1.");
} else {
    order.addItem(menuNumber, quantity);
}
```

Improve empty-order handling in `Order.showSummary()`:

```java
public void showSummary() {
    System.out.println("=== Order Summary ===");

    if (orderList.isEmpty()) {
        System.out.println("No items ordered.");
        return;
    }

    for (MenuItem item : orderList.keySet()) {
        int quantity = orderList.get(item);
        int subtotal = quantity * item.getPrice();

        System.out.printf("%s X %d = $%,d%n",
                item.getMenuName(),
                quantity,
                subtotal);
    }

    System.out.println("Total Quantity: " + totalQuantity);
    System.out.printf("Total Price: $%,d%n", totalPrice);
}
```

Improve encapsulation by making fields `private`:

```java
private int menuNumber;
private String menuName;
private int price;
```

```java
private LinkedHashMap<MenuItem, Integer> orderList = new LinkedHashMap<>();
private MenuItem[] menuItems;
private int totalPrice;
private int totalQuantity;
```

This protects object state and encourages access through methods instead of direct field access.

---

## What I Learned

I learned that `Scanner` is best placed in the class that controls input flow, not inside data or calculation classes. In this program, keeping `Scanner` in `CafeOrderApp.main()` is a good design because `main` coordinates the console interaction, while `Order` focuses on order storage and calculation.

I also learned that `Map.put()` replaces an existing value when the same key is used again. To accumulate quantities, I need to first read the current value using `getOrDefault()` and then store the updated quantity.

I learned that input validation should happen before data is passed into the object that manages program state. Invalid quantities such as `0` or negative numbers should not be added to the order.

I also learned to check edge cases such as “no order was placed” before printing normal summary output.

Next time, I should test with these cases:

```text
1. Order one item once
2. Order the same item multiple times
3. Enter an invalid menu number
4. Enter quantity 0
5. Enter a negative quantity
6. Finish immediately without ordering
```

These tests help catch logic errors that do not appear when only the normal case is tested.

---

## Related Java Concepts

- [[13. Input, Output and Files#Scanner|Scanner]]
- [[8. Class part 1#Access Modifier|Encapsulation]]
- [[6. Array#Array list|Array]]
- [[4. Conditional statement#if Statement|if Statement]]
- [[11. Collection Famework#HashMap|LinkedHashMap]]
