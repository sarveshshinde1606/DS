import java.rmi.*;              // For RMI classes
import java.rmi.registry.*;     // For RMI registry

// Server class
public class Server {

    // Main method
    public static void main(String[] args) {

        try {

            // Create remote object
            CalcImpl obj = new CalcImpl();

            // Create RMI registry on port 1099
            LocateRegistry.createRegistry(1099);

            // Bind remote object with name
            Naming.rebind("rmi://localhost/CalcService",obj);

            // Display server ready message
            System.out.println("Server ready...");

            } catch (Exception e) {

            // Display exception if error occurs
            System.out.println(e);
        }
    }
}