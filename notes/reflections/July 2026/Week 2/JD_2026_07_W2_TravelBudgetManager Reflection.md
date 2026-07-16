

## Metadata

Drill ID: Travel Budget Manager

Linked code:
- [View Java Code](../../../java-drills/src/July_2026/Week_2/JD_2026_07_W2_TravelBudgetManager)

Difficulty: Level 3 --- Object-Oriented Problem Solving

Estimated Time: 70--90 minutes

Actual Time Taken: 78 minutes

Written by: Chanwoo Park

------------------------------------------------------------------------

## Goal

The goal of this project was to create a travel budget management
program using multiple classes. I designed the program using `Trip`,
`Expense`, and `ExpenseCategory`, stored multiple expenses in an
`ArrayList`, calculated the total spending and remaining budget, grouped
expenses by category, and found the highest expense while keeping
responsibilities separated between classes.

------------------------------------------------------------------------

## What I Tried

- Created separate classes for `TravelBudgetMain`, `Trip`, `Expense`, and `ExpenseCategory`.
- Used constructors to initialize objects.
- Stored multiple `Expense` objects in an `ArrayList<Expense>`.
- Implemented separate methods for total expense, remaining budget, budget status, category totals, and highest expense.
- Used an `enum` to limit available expense categories.
- Added validation to reject invalid expense data.
- Kept all business logic inside the `Trip` class instead of calculating values in `TravelBudgetMain`.

------------------------------------------------------------------------

## Mistakes

The main mistake was assuming that the expense list always contained at
least one element.

``` java
Expense highestExpense = expenses.getFirst();
```

When the list is empty, this statement throws an exception.

A better approach is:

``` java
if (expenses.isEmpty()) {
    return null;
}
```

Another mistake was creating the `ArrayList` without generics.

``` java
this.expenses = new ArrayList();
```

instead of

``` java
this.expenses = new ArrayList<>();
```

I also compared an enum with an empty string, even though an enum can
only be a valid constant or `null`.

------------------------------------------------------------------------

## Why the Mistake Happened

I focused on implementing the normal execution flow and overlooked edge
cases such as an empty expense list.

I also treated `ExpenseCategory` like a `String`, forgetting that enums
should only be checked against `null` or compared with enum constants.

Finally, I paid more attention to making the program work than
eliminating compiler warnings and improving maintainability.

------------------------------------------------------------------------

## Improved Solution

Improve the validation logic by checking for an empty collection before
searching for the highest expense.

``` java
if (expenses.isEmpty()) {
    return null;
}
```

Initialize collections using generics.

``` java
this.expenses = new ArrayList<>();
```

Validate enum values correctly.

``` java
if (expense.getCategory() == null) {
    ...
}
```

These changes make the program safer, reduce unnecessary warnings, and
handle edge cases more reliably.

------------------------------------------------------------------------

## What I Learned

This project helped me understand how responsibilities should be divided
between classes in an object-oriented program.

I also learned that handling edge cases is just as important as
implementing the main functionality. Even if the normal flow works
correctly, failing to consider empty collections or invalid input can
still cause runtime errors.

During future projects, I will test not only valid data but also empty
and invalid cases before considering the program complete.

------------------------------------------------------------------------

## Related Java Concepts

- [[9. Class part 2|Class]]
- [[11. Collection Framework|Collection Framework]]
- [[7. Method|Method]]
- [[9. Class part 2#Enum|Enum]]
- [[12. Exception Handling|Exception Handling]]

