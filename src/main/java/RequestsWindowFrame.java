import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RequestsWindowFrame extends NewWindowFrame
{
    private Client client;
    private int version;
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
    private DefaultListModel<String> list = new DefaultListModel<>();
    private JList requestList;

    private ResultSet requestResult;

    RequestsWindowFrame(Client client,int version,ResultSet requestResult) throws SQLException
    {
        this.client = client;
        this.version = version;
        this.requestResult = requestResult;
        buildFrame();
        makeGui();
        getList();
    }

    void makeGui()
    {
        JLabel stanLabel = new JLabel("STAN ZAPOTRZEBOWANIA");
        stanLabel.setFont(stanLabel.getFont().deriveFont(40f));
        stanLabel.setBounds(383,10,600,50);
        add(stanLabel);

        requestList = new JList(list);
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

        projectLabel = new JLabel("Projekt:\t");
        materialLabel = new JLabel("Materiał:\t");
        potrzLabel = new JLabel("Ilość Potrzebna:\t");
        zgromLabel = new JLabel("Ilość Zgromadzona:\t");
        brakLabel = new JLabel("Ilość Brakująca:\t");
        valueLabel = new JLabel("Wartość:\t");

        projectLabel.setForeground(Color.gray);
        materialLabel.setForeground(Color.gray);
        potrzLabel.setForeground(Color.gray);
        zgromLabel.setForeground(Color.gray);
        brakLabel.setForeground(Color.gray);
        valueLabel.setForeground(Color.gray);

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

        if(version == 0)
        {
            add(editButton);
            add(deleteButton);

        }


        add(projectLabel);
        add(materialLabel);
        add(potrzLabel);
        add(zgromLabel);
        add(brakLabel);
        add(valueLabel);


    }
    private void setInfoRequest() throws SQLException
    {
        requestResult.absolute(requestList.getSelectedIndex()+1);
        projectLabel.setText("Projekt:\t    "+requestResult.getString("Projekt"));
        materialLabel.setText("Materiał:\t  "+requestResult.getString("Material"));
        potrzLabel.setText("Ilość Potrzebna:\t  "+requestResult.getString("Ilosc_potrzebna"));
        zgromLabel.setText("Ilość Zgromadzona:\t    "+requestResult.getString("Ilosc_zgromadzona"));
        brakLabel.setText("Ilość Brakująca:\t   "+requestResult.getString("Ilosc_brakujaca"));
        valueLabel.setText("Wartość:\t  "+requestResult.getString("Wartosc"));

    }
    void getList() throws SQLException
    {
        list.clear();
        while (requestResult.next())
        {
            list.addElement(requestResult.getString("Projekt"));

        }
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

        else if(source == editButton)
        {
            client.setEditRequestDialog();
        }
        else if(source == deleteButton)
        {
            int i = requestList.getSelectedIndex()+1;
            try
            {
                requestResult.absolute(i);
                //client.server.removeReq(requestResult.getString("Login"));
                client.requestsWindowFrame.dispose();
                client.setRequestsWindow();

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
            setInfoRequest();
        } catch (SQLException e1)
        {
            e1.printStackTrace();
        }
        deleteButton.setEnabled(true);

    }
}
