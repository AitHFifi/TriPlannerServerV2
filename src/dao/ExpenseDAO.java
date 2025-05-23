/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author Hp
 */
import model.Expense;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class ExpenseDAO {

    public Expense findById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Expense expense = (Expense) session.get(Expense.class, id);
        session.close();
        return expense;
    }

    public List<Expense> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Expense> list = session.createQuery("from Expense").list();
        session.close();
        return list;
    }

    public boolean save(Expense expense) {
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            session.save(expense);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    public boolean update(Expense expense) {
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            session.update(expense);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    public boolean delete(Expense expense) {
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            session.delete(expense);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    public List<Expense> findByExpenseType(String expenseType) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Expense> list = session.createQuery("from Expense where expenseType = :expenseType")
                                    .setParameter("expenseType", expenseType)
                                    .list();
        session.close();
        return list;
    }

    // New: Find all expenses for a specific trip
    public List<Expense> findByTrip(String tripId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Expense> list = session.createQuery("from Expense where trip.tripId = :tripId")
                                    .setParameter("tripId", tripId)
                                    .list();
        session.close();
        return list;
    }

    // New: Sum all expenses for a specific trip
    public double sumExpensesByTrip(String tripId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Double sum = (Double) session.createQuery("select sum(amount) from Expense where trip.tripId = :tripId")
                                     .setParameter("tripId", tripId)
                                     .uniqueResult();
        session.close();
        return sum != null ? sum : 0.0;
    }
    
    public double sumAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Double sum = (Double) session.createQuery("select sum(e.amount) from Expense e").uniqueResult();
        session.close();
        return sum != null ? sum : 0.0;
    }
}
