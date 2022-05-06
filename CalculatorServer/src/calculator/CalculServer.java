package calculator;

import CalculApp.CalculHelper;

import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;


public class CalculServer {
    
    public static void main(String [] args){
        
        try{
                ORB orb = ORB.init(args, null); 
                POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
                rootpoa.the_POAManager().activate(); 
                CalculObj calobj = new CalculObj(); 
                calobj.setORB(orb); 
                org.omg.CORBA.Object ref = rootpoa.servant_to_reference(calobj);
                CalculApp.Calcul href = CalculHelper.narrow(ref);
                org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
                NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
                NameComponent path[] = ncRef.to_name( "ABC" );
                ncRef.rebind(path, href);
                System.out.println("Serveur Calcul en attend ...");
                for (;;){
                    orb.run();
                }
            } 
            catch (Exception e) {
                System.err.println("ERROR: " + e);
                e.printStackTrace(System.out);
                }
            System.out.println("Serveur Calcul fermé ...");
        
    }
    
}
