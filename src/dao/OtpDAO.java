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
import model.Otp;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class OtpDAO {

    public Otp findById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Otp otp = (Otp) session.get(Otp.class, id);
        session.close();
        return otp;
    }

    public List<Otp> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Otp> list = session.createQuery("from Otp").list();
        session.close();
        return list;
    }

    public void save(Otp otp) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(otp);
        tx.commit();
        session.close();
    }

    public void update(Otp otp) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.update(otp);
        tx.commit();
        session.close();
    }

    public void delete(Otp otp) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.delete(otp);
        tx.commit();
        session.close();
    }
}
