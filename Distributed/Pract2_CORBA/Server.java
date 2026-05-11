// Import ORB package
import org.omg.CORBA.ORB;

// Import naming service classes
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

// Import POA classes
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

// Import generated calculator classes
import CalculatorApp.Calculator;
import CalculatorApp.CalculatorHelper;

// Server class
public class Server {

    // Main method
    public static void main(String[] args) {

        try {

            // Initialize ORB
            ORB orb =
                ORB.init(args, null);

            // Get reference to RootPOA
            POA rootPOA =

                POAHelper.narrow(

                    orb.resolve_initial_references(
                        "RootPOA"
                    )
                );

            // Activate POA Manager
            rootPOA
                .the_POAManager()
                .activate();

            // Create Calculator servant object
            CalculatorImpl calculator =
                new CalculatorImpl();

            // Convert servant to CORBA object reference
            org.omg.CORBA.Object ref =

                rootPOA
                    .servant_to_reference(
                        calculator
                    );

            // Narrow CORBA object reference
            Calculator href =

                CalculatorHelper.narrow(ref);

            // Get NameService reference
            org.omg.CORBA.Object objRef =

                orb.resolve_initial_references(
                    "NameService"
                );

            // Narrow naming context reference
            NamingContextExt ncRef =

                NamingContextExtHelper
                    .narrow(objRef);

            // Create object name path
            NameComponent[] path =

                ncRef.to_name("ABC");

            // Bind object with name "ABC"
            ncRef.rebind(path, href);

            // Display server ready message
            System.out.println(

                "Server ready and waiting..."
            );

            // Keep server running
            orb.run();
        }

        catch (Exception e) {

            // Display error message
            System.out.println(
                "Server Error: " + e
            );

            // Print exception details
            e.printStackTrace(System.out);
        }
    }
}