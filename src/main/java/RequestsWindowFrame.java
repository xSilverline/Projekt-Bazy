import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionEvent;

public class RequestsWindowFrame extends NewWindowFrame
{
    private Client client;
    private JButton returnButton;
    private MenuButton addButton;
    private MenuButton editButton;
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

        returnButton = new MenuButton("POWRÓT");
        returnButton.setBounds(1156,708,200,50);
        add(returnButton);
        returnButton.addActionListener(this);

        addButton = new MenuButton("DODAJ");
        addButton.setBounds(150,560,200,50);
        add(addButton);
        addButton.addActionListener(this);

        idLabel = new JLabel("ID:\t");
        nameLabel = new JLabel("Nazwa:\t");
        startLabel = new JLabel("Data rozpoczęcia:\t");
        termLabel = new JLabel("Termin:\t");
        careLabel = new JLabel("Nadzorujący:\t");
        orderLabel = new JLabel("Zamawiający:\t");
        priceLabel = new JLabel("Wynagrodzenie:\t");
        budgetLabel = new JLabel("Budżet:\t");
        matCostLabel = new JLabel("Koszt Materiałów:\t");
        totalLabel = new JLabel("Koszt Całkowity:\t");
        statusLabel = new JLabel("Status:\t");

        idLabel.setBounds(500,150,500,30);
        nameLabel.setBounds(500,180,500,30);
        startLabel.setBounds(500,210,500,30);
        termLabel.setBounds(500,240,500,30);
        careLabel.setBounds(500,270,500,30);
        orderLabel.setBounds(500,300,500,30);
        priceLabel.setBounds(500,330,500,30);
        budgetLabel.setBounds(500,360,500,30);
        matCostLabel.setBounds(500,390,500,30);
        totalLabel.setBounds(500,420,500,30);
        statusLabel.setBounds(500,450,500,30);

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

    }

    @Override
    public void valueChanged(ListSelectionEvent e)
    {

    }
}
