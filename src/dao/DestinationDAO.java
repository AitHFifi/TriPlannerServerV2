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

    public void save(Destination destination) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(destination);
        tx.commit();
        session.close();
    }

    public void update(Destination destination) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.update(destination);
        tx.commit();
        session.close();
    }

    public void delete(Destination destination) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.delete(destination);
        tx.commit();
        session.close();
    }
}
