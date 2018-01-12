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
            ex.printStackTrace();
        }
    }

    private void doCall(String query) throws SQLException {
        CallableStatement statement = connection.prepareCall(query);
        statement.execute();
        statement.close();

    }

    protected void connect(){
        try {
            if(connection != null){
                connection.close();
                //log out from the default account
            }
            connection = DriverManager.getConnection(addr, loginInfo);
        } catch (SQLException ex) {
            System.out.println("DBConnector.connect: Could not connect.");
            ex.printStackTrace();
        }
    }

    protected void disconnect(){
        try {
            connection.close();
            System.out.println("DBConnector.disconnect: Disconnected.");
        } catch (SQLException ex) {
            System.out.println("DBConnector.disconnect: Could not disconnect.");
            ex.printStackTrace();
        }
    }

    protected void login(String login, String password){
        //We remember username and password and save it in private variables for later use
        loginInfo = new Properties();
        loginInfo.put("user", login);
        loginInfo.put("password", password);
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

    protected boolean checkValidId(String id){
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(id);
        int counter = 0;
        while (m.find()){
            counter++;
        }
        if(counter == 1){
            System.out.println("DBConnector.checkValidId: Id is correct.");
            return true;
        }
        System.out.println("DBConnector.checkValidId: Id is incorrect.");
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
            }else if (connection.isClosed()){
                connection = DriverManager.getConnection(addr, "dbmeta", "doktorsyga");
            }
            s = connection.createStatement();
            int counter = 0;
            ResultSet rs = s.executeQuery(query);
            while(rs.next()){
                counter++;
            }

            if(counter == 1){
                System.out.println("DBConnector.checkIfUserExists: User exists.");
                return 1;
            }
            else {
                System.out.println("DBConnector.checkIfUserExists: User doesn't exist.");
                return 0;
            }
        } catch (SQLException ex) {
            System.out.println("DBConnector.checkIfUserExists: Exception has occurred.");
            ex.printStackTrace();
            return -1;
        }
        finally{
            if(s != null){
                try {
                    s.close();
                } catch (SQLException e) {
                    System.out.println("DBConnector.checkIfUserExists: Exception has occurred.");
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     *
     * @param login
     * @return 1 if user exists, 0 if user doesn't exist, -1 if exception has occurred.
     */
    protected int checkIfLoginExists(String login) {
        Statement s = null;
        String query = "SELECT kadra.login FROM kadra WHERE kadra.login = '"+login+"';";

        //We are connecting with default account to check if desired user exists. If we are already logged in,
        //we don't use default account, BUT we have to remember that used account has to have permission to view
        //the table with user accounts!!!
        try {
            s = connection.createStatement();
            int counter = 0;
            ResultSet rs = s.executeQuery(query);
            while(rs.next()){
                //Only one row is returned... supposedly
                counter++;
            }

            if(counter == 1){
                System.out.println("DBConnector.checkIfLoginExists: User exists.");
                return 1;
            }
            else {
                System.out.println("DBConnector.checkIfLoginExists: User doesn't exist.");
                return 0;
            }
        } catch (SQLException ex) {
            System.out.println("DBConnector.checkIfLoginExists: Exception has occurred.");
            ex.printStackTrace();
            return -1;
        }
        finally{
            if(s != null){
                try {
                    s.close();
                } catch (SQLException e) {
                    System.out.println("DBConnector.checkIfLoginExists: Exception has occurred.");
                    e.printStackTrace();
                }
            }

        }
    }

    /**
     *
     * @param id of project
     * @return 1 if project exists, 0 if does not, -1 if exception
     */
    protected int checkIfProjectExists(String id){
        Statement s = null;
        String query = "SELECT projekty.id FROM projekty WHERE projekty.id = "+id+";";
        try {
            s = connection.createStatement();
            int counter = 0;
            ResultSet rs = s.executeQuery(query);
            while(rs.next()){
                //Only one row is returned... supposedly
                counter++;
            }

            if(counter == 1){
                System.out.println("DBConnector.checkIfProjectExists: Project exists.");
                return 1;
            }
            else {
                System.out.println("DBConnector.checkIfProjectExists: Project doesn't exist.");
                return 0;
            }
        } catch (SQLException ex) {
            System.out.println("DBConnector.checkIfProjectExists: Exception has occurred.");
            ex.printStackTrace();
            return -1;
        }
        finally{
            if(s != null){
                try {
                    s.close();
                } catch (SQLException e) {
                    System.out.println("DBConnector.checkIfProjectExists: Exception has occurred.");
                    e.printStackTrace();
                }
            }

        }
    }



    /**
     *
     * @param imie
     * @param nazwisko
     * @param stanowisko
     * @param pensja
     * @param login
     * @param password
     * @return 1 if user created, 0 if exception
     */

    //==================================================================================================================
    //
    //ADDING REMOVING THINGS
    //
    //==================================================================================================================

    protected int addUser(String imie, String nazwisko, String stanowisko, String pensja, String login, String password){
        String query = "CALL createUser('"+imie+"', '"+nazwisko+"', '"+stanowisko+"', "+pensja+", '"+login+"', '"+password+"');";
        try {
            doCall(query);
            System.out.println("DBConnector.addUser: User added.");
            return 1;
        } catch (SQLException e) {
            System.out.println("DBConnector.addUser: Exception has occurred. Could not add user.");
            e.printStackTrace();
            return 0;
        }
    }

    /**
     *
     * @param login
     * @return 1 if user removed, 0 if exception
     */
    protected int removeUser(String login) {
        String query = "CALL removeUser('"+login+"');";
        try{
            doCall(query);
            System.out.println("DBConnector.removeUser: User removed.");
            return 1;
        } catch (SQLException e) {
            System.out.println("DBConnector.removeUser: Exception has occurred. Could not remove user.");
            e.printStackTrace();
            return 0;
        }
    }

    /**
     *
     * @param nazwa
     * @param data_rozpoczecia
     * @param termin
     * @param ilosc
     * @param nadzorca
     * @param zamawiajacy
     * @param wynagrodzenie
     * @param budzet
     * @param koszt_materialow
     * @param koszt_calkowity
     * @param status
     * @return 1 if project added, 0 if exception
     */
    protected int addProject(String nazwa, String data_rozpoczecia, String termin, String ilosc, String nadzorca, String zamawiajacy, String wynagrodzenie, String budzet, String koszt_materialow, String koszt_calkowity, String status){
        String query = "CALL addProject('"+nazwa+"', '"+data_rozpoczecia+"', '"+termin+"', "+ilosc+", "+nadzorca+", '"+zamawiajacy+"', "+wynagrodzenie+", "+budzet+", "+koszt_materialow+", "+koszt_calkowity+", "+status+");";
        try{
            doCall(query);
            System.out.println("DBConnector.addProject: Project added.");
            return 1;
        } catch (SQLException e) {
            System.out.println("DBConnector.addProject: Exception has occurred. Could not add project.");
            e.printStackTrace();
            return 0;
        }
    }

    /**
     *
     * @param id
     * @return 1 if project removed, 0 if exception
     */
    protected int removeProject(String id){
        String query = "CALL removeProject('"+id+"');";
        try{
            doCall(query);
            System.out.println("DBConnector.removeProject: Project removed.");
            return 1;
        } catch (SQLException e) {
            System.out.println("DBConnector.removeProject: Exception has occurred. Could not remove project.");
            e.printStackTrace();
            return 0;
        }
    }

    protected int addRequired(String projekt, String material, String ilosc_potrzebna, String ilosc_zgromadzona, String ilosc_brakujaca, String wartosc){
        String query = "CALL addRequired("+projekt+", '"+material+"', "+ilosc_potrzebna+", "+ilosc_zgromadzona+", "+ilosc_brakujaca+", "+wartosc+");";
        try{
            doCall(query);
            System.out.println("DBConnector.addRequired: Required added.");
            return 1;
        } catch (SQLException e) {
            System.out.println("DBConnector.addRequired: Exception has occurred. Could not add required.");
            e.printStackTrace();
            return 0;
        }
    }

    protected int addOrder(String material, String ilosc_zamowiona, String wartosc, String data_zamowienia){
        String query = "CALL addOrder('"+material+"', "+ilosc_zamowiona+", "+wartosc+", '"+data_zamowienia+"');";
        try{
            doCall(query);
            System.out.println("DBConnector.addOrder: Order added.");
            return 1;
        } catch (SQLException e) {
            System.out.println("DBConnector.addOrder: Exception has occurred. Could not add order.");
            e.printStackTrace();
            return 0;
        }
    }

    //==================================================================================================================
    //
    //SHOWING THINGS
    //
    //==================================================================================================================


    protected ResultSet getProjects(){
        Statement s = null;
        String query = "SELECT projekty.id, projekty.Nazwa, projekty.Data_rozpoczecia, projekty.Termin, projekty.Ilosc, projekty.Nadzorca, projekty.Zamawiajacy, projekty.Wynagrodzenie, projekty.Budzet, projekty.Koszt_materialow, projekty.Koszt_calkowity, projekty.Status FROM projekty;";
        try{
            s = connection.createStatement();
            ResultSet rs = s.executeQuery(query);
            System.out.println("DBConnector.getProjects: ResultSet got.");
            return rs;
        } catch (SQLException e) {
            System.out.println("DBConnector.getProjects: Could not receive ResultSet");
            e.printStackTrace();
            return null;
        } /*finally{
            try {
                if (s != null) {
                    s.close();
                }
            } catch (SQLException e) {
                System.out.println("DBConnector.getProjects: Could not close statement.");
                e.printStackTrace();
            }
        }*/
        //^^^Because prevents from operations on ResultSet
    }




}
