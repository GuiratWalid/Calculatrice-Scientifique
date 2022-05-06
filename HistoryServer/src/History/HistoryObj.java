package History;

import HistoryApp.HistoryPOA;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import org.omg.CORBA.ORB;

public class HistoryObj extends HistoryPOA{
    
    private ORB orb;
    
    public void setORB(ORB orb_val) {
        orb = orb_val; 
    }
    
    private Connection connection(){
        Connection cn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/history";
            String user = "root";
            String password = "";
            
            cn = (Connection) DriverManager.getConnection(url, user, password);
            System.out.println("connexion etablie");

        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return cn;
    }

    @Override
    public void addCalculHistory(String cal) {
        try{
            Connection cn = connection();
            String query = "INSERT INTO calculator(data) VALUES('"+cal+"')";
            Statement st = cn.createStatement();
            st.execute(query);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public String[] getCalculHistory() {
        String list [] = null;
        try{
            Connection cn = connection();
            String query = "SELECT count(*) FROM calculator";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);
            if(rs.next())
                list = new String[rs.getInt(1)];
            query = "SELECT * FROM calculator ORDER BY add_date desc";
            st = cn.createStatement();
            rs = st.executeQuery(query);
            int i = 0;
            while(rs.next()){
                list[i] = rs.getString(1);
                i+=1;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void deleteCalculHistory() {
        try{
            Connection cn = connection();
            String query = "DELETE FROM calculator";
            Statement st = cn.createStatement();
            st.execute(query);
        }catch(Exception e){
            e.printStackTrace();
        }
    }   

    @Override
    public void addConvertHistory(String cal) {
        try{
            Connection cn = connection();
            String query = "INSERT INTO converter(data) VALUES('"+cal+"')";
            Statement st = cn.createStatement();
            st.execute(query);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public String[] getConvertHistory() {
        String list [] = null;
        try{
            Connection cn = connection();
            String query = "SELECT count(*) FROM converter";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);
            if(rs.next())
                list = new String[rs.getInt(1)];
            query = "SELECT * FROM converter ORDER BY add_date desc";
            st = cn.createStatement();
            rs = st.executeQuery(query);
            int i = 0;
            while(rs.next()){
                list[i] = rs.getString(1);
                i+=1;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void deleteConvertHistory() {
        try{
            Connection cn = connection();
            String query = "DELETE FROM converter";
            Statement st = cn.createStatement();
            st.execute(query);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void shutdown() {
        orb.shutdown(false);
    }
    
}
