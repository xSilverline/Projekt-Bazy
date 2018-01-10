import org.junit.Before;
import Server.*;
import org.junit.Test;

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
    public void clientShouldConnectToDB(){
        server.register("Jan", "Kowalski", "Pracownik", "2000", "jankowalski97", "grazyna");
        server.login("jankowalski97", "grazyna");


    }


}
