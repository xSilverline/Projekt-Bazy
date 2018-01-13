import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class AddProjectDialog extends NewWindowDialog
{
    private Client client;
    private MenuButton addButton;
    private MenuButton closeButton;

    private JLabel idLabel;

    private JTextField nameField;
    private JTextField startField;
    private JTextField termField;
    private JTextField careField;
    private JTextField orderField;
    private JTextField priceField;
    private JTextField budgetField;
    private JTextField matCostField;
    private JTextField totalFIeld;
    private JTextField statusField;
    private JTextField amountField;

    AddProjectDialog(Client client)
    {
        this.client = client;
        buildDialog();
        makeGui();
        setModalityType(ModalityType.APPLICATION_MODAL);
        setVisible(true);
    }

    @Override
    void makeGui()
    {


        JLabel titleLabel = new JLabel("DODAWANIE DO PROJEKTÓW");
        titleLabel.setBounds(100,10,600,50);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(titleLabel.getFont().deriveFont(40f));
        add(titleLabel);

        addButton = new MenuButton("DODAJ");
        closeButton = new MenuButton("ZAMKNIJ");

        addButton.setBounds(590,400,200,50);
        closeButton.setBounds(590,540,200,50);

        JLabel nameLabel = new JLabel("PROJEKT:");
        JLabel startLabel = new JLabel("DATA ROZPOCZĘCIA:");
        JLabel termLabel = new JLabel("TERMIN:");
        JLabel amountLabel = new JLabel("ILOŚĆ:");
        JLabel careLabel = new JLabel("ZARZĄDCA:");
        JLabel orderLabel = new JLabel("ZAMAWIAJĄCY:");
        JLabel priceLabel = new JLabel("WARTOŚĆ:");
        JLabel budgetLabel = new JLabel("BUDŻET:");
        JLabel matCostLabel = new JLabel("KOSZT MATERIAŁÓW:");
        JLabel totalLabel = new JLabel("KOSZT CAŁKOWITY:");
        JLabel statusLabel = new JLabel("STATUS:");


        nameField = new JTextField("", 30);
        startField = new JTextField("", 15);
        termField = new JTextField("", 15);
        amountField = new JTextField("",15);
        careField = new JTextField("",30);
        orderField = new JTextField("",30);
        priceField = new JTextField("",30);
        budgetField = new JTextField("",30);
        matCostField = new JTextField("",30);
        totalFIeld = new JTextField("",30);
        statusField = new JTextField("",30);


        nameLabel.setBounds(150,100,140,40);
        startLabel.setBounds(150,140,140,40);
        termLabel.setBounds(150,180,140,40);
        amountLabel.setBounds(150,220,140,40);
        careLabel.setBounds(150,260,140,40);
        orderLabel.setBounds(150,300,140,40);
        priceLabel.setBounds(150,340,140,40);
        budgetLabel.setBounds(150,380,140,40);
        matCostLabel.setBounds(150,420,140,40);
        totalLabel.setBounds(150,460,140,40);
        statusLabel.setBounds(150,500,140,40);


        nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        startLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        termLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        amountLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        careLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        orderLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        priceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        budgetLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        matCostLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        totalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        statusLabel.setHorizontalAlignment(SwingConstants.RIGHT);


        nameField.setBounds(300,105,200,30);
        startField.setBounds(300,145,200,30);
        termField.setBounds(300,185,200,30);
        amountField.setBounds(300,225,200,30);
        careField.setBounds(300,265,200,30);
        orderField.setBounds(300,305,200,30);
        priceField.setBounds(300,345,200,30);
        budgetField.setBounds(300,385,200,30);
        matCostField.setBounds(300,425,200,30);
        totalFIeld.setBounds(300,465,200,30);
        statusField.setBounds(300,505,200,30);


        startField.setFont(startField.getFont().deriveFont(20f));
        nameField.setFont(nameField.getFont().deriveFont(20f));
        termField.setFont(termField.getFont().deriveFont(20f));
        amountField.setFont(amountField.getFont().deriveFont(20f));
        careField.setFont(careField.getFont().deriveFont(20f));
        orderField.setFont(orderField.getFont().deriveFont(20f));
        priceField.setFont(priceField.getFont().deriveFont(20f));
        budgetField.setFont(budgetField.getFont().deriveFont(20f));
        matCostField.setFont(matCostField.getFont().deriveFont(20f));
        totalFIeld.setFont(totalFIeld.getFont().deriveFont(20f));
        statusField.setFont(statusField.getFont().deriveFont(20f));

        add(nameField);
        add(startField);
        add(termField);
        add(careField);
        add(orderField);
        add(priceField);
        add(budgetField);
        add(matCostField);
        add(totalFIeld);
        add(statusField);
        add(amountField);

        add(nameLabel);
        add(matCostLabel);
        add(totalLabel);
        add(startLabel);
        add(termLabel);
        add(careLabel);
        add(orderLabel);
        add(priceLabel);
        add(budgetLabel);
        add(statusLabel);
        add(amountLabel);

        add(addButton);
        add(closeButton);

        addButton.addActionListener(this);
        closeButton.addActionListener(this);


    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        if(source == closeButton)
        {
            dispose();
        }
        else if(source == addButton)
        {
            if(nameField.getText().isEmpty()||amountField.getText().isEmpty() || startField.getText().isEmpty() || termField.getText().isEmpty() || totalFIeld.getText().isEmpty() || careField.getText().isEmpty()|| budgetField.getText().isEmpty()|| orderField.getText().isEmpty() || priceField.getText().isEmpty() || matCostField.getText().isEmpty() || statusField.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null,"Błąd danych - uzupełnij");
            }
            else
            {
                client.server.addProject(nameField.getText(),startField.getText(),termField.getText(),amountField.getText(),careField.getText(),orderField.getText(),priceField.getText(),budgetField.getText(),matCostField.getText(),totalFIeld.getText(),statusField.getText());

                dispose();
                client.projectsWindowFrame.dispose();
                client.setProjectsWindow();
            }

        }

    }
}
