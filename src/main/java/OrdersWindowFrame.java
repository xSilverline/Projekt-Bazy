import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    private JList ordersList;
    private JLabel idLabel;
    private DefaultListModel<String> list = new DefaultListModel<>();

    private ResultSet ordersResult;



    OrdersWindowFrame(Client client,int version,ResultSet ordersResult) throws SQLException
    {
        this.ordersResult = ordersResult;
        this.client=client;
        this.version = version;
        buildFrame();
        makeGui();
        getList();
    }
    void makeGui()
    {
        JLabel stanLabel = new JLabel("STAN ZAMÓWIEŃ");
        stanLabel.setFont(stanLabel.getFont().deriveFont(40f));
        stanLabel.setBounds(383,10,600,50);
        add(stanLabel);
        stanLabel.setHorizontalAlignment(SwingConstants.CENTER);

        ordersList = new JList(list);
        JScrollPane scrollList = new JScrollPane(ordersList);
        scrollList.setBounds(100,150,300,400);
        add(scrollList);
        ordersList.addListSelectionListener(this);

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
        materialLabel = new JLabel("Materiał:\t");
        numberLabel = new JLabel("Ilość Zamówiona:\t");
        valueLabel = new JLabel("Wartość:\t");
        dateLabel = new JLabel("Data Zamówienia:\t");
        statusLabel = new JLabel("Status:\t");

        idLabel.setBounds(500,150,500,30);
        materialLabel.setBounds(500,180,500,30);
        numberLabel.setBounds(500,210,500,30);
        valueLabel.setBounds(500,240,500,30);
        dateLabel.setBounds(500,270,500,30);
        statusLabel.setBounds(500,300,500,30);

        idLabel.setFont(idLabel.getFont().deriveFont(15f));
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
        add(idLabel);
        add(materialLabel);
        add(numberLabel);
        add(valueLabel);
        add(dateLabel);
        add(statusLabel);


    }
    void getList() throws SQLException
    {
        list.clear();
        while (ordersResult.next())
        {
            list.addElement(ordersResult.getString("ID")+"  "+ordersResult.getString("Material")+"  "+ordersResult.getString("Status"));
        }
    }

    private void setInfoOrder() throws SQLException
    {
        ordersResult.absolute(ordersList.getSelectedIndex()+1);
        materialLabel.setText("Materiał:\t"+ordersResult.getString("Material"));
        numberLabel.setText("Ilość Zamówiona:\t"+ordersResult.getString("Ilosc_zamowiona"));
        valueLabel.setText("Wartość:\t"+ordersResult.getString("Wartosc"));
        dateLabel.setText("Data Zamówienia:\t"+ordersResult.getString("Data_zamowienia"));
        statusLabel.setText("Status:\t"+ordersResult.getString("Status"));

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
            int i = ordersList.getSelectedIndex()+1;
            try
            {
                ordersResult.absolute(i);
                //client.server.(ordersList.getString("Login"));
                client.ordersWindowFrame.dispose();
                client.setOrdersWindow();

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
            setInfoOrder();
        } catch (SQLException e1)
        {
            e1.printStackTrace();
        }
        deleteButton.setEnabled(true);

    }
}
