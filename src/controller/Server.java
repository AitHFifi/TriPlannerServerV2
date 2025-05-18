/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import service.implementation.UserServiceImpl;
import service.implementation.ProfileServiceImpl;
import service.implementation.TripServiceImpl;
import service.implementation.DestinationServiceImpl;
import service.implementation.BookingServiceImpl;
import service.implementation.OtpServiceImpl;

/**
 *
 * @author Hp
 */
public class Server {
    public static void main(String[] args) {
        try {
            System.setProperty("java.rmi.server.hostname", "127.0.0.1");
            Registry theRegistry = LocateRegistry.createRegistry(5000);
            theRegistry.rebind("user", new UserServiceImpl());
            theRegistry.rebind("profile", new ProfileServiceImpl());
            theRegistry.rebind("trip", new TripServiceImpl());
            theRegistry.rebind("destination", new DestinationServiceImpl());
            theRegistry.rebind("booking", new BookingServiceImpl());
            theRegistry.rebind("otp", new OtpServiceImpl());
            System.out.println("Server is running on port 5000");
            Thread.currentThread().join();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
