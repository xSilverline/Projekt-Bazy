import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectsWindowFrame extends NewWindowFrame
{
    private Client client;
    private int version;
    private JButton returnButton;
    private MenuButton addButton;
    private MenuButton editButton;
    private MenuButton deleteButton;
    private JList projectList;
    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel startLabel;
    private JLabel termLabel;
    private JLabel careLabel;
    private JLabel orderLabel;
    private JLabel priceLabel;
    private JLabel budgetLabel;
    private JLabel matCostLabel;
    private JLabel totalLabel;
    private JLabel statusLabel;
    private JLabel amountLabel;

    private ResultSet projectResult;

    private DefaultListModel<String> list = new DefaultListModel<>();


    ProjectsWindowFrame(Client client, int version, ResultSet projectResult) throws SQLException
    {
        this.client = client;
        this.version = version;
        this.projectResult = projectResult;
        buildFrame();
        makeGui();
        getList();
    }
    void makeGui()
    {
        JLabel stanLabel = new JLabel("STAN PROJEKTÓW");
        stanLabel.setFont(stanLabel.getFont().deriveFont(40f));
        stanLabel.setBounds(483,10,400,50);
        add(stanLabel);




        projectList = new JList(list);
        JScrollPane scrollList = new JScrollPane(projectList);
        scrollList.setBounds(100,150,300,400);
        add(scrollList);
        projectList.addListSelectionListener(this);

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


        idLabel = new JLabel("ID:\t");
        nameLabel = new JLabel("NAZWA:\t");
        startLabel = new JLabel("DATA ROZPOCZĘCIA:\t");
        termLabel = new JLabel("TERMIN:\t");
        careLabel = new JLabel("NADZORUJĄCY:\t");
        orderLabel = new JLabel("ZAMAWIAJĄCY:\t");
        priceLabel = new JLabel("WYNAGRODZENIE:\t");
        budgetLabel = new JLabel("BUDŻET:\t");
        matCostLabel = new JLabel("KOSZTY MATERIAŁÓW:\t");
        totalLabel = new JLabel("KOSZT CAŁKOWITY:\t");
        statusLabel = new JLabel("STATUS:\t");
        amountLabel = new JLabel("ILOŚĆ:");

        idLabel.setBounds(500,150,500,30);
        nameLabel.setBounds(500,180,500,30);
        startLabel.setBounds(500,210,500,30);
        termLabel.setBounds(500,240,500,30);
        amountLabel.setBounds(500,270,500,30);
        careLabel.setBounds(500,300,500,30);
        orderLabel.setBounds(500,330,500,30);
        priceLabel.setBounds(500,360,500,30);
        budgetLabel.setBounds(500,390,500,30);
        matCostLabel.setBounds(500,420,500,30);
        totalLabel.setBounds(500,450,500,30);
        statusLabel.setBounds(500,480,500,30);

        idLabel.setFont(idLabel.getFont().deriveFont(15f));
        nameLabel.setFont(nameLabel.getFont().deriveFont(15f));
        startLabel.setFont(startLabel.getFont().deriveFont(15f));
        termLabel.setFont(termLabel.getFont().deriveFont(15f));
        careLabel.setFont(careLabel.getFont().deriveFont(15f));
        orderLabel.setFont(orderLabel.getFont().deriveFont(15f));
        priceLabel.setFont(priceLabel.getFont().deriveFont(15f));
        budgetLabel.setFont(budgetLabel.getFont().deriveFont(15f));
        matCostLabel.setFont(matCostLabel.getFont().deriveFont(15f));
        totalLabel.setFont(totalLabel.getFont().deriveFont(15f));
        statusLabel.setFont(statusLabel.getFont().deriveFont(15f));

        if(version == 0)
        {
            add(editButton);
            add(deleteButton);
            add(addButton);
        }

        add(idLabel);
        add(nameLabel);
        add(startLabel);
        add(termLabel);
        add(careLabel);
        add(orderLabel);
        add(priceLabel);
        add(budgetLabel);
        add(matCostLabel);
        add(totalLabel);
        add(statusLabel);


    }

    void getList() throws SQLException
    {
        list.clear();
        while (projectResult.next())
        {
            list.addElement(projectResult.getString("ID")+"  "+projectResult.getString("Nazwa")+"  "+projectResult.getString("Status"));
        }
    }
    private void setInfoProject() throws SQLException
    {
        projectResult.absolute(projectList.getSelectedIndex()+1);
        idLabel.setText("ID:\t"+projectResult.getString("ID"));
        nameLabel.setText("NAZWA:\t"+projectResult.getString("Nazwa"));
        startLabel.setText("DATA ROZPOCZĘCIA:\t"+projectResult.getString("Data_rozpoczecia"));
        termLabel.setText("TERMIN:\t"+projectResult.getString("Termin"));
        amountLabel.setText("ILOŚĆ:\t"+projectResult.getString("Ilosc"));
        careLabel.setText("NADZORUJĄCY:\t"+projectResult.getString("Nadzorca"));
        orderLabel.setText("ZAMAWIAJĄCY:\t"+projectResult.getString("Zamawiajacy"));
        priceLabel.setText("WYNAGRODZENIE:\t"+projectResult.getString("Wynagrodzenie"));
        budgetLabel.setText("BUDŻET:\t"+projectResult.getString("Budzet"));
        matCostLabel.setText("KOSZT MATERIAŁÓW:\t"+projectResult.getString("Koszt_materialow"));
        totalLabel.setText("KOSZT CAŁKOWITY:\t"+projectResult.getString("Koszt_calkowity"));
        statusLabel.setText("STATUS:\t"+projectResult.getString("Status"));

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
            client.setAddProjectDialog();

        }
        else if(source == editButton)
        {
            client.setEditProjectDialog();
        }
        else if(source == deleteButton)
        {
            int i = projectList.getSelectedIndex()+1;
            try
            {
                projectResult.absolute(i);
                client.server.removeProject(projectResult.getString("ID"));
                client.projectsWindowFrame.dispose();
                client.setProjectsWindow();

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
        projectList.getSelectedIndex();
        try
        {
            setInfoProject();
        } catch (SQLException e1)
        {
            e1.printStackTrace();
        }
        deleteButton.setEnabled(true);

    }
}
