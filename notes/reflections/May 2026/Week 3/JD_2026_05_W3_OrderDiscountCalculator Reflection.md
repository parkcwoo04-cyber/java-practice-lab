

## Metadata  

Drill ID: JD_2026_05_W3_OrderDiscountCalculator

Linked code: [View Java Code](../../../../java-drills/src/May_2026/Week_3/JD_2026_05_W3_OrderDiscountCalculator.java)

Class Name: OrderDiscountCalculator

Difficulty: Level 3 — Multi-Step Problem

Estimated Time: 25–30 minutes

Actual Time Taken: 35 minutes

Written by: Chanwoo Park

---

## Goal

Calculate the final payment amount for an online order by applying basic discount rules, member coupon discount rules, delivery fee rules, and final amount calculation.

---

## What I Tried

Created variables for order amount, membership status, coupon status, delivery fee, discount, final amount, and coupon application status.

Used an `if / else if / else` structure to apply only one basic discount based on `orderAmount`.

Used a separate `if` statement with `isMember && hasCoupon` to apply an additional coupon discount.

Used another `if` statement to add a delivery fee when the order amount is below 30000.

Calculated the final amount using `orderAmount - discount + deliveryFee`.

---

## Mistakes

The code does not have a major logic error. The main improvement point is code clarity:

- The empty `else` block after the discount condition is unnecessary.

- `discount += 10000` and `deliveryFee += 3000` work correctly, but direct assignment such as `discount = 10000` and `deliveryFee = 3000` would be clearer because these values are being set, not accumulated repeatedly.

---

## Why the Mistake Happened

The use of `+=` suggests that the value may be accumulated multiple times. In this program, the basic discount and delivery fee are decided once, so direct assignment communicates the logic more clearly.

The empty `else` block happened because the condition structure was completed syntactically, but no action was needed for the final case.

---

## Improved Solution

The logic can be improved by using direct assignment for one-time decisions:

- Use `discount = 10000` or `discount = 5000` for basic discount.

- Use `deliveryFee = 3000` when the order amount is below 30000.

- Remove the empty `else` block.

- Keep `discount += 3000` for the coupon discount because this is intentionally added on top of the basic discount.

---

## What I Learned

- `if / else if / else` executes only one matching branch.

- A separate `if` statement is useful when an additional condition should be applied independently.

- `&&` is used when both conditions must be true.

- Direct assignment is clearer when setting a value once.

- `+=` is better when intentionally adding to an existing value.

  

---

## Related Java Concepts

- [[4. Conditional statement#if Statement|if Statement]]
- [[4. Conditional statement#else if Statement|else if Statement]]
- [[2. Operator#Logical Operator|Logical Operator]]
- [[2. Operator#Assignment Operator|Compound Assignment Operator]]
- [[1. Data Type and Variables#Types of Data|boolean]]
- [[2. Operator#Arithmetic Operator|Arithmetic Operator]]