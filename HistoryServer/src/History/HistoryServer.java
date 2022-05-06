/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package History;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import HistoryApp.*;

public class HistoryServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try{
                ORB orb = ORB.init(args, null); 
                POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
                rootpoa.the_POAManager().activate(); 
                HistoryObj hisobj = new HistoryObj(); 
                hisobj.setORB(orb); 
                org.omg.CORBA.Object ref = rootpoa.servant_to_reference(hisobj);
                History href = HistoryHelper.narrow(ref);
                org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
                NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
                NameComponent path[] = ncRef.to_name( "GHI" );
                ncRef.rebind(path, href);
                System.out.println("Serveur History en attend ...");
                for (;;){
                    orb.run();
                }
            } 
        catch (Exception e) {
                System.err.println("ERROR: " + e);
                e.printStackTrace(System.out);
        }
            System.out.println("Serveur History fermé ...");
        
    }
    
}
