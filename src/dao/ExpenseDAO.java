package dao;

import model.Expense;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class ExpenseDAO {

    public Expense findExpenseById(Long id) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    try {
        // Use JOIN FETCH to load the user eagerly
        return (Expense) session.createQuery(
            "select e from Expense e join fetch e.user where e.expenseId = :id"
        )
        .setParameter("id", id)
        .uniqueResult();
    } finally {
        session.close();
    }
}

    public List<Expense> findAllExpensesByUser(Long userId) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    List<Expense> list = session.createQuery(
        "select e from Expense e join fetch e.trip where e.user.userId = :userId"
    ).setParameter("userId", userId).list();
    session.close();
    return list;
}

    public boolean saveExpense(Expense expense) {
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

    public boolean updateExpense(Expense expense) {
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            session.update(expense);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    public boolean deleteExpense(Expense expense) {
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            session.delete(expense);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    // Find all expenses by category (for a user)
    public List<Expense> findExpensesByCategory(Long userId, String category) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Expense> list = session.createQuery("from Expense where user.userId = :userId and category = :category")
                                    .setParameter("userId", userId)
                                    .setParameter("category", category)
                                    .list();
        session.close();
        return list;
    }

    // Find all expenses for a specific trip (for a user)
    public List<Expense> findExpensesByTrip(Long userId, Long tripId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Expense> list = session.createQuery("from Expense where user.userId = :userId and trip.tripId = :tripId")
                                    .setParameter("userId", userId)
                                    .setParameter("tripId", tripId)
                                    .list();
        session.close();
        return list;
    }

    // Sum all expenses for a specific trip (for a user)
    public double sumExpensesByTrip(Long userId, Long tripId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Double sum = (Double) session.createQuery(
            "select sum(amount) from Expense where user.userId = :userId and trip.tripId = :tripId")
            .setParameter("userId", userId)
            .setParameter("tripId", tripId)
            .uniqueResult();
        session.close();
        return sum != null ? sum : 0.0;
    }

    // Sum all expenses (for a user)
    public double sumAllExpenses(Long userId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Double sum = (Double) session.createQuery(
            "select sum(e.amount) from Expense e where e.user.userId = :userId")
            .setParameter("userId", userId)
            .uniqueResult();
        session.close();
        return sum != null ? sum : 0.0;
    }
}