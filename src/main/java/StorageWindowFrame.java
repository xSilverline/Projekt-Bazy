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
        scrollList.setBounds(283,100,800,600);
        add(scrollList);
        storageList.addListSelectionListener(this);

        editButton = new MenuButton("EDYTUJ");
        editButton.setBounds(0,200,200,50);
        add(editButton);
        editButton.addActionListener(this);
        editButton.setEnabled(false);

        returnButton = new MenuButton("POWRÓT");
        returnButton.setBounds(1156,708,200,50);
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
        else if(source == editButton)
        {
            client.setEditStorageDialog();
        }

    }

    @Override
    public void valueChanged(ListSelectionEvent e)
    {
        editButton.setEnabled(true);
    }
}
