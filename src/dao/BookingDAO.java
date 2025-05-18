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

    public void save(Booking booking) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(booking);
        tx.commit();
        session.close();
    }

    public void update(Booking booking) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.update(booking);
        tx.commit();
        session.close();
    }

    public void delete(Booking booking) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.delete(booking);
        tx.commit();
        session.close();
    }
}
