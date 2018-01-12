import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionEvent;

public class RequestsWindowFrame extends NewWindowFrame
{
    private Client client;
    private JButton returnButton;
    private MenuButton addButton;
    private MenuButton editButton;
    private JLabel projectLabel;
    private JLabel materialLabel;
    private JLabel potrzLabel;
    private JLabel zgromLabel;
    private JLabel brakLabel;
    private JLabel valueLabel;
    private MenuButton deleteButton;

    RequestsWindowFrame(Client client)
    {
        this.client = client;
        buildFrame();
        makeGui();
    }

    void makeGui()
    {
        JLabel stanLabel = new JLabel("STAN ZAPOTRZEBOWANIA");
        stanLabel.setFont(stanLabel.getFont().deriveFont(40f));
        stanLabel.setBounds(383,10,600,50);
        add(stanLabel);

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

        projectLabel = new JLabel("Projekt:\t");
        materialLabel = new JLabel("Materiał:\t");
        potrzLabel = new JLabel("Ilość Potrzebna:\t");
        zgromLabel = new JLabel("Ilość Zgromadzona:\t");
        brakLabel = new JLabel("Ilość Brakująca:\t");
        valueLabel = new JLabel("Wartość:\t");


        projectLabel.setBounds(500,150,500,30);
        materialLabel.setBounds(500,180,500,30);
        potrzLabel.setBounds(500,210,500,30);
        zgromLabel.setBounds(500,240,500,30);
        brakLabel.setBounds(500,270,500,30);
        valueLabel.setBounds(500,300,500,30);


        projectLabel.setFont(projectLabel.getFont().deriveFont(15f));
        materialLabel.setFont(materialLabel.getFont().deriveFont(15f));
        potrzLabel.setFont(potrzLabel.getFont().deriveFont(15f));
        zgromLabel.setFont(zgromLabel.getFont().deriveFont(15f));
        brakLabel.setFont(brakLabel.getFont().deriveFont(15f));
        valueLabel.setFont(valueLabel.getFont().deriveFont(15f));


        add(projectLabel);
        add(materialLabel);
        add(potrzLabel);
        add(zgromLabel);
        add(brakLabel);
        add(valueLabel);


    }
    private void setInfoRequest()
    {
        projectLabel.setText("Projekt:\t");
        materialLabel.setText("Materiał:\t");
        potrzLabel.setText("Ilość Potrzebna:\t");
        zgromLabel.setText("Ilość Zgromadzona:\t");
        brakLabel.setText("Ilość Brakująca:\t");
        valueLabel.setText("Wartość:\t");

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
            client.setAddRequestDialog();

        }
        else if(source == editButton)
        {
            client.setEditRequestDialog();
        }
        else if(source == deleteButton)
        {

        }

    }

    @Override
    public void valueChanged(ListSelectionEvent e)
    {
        editButton.setEnabled(true);
        setInfoRequest();
        deleteButton.setEnabled(true);

    }
}
