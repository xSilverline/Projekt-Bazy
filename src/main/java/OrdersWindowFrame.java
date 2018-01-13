import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionEvent;

public class OrdersWindowFrame extends  NewWindowFrame
{
    private Client client;
    private int version;
    private JButton returnButton;
    private MenuButton addButton;
    private MenuButton editButton;
    private MenuButton deleteButton;
    private JLabel materialLabel;
    private JLabel numberLabel;
    private JLabel valueLabel;
    private JLabel dateLabel;
    private JLabel statusLabel;


    OrdersWindowFrame(Client client,int version)
    {
        this.client=client;
        this.version = version;
        buildFrame();
        makeGui();
    }
    void makeGui()
    {
        JLabel stanLabel = new JLabel("STAN ZAMÓWIEŃ");
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

        materialLabel = new JLabel("Materiał:\t");
        numberLabel = new JLabel("Ilość Zamówiona:\t");
        valueLabel = new JLabel("Wartość:\t");
        dateLabel = new JLabel("Data Zamówienia:\t");
        statusLabel = new JLabel("Status:\t");

        materialLabel.setBounds(500,150,500,30);
        numberLabel.setBounds(500,180,500,30);
        valueLabel.setBounds(500,210,500,30);
        dateLabel.setBounds(500,240,500,30);
        statusLabel.setBounds(500,270,500,30);

        materialLabel.setFont(materialLabel.getFont().deriveFont(15f));
        numberLabel.setFont(numberLabel.getFont().deriveFont(15f));
        valueLabel.setFont(valueLabel.getFont().deriveFont(15f));
        dateLabel.setFont(dateLabel.getFont().deriveFont(15f));
        statusLabel.setFont(statusLabel.getFont().deriveFont(15f));

        if(version == 0)
        {
            add(editButton);
            add(deleteButton);
            add(addButton);
        }

        add(materialLabel);
        add(numberLabel);
        add(valueLabel);
        add(dateLabel);
        add(statusLabel);


    }

    private void setInfoOrder()
    {
        materialLabel.setText("Materiał:\t");
        numberLabel.setText("Ilość Zamówiona:\t");
        valueLabel.setText("Wartość:\t");
        dateLabel.setText("Data Zamówienia:\t");
        statusLabel.setText("Status:\t");

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
            client.setAddOrdersDialog();

        }
        else if(source == editButton)
        {
            client.setEditOrdersDialog();
        }
        else if(source == deleteButton)
        {

        }

    }

    @Override
    public void valueChanged(ListSelectionEvent e)
    {

        editButton.setEnabled(true);
        setInfoOrder();
        deleteButton.setEnabled(true);

    }
}
