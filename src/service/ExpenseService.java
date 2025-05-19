/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

/**
 *
 * @author Hp
 */
import model.Expense;
import java.util.List;

public interface ExpenseService {
    Expense findById(Long id);
    List<Expense> findAll();
    boolean save(Expense expense, String tripId);
    boolean update(Expense expense, String tripId);
    boolean delete(Expense expense);
    List<Expense> findByExpenseType(String expenseType);
    List<Expense> findByTrip(String tripId);
    double sumExpensesByTrip(String tripId);
    double sumAll();
}