public class Client
{

    Client()
    {
        setLoginWindow();
    }

    private void setLoginWindow()
    {
        new LoginScreenFrame(this);
    }


    public static void main(String[] args) throws Exception
    {
       Client client = new Client();
    }
}
