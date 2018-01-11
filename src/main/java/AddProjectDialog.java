import java.awt.event.ActionEvent;

public class AddProjectDialog extends NewWindowDialog
{
    private Client client;

    AddProjectDialog(Client client)
    {
        this.client = client;
        buildDialog();
        makeGui();
    }

    @Override
    void makeGui()
    {


    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

    }
}
