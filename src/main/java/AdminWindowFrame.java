import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionEvent;

public class AdminWindowFrame extends NewWindowFrame
{
    private Client client;

    AdminWindowFrame(Client client)
    {
        this.client=client;
        buildFrame();
        makeGui();
    }
    void makeGui()
    {

    }

    void closeWindow()
    {

    }

    public void actionPerformed(ActionEvent e)
    {

    }

    @Override
    public void valueChanged(ListSelectionEvent e)
    {

    }
}
