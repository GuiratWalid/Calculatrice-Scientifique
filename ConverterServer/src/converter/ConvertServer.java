package converter;

import ConvertApp.Convert;
import ConvertApp.ConvertHelper;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;


public class ConvertServer {
    
    public static void main(String [] args){
        
        try{
                ORB orb = ORB.init(args, null); 
                POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
                rootpoa.the_POAManager().activate(); 
                ConvertObj convobj = new ConvertObj(); 
                convobj.setORB(orb); 
                org.omg.CORBA.Object ref = rootpoa.servant_to_reference(convobj);
                Convert href = ConvertHelper.narrow(ref);
                org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
                NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
                NameComponent path[] = ncRef.to_name( "DEF" );
                ncRef.rebind(path, href);
                System.out.println("Serveur Convert en attend ...");
                for (;;){
                    orb.run();
                }
            } 
        catch (Exception e) {
                System.err.println("ERROR: " + e);
                e.printStackTrace(System.out);
        }
            System.out.println("Serveur Convert fermé ...");
        
    }
    
}
