package src.July_2026.Week_2.JD_2026_07_W2_TripBudgetManager;

public class TravelBudgetMain {
    public static void main(String[] args) {
        Trip trip = new Trip("Budapest", 900.00);

        trip.addExpense(new Expense("Flight Ticket", 300.00, ExpenseCategory.TRANSPORT));
        trip.addExpense(new Expense(null, 80.00, ExpenseCategory.TRANSPORT));
        trip.addExpense(new Expense("Hotel", 150, ExpenseCategory.ACCOMMODATION));
        trip.addExpense(new Expense("Restaurant", 100, ExpenseCategory.FOOD));
        trip.addExpense(new Expense("Museum", 60, ExpenseCategory.ACTIVITY));
        System.out.println();

        trip.printTripSummary();
        System.out.println();

        trip.printExpenseSummary();
        System.out.println();

        trip.printExpenseByCategory();

        System.out.printf("Total Expense: $%.2f\n", trip.totalExpense());
        System.out.printf("Remaining Budget : $%.2f\n", trip.remainingBudget());
        System.out.println("Budget Status: " + trip.budgetStatus());
        System.out.println();

        trip.printHighestExpense();
    }
}
