import java.awt.event.ActionEvent;

public class StorageWindowFrame extends NewWindowFrame
{
    private Client client;

    StorageWindowFrame(Client client)
    {
        this.client=client;
        buildFrame();
        makeGui();
    }
    @Override
    void makeGui()
    {

    }

    @Override
    void closeWindow()
    {

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

    }
}
