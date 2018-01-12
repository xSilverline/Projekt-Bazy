import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;

public class StorageWindowFrame extends NewWindowFrame
{
    private Client client;
    private JButton returnButton;
    private MenuButton addButton;
    private MenuButton editButton;
    private MenuButton deleteButton;
    private JLabel materialLabel;
    private JLabel numberLabel;
    private JLabel valueLabel;

    StorageWindowFrame(Client client)
    {
        this.client=client;
        buildFrame();
        makeGui();
    }
    @Override
    void makeGui()
    {
        JLabel stanLabel = new JLabel("STAN MAGAZYNU");
        stanLabel.setFont(stanLabel.getFont().deriveFont(40f));
        stanLabel.setBounds(483,10,400,50);
        add(stanLabel);

        DefaultListModel<String> list = new DefaultListModel<>();
        list.addElement("DUPA");
        JList storageList = new JList(list);
        JScrollPane scrollList = new JScrollPane(storageList);
        scrollList.setBounds(100,150,300,400);
        add(scrollList);
        storageList.addListSelectionListener(this);

        deleteButton = new MenuButton("USUŃ");
        deleteButton.setBounds(150,90,200,50);
        add(deleteButton);
        deleteButton.addActionListener(this);
        deleteButton.setEnabled(false);

        editButton = new MenuButton("EDYTUJ");
        editButton.setBounds(150,620,200,50);
        add(editButton);
        editButton.addActionListener(this);
        editButton.setEnabled(false);

        returnButton = new MenuButton("POWRÓT");
        returnButton.setBounds(1156,708,200,50);
        add(returnButton);
        returnButton.addActionListener(this);

        addButton = new MenuButton("DODAJ");
        addButton.setBounds(150,560,200,50);
        add(addButton);
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

        add(materialLabel);
        add(numberLabel);
        add(valueLabel);

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
