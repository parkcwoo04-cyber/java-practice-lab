

## Metadata

Drill ID: JD_2026_08_W2_IndieFilmFestivalVoting

Linked code: [View Java Code](../../../../java-drills/src/August_2026/Week_2/JD_2026_08_W2_IndieFilmFestivalVoting/FestivalVotingApp.java)

Difficulty: Level 2 — Multi-Class Application

Estimated Time: 45–60 minutes

Actual Time Taken: 48 minutes

Written by: Chanwoo Park

---

## Goal

Build an indie film festival voting system using multiple collaborating classes. The program registers films and viewers, validates ratings, prevents duplicate voting for the same film, stores film ratings, calculates average scores, finds the top film, and prints each viewer's voting history.

---

## What I Tried

- Created separate `Film`, `Viewer`, `FestivalManager`, `FestivalVotingApp`, and `Genre` types.
- Used `Genre` as an `enum` instead of storing genre as arbitrary text.
- Used `FestivalManager` to coordinate registration and voting instead of placing the business logic in `main`.
- Added duplicate-ID validation for films and viewers.
- Added constructor validation for empty film IDs, titles, viewer IDs, and names.
- Let `Film` own its rating collection and average calculation.
- Safely handled films with no ratings before calculating an average.
- Used exceptions so invalid operations could return to `FestivalVotingApp`, where user-facing messages were printed.
- Used a loop to determine the film with the highest average rating.

---

## Mistakes

The main logic mistake was modeling voting history as one boolean and one film ID:

```java
private String filmID;
private boolean hasVoted;
```

and then checking:

```java
if (!v.voted()) {
    // allow vote
} else {
    throw new IllegalArgumentException("Error: " + viewID + " already voted");
}
```

This means a viewer can vote only **once in the entire festival**.

The requirement was different:

```text
A viewer may vote for multiple different films,
but may vote for the same film only once.
```

For example, after:

```text
V-101 → F-201
```

the viewer should still be able to vote for `F-202`, `F-203`, or `F-204`.

The current design rejects all later votes because `hasVoted` becomes `true`.

A better representation is a collection of voted film IDs, such as:

```java
private List<String> votedFilmIDs = new ArrayList<>();
```

Then the duplicate check can ask whether the specific film ID already exists.

---

Another logic mismatch is the rating range.

The assignment requires:

```text
1 to 10
```

but the current validation is:

```java
if (ratings < 0 || ratings > 10) {
    throw new IllegalArgumentException("Ratings must be between 0 and 10");
}
```

This allows `0`, which should be invalid.

The condition should represent the same rule as the requirement:

```text
rating < 1 OR rating > 10
```

---

The average calculation also loses decimal values:

```java
return sum / ratings.size();
```

Both operands are integers, so Java performs integer division.

For example:

```text
9 + 8 = 17
17 / 2 = 8
```

instead of:

```text
8.5
```

Since film ratings can naturally produce decimal averages, the calculation should return a `double`.

---

The collection abstraction requirement was not fully followed.

Current fields:

```java
private ArrayList<Film> films;
private ArrayList<Viewer> viewers;
```

The exercise asked for interface reference types:

```java
List<Film>
List<Viewer>
```

Using `ArrayList` as the implementation is fine, but the field type should depend on `List`.

The same encapsulation issue appears in:

```java
public ArrayList<Film> getFilms()
public ArrayList<Viewer> getViewers()
public ArrayList<Integer> getRatings()
```

These methods return the actual mutable collections.

Outside code can therefore modify internal state directly and bypass validation.

For example, code outside `Film` could add a rating directly to `getRatings()` without using `addRating()`.

---

A smaller issue is the duplicate viewer message:

```java
throw new IllegalArgumentException("Error: Duplicate Film ID");
```

inside `registerViewer()`.

The validation logic is correct, but the message says `Film ID` instead of `Viewer ID`.

---

## Why the Mistake Happened

The main mistake came from representing the question:

```text
"Has this viewer voted?"
```

instead of the more precise question:

```text
"Has this viewer voted for this specific film?"
```

A boolean works only when the system needs two global states:

```text
not voted
voted
```

but this problem requires remembering multiple film IDs.

That means the data structure must match the business rule.

I also focused on getting average calculation working, but because the ratings and sum were integers, I did not account for Java's integer division behavior.

For the collections, I used `ArrayList` successfully, but I did not fully separate the interface type (`List`) from the implementation type (`ArrayList`).

---

## Improved Solution

The biggest improvement is to let `Viewer` store multiple voted film IDs.

Conceptually:

```java
private List<String> votedFilmIDs = new ArrayList<>();
```

Then `Viewer` can provide behavior such as:

```java
public boolean hasVotedFor(String filmID) {
    return votedFilmIDs.contains(filmID);
}
```

and after a successful vote:

```java
public void recordVote(String filmID) {
    votedFilmIDs.add(filmID);
}
```

This allows:

```text
V-101 → F-201
V-101 → F-202
```

while still rejecting:

```text
V-101 → F-201
V-101 → F-201
```

The manager should validate all conditions before updating either object:

```text
1. Viewer exists
2. Film exists
3. Rating is between 1 and 10
4. Viewer has not already voted for this film
5. Film receives rating
6. Viewer records film ID
```

This preserves `Film` and `Viewer` consistency.

Average calculation should use floating-point division, for example conceptually:

```java
return (double) sum / ratings.size();
```

Collection fields should depend on the interface:

```java
private List<Film> films = new ArrayList<>();
private List<Viewer> viewers = new ArrayList<>();
```

and internal mutable collections should not be returned directly.

---

## What I Learned

I learned that choosing the correct data structure depends on the exact business rule.

A boolean was too simple for this problem because the program needed to remember **which films** each viewer had already voted for, not just whether the viewer had ever voted.

I also learned to check whether arithmetic involving integers should actually produce a decimal result. When calculating averages, integer division can silently produce a logically incorrect result even though the program continues running.

The strongest part of this implementation was the overall class separation: `FestivalManager` coordinated the flow, `Film` owned rating calculation, and `FestivalVotingApp` handled user-facing output and exceptions.

For future exercises, I should check:

- whether the stored data can represent every valid state required by the problem
- whether validation matches the requirement exactly
- whether an average or percentage needs `double`
- whether collection fields use `List` instead of concrete `ArrayList` references
- whether getters expose mutable internal collections
- whether two related objects are updated only after all validation succeeds

---

## Related Java Concepts

- [[11. Collection Framework#ArrayList|ArrayList]]
- [[8. Class part 1#Class|Class]]
- [[8. Class part 1#Access Modifier|Encapsulation]]
- [[9. Class part 2#Enum|Enum]]
- [[4. Conditional statement#if Statement|if Statement]]
- [[5. Loop Statement#for Loop|for Loop]]
