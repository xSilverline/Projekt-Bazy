package Server;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            System.out.println("DBConnector.DBConnector: Could not set the drivers.");
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
            ex.printStackTrace();
        }
    }

    private void disconnect(){
        try {
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    protected boolean checkValidLogin(String login, String password){
        //Login/password only letters and numbers, max 20 characters
        Pattern p = Pattern.compile("[a-zA-Z0-9]{1,20}");
        Matcher m = p.matcher(login);
        int counter = 0;
        while(m.find()){
            counter++;
        }
        if(counter == 1){
            System.out.println("DBConnector.checkValidLogin: Login is correct.");
        }
        else {
            System.out.println("DBConnector.checkValidLogin: Login is incorrect.");
            return false;
        }
        counter = 0;
        m = p.matcher(password);
        while(m.find()){
            counter++;
        }
        if(counter == 1){
            System.out.println("DBConnector.checkValidLogin: Password is correct.");
            return true;
        }
        System.out.println("DBConnector.checkValidLogin: Password is incorrect.");
        return false;
    }

    /**
     *
     * @param login
     * @param password
     * @return 1 if user exists, 0 if it doesn't, -1 if exception has occurred
     */
    protected int checkIfUserExists(String login, String password) {
        Statement s = null;
        String query = "SELECT kadra.login, kadra.haslo FROM kadra WHERE kadra.login = '"+login+"' AND kadra.haslo = '"+password+"';";

        //We are connecting with default account to check if desired user exists. If we are already logged in,
        //we don't use default account, BUT we have to remember that used account has to have permission to view
        //the table with user accounts!!!
        try {
            if(connection == null){
                connection = DriverManager.getConnection(addr, "dbmeta", "doktorsyga");
            }
            s = connection.createStatement();
            String tmpLogin = null;
            String tmpPassword = null;
            ResultSet rs = s.executeQuery(query);
            while(rs.next()){
                //Only one row is returned... supposedly
                tmpLogin = rs.getString("Login");
                tmpPassword = rs.getString("Haslo");
            }

            if(login.equals(tmpLogin) && password.equals(tmpPassword)){
                System.out.println("DBConnector.checkIfUserExists: User exists.");
                return 1;
            }
            else {
                System.out.println("DBConnector.checkIfUserExists: User doesn't exist.");
                return 0;
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
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
    }

    protected void login(String login, String password){
        //We remember username and password and save it in private variables for later use
        loginInfo = new Properties();
        loginInfo.put("user", login);
        loginInfo.put("password", password);
        connect();
    }

    protected int addUser(String imie, String nazwisko, String stanowisko, String pensja, String login, String password){
        String query = "CALL createUser('"+imie+"', '"+nazwisko+"', '"+stanowisko+"', "+pensja+", '"+login+"', '"+password+"');";
        try {
            CallableStatement statement = connection.prepareCall(query);
            statement.execute();
            System.out.println("DBConnector.addUser: User created.");
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }


    }
}