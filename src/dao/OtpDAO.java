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
import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;

import java.time.LocalDateTime;

public class OtpDAO {

    public boolean save(Otp otp) {
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            session.save(otp);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    public Otp findValidOtpByUser(User user, String code) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query query = session.createQuery(
                "FROM Otp WHERE user = :user AND code = :code AND used = false AND expirationTime > :now"
            );
            query.setParameter("user", user);
            query.setParameter("code", code);
            query.setParameter("now", LocalDateTime.now());
            Otp otp = (Otp) query.uniqueResult();
            return otp;
        } finally {
            session.close();
        }
    }

    public boolean markOtpAsUsed(Otp otp) {
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            otp.setUsed(true);
            session.update(otp);
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