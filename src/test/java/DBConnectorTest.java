import org.junit.Before;
import Server.*;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

/**
 * Created by Mateusz on 2018-01-10.
 */
public class DBConnectorTest {
    private static Server server;

    @Before
    public void setUpServer(){
        server = new Server();

    }

    @Test
    public void shouldCreateAccountLoginAndDelete(){
        assertEquals(1, server.register("Jan", "Kowalski", "Pracownik", "2000", "jankowalski97", "grazyna"));
        assertEquals(1, server.login("jankowalski97", "grazyna"));
        server.logout();
        assertEquals(1, server.login("dbmeta", "doktorsyga"));
        assertEquals(1, server.removeUser("jankowalski97"));
        server.logout();
    }

    @Test
    public void shouldCreateProjectAndDelete(){
        server.login("dbmeta", "doktorsyga");
        assertEquals(1, server.addProject("Test", "21-08-1997", "24-01-2018", "3", "1", "Mati", "6502", "86", "1000", "2000", "Oczekujacy"));
        assertEquals(1, server.removeProject("1"));
        server.logout();
    }






}
