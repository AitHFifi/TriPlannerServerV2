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
import model.Trip;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import model.User;

public class TripDAO {

    
    public Trip save(Trip trip) {
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            session.save(trip);
            tx.commit();
            return trip;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            return null;
        } finally {
            session.close();
        }
    }

    public Trip update(Trip trip) {
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            session.update(trip);
            tx.commit();
            return trip;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            return null;
        } finally {
            session.close();
        }
    }

    public Trip delete(Trip trip) {
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            session.delete(trip);
            tx.commit();
            return trip;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            return null;
        } finally {
            session.close();
        }
    }
    
    // Find all trips for a given user
    public List<Trip> findByUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            List<Trip> trips = (List<Trip>) session.createQuery(
    "from Trip t where t.user.id = :userId")
    .setParameter("userId", user.getUserId())
    .list();
            return trips;
        } finally {
            session.close();
        }
    }
    
    // Find a trip by its id
    public Trip findById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return (Trip) session.get(Trip.class, id);
        } finally {
            session.close();
        }
    }
    
        public long countAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        long count = (Long) session.createQuery("select count(t) from Trip t").uniqueResult();
        session.close();
        return count;
    }

    public long countUpcoming() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        long count = (Long) session.createQuery("select count(t) from Trip t where t.startDate > current_date").uniqueResult();
        session.close();
        return count;
    }

    public long countCompleted() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        long count = (Long) session.createQuery("select count(t) from Trip t where t.endDate < current_date").uniqueResult();
        session.close();
        return count;
    }

    public double sumBudgets() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Double sum = (Double) session.createQuery("select sum(t.budget) from Trip t").uniqueResult();
        session.close();
        return sum != null ? sum : 0.0;
    }
}
