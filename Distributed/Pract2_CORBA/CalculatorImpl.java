// Import generated Calculator package
import CalculatorApp.*;

// Import CORBA package
import org.omg.CORBA.*;

// Import Portable Server package
import org.omg.PortableServer.*;

// Calculator implementation class
// Extends CalculatorPOA generated from IDL
public class CalculatorImpl
        extends CalculatorPOA {

    // Remote method for addition
    @Override
    public double add(
            double x,
            double y) {

        // Return addition result
        return x + y;
    }

    // Remote method for subtraction
    @Override
    public double subtract(
            double x,
            double y) {

        // Return subtraction result
        return x - y;
    }

    // Remote method for multiplication
    @Override
    public double multiply(
            double x,
            double y) {

        // Return multiplication result
        return x * y;
    }

    // Remote method for division
    @Override
    public double divide(
            double x,
            double y) {

        // Check divide by zero
        if (y == 0)

            // Return 0 if denominator is zero
            return 0;

        // Return division result
        return x / y;
    }

    // Shutdown method
    @Override
    public void shutdown() {

        // TODO Auto-generated method stub
    }
}