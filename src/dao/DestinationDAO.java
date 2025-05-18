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
import model.Destination;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class DestinationDAO {

    public Destination findById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Destination destination = (Destination) session.get(Destination.class, id);
        session.close();
        return destination;
    }

    public List<Destination> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Destination> list = session.createQuery("from Destination").list();
        session.close();
        return list;
    }

    public boolean save(Destination destination) {
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            session.save(destination);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    public boolean update(Destination destination) {
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            session.update(destination);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    public boolean delete(Destination destination) {
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            session.delete(destination);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            return false;
        } finally {
            session.close();
        }
    }
}
