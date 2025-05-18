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
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import model.Booking;
import service.BookingService;
import java.util.List;

public class BookingServiceImpl extends UnicastRemoteObject implements BookingService {

    public BookingServiceImpl() throws RemoteException{
    super();
    }
    
    private BookingDAO bookingDAO = new BookingDAO();

    @Override
    public Booking findById(Long id) {
        return bookingDAO.findById(id);
    }

    @Override
    public List<Booking> findAll() {
        return bookingDAO.findAll();
    }

    @Override
    public void save(Booking booking) {
        bookingDAO.save(booking);
    }

    @Override
    public void update(Booking booking) {
        bookingDAO.update(booking);
    }

    @Override
    public void delete(Booking booking) {
        bookingDAO.delete(booking);
    }
}
