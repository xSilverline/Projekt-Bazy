package Server;

/**
 * Created by Mateusz on 2018-01-08.
 */

import java.sql.ResultSet;

/**
 * A class communicating with the database through JDBC lmao not
 */
public class Server {
    private DBConnector dbConnector;

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
        System.out.println("Server.logout: Logged out.");
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
     * @return 1 if project added, 0 if exception
     */
    public int addProject(String nazwa, String data_rozpoczecia, String termin, String ilosc, String nadzorca, String zamawiajacy, String wynagrodzenie, String budzet, String koszt_materialow, String koszt_calkowity){
        int add = dbConnector.addProject(nazwa, data_rozpoczecia, termin, ilosc, nadzorca, zamawiajacy, wynagrodzenie, budzet, koszt_materialow, koszt_calkowity);
        if(add == 1){
            System.out.println("Server.addProject: Project added.");
            return 1;
        }
        System.out.println("Server.addProject: Could not add project.");
        return 0;
    }

    /**
     *
     * @param id of project
     * @return 1 if project deleted, 0 if does not exist, -1 if exception, -2 if invalid input
     */
    public int removeProject(String id){
        if(!dbConnector.checkValidId(id)){
            System.out.println("Server.removeProject: Invalid input.");
            return -2;
        }
        int exists = dbConnector.checkIfProjectExists(id);
        if(exists == 1){
            int delete = dbConnector.removeProject(id);
            if(delete == 1){
                System.out.println("Server.removeProject: Project deleted.");
                return 1;
            }
            else if (delete == 0){
                System.out.println("Server.removeProject: Could not delete project.");
                return -1;
            }
        }
        else if(exists == 0){
            System.out.println("Server.removeProject: Project does not exist.");
            return 0;
        }
        System.out.println("Server.removeProject: Exception has occurred.");
        return -1;
    }

    /**
     *
     * @param projekt
     * @param material
     * @param ilosc_potrzebna
     * @param ilosc_zgromadzona
     * @param ilosc_brakujaca
     * @param wartosc
     * @return 1 if required added, 0 if does not exist, -1 if exception
     */
    public int addRequired(String projekt, String material, String ilosc_potrzebna, String ilosc_zgromadzona, String ilosc_brakujaca, String wartosc){
        int exists = dbConnector.checkIfProjectExists(projekt);
        if(exists == 0){
            System.out.println("Server.addRequired: Project does not exist.");
            return 0;
        }
        if(exists == -1){
            System.out.println("Server.addRequired: Exception has occurred.");
            return -1;
        }
        int add = dbConnector.addRequired(projekt, material, ilosc_potrzebna, ilosc_zgromadzona, ilosc_brakujaca, wartosc);
        if(add == 0){
            System.out.println("Server.addRequired: Could not add required.");
            return -1;
        }
        System.out.println("Server.addRequired: Required added.");
        return 1;

    }

    /**
     *
     * @param material
     * @param ilosc_zamowiona
     * @param wartosc
     * @param data_zamowienia
     * @return 1 if order added, 0 if exception
     */
    public int addOrder(String material, String ilosc_zamowiona, String wartosc, String data_zamowienia){
        int add = dbConnector.addOrder(material, ilosc_zamowiona, wartosc, data_zamowienia);
        if(add == 0){
            System.out.println("Server.addOrder: Could not add order");
            return 0;
        }
        System.out.println("Server.addOrder: Order added.");
        return 1;
    }

    public int addStock(String material, String ilosc, String wartosc_sztuki){
        if(dbConnector.addStock(material, ilosc, wartosc_sztuki) ==1){
            System.out.println("Server.addStock: Stock entry added.");
            return 1;
        }
        System.out.println("Server.addStock: Could not add stock entry.");
        return 0;
    }

    public int removeStock(String material){
        if(dbConnector.removeStock(material) ==1){
            System.out.println("Server.removeStock: Stock entry removed.");
            return 1;
        }
        System.out.println("Server.removeStock: Could not remove stock entry.");
        return 0;
    }

    public int setStock(String material, String ilosc){
        if(dbConnector.setStock(material, ilosc) ==1){
            System.out.println("Server.setStock: Stock entry set.");
            return 1;
        }
        System.out.println("Server.setStock: Could not set stock entry.");
        return 0;
    }

    public ResultSet showProjects(){
        return dbConnector.getProjects();
    }

    public ResultSet showEmployees(){
        return dbConnector.getEmployees();
    }

    public ResultSet showStock(){
        return dbConnector.getStock();
    }

    public ResultSet showRequired(){
        return dbConnector.getRequired();
    }

    public ResultSet showOrders(){
        return dbConnector.getOrders();
    }

    public int setProjectStatus(String id, String status){
        if(dbConnector.setProjectStatus(id, status) == 1){
            System.out.println("Server.setProjectStatus: Project status set.");
            return 1;
        }
        System.out.println("Server.setProjectStatus: Could not set project status");
        return 0;
    }

    public int setEmployeeData(String id, String pensja, String stanowisko){
        if(dbConnector.setEmployeeData(id, pensja, stanowisko) == 1){
            System.out.println("Server.setEmployeeData: Employee data set.");
            return 1;
        }
        System.out.println("Server.setEmployeeData: Could not set employee data.");
        return 0;
    }

    public int setOrderStatus(String id, String status){
        if(dbConnector.setOrderStatus(id, status) == 1){
            System.out.println("Server.setOrderStatus: Status set.");
            return 1;
        }
        System.out.println("Server.setOrderStatus: Could not set order status.");
        return 0;
    }

    public String getStanowisko(String login){
        System.out.println("Server.getStanowisko: Stanowisko got.");
        return dbConnector.getStanowisko(login);

    }

    public int setRequiredAmount(String id, String amount){
        if(dbConnector.setRequiredAmount(id, amount) == 1){
            System.out.println("Server.setRequiredAmount: Amount set.");
            return 1;
        }
        System.out.println("Server.setRequiredAmount: Could not set amount.");
        return 0;
    }

}