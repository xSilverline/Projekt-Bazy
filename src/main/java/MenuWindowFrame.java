import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionEvent;

public class MenuWindowFrame extends NewWindowFrame
{
    private Client client;
    private int version;
    private JButton exitButton;
    private MenuButton storageButton;
    private MenuButton projectsButton;
    private MenuButton crewButton;
    private MenuButton neededButton;
    private MenuButton orderedButton;
    private MenuButton adminButton;
    private JButton infoButton;

    MenuWindowFrame(Client client,int version)
    {
        this.client = client;
        this.version = version;
        buildFrame();
        makeGui();
    }
    @Override
    void makeGui()
    {

        exitButton = new MenuButton("WYJŚĆIE");
        exitButton.setBounds(1066,594,300,140);
        exitButton.addActionListener(this);

        storageButton = new MenuButton("MAGAZYN");
        projectsButton = new MenuButton("PROJEKTY");
        crewButton = new MenuButton("KADRA");
        neededButton = new MenuButton("POTRZEBNE");
        orderedButton = new MenuButton("ZAMÓWIONE");
        adminButton = new MenuButton("ZARZĄDZANIE");
        infoButton = new JButton("INFO");

        storageButton.setBounds(0,34,300,140);
        projectsButton.setBounds(0,174,300,140);
        crewButton.setBounds(0,314,300,140);
        neededButton.setBounds(0,454,300,140);
        orderedButton.setBounds(0,594,300,140);
        infoButton.setBounds(1250,34,100,30);
        adminButton.setBounds(1066,454,300,140);

        storageButton.addActionListener(this);
        projectsButton.addActionListener(this);
        crewButton.addActionListener(this);
        neededButton.addActionListener(this);
        orderedButton.addActionListener(this);
        infoButton.addActionListener(this);
        adminButton.addActionListener(this);

        add(exitButton);
        add(storageButton);
        add(projectsButton);
        add(crewButton);
        add(neededButton);
        add(orderedButton);
        add(infoButton);
        if(version == 0)
        {
            add(adminButton);
        }
    }

    @Override
    void closeWindow()
    {

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        if(source==exitButton)
        {
            int result = JOptionPane.showConfirmDialog(null, "CZY CHCESZ WYJŚĆ Z PROGRAMU?", "Exit", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION)
            {
                System.exit(0);
            }
        }
        else if(source == storageButton)
        {
            client.setStorageWindow();
            dispose();

        }
        else if(source == projectsButton)
        {
            client.setProjectsWindow();
            dispose();

        }
        else if(source == crewButton)
        {
            client.setCrewWindow();
            dispose();

        }
        else if(source == neededButton)
        {
            client.setRequestsWindow();
            dispose();

        }
        else if(source == orderedButton)
        {
            client.setOrdersWindow();
            dispose();

        }
        else if(source == adminButton)
        {
            client.setAdminWindow();
            dispose();
        }
        else if(source == infoButton)
        {
            JOptionPane.showMessageDialog(null,"INFO","INFO",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e)
    {

    }
}
