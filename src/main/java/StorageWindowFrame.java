import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
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

        JList storageList = new JList(list);
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

    private void setInfoStorage()
    {
        materialLabel.setText("Materiał:\t");
        numberLabel.setText("Ilość:\t");
        valueLabel.setText("Wartość Średnia:\t");
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
            client.setEditStorageDialog();
        }
        else if(source == deleteButton)
        {

        }

    }

    @Override
    public void valueChanged(ListSelectionEvent e)
    {
        editButton.setEnabled(true);
        setInfoStorage();
        deleteButton.setEnabled(true);
    }
}
