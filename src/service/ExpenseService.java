package service;

import java.rmi.Remote;
import model.Expense;
import java.util.List;

public interface ExpenseService extends Remote {
    /**
     * Save a new expense for the current session/user.
     */
    boolean saveExpense(String sessionToken, Expense expense) throws Exception;

    /**
     * Update an existing expense.
     */
    boolean updateExpense(String sessionToken, Expense expense) throws Exception;

    /**
     * Delete an expense.
     */
    boolean deleteExpense(String sessionToken, Expense expense) throws Exception;

    /**
     * Get all expenses for the current session/user.
     */
    List<Expense> getAllExpensesBySession(String sessionToken) throws Exception;

    /**
     * Get a specific expense by its ID.
     */
    Expense getExpenseById(String sessionToken, Long expenseId) throws Exception;

    /**
     * Find all expenses by category (for the current user/session).
     */
    List<Expense> findExpensesByCategory(String sessionToken, String category) throws Exception;

    /**
     * Find all expenses for a specific trip (for the current user/session).
     */
    List<Expense> findExpensesByTrip(String sessionToken, Long tripId) throws Exception;

    /**
     * Sum all expenses for a specific trip (for the current user/session).
     */
    double sumExpensesByTrip(String sessionToken, Long tripId) throws Exception;

    /**
     * Sum all expenses for the current user/session.
     */
    double sumAllExpenses(String sessionToken) throws Exception;
}