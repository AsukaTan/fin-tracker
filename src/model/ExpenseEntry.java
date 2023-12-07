package src.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ExpenseEntry implements Serializable {

    private List<Expense> expenses;

    public ExpenseEntry() {
        expenses = new ArrayList<>();
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    // Method to remove an expense at a specific index
    public void removeExpenseAt(int index) {
        if (index >= 0 && index < expenses.size()) {
            expenses.remove(index);
        }
    }
}

