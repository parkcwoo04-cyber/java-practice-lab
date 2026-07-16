package src.July_2026.Week_2.JD_2026_07_W2_TripBudgetManager;

import java.util.ArrayList;

public class Trip {
    private String destination;
    private double budget;
    ArrayList<Expense> expenses = new ArrayList<Expense>();

    public Trip(String destination, double budget) {
        this.destination = destination;
        this.budget = budget;
        this.expenses = new ArrayList();
    }

    public void addExpense(Expense expense) {
        try {
            if (expense.getCategory() == null || expense.getCategory().equals("")) {
                throw new IllegalArgumentException("Expense Rejected: Expense Category is invalid.");
            } else if (expense.getDescription() == null || expense.getDescription().equals("")) {
                throw new IllegalArgumentException("Expense Rejected: Expense Description is invalid.");
            } else if (expense.getAmount() <= 0) {
                throw new IllegalArgumentException("Expense Rejected: Expense Amount should be greater than 0.");
            } else {
                expenses.add(expense);
                System.out.println("Expense added: " + expense.getDescription());
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void printTripSummary() {
        System.out.println("===== Trip Summary =====");
        System.out.println("Destination: " + destination);
        System.out.printf("Budget: $%.2f\n", budget);
    }

    public void printExpenseSummary() {
        System.out.println("Expenses");
        for (Expense expense : expenses) {
            expense.printExpense();
        }
    }

    public void printExpenseByCategory() {
        double transport = 0;
        double accommodation = 0;
        double food = 0;
        double activity = 0;
        double other = 0;
        for (Expense expense : expenses) {
            if (expense.getCategory().equals(ExpenseCategory.TRANSPORT)) {
                transport += expense.getAmount();
            } else if (expense.getCategory().equals(ExpenseCategory.ACCOMMODATION)) {
                accommodation += expense.getAmount();
            } else if (expense.getCategory().equals(ExpenseCategory.FOOD)) {
                food += expense.getAmount();
            } else if (expense.getCategory().equals(ExpenseCategory.ACTIVITY)) {
                activity += expense.getAmount();
            } else if (expense.getCategory().equals(ExpenseCategory.OTHER)) {
                other += expense.getAmount();
            }
        }

        System.out.println("Expense by Category");
        System.out.printf("TRANSPORT: $%.2f\n", transport);
        System.out.printf("ACCOMMODATION: $%.2f\n", accommodation);
        System.out.printf("FOOD: $%.2f\n", food);
        System.out.printf("ACTIVITY: $%.2f\n", activity);
        System.out.printf("OTHER: $%.2f\n", other);
        System.out.println();
    }

    public double totalExpense() {
        double total = 0;
        for (Expense expense : expenses) {
            total += expense.getAmount();
        }
        return total;
    }

    public double remainingBudget() {
        return budget - totalExpense();
    }

    public String budgetStatus() {
        if (totalExpense() > budget) {
            return "Out of Budget";
        } else {
            return "Within budget";
        }
    }

    public Expense highestExpense() {
        Expense highestExpense = expenses.getFirst();
        for (Expense expense : expenses) {
            if (expense.getAmount() > highestExpense.getAmount()) {
                highestExpense = expense;
            }
        }
        return highestExpense;
    }

    public void printHighestExpense() {
        System.out.println("Highest Expense");
        System.out.printf("%s - $%.2f\n", highestExpense().getDescription(), highestExpense().getAmount());
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }
}
