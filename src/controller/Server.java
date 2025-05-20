/*
 * Server entry point for RMI-based service registration.
 * Binds various service implementations for user, trip, destination, booking, otp, and expense to the RMI registry on port 5000.
 */
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import service.implementation.UserServiceImpl;
//import service.implementation.TripServiceImpl;
import service.implementation.DestinationServiceImpl;
import service.implementation.BookingServiceImpl;
import service.implementation.ExpenseServiceImpl;
import service.implementation.OtpServiceImpl;

public class Server {
    public static void main(String[] args) {
        try {
            System.setProperty("java.rmi.server.hostname", "127.0.0.1");
            Registry theRegistry = LocateRegistry.createRegistry(5000);
            theRegistry.rebind("user", new UserServiceImpl());
//            theRegistry.rebind("trip", new TripServiceImpl());
            theRegistry.rebind("destination", new DestinationServiceImpl());
            theRegistry.rebind("booking", new BookingServiceImpl());
            theRegistry.rebind("otp", new OtpServiceImpl());
            theRegistry.rebind("expense", new ExpenseServiceImpl());
            System.out.println("Server is running on port 5000");
            Thread.currentThread().join();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}