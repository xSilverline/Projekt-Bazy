package Server;

import java.sql.*;
import java.util.Properties;

/**
 * Created by Mateusz on 2018-01-08.
 */
public class DBConnector {
    private String addr;
    private Properties loginInfo;
    private Connection connection = null;

    DBConnector(String addr) {
        this.addr = addr;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            System.out.println("Could not set the drivers.");
        }
    }

    private void connect(){
        try {
            if(connection != null){
                connection.close();
                //log out from the default account
            }
            connection = DriverManager.getConnection(addr, loginInfo);
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    private void disconnect(){
        try {
            connection.close();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    /**
     *
     * @param login
     * @param password
     * @return 1 if user exists, 0 if it doesn't, -1 if exception has occurred.
     */
    protected int checkIfUserExists(String login, String password) {
        Statement s = null;
        String query = "SELECT kadra.login, kadra.haslo FROM kadra WHERE kadra.login = "+login+" AND kadra.haslo = "+password+";";

        //We are connecting with default account to check if desired user exists. If we are already logged in,
        //we don't use default account, BUT we have to remember that used account has to have permission to view
        //the table with user accounts!!!
        try {
            if(connection.isClosed()){
                connection = DriverManager.getConnection(addr, "dbmeta", "doktorsyga");
            }
            s = connection.createStatement();
            String tmpLogin = null;
            String tmpPassword = null;
            ResultSet rs = s.executeQuery(query);
            while(rs.next()){
                //Only one row is returned... supposedly
                tmpLogin = rs.getString("login");
                tmpPassword = rs.getString("haslo");
            }

            if(login.equals(tmpLogin) && password.equals(tmpPassword)) return 1;
            else return 0;


        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        finally{
            if(s != null){
                try {
                    s.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return -1;
    }

    protected void login(String login, String password){
        //We remember username and password and save it in private variables for later use
        loginInfo = new Properties();
        loginInfo.put("user", login);
        loginInfo.put("password", password);
        connect();
    }

    protected void addUser(String login, String password){
        //TODO Create user in database
    }
}
