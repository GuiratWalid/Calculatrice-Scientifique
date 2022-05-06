package converter;

import ConvertApp.ConvertPOA;
import org.omg.CORBA.ORB;

public class ConvertObj extends ConvertPOA{
    
    private ORB orb;
    
    public void setORB(ORB orb_val) {
        orb = orb_val; 
    }

    @Override
    public String decimalToBinary(int decimal) {
        return Integer.toBinaryString(decimal);
    }

    @Override
    public String decimalToOctal(int decimal) {
        return Integer.toOctalString(decimal);
    }

    @Override
    public String decimalToHexadecimal(int decimal) {
        return Integer.toHexString(decimal);
    }

    @Override
    public String BinaryToDecimal(String binary) {
        return Integer.toString(Integer.parseInt(binary,2));
    }

    @Override
    public String binaryToOctal(String binary) {
        return Integer.toOctalString(Integer.parseInt(binary,2));
    }

    @Override
    public String binaryToHexadecimal(String binary) {
        return Integer.toHexString(Integer.parseInt(binary,2));
    }

    @Override
    public String octalToBinary(int octal) {
        return Integer.toBinaryString(Integer.parseInt(Integer.toString(octal),8));
    }

    @Override
    public String octalToDecimal(int octal) {
        return Integer.toString(Integer.parseInt(Integer.toString(octal),8));
    }

    @Override
    public String octalToHexaDecimal(int octal) {
        return Integer.toHexString(Integer.parseInt(Integer.toString(octal),8));
    }

    @Override
    public String hexadecimalToBinary(String hexadecimal) {
        return Integer.toBinaryString(Integer.parseInt(hexadecimal,16));
    }

    @Override
    public String hexadecimalToOctal(String hexadecimal) {
        return Integer.toOctalString(Integer.parseInt(hexadecimal,16));
    }

    @Override
    public String hexadecimalToDecimal(String hexadecimal) {
        return Integer.toString(Integer.parseInt(hexadecimal,16));
    }

    @Override
    public void shutdown() {
        orb.shutdown(false);
    }

   
    
}
