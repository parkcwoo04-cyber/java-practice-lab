
## Metadata

Drill ID: JD_2026_06_W1_CampusLostAndFoundApp

Linked code:
- [View Java Code](../../../../java-drills/src/June_2026/Week_1/JD_2026_06_W1_CampusLostAndFoundApp/CampusLostAndFoundApp.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_1/JD_2026_06_W1_CampusLostAndFoundApp/LostItem.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_1/JD_2026_06_W1_CampusLostAndFoundApp/LostAndFoundDesk.java)

Difficulty: Level 2 — Multi-Class Application

Estimated Time: 45–60 minutes

Actual Time Taken: ______ 47 minutes

Written by: Chanwoo Park

---

## Goal

The goal of this program was to build a small campus lost-and-found system using three classes: `CampusLostAndFoundApp`, `LostAndFoundDesk`, and `LostItem`. The program stores multiple `LostItem` objects in an array, prints all items, searches by item name or location, updates an item’s claim status, and counts how many items are still unclaimed.

---

## What I Tried

- Created a main class, `CampusLostAndFoundApp`, to create sample `LostItem` objects and run the program.
- Created a `LostItem` class with fields for item name, found location, owner name, and claim status.
- Used a constructor to initialize each `LostItem` object.
- Created getter and setter methods for the `LostItem` fields.
- Created a `LostAndFoundDesk` class that receives a `LostItem[]` array through its constructor.
- Used loops to print all items, search through the array, claim an item, and count unclaimed items.
- Used `equalsIgnoreCase()` for owner-name comparison.
- Used `toLowerCase()` and `contains()` to support case-insensitive keyword search.

---

## Mistakes

The main design mistake was declaring the fields in `LostItem` as `public`:

```java
public String itemName;
public String foundLocation;
public String ownerName;
public boolean isClaimed;
```

instead of:

```java
private String itemName;
private String foundLocation;
private String ownerName;
private boolean isClaimed;
```

This did not break compilation or execution, but it weakened the object-oriented design. Since the fields were `public`, other classes could directly modify the internal data of a `LostItem` object without using the getter and setter methods.

A smaller logic/design issue was the search string construction:

```java
String itemLocation = item.concat(location);
```

This works for the current data, but it combines the item name and location without a separator, creating a value such as `"Laptop ChargerLibrary"`. The search logic is clearer if item name and location are checked separately, or if a space is added between them.

---

## Why the Mistake Happened

The main issue came from not fully applying **encapsulation**. The `LostItem` class already had getter and setter methods, which means the class was intended to control access to its data. However, leaving the fields as `public` allowed outside classes to bypass those methods.

This matters because object fields represent the internal state of an object. If every class can directly change them, the object becomes harder to protect from invalid or unexpected changes. Using `private` fields and public methods keeps the object’s data safer and makes the class responsibility clearer.

The search issue happened because `concat()` was used to combine two searchable values into one string. The program still works, but the logic is less readable than checking the item name and location as two separate conditions.

---

## Improved Solution

Change the fields in `LostItem` to:

```java
private String itemName;
private String foundLocation;
private String ownerName;
private boolean isClaimed;
```

This keeps the object state inside the class and forces other classes to use methods such as:

```java
lostItem.getItemName();
lostItem.setIsClaimed(true);
```

The search logic can also be improved to:

```java
String keywordLower = keyWord.toLowerCase();

if (item.toLowerCase().contains(keywordLower) ||
    location.toLowerCase().contains(keywordLower)) {
    System.out.println(item + " found at " + location);
    found = true;
}
```

This makes the condition easier to understand because the program is explicitly checking two fields: item name and found location.

---

## What I Learned

I learned that creating getter and setter methods is not enough by itself. To apply encapsulation properly, the instance variables should usually be declared as `private`, and outside classes should access them through methods.

I also practiced passing an object array into another class through a constructor:

```java
public LostAndFoundDesk(LostItem[] lostItems) {
    this.lostItems = lostItems;
}
```

This helped me understand that the `LostAndFoundDesk` object does not need to create the array itself. It can receive an existing `LostItem[]` and manage it.

Next time, I should check whether each class is protecting its own data and whether each method has one clear responsibility. For search logic, I should prefer readable conditions over combining values in a way that makes the logic harder to understand.

---

## Related Java Concepts

- [[8. Class part 1|Class]]
- [[8. Class part 1#Instance Variable|Instance Variable]]
- [[8. Class part 1#Constructor|Constructor]]
- [[8. Class part 1#Getter and Setter|Getter and Setter]]
- [[8. Class part 1#Access Modifier|Access Modifier]]
- [[8. Class part 1#Access Modifier|Encapsulation]]
- [[6. Array|Array]]
- [[6. Array#Enhanced for loop|Enhanced For Loop]]

