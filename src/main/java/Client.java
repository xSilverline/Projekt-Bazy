public class Client
{

    Client() throws Exception
    {
        setLoginWindow();
    }

    private void setLoginWindow()
    {
        new LoginScreenFrame(this);
    }

    void setMenuWindow()
    {
        new MenuWindowFrame(this);
    }


    public static void main(String[] args) throws Exception
    {
       Client client = new Client();

    }
}
