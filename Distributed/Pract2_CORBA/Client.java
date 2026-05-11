// Import Scanner class
import java.util.Scanner;

// Import ORB package
import org.omg.CORBA.ORB;

// Import Naming Service classes
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

// Import generated Calculator classes
import CalculatorApp.Calculator;
import CalculatorApp.CalculatorHelper;

// Client class
public class Client {

    // Main method
    public static void main(String[] args) {

        try {

            // Initialize ORB
            ORB orb =
                ORB.init(args, null);

            // Get reference to NameService
            org.omg.CORBA.Object objRef =

                orb.resolve_initial_references(
                    "NameService"
                );

            // Narrow NameService reference
            NamingContextExt ncRef =

                NamingContextExtHelper
                    .narrow(objRef);

            // Lookup Calculator object named "ABC"
            Calculator calculator =

                CalculatorHelper.narrow(

                    ncRef.resolve_str("ABC")
                );

            // Scanner object for input
            Scanner sc =
                new Scanner(System.in);

            // Display welcome message
            System.out.println(
                "Welcome to Calculator System"
            );

            // Infinite loop for menu
            while (true) {

                // Display menu
                System.out.println(
                    "\n1. Add"
                );

                System.out.println(
                    "2. Subtract"
                );

                System.out.println(
                    "3. Multiply"
                );

                System.out.println(
                    "4. Divide"
                );

                System.out.println(
                    "5. Exit"
                );

                // Input choice
                System.out.print(
                    "Enter your choice: "
                );

                int choice =
                    sc.nextInt();

                // Exit condition
                if (choice == 5)

                    break;

                // Input first number
                System.out.print(
                    "Enter first number: "
                );

                double x =
                    sc.nextDouble();

                // Input second number
                System.out.print(
                    "Enter second number: "
                );

                double y =
                    sc.nextDouble();

                // Perform selected operation
                switch (choice) {

                    // Addition
                    case 1:

                        System.out.println(

                            "Answer = "

                            + calculator.add(x, y)
                        );

                        break;

                    // Subtraction
                    case 2:

                        System.out.println(

                            "Answer = "

                            + calculator.subtract(x, y)
                        );

                        break;

                    // Multiplication
                    case 3:

                        System.out.println(

                            "Answer = "

                            + calculator.multiply(x, y)
                        );

                        break;

                    // Division
                    case 4:

                        System.out.println(

                            "Answer = "

                            + calculator.divide(x, y)
                        );

                        break;

                    // Invalid choice
                    default:

                        System.out.println(
                            "Invalid choice"
                        );
                }
            }

            // Close scanner
            sc.close();
        }

        catch (Exception e) {

            // Display error message
            System.out.println(
                "Client Error: " + e
            );

            // Print exception details
            e.printStackTrace(System.out);
        }
    }
}