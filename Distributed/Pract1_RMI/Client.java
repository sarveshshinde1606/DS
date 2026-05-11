import java.rmi.*;          // For RMI classes
import java.util.Scanner;   // For user input

// Client class
public class Client {

    // Main method
    public static void main(String[] args) {

        try {
            // Lookup remote object from RMI registry
            Calc obj =(Calc) Naming.lookup("rmi://localhost/CalcService");

            // Scanner object for input
            Scanner sc =new Scanner(System.in);

            // Input first number
            System.out.print("Enter First Number: ");

            double a =sc.nextDouble();

            // Input second number
            System.out.print("Enter Second Number: ");

            double b = sc.nextDouble();

            // Display first number
            System.out.println("First Number Is: " + a);

            // Display second number
            System.out.println("Second Number Is: " + b);

            // Display result heading
            System.out.println("----------- Results -----------");

            // Call remote add() method
            System.out.println("Addition Is: " + obj.add(a, b));

            // Call remote sub() method
            System.out.println("Subtraction Is: " + obj.sub(a, b));

            // Call remote mul() method
            System.out.println("Multiplication Is: " + obj.mul(a, b));

            // Call remote div() method
            System.out.println("Division Is: " + obj.div(a, b));

        } catch (Exception e) {

            // Display exception if error occurs
            System.out.println(e);
        }
    }
}