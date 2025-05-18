/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.implementation;

/**
 *
 * @author Hp
 */

import dao.BookingDAO;
import model.Booking;
import service.BookingService;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class BookingServiceImpl extends UnicastRemoteObject implements BookingService {

    private BookingDAO bookingDAO = new BookingDAO();

    public BookingServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public Booking findById(Long id) throws RemoteException {
        return bookingDAO.findById(id);
    }

    @Override
    public List<Booking> findAll() throws RemoteException {
        return bookingDAO.findAll();
    }

    @Override
    public boolean save(Booking booking) throws RemoteException {
        return bookingDAO.save(booking);
    }

    @Override
    public boolean update(Booking booking) throws RemoteException {
        return bookingDAO.update(booking);
    }

    @Override
    public boolean delete(Booking booking) throws RemoteException {
        return bookingDAO.delete(booking);
    }
}
