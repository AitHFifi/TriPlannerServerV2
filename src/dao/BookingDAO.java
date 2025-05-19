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
import model.Booking;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class BookingDAO {

    public Booking findById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Booking booking = (Booking) session.get(Booking.class, id);
        session.close();
        return booking;
    }

    public List<Booking> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Booking> list = session.createQuery("from Booking").list();
        session.close();
        return list;
    }

    public boolean save(Booking booking) {
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            session.save(booking);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    public boolean update(Booking booking) {
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            session.update(booking);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    public boolean delete(Booking booking) {
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            session.delete(booking);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            return false;
        } finally {
            session.close();
        }
    }
    
    public long countAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        long count = (Long) session.createQuery("select count(b) from Booking b").uniqueResult();
        session.close();
        return count;
    }

    public long countPending() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        long count = (Long) session.createQuery("select count(b) from Booking b where b.status = :status")
                                  .setParameter("status", "pending")
                                  .uniqueResult();
        session.close();
        return count;
    }
}
