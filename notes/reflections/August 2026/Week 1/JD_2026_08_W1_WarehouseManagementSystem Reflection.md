

## Metadata

Drill ID: JD_2026_08_W1_WarehouseManagementSystem

Linked code: [View Main.java](../../java-drills/src/August_2026/Week_1/JD_2026_08_W1_WarehouseManagementSystem/Main.java)

Difficulty: Level 4 — Object-Oriented System Design

Estimated Time: 120–180 minutes

Actual Time Taken: 165 minutes

Written by: Chanwoo Park

---

## Goal

Build a warehouse management system that stores `Product` objects in a `LinkedHashMap`, prevents duplicate IDs, supports stock updates, searches and sorts products, prints inventory statistics, and saves or restores product data through a separate `FileManager`.

---

## What I Tried

- Created an abstract `Product` parent class with `FoodProduct` and `ElectronicProduct` subclasses.
- Used `Category` as an `enum` to restrict product categories.
- Used `LinkedHashMap<String, Product>` to preserve insertion order and provide ID-based lookup.
- Implemented inventory operations through the `InventoryAction` interface.
- Created `InvalidProductException` for duplicate IDs, missing products, and invalid stock decreases.
- Returned a copied `List<Product>` for sorting so the warehouse’s original insertion order was not changed.
- Implemented `FileManager` with `BufferedWriter`, `BufferedReader`, and try-with-resources.
- Converted products to comma-separated text and reconstructed the correct subclass from the saved category.
- Added helper methods in `Main` to keep exception handling out of the main execution flow.

---

## Mistakes

The main mistake was leaving the product comparison unfinished:

```java
@Override
public int compareTo(Product o) {
    return 0;
}
```

instead of defining the intended name comparison once in the parent class:

```java
@Override
public int compareTo(Product other) {
    return productName.compareToIgnoreCase(other.getProductName());
}
```

Returning `0` tells `Collections.sort()` that every product has the same ordering position, so `getProductSortedByName()` does not actually sort the products by name.

A second design issue was exposing the warehouse’s internal map directly:

```java
public LinkedHashMap<String, Product> getProducts() {
    return products;
}
```

`FileManager` can now modify the same collection without using `Warehouse` validation. This weakens encapsulation and allows duplicate checking or other inventory rules to be bypassed.

The file location was also stored as an absolute path:

```java
final private String fileName =
        "/Users/parkcwoo04/Desktop/GitHub/.../FileStorage.txt";
```

This works only on the same machine and directory structure.

---

## Why the Mistake Happened

I understood that `Collections.sort()` required `Comparable<Product>`, but I had not yet completed the comparison rule that determines which product comes first.

I also focused on giving `FileManager` access to the products needed for saving, but this caused `Warehouse` to reveal its mutable internal collection. The design achieved file persistence, but the boundary between inventory management and file storage became weaker.

The absolute path was convenient during development, but it tied the program to one local environment instead of treating the file path as configurable data.

---

## Improved Solution

Move the default name-ordering rule into `Product` and remove the duplicate `compareTo()` implementations from both subclasses:

```java
public abstract class Product implements Comparable<Product> {

    @Override
    public int compareTo(Product other) {
        return getProductName()
                .compareToIgnoreCase(other.getProductName());
    }
}
```

Allow `FileManager` to receive products without exposing the actual mutable map. For example, `Warehouse` can provide a copied list:

```java
public List<Product> getAllProducts() {
    return new ArrayList<>(products.values());
}
```

Then save that list:

```java
for (Product product : warehouse.getAllProducts()) {
    bw.write(convertProductToLine(product));
    bw.newLine();
}
```

Make the file path configurable through the constructor:

```java
private final String fileName;

public FileManager(Warehouse warehouse, String fileName) {
    this.warehouse = warehouse;
    this.fileName = fileName;
}
```

The file-loading logic should also handle malformed lines and invalid numbers without terminating the entire application. Each line can be validated separately before adding its product to the warehouse.

---

## What I Learned

`Collections.sort()` does not know how to order custom objects unless the objects provide a meaningful comparison rule. Implementing an interface is not enough; its required behavior must match the method’s purpose.

A class should not expose its internal mutable collection merely because another class needs to read the data. Providing a copy preserves encapsulation and keeps warehouse validation inside `Warehouse`.

File I/O should be separated from inventory rules. `FileManager` should translate objects to and from text, while `Warehouse` should remain responsible for duplicate IDs and valid inventory state.

When testing file persistence, I should check more than a successful save and load. I should also test a missing file, an empty file, malformed lines, invalid numbers, unknown categories, and duplicate IDs inside the file.

---

## Related Java Concepts

- [[11. Collection Framework#Collections.sort()|Collections.sort()]]
- [[10. Abstraction and Interface#Abstraction|Abstraction]]
- [[9. Class part 2#Inheritance|Inheritance]]
- [[9. Class part 2#Polymorphism|Polymorphism]]
- [[12. Exception Handling#Custom Exception|Custom Exception]]
- [[13. Input, Output and Files#BufferedReader|BufferedReader]]

