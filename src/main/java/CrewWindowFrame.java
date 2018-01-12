import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionEvent;

public class CrewWindowFrame extends NewWindowFrame
{
    Client client;
    private JButton returnButton;
    private MenuButton addButton;
    private MenuButton editButton;
    private MenuButton deleteButton;

    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel surLabel;
    private JLabel posLabel;
    private JLabel payLabel;

    CrewWindowFrame(Client client)
    {
        this.client=client;
        buildFrame();
        makeGui();
    }


    void makeGui()
    {
        JLabel stanLabel = new JLabel("KADRA");
        stanLabel.setFont(stanLabel.getFont().deriveFont(40f));
        stanLabel.setBounds(383,10,600,50);
        add(stanLabel);
        stanLabel.setHorizontalAlignment(SwingConstants.CENTER);

        DefaultListModel<String> list = new DefaultListModel<>();
        list.addElement("DUPA");

        JList requestList = new JList(list);
        JScrollPane scrollList = new JScrollPane(requestList);
        scrollList.setBounds(100,150,300,400);
        add(scrollList);
        requestList.addListSelectionListener(this);

        editButton = new MenuButton("EDYTUJ");
        editButton.setBounds(150,620,200,50);
        add(editButton);
        editButton.addActionListener(this);
        editButton.setEnabled(false);

        deleteButton = new MenuButton("USUŃ");
        deleteButton.setBounds(150,90,200,50);
        add(deleteButton);
        deleteButton.addActionListener(this);
        deleteButton.setEnabled(false);

        returnButton = new MenuButton("POWRÓT");
        returnButton.setBounds(1156,708,200,50);
        add(returnButton);
        returnButton.addActionListener(this);

        addButton = new MenuButton("DODAJ");
        addButton.setBounds(150,560,200,50);
        add(addButton);
        addButton.addActionListener(this);

        idLabel = new JLabel("ID:\t");
        nameLabel = new JLabel("Imię:\t");
        surLabel = new JLabel("Nazwisko:\t");
        posLabel = new JLabel("Stanowisko:\t");
        payLabel = new JLabel("Pensja:\t");

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



        add(idLabel);
        add(nameLabel);
        add(surLabel);
        add(posLabel);
        add(payLabel);


    }

    private void setInfoCrew()
    {
        idLabel.setText("ID:\t");
        nameLabel.setText("Imię:\t");
        surLabel.setText("Nazwisko:\t");
        posLabel.setText("Stanowisko:\t");
        payLabel.setText("Pensja:\t");

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

        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e)
    {
        editButton.setEnabled(true);
        setInfoCrew();
        deleteButton.setEnabled(true);

    }
}
