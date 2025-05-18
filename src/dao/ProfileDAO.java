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
import model.Profile;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class ProfileDAO {

    public Profile findById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Profile profile = (Profile) session.get(Profile.class, id);
        session.close();
        return profile;
    }

    public List<Profile> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Profile> list = session.createQuery("from Profile").list();
        session.close();
        return list;
    }

    public void save(Profile profile) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(profile);
        tx.commit();
        session.close();
    }

    public void update(Profile profile) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.update(profile);
        tx.commit();
        session.close();
    }

    public void delete(Profile profile) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.delete(profile);
        tx.commit();
        session.close();
    }
}