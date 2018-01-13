import Server.Server;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Client
{
    int version=1;
    Server server;

   StorageWindowFrame storageWindowFrame;
   CrewWindowFrame crewWindowFrame;
   OrdersWindowFrame ordersWindowFrame;
   ProjectsWindowFrame projectsWindowFrame;
   RequestsWindowFrame requestsWindowFrame;

    private Client()
    {
        server = new Server();
        setLoginWindow();

    }

    void setVersion(int x)
    {
        this.version=x;
    }

    private void setLoginWindow()
    {
        new LoginScreenFrame(this);
    }

    void setAddStorageDialog()
    {
        new AddStorageDialog(this);
    }

    void setEditStorageDialog()
    {
        new EditStorageDialog(this);
    }

    void setAddProjectDialog()
    {
        new AddProjectDialog(this);
    }

    void setEditProjectDialog()
    {
        new EditProjectDialog(this);
    }

    void setAddRequestDialog()
    {
        new AddRequestDialog(this);
    }

    void setEditRequestDialog()
    {
        new EditRequestDialog(this);
    }

    void setAddCrewDialog()
    {
        new AddCrewDialog(this);
    }

    void setEditCrewDialog()
    {
        new EditCrewDialog(this);
    }

    void setAddOrdersDialog()
    {
        new AddOrderDialog(this);
    }

    void setEditOrdersDialog()
    {
        new EditOrderDialog(this);
    }

    void setMenuWindow()
    {
        new MenuWindowFrame(this,version);
    }

    void setStorageWindow() throws SQLException
    {
        storageWindowFrame=new StorageWindowFrame(this,version,server.showStock());
    }

    void setProjectsWindow() throws SQLException
    {
        projectsWindowFrame=new ProjectsWindowFrame(this,version,server.showProjects());
    }

    void setCrewWindow() throws SQLException
    {
        crewWindowFrame=new CrewWindowFrame(this,version,server.showEmployees());
    }

    void setRequestsWindow() throws SQLException
    {
        requestsWindowFrame=new RequestsWindowFrame(this,version,server.showRequired());
    }

    void setOrdersWindow() throws SQLException
    {
        ordersWindowFrame=new OrdersWindowFrame(this,version,server.showOrders());
    }

    void setAdminWindow()
    {
        new AdminWindowFrame(this);
    }


    public static void main(String[] args) throws Exception
    {
      new Client();

    }
}
