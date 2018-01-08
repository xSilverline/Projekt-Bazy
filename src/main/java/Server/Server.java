package Server;

/**
 * Created by Mateusz on 2018-01-08.
 */

/**
 * A class communicating with the database through JDBC
 */
public class Server {
    DBConnector dbConnector;

    public Server(){
        this.dbConnector = new DBConnector("jdbc:mysql//localhost:3306/Projekt-Bazy");
    }

    /**
     * When user tries to log in, we check if an account exists. If it does, we let user log in and then we will use its
     * login and password to communicate with the database.
     * @param login
     * @param password
     * @return returns 1 if logged in successfully, 0 if invalid username/password.
     */
    public int login(String login, String password){
        if(dbConnector.checkIfUserExists(login, password)){
            dbConnector.login(login, password);
            return 1;
        }
        else return 0;
    }

    public int register(String login, String password){
        if(!dbConnector.checkIfUserExists(login, password)){
            dbConnector.addUser(login, password);
            return 1;
        }
        return 0;
    }


}