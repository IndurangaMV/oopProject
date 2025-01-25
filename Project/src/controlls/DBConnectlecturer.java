package controlls;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnectlecturer extends DBConnect{
    public boolean login(String username,String password){
        Connection connection= sqlConnector();
        boolean rtnStt=false;
        try{
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery("SELECT * FROM lecturer");
            while(rs.next()){
                if(rs.getString("lecturer_id").equals(username) && rs.getString("password").equals(password)){
                    rtnStt= true;
                    break;
                }else{
                    rtnStt= false;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return rtnStt;
    }
}

