package src.July_2026.Week_2.JD_2026_07_W2_TripBudgetManager;

public class Expense {
    private String description;
    private double amount;
    private ExpenseCategory category;

    public Expense(String description, double amount, ExpenseCategory category) {
        this.description = description;
        this.amount = amount;
        this.category = category;
    }

    public void printExpense() {
        System.out.printf("[%s]  %s - $%.2f\n", getCategory(), getDescription(), getAmount());
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public ExpenseCategory getCategory() {
        return category;
    }

    public void setCategory(ExpenseCategory category) {
        this.category = category;
    }
}
