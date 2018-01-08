package Server;

import java.util.Properties;

/**
 * Created by Mateusz on 2018-01-08.
 */
public class DBConnector {
    private String addr;
    private Properties loginInfo;

    DBConnector(String addr) {
        this.addr = addr;
    }

    private void connect(){
        //TODO Connecting to database, DriverManager.getConnection(addr, loginInfo);
    }

    protected boolean checkIfUserExists(String login, String password){
        //TODO Checking if user exists
    }

    protected void login(String login, String password){
        loginInfo = new Properties();
        loginInfo.put("user", login);
        loginInfo.put("password", password);
    }

    protected void addUser(String login, String password){
        //TODO Create user in database
    }
}
