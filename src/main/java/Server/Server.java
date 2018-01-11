package Server;

/**
 * Created by Mateusz on 2018-01-08.
 */

/**
 * A class communicating with the database through JDBC lmao not
 */
public class Server {
    DBConnector dbConnector;

    public Server(){
        this.dbConnector = new DBConnector("jdbc:mysql://localhost:3306/projekt-bazy");
    }

    /**
     * When user tries to log in, we check if an account exists. If it does, we let user log in and then we will use its
     * login and password to communicate with the database.
     * @param login
     * @param password
     * @return returns 1 if logged in successfully, 0 if account doesnt exists, -1 if exception has occurred,
     * -2 invalid input
     */
    public int login(String login, String password){
        if(!dbConnector.checkValidLogin(login, password)){
            System.out.println("Server.login: Invalid input.");
            return -2;
        }
        int exists = dbConnector.checkIfUserExists(login, password);
        //If user exists...
        if(exists == 1){
            dbConnector.login(login, password);
            System.out.println("Server.login: Logged in.");
            dbConnector.connect();
            return 1;
        }
        else if(exists == 0){
            System.out.println("Server.login: User doesn't exist.");
            return 0;
        }
        System.out.println("Server.login: Exception has occurred.");
        return -1;
    }

    public void logout(){
        dbConnector.disconnect();
    }

    /**
     *
     * @param login
     * @param password
     * @return 1 if user has been successfully created, 0 if already exists, -1 if something's gone wrong(exception),
     * -2 if invalid input
     */
    public int register(String imie, String nazwisko, String stanowisko, String pensja, String login, String password){
        if(!dbConnector.checkValidLogin(login, password)){
            System.out.println("Server.register: Invalid input.");
            return -2;
        }
        int exists = dbConnector.checkIfUserExists(login, password);
        //If user doesn't exist...
        if(exists == 0){
            int add = dbConnector.addUser(imie, nazwisko, stanowisko, pensja, login, password);
            if(add == 1){
                System.out.println("Server.register: User created.");
                return 1;
            }
            else if (add == 0){
                System.out.println("Server.register: Couldn't create user.");
                return -1;
            }
        }
        else if(exists == 1){
            System.out.println("Server.register: User already exists.");
            return 0;
        }
        System.out.println("Server.register: Exception has occurred.");
        return -1;
    }


    public int removeUser(String login) {
        if(!dbConnector.checkValidLogin(login, login)){
            System.out.println("Server.removeUser: Invalid input.");
            return -2;
        }
        int exists = dbConnector.checkIfLoginExists(login);
        if(exists == 1){
            int delete = dbConnector.removeUser(login);
            if(delete == 1){
                System.out.println("Server.removeUser: User deleted.");
                return 1;
            }
            else if (delete == 0){
                System.out.println("Server.removeUser: Couldn't delete user.");
                return -1;
            }
        }
        else if(exists == 0){
            System.out.println("Server.removeUser: User doesn't exist.");
            return 0;
        }
        System.out.println("Server.removeUser: Exception has occurred.");
        return -1;
    }

    public int addProject(){
        //TODO Implement
    }

    public int removeProject(){
        //TODO Implement
    }
}