public class Client
{
    int version;

    private Client() throws Exception
    {
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

    void setMenuWindow()
    {
        new MenuWindowFrame(this,0);
    }

    void setStorageWindow()
    {
        new StorageWindowFrame(this);
    }

    void setProjectsWindow()
    {
        new ProjectsWindowFrame(this);
    }

    void setCrewWindow()
    {
        new CrewWindowFrame(this);
    }

    void setRequestsWindow()
    {
        new RequestsWindowFrame(this);
    }

    void setOrdersWindow()
    {
        new OrdersWindowFrame(this);
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
