import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StorageWindowFrame extends NewWindowFrame
{
    private Client client;
    private int version;
    private ResultSet storageResult;
    private JButton returnButton;
    private MenuButton addButton;
    private MenuButton editButton;
    private MenuButton deleteButton;
    private JLabel materialLabel;
    private JLabel numberLabel;
    private JLabel valueLabel;
    private DefaultListModel<String> list = new DefaultListModel<>();
    private JList storageList;

    StorageWindowFrame(Client client, int version, ResultSet storageResult) throws SQLException
    {
        this.client=client;
        this.version = version;
        this.storageResult = storageResult;
        buildFrame();
        makeGui();
        getList();
    }
    @Override
    void makeGui() throws SQLException
    {
        JLabel stanLabel = new JLabel("STAN MAGAZYNU");
        stanLabel.setFont(stanLabel.getFont().deriveFont(40f));
        stanLabel.setBounds(483,10,400,50);
        add(stanLabel);


        list.clear();

        storageList = new JList(list);
        JScrollPane scrollList = new JScrollPane(storageList);
        scrollList.setBounds(100,150,300,400);
        add(scrollList);
        storageList.addListSelectionListener(this);

        deleteButton = new MenuButton("USUŃ");
        deleteButton.setBounds(150,90,200,50);

        deleteButton.addActionListener(this);
        deleteButton.setEnabled(false);

        editButton = new MenuButton("EDYTUJ");
        editButton.setBounds(150,620,200,50);

        editButton.addActionListener(this);
        editButton.setEnabled(false);

        returnButton = new MenuButton("POWRÓT");
        returnButton.setBounds(1156,708,200,50);
        add(returnButton);
        returnButton.addActionListener(this);

        addButton = new MenuButton("DODAJ");
        addButton.setBounds(150,560,200,50);

        addButton.addActionListener(this);

        materialLabel = new JLabel("Materiał:\t");
        numberLabel = new JLabel("Ilość:\t");
        valueLabel = new JLabel("Wartość Średnia:\t");

        materialLabel.setBounds(500,150,500,30);
        numberLabel.setBounds(500,180,500,30);
        valueLabel.setBounds(500,210,500,30);

        materialLabel.setFont(materialLabel.getFont().deriveFont(15f));
        numberLabel.setFont(numberLabel.getFont().deriveFont(15f));
        valueLabel.setFont(valueLabel.getFont().deriveFont(15f));

        materialLabel.setForeground(Color.gray);
        numberLabel.setForeground(Color.gray);
        valueLabel.setForeground(Color.gray);

        if(version == 0)
        {
            add(editButton);
            add(deleteButton);
            add(addButton);
        }

        add(materialLabel);
        add(numberLabel);
        add(valueLabel);

    }

    void getList() throws SQLException
    {
        String input;
        while(storageResult.next())
        {
            input = storageResult.getString("Material");
            list.addElement(input);
        }

    }

    @Override
    void closeWindow()
    {

    }

    private void setInfoStorage() throws SQLException
    {
        storageResult.absolute(storageList.getSelectedIndex()+1);
        materialLabel.setText("Materiał:\t  "+storageResult.getString("Material"));
        numberLabel.setText("Ilość:\t   "+storageResult.getString("Ilosc"));
        valueLabel.setText("Wartość sztuki:\t   "+storageResult.getString("Wartos_sztuki"));
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
        else if(source == editButton)
        {
            try
            {
                storageResult.absolute(storageList.getSelectedIndex()+1);
                client.setEditStorageDialog(storageResult.getString("Material"),storageResult.getString("Ilosc"));
            } catch (SQLException e1)
            {
                e1.printStackTrace();
            }

        }
        else if(source == deleteButton)
        {
            int i = storageList.getSelectedIndex()+1;
            try
            {
                storageResult.absolute(i);
                client.server.removeStock(storageResult.getString("Material"));
                client.storageWindowFrame.dispose();
                client.setStorageWindow();

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
            setInfoStorage();
        } catch (SQLException e1)
        {
            e1.printStackTrace();
        }
        deleteButton.setEnabled(true);
    }
}
