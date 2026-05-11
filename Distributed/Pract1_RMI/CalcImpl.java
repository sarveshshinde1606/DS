import java.rmi.*;
import java.rmi.server.*;

public class CalcImpl extends UnicastRemoteObject implements Calc {

    public CalcImpl() throws RemoteException {super();}

    public double add(double a, double b) {return a + b;}

    public double sub(double a, double b) {return a - b;}

    public double mul(double a, double b) {return a * b;}

    public double div(double a, double b) {
        if (b == 0) return Double.POSITIVE_INFINITY;
        return a / b;
    }
}