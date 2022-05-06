package calculator;

import CalculApp.CalculPOA;
import org.omg.CORBA.ORB;

public class CalculObj extends CalculPOA{
    
    private ORB orb;
    
    public void setORB(ORB orb_val) {
        orb = orb_val; 
    }

    @Override
    public double addition(double a, double b) {
        return a+b;
    }

    @Override
    public double soustraction(double a, double b) {
        return a-b;
    }

    @Override
    public double multiplication(double a, double b) {
        return a*b;
    }

    @Override
    public double division(double a, double b) {
        try{
            return a/b;
        }catch(ArithmeticException e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void shutdown() {
        orb.shutdown(false);
    }
    
}
