import javax.swing.*;
import java.awt.event.ActionEvent;

public class StorageWindowFrame extends NewWindowFrame
{
    private Client client;
    JButton returnButton;
    MenuButton addButton;

    StorageWindowFrame(Client client)
    {
        this.client=client;
        buildFrame();
        makeGui();
    }
    @Override
    void makeGui()
    {

        returnButton = new JButton("POWRÓT");
        returnButton.setBounds(1256,728,100,30);
        add(returnButton);
        returnButton.addActionListener(this);

        addButton = new MenuButton("DODAJ");
        addButton.setBounds(0,100,200,50);
        add(addButton);
        addButton.addActionListener(this);
    }

    @Override
    void closeWindow()
    {

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        if(source == returnButton)
        {
            client.setMenuWindow();
            dispose();
        }
        else if(source == addButton)
        {
            client.setAddStorageDialog();

        }

    }
}
