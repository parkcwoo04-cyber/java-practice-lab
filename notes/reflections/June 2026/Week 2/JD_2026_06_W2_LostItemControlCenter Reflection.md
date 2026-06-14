

## Metadata

Drill ID: JD_2026_06_W2_LostItemControlCenter

Linked code:
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W2_LostItemControlCenter/LostItemControlCenter.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W2_LostItemControlCenter/LostItemRegistry.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W2_LostItemControlCenter/LostItem.java)
- [View Java Code](../../../../java-drills/src/June_2026/Week_2/JD_2026_06_W2_LostItemControlCenter/ClaimRequest.java)

Difficulty: Level 3 — Object-Oriented Problem Solving

Estimated Time: 60–90 minutes

Actual Time Taken: 83 minutes

Written by: Chanwoo Park

---

## Goal

The goal of this program was to build a lost item control center using multiple Java classes and the Collection Framework.  
The program registers lost items, prevents duplicate item IDs, prints existing categories, searches items by category, processes claim requests, updates item claim status, and counts claim attempts by student.

Main structures used:

- `LostItemControlCenter`: runs the program and creates test data
- `LostItemRegistry`: manages collections and main business logic
- `LostItem`: stores item information and claim status
- `ClaimRequest`: stores claim request information
- `ArrayList<LostItem>`: stores registered lost items
- `HashSet<String>`: intended for duplicate item IDs and unique categories
- `HashMap<String, Integer>`: counts claim attempts by student

---

## What I Tried

- Created separate classes for item data, claim request data, registry logic, and main execution.
- Used `ArrayList<LostItem>` to store all registered lost items.
- Used `HashSet<String>` for item IDs and categories.
- Used `HashMap<String, Integer>` to count how many times each student attempted to claim an item.
- Used constructors to initialize object state.
- Used instance methods such as `addLostItem()`, `printLostItems()`, `searchByCategory()`, and `claimLostItem()`.
- Used loops to search through registered lost items.
- Used `equalsIgnoreCase()` for item ID and category comparison.
- Used `merge()` to update claim attempt counts concisely.

---

## Evaluation

Score: **88 / 100**

Your solution shows strong Level 3 effort because it uses multiple classes and several collection types together.  
The overall class separation is clear, and the `LostItemRegistry` class correctly acts as the central manager for lost item registration, searching, claiming, and claim-count tracking.

The strongest part of the solution is the use of:

```java
countClaimAttempts.merge(studentName, 1, Integer::sum);
```

This is a clean and appropriate use of `HashMap` for counting repeated events.

The main issue is not the overall structure.  
The main issue is that some collections were declared but not fully used according to their intended responsibility.

---

## Mistakes

The main mistake was:

```java
for(LostItem lostItem1 : lostItems){
    alreadyExists = lostItem1.getItemID().equalsIgnoreCase(lostItem.getItemID());
    if(alreadyExists){
        break;
    }
}
```

instead of using the existing `HashSet<String> itemIDs`:

```java
if (itemIDs.contains(lostItem.getItemID())) {
    System.out.println("Rejected duplicate item ID: " + lostItem.getItemID());
} else {
    lostItems.add(lostItem);
    itemIDs.add(lostItem.getItemID());
    System.out.println("Registered: " + lostItem.getItemID() + " - " + lostItem.getName());
}
```

The current code still works logically for duplicate checking, but it does not fully use the purpose of `HashSet`.

A `HashSet` is useful when the program needs to check whether a value already exists.  
Since item IDs must be unique, `itemIDs.contains()` is a better match than manually looping through the `ArrayList`.

Another mistake was about logic:

```java
public boolean matchesDescription(String description) {
    return description.equals(this.name);
}
```

This only returns `true` when the description is exactly the same as the item name.

A better version is:

```java
public boolean matchesDescription(String description) {
    return description.toLowerCase().contains(this.name.toLowerCase());
}
```

This checks whether the claim description contains the item name while ignoring uppercase and lowercase differences.

For example:

```java
"Laptop with black sticker".toLowerCase().contains("Laptop".toLowerCase())
```

would correctly return `true`.

---

## Why the Mistake Happened

The main concept that needs reinforcement is choosing the right collection for the right responsibility.

`ArrayList` is useful when:

- order matters
- you need to print all items
- you need to access objects one by one

`HashSet` is useful when:

- duplicates must be prevented
- existence checking is important
- the program only needs to know whether a value already exists

`HashMap` is useful when:

- one value must be connected to another value
- a key should lead directly to a value
- counting or tracking by name, ID, or category is needed

In your code, `HashMap` was used very well.  
`HashSet` was declared correctly, but its responsibility was not fully applied in `addLostItem()`.

---

## Improved Solution

Change `addLostItem()` to use `itemIDs` directly:

```java
public void addLostItem(LostItem lostItem) {
    if (itemIDs.contains(lostItem.getItemID())) {
        System.out.println("Rejected duplicate item ID: " + lostItem.getItemID());
        return;
    }

    lostItems.add(lostItem);
    itemIDs.add(lostItem.getItemID());
    categories.add(lostItem.getCategory());

    System.out.println("Registered: " + lostItem.getItemID() + " - " + lostItem.getName());
}
```

This improves the design because:

- `lostItems` stores the actual `LostItem` objects.
- `itemIDs` tracks which IDs already exist.
- `categories` tracks unique categories as items are added.
- `printAllCategories()` no longer needs to rebuild the category set every time.

Then `printAllCategories()` can become simpler:

```java
public void printAllCategories() {
    System.out.println("=== Existing Categories ===");

    for (String category : categories) {
        System.out.println(category);
    }

    System.out.println();
}
```

In `ClaimRequest`, the fields currently have package-level access:

```java
String studentName;
String itemID;
String description;
```

A cleaner object-oriented style is:

```java
private String studentName;
private String itemID;
private String description;
```

Then add a getter:

```java
public String getDescription() {
    return description;
}
```

After that, `LostItemRegistry` should use:

```java
claimRequest.getDescription()
```

instead of:

```java
claimRequest.description
```

This keeps object data protected and makes the class design more consistent with your `LostItem` class.

---

## What I Learned

The most important learning point from this solution is that declaring a collection is not enough.  

Each collection should have a clear responsibility in the design.

For this problem:

- `ArrayList<LostItem>` should manage the full list of registered item objects.
- `HashSet<String> itemIDs` should prevent duplicate item IDs.
- `HashSet<String> categories` should maintain unique categories.
- `HashMap<String, Integer> countClaimAttempts` should count student claim attempts.

---

## Related Java Concepts

- [[11. Collection Famework#List Interface|List Interface]]
- [[11. Collection Famework#HashSet|Hash Set]]
- [[11. Collection Famework#HashMap|HashMap]]
- [[8. Class part 1#Instance Variable|Instance Variable]]
- [[8. Class part 1#Constructor|Constructor]]
- [[7. Method#Method|Method]]

