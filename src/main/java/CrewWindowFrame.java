import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrewWindowFrame extends NewWindowFrame
{
    private Client client;
    private int version;
    private JButton returnButton;
    private MenuButton addButton;
    private MenuButton editButton;
    private MenuButton deleteButton;
    private ResultSet crewResult;
    private  JList crewList;

    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel surLabel;
    private JLabel posLabel;
    private JLabel payLabel;

    private DefaultListModel<String> list = new DefaultListModel<>();

    CrewWindowFrame(Client client,int version,ResultSet crewResult) throws SQLException
    {
        this.version=version;
        this.client=client;
        this.crewResult = crewResult;

        buildFrame();
        makeGui();
        getList();
    }


    void makeGui() throws SQLException
    {
        JLabel stanLabel = new JLabel("KADRA");
        stanLabel.setFont(stanLabel.getFont().deriveFont(40f));
        stanLabel.setBounds(383,10,600,50);
        add(stanLabel);
        stanLabel.setHorizontalAlignment(SwingConstants.CENTER);

        crewList = new JList(list);
        JScrollPane scrollList = new JScrollPane(crewList);
        scrollList.setBounds(100,150,300,400);
        add(scrollList);
        crewList.addListSelectionListener(this);

        editButton = new MenuButton("EDYTUJ");
        editButton.setBounds(150,620,200,50);

        editButton.addActionListener(this);
        editButton.setEnabled(false);

        deleteButton = new MenuButton("USUŃ");
        deleteButton.setBounds(150,90,200,50);

        deleteButton.addActionListener(this);
        deleteButton.setEnabled(false);

        returnButton = new MenuButton("POWRÓT");
        returnButton.setBounds(1156,708,200,50);
        add(returnButton);
        returnButton.addActionListener(this);

        addButton = new MenuButton("DODAJ");
        addButton.setBounds(150,560,200,50);

        addButton.addActionListener(this);

        idLabel = new JLabel("ID:\t");
        nameLabel = new JLabel("Imię:\t ");
        surLabel = new JLabel("Nazwisko:\t");
        posLabel = new JLabel("Stanowisko:\t");
        payLabel = new JLabel("Pensja:\t");


        idLabel.setForeground(Color.gray);
        nameLabel.setForeground(Color.gray);
        surLabel.setForeground(Color.gray);
        posLabel.setForeground(Color.gray);
        payLabel.setForeground(Color.gray);

        idLabel.setBounds(500,150,500,30);
        nameLabel.setBounds(500,180,500,30);
        surLabel.setBounds(500,210,500,30);
        posLabel.setBounds(500,240,500,30);
        payLabel.setBounds(500,270,500,30);

        idLabel.setFont(idLabel.getFont().deriveFont(15f));
        nameLabel.setFont(nameLabel.getFont().deriveFont(15f));
        surLabel.setFont(surLabel.getFont().deriveFont(15f));
        posLabel.setFont(posLabel.getFont().deriveFont(15f));
        payLabel.setFont(payLabel.getFont().deriveFont(15f));

        if(version == 0)
        {
           // add(editButton);
            add(deleteButton);
            add(addButton);
        }

        add(idLabel);
        add(nameLabel);
        add(surLabel);
        add(posLabel);
        add(payLabel);


    }

    void getList() throws SQLException
    {
        list.clear();
        while (crewResult.next())
        {
            list.addElement(crewResult.getString("Imie")+"  "+crewResult.getString("Nazwisko"));

        }
    }

    private void setInfoCrew() throws SQLException
    {
        crewResult.absolute(crewList.getSelectedIndex()+1);
        idLabel.setText("ID:\t  "+crewResult.getString("ID"));
        nameLabel.setText("Imię:\t  "+crewResult.getString("Imie"));
        surLabel.setText("Nazwisko:\t   "+crewResult.getString("Nazwisko"));
        posLabel.setText("Stanowisko:\t "+crewResult.getString("Stanowisko"));
        payLabel.setText("Pensja:\t     "+crewResult.getString("Pensja"));

    }

    void closeWindow()
    {

    }


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
            client.setAddCrewDialog();

        }
        else if(source == editButton)
        {
            client.setEditCrewDialog();
        }
        else if(source == deleteButton)
        {
            int i = crewList.getSelectedIndex()+1;
            try
            {
                crewResult.absolute(i);
                client.server.removeUser(crewResult.getString("Login"));
                client.crewWindowFrame.dispose();
                client.setCrewWindow();

            } catch (SQLException e1)
            {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e)
    {
        editButton.setEnabled(true);
        try
        {
            setInfoCrew();
        } catch (SQLException e1)
        {
            e1.printStackTrace();
        }
        deleteButton.setEnabled(true);

    }
}
