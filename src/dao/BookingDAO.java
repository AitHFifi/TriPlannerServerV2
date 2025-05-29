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
import model.User;
import org.hibernate.Hibernate;

public class BookingDAO {

    // Find all bookings by user
    public List<Booking> findBookingsByUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            List<Booking> bookings = session.createQuery(
                    "select b from Booking b where b.user.userId = :userId")
                    .setParameter("userId", user.getUserId())
                    .list();
            // Initialize references to avoid LazyInitializationException
            for (Booking booking : bookings) {
                if (booking.getTrip() != null) {
                    Hibernate.initialize(booking.getTrip());
                }
            }
            return bookings;
        } finally {
            session.close();
        }
    }


    // Find booking by its ID
    public Booking findBookingById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Booking booking = (Booking) session.get(Booking.class, id);
            if (booking != null) {
                // Initialize Trip and User references if needed
                if (booking.getTrip() != null) {
                    Hibernate.initialize(booking.getTrip());
                    if (booking.getTrip().getUser() != null) {
                        Hibernate.initialize(booking.getTrip().getUser());
                    }
                }
            }
            return booking;
        } finally {
            session.close();
        }
    }
//    public List<Booking> findAll() {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        List<Booking> list = session.createQuery("from Booking").list();
//        session.close();
//        return list;
//    }

    public boolean saveBooking(Booking booking) {
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            session.save(booking);
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

    public boolean updateBooking(Booking booking) {
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

    public boolean deleteBooking(Booking booking) {
        Transaction tx = null;
    Session session = HibernateUtil.getSessionFactory().openSession();
    try {
        tx = session.beginTransaction();
        session.delete(booking);
        session.flush();
        tx.commit();
        return true;
    } catch (org.hibernate.exception.ConstraintViolationException cve) {
        if (tx != null) tx.rollback();
        System.err.println("Constraint violation: " + cve.getMessage());
        return false;
    } catch (Exception e) {
        if (tx != null) tx.rollback();
        e.printStackTrace();
        return false;
    } finally {
        session.close();
    }
    }
    
    // Count all bookings by user
    public long countAllBookingByUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return (Long) session.createQuery(
                    "select count(b) from Booking b where b.trip.user.userId = :userId")
                    .setParameter("userId", user.getUserId())
                    .uniqueResult();
        } finally {
            session.close();
        }
    }

    // Count pending bookings by user
    public long countPendingBookingByUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return (Long) session.createQuery(
                    "select count(b) from Booking b where b.trip.user.userId = :userId and lower(b.status) = :status")
                    .setParameter("userId", user.getUserId())
                    .setParameter("status", "pending")
                    .uniqueResult();
        } finally {
            session.close();
        }
    }
}
