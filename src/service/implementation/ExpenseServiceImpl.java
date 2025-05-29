package service.implementation;

import dao.ExpenseDAO;
import dao.TripDAO;
import dao.UserDAO;
import model.Expense;
import model.Trip;
import model.User;
import service.ExpenseService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class ExpenseServiceImpl extends UnicastRemoteObject implements ExpenseService {

    private final ExpenseDAO expenseDAO = new ExpenseDAO();
    private final UserDAO userDAO = new UserDAO();
    private final TripDAO tripDAO = new TripDAO();

    public ExpenseServiceImpl() throws RemoteException {
        super();
    }

    private User getUserBySessionToken(String sessionToken) throws RemoteException {
        User user = userDAO.findBySessionToken(sessionToken);
        if (user == null) {
            throw new RemoteException("Invalid session token.");
        }
        return user;
    }

    @Override
    public boolean saveExpense(String sessionToken, Expense expense) throws RemoteException {
        User user = getUserBySessionToken(sessionToken);
        expense.setUser(user);
        // Attach managed Trip entity
        if (expense.getTrip() != null && expense.getTrip().getTripId() != null) {
            Trip trip = tripDAO.findTripById(expense.getTrip().getTripId());
            expense.setTrip(trip);
        } else {
            expense.setTrip(null);
        }
        return expenseDAO.saveExpense(expense);
    }

    @Override
    public boolean updateExpense(String sessionToken, Expense expense) throws RemoteException {
        try {
        User user = getUserBySessionToken(sessionToken);
        Expense dbExpense = expenseDAO.findExpenseById(expense.getExpenseId());
        if (dbExpense == null || dbExpense.getUser() == null || !dbExpense.getUser().getUserId().equals(user.getUserId())) {
            throw new RemoteException("Expense not found or access denied.");
        }
        Trip trip = null;
        if (expense.getTrip() != null && expense.getTrip().getTripId() != null) {
            trip = tripDAO.findTripById(expense.getTrip().getTripId());
        }
        dbExpense.setTrip(trip);
        dbExpense.setCategory(expense.getCategory());
        dbExpense.setAmount(expense.getAmount());
        dbExpense.setDate(expense.getDate());
        dbExpense.setDescription(expense.getDescription());
        return expenseDAO.updateExpense(dbExpense);
    } catch (Exception ex) {
        ex.printStackTrace(); // <-- Add this line
        throw new RemoteException("Update failed: " + ex.getMessage(), ex);
    }
}

    @Override
    public boolean deleteExpense(String sessionToken, Expense expense) throws RemoteException {
    try {
        User user = getUserBySessionToken(sessionToken);
        Expense dbExpense = expenseDAO.findExpenseById(expense.getExpenseId());
        if (dbExpense == null || dbExpense.getUser() == null || !dbExpense.getUser().getUserId().equals(user.getUserId())) {
            throw new RemoteException("Expense not found or access denied.");
        }
        return expenseDAO.deleteExpense(dbExpense);
    } catch (Exception ex) {
        ex.printStackTrace(); // <-- Add this line
        throw new RemoteException("Delete failed: " + ex.getMessage(), ex);
    }
}

    @Override
    public List<Expense> getAllExpensesBySession(String sessionToken) throws RemoteException {
        User user = getUserBySessionToken(sessionToken);
        return expenseDAO.findAllExpensesByUser(user.getUserId());
    }

    @Override
    public Expense getExpenseById(String sessionToken, Long expenseId) throws RemoteException {
        User user = getUserBySessionToken(sessionToken);
        Expense expense = expenseDAO.findExpenseById(expenseId);
        if (expense == null || expense.getUser() == null || !expense.getUser().getUserId().equals(user.getUserId())) {
            throw new RemoteException("Expense not found or access denied.");
        }
        return expense;
    }

    @Override
    public List<Expense> findExpensesByCategory(String sessionToken, String category) throws RemoteException {
        User user = getUserBySessionToken(sessionToken);
        return expenseDAO.findExpensesByCategory(user.getUserId(), category);
    }

    @Override
    public List<Expense> findExpensesByTrip(String sessionToken, Long tripId) throws RemoteException {
        User user = getUserBySessionToken(sessionToken);
        return expenseDAO.findExpensesByTrip(user.getUserId(), tripId);
    }

    @Override
    public double sumExpensesByTrip(String sessionToken, Long tripId) throws RemoteException {
        User user = getUserBySessionToken(sessionToken);
        return expenseDAO.sumExpensesByTrip(user.getUserId(), tripId);
    }

    @Override
    public double sumAllExpenses(String sessionToken) throws RemoteException {
        User user = getUserBySessionToken(sessionToken);
        return expenseDAO.sumAllExpenses(user.getUserId());
    }
}