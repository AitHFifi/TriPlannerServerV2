/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.implementation;

/**
 *
 * @author Hp
 */
import dao.ExpenseDAO;
import model.Expense;
import service.ExpenseService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class ExpenseServiceImpl extends UnicastRemoteObject implements ExpenseService {

    private ExpenseDAO expenseDAO = new ExpenseDAO();

    public ExpenseServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public Expense findById(Long id) {
        return expenseDAO.findById(id);
    }

    @Override
    public List<Expense> findAll() {
        return expenseDAO.findAll();
    }

    @Override
    public boolean save(Expense expense, String tripId) {
       return expenseDAO.save(expense);
    }

    @Override
    public boolean update(Expense expense, String tripId) {
        return expenseDAO.update(expense);
    }

    @Override
    public boolean delete(Expense expense) {
       return expenseDAO.delete(expense);
    }

    @Override
    public List<Expense> findByExpenseType(String expenseType) {
        return expenseDAO.findByExpenseType(expenseType);
    }

    @Override
    public double sumExpensesByTrip(String tripId) {
        return expenseDAO.sumExpensesByTrip(tripId);
    }

    @Override
    public List<Expense> findByTrip(String tripId) {
        return expenseDAO.findByTrip(tripId);
    }

    @Override
    public double sumAll() {
        return expenseDAO.sumAll();
    }
}
